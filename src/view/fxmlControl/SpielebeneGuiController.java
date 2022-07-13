package view.fxmlControl;

import control.SpielfigurEbeneController;
import control.WaffenController;
import exceptions.JsonNichtLesbarException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Ebene;
import model.Position;
import model.Raum;
import model.ereignisse.*;
import resources.Waffen;
import utility.JsonIO;
import utility.KonsolenIO;
import view.components.KarteGrossVBox;
import view.components.RaumPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.*;
import static resources.KonstantenGUI.*;
import static resources.Strings.AKTUELLE_EBENE_PFAD;
import static resources.Strings.HAENDLER_DECK_EINS_PFAD;
import static resources.StringsGUI.*;

/**
 * Klasse, welche alle Methoden der Spielebene Szene enthaelt.
 */
public class SpielebeneGuiController extends GuiController
{
    @FXML GridPane spielebenenGitter;
    @FXML Label spielerLabel;
    @FXML MenuBar menueLeiste;
    Ebene spielEbene;
    private ObjectProperty<Position> spielerPosition = new SimpleObjectProperty<>();

    /**
     * Wird aufgerufen, um diesen Controller zu initialisieren.
     *
     * @param url Der Standort, der zum Auflösen relativer Pfade für das Root-Objekt verwendet wird.
     * @param resourceBundle Die zum Lokalisieren des Root-Objekts verwendeten Ressourcen.
     */
    @Override public void initialize (URL url, ResourceBundle resourceBundle)
    {
        initialisiereEbene();
    }

    /**
     * Methode zum initialisieren der Ebene
     */
    private void initialisiereEbene ()
    {
        try
        {
            spiel = spielStandIO.leseSpielstand();
            spielerLabel.setText(spiel.getSpieler().getName());
            spielEbene = spiel.getAktuelleEbene();

            for (int i = 0; i < spielEbene.getEbenenZeile(); i++)
            {
                spielebenenGitter.addRow(0);
            }

            for (int i = 0; i < spielEbene.getEbenenSpalte(); i++)
            {
                spielebenenGitter.addColumn(0);
                for (int j = 0; j < spielEbene.getEbenenZeile(); j++)
                {
                    initialisiereRaum(spielEbene, i, j);
                }
            }
            spielerPosition.set(spielEbene.getSpielfigur().getPosition());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Initialisiert einen Raum aus einer Ebene an der Stelle x/y in ein RaumPane und fuegt ihn an derselben Stelle in
     * das Spielfeld-Gitter ein.
     *
     * @param ebene die gegebene Ebene
     * @param x die Spalte
     * @param y die Zeile
     */
    private void initialisiereRaum (Ebene ebene, int x, int y)
    {
        Raum aktuellerRaum = ebene.getRaumAnPosition(x, y);
        RaumPane raum = new RaumPane(aktuellerRaum);
        ObjectProperty<Position> aktuellePosition = new SimpleObjectProperty<>(new Position(x, y));
        spielerPosition.addListener((observableValue, position, t1) -> raum.setBeinhaltetSpieler(
                spielerPosition.get().equals(aktuellePosition.get())));
        raum.setOnMouseClicked(mouseEvent ->
                               {
                                   if (SpielfigurEbeneController.bewegen(ebene, x, y, spiel))
                                   {
                                       spielerPosition.bindBidirectional(aktuellePosition);
                                       if (! ebene.getRaumAnPosition(x, y).getEreignis().isAusgefuehrt())
                                       {
                                           if (! (ebene.getRaumAnPosition(x, y).getEreignis() instanceof LeererRaum))
                                           {
                                               oeffneEreignis(ebene.getRaumAnPosition(x, y).getEreignis());
                                           }
                                       }
                                       spielerPosition.unbindBidirectional(aktuellePosition);
                                   }
                               });
        spielebenenGitter.add(raum, x, y);
    }

    /**
     * Ueberlagern der Methode, damit diese in der MenueLeiste auf die Methode zugreifen kann.
     * @param event
     */
    @FXML public void spielSpeichern (ActionEvent event)
    {
        try
        {
            spielStandIO.schreibeDatei(spiel);
            ebeneIO.schreibeDatei(spielEbene, AKTUELLE_EBENE_PFAD);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Ueberlagern der Methode wechselZu damit durch die MenueLeiste auf die Methode zugegriffen werden kann.
     *
     * @param event Event durch welches die Methode ausgeloest wird.
     * @param pfad String mit dem Pfad der .fxml Datei welche geladen werden soll.
     * @throws IOException wenn die FXML nicht geladen werden kann.
     */
    @Override protected void wechselZu (ActionEvent event, String pfad) throws IOException
    {
        File f = new File(pfad);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        super.setStage(((Stage) menueLeiste.getScene().getWindow()));
        super.getStage().setScene(scene);
        super.getStage().show();
    }

    /**
     * Ueberlagern der Methode um uber die Menueleiste zum Hauptmenue zu kommen.
     *
     * @param event Event, welche diese Methode ausloest.
     */
    @FXML public void zurueckHauptmenue (ActionEvent event)
    {
        try
        {
            wechselZuHauptmenue(event);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Diese Methode oeffnet ein Pop-Up Fenster auf der Spielebene, welches das Ereignis repraesentiert
     *
     * @param ereignis das zu oeffnende Ereignis
     */
    public void oeffneEreignis (Ereignis ereignis)
    {

        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(ereignis.getName());
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(POPUP_VBOX);
        TextArea text = new TextArea();
        text.setText(ereignis.getBeschreibung());
        text.setWrapText(true);
        text.setEditable(false);
        vbox.getChildren().add(text);
        if(ereignis instanceof Schmied)
        {
            Schmied schmied = (Schmied)ereignis;
            text.setText(ereignis.getBeschreibung() + "\n\n\r" + GRATIS_HANDELN + schmied.getGratisInteraktionen() + "\n\r" + KOSTEN + schmied.getKosten() + "\n\r" + PREISERHOEHUNG + schmied.aktionenBisPreisErhöhung() + "\n\n\r" + AKTUELLES_GOLD + spiel.getGold());
        }
        else if(ereignis instanceof Tempel)
        {
            Tempel tempel = (Tempel)ereignis;
            text.setText(ereignis.getBeschreibung() + "\n\n\r" + GRATIS_HANDELN + tempel.getGratisInteraktionen()+ "\n\r" + KOSTEN + tempel.getKosten() + "\n\r" + PREISERHOEHUNG + tempel.aktionenBisPreisErhöhung() + "\n\n\r" + AKTUELLES_GOLD + spiel.getGold());
        }
        else if(ereignis instanceof Haendler)
        {
            Haendler haendler = (Haendler)ereignis;
            text.setText(ereignis.getBeschreibung() + "\n\n\r" + GRATIS_HANDELN + haendler.getGratisInteraktionen()+ "\n\r" + KOSTEN + haendler.getKosten() + "\n\r" + PREISERHOEHUNG + haendler.aktionenBisPreisErhöhung() + "\n\n\r" + AKTUELLES_GOLD + spiel.getGold());
        }
        else if(ereignis instanceof Heiler)
        {
            Heiler heiler = (Heiler)ereignis;
            text.setText(ereignis.getBeschreibung() + "\n\n\r" + GRATIS_HANDELN + heiler.getGratisInteraktionen()+ "\n\r" + KOSTEN + heiler.getKosten() + "\n\r" + PREISERHOEHUNG + heiler.aktionenBisPreisErhöhung() + "\n\n\r" + AKTUELLES_GOLD + spiel.getGold() + "\n\r" + AKTUELLE_LEBENSPUNKTE + spiel.getSpieler().getLebenspunkte());
        }

        Scene popupScene = new Scene(vbox, POPUP_VBOX_BREITE2, POPUP_VBOX_HOEHE2);
        Button annehmenButton = new Button(EREIGNIS_ANNEHMEN);
        Button ablehnenButton = new Button(EREIGNIS_ABLEHNEN);
        annehmenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle (ActionEvent arg0)
            {
                ereignis.setAuswahl(true);
                if(ereignis instanceof Schmied || ereignis instanceof Tempel || ereignis instanceof Haendler || ereignis instanceof Heiler)
                {
                    if(((Mensch) ereignis).getKosten() <= spiel.getGold() || ((Mensch) ereignis).pruefeGratisInteraktion())
                    {
                        ereignisGuiAusfuehren(ereignis, arg0);
                    }
                    else
                    {
                        text.setText(ZU_WENIG_GOLD);
                    }
                }
                else
                {
                    ereignisGuiAusfuehren(ereignis, arg0);
                    if (ereignis instanceof Treppe || ereignis instanceof Gegner)
                    {
                        popupStage.close();
                    }
                }
            }
        });
        ablehnenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle (ActionEvent arg0)
            {
                ereignis.setAuswahl(false);
                popupStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(annehmenButton);
        vbox.getChildren().add(ablehnenButton);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }

    /**
     * Diese Methode erkennt, welches Ereignis in der Spielebene angetroffen wurde und fuehrt die zugehoerige Methode
     * aus
     *
     * @param ereignis das angetroffene Ereignis
     * @param event
     */
    public void ereignisGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        if (ereignis instanceof Gegner)
        {
            gegnerGuiAusfuehren(ereignis, event);
        }
        else if (ereignis instanceof Haendler)
        {
            haendlerGuiAusfuehren(ereignis, event);
        }
        else if (ereignis instanceof Heiler)
        {
            heilerGuiAusfuehren(ereignis, event);
        }
        else if (ereignis instanceof Schmied)
        {
            schmiedGuiAusfuehren(ereignis, event);
        }
        else if (ereignis instanceof Tempel)
        {
            tempelGuiAusfuehren(ereignis, event);
        }
        else if (ereignis instanceof Treppe)
        {
            treppeGuiAusfuehren(ereignis);
        }
        else if (ereignis instanceof Truhe)
        {
            truheGuiAusfuehren(ereignis, event);
        }
        else if (ereignis instanceof ZufallsEreignis)
        {
            zufallsEreignisGuiAusfuehren(ereignis, event);
        }
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "Heiler" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Haendler
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void gegnerGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        try
        {
            wechselZuSpielerFeld(event);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(STRING_FEHLER_WECHSEL_SPIELFELD);
        }
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "Heiler" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Haendler
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void haendlerGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
            kartenDeckAnzeigenHaendler(ereignis, event);
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "Heiler" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Heiler
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void heilerGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        int lebenVorher = spiel.getSpieler().getLebenspunkte();
        ereignis.ausfuehren(spiel);
        int lebenNachher = spiel.getSpieler().getLebenspunkte();
        int lebenErhalten = lebenNachher - lebenVorher;

        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.setTitle(ereignis.getName());
        spielstandPopUp.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(POPUP_VBOX);
        TextArea ereignisText = new TextArea();
        ereignisText.setWrapText(true);
        ereignisText.setEditable(false);
        ereignisText.setText(HAENDLER_AUSFUEHREN);
        Scene popupScene = new Scene(vbox, POPUP_VBOX_BREITE1, POPUP_VBOX_HOEHE1);
        Button gehenButton = new Button(EREIGNIS_GEHEN);
        ereignisText.setText(HEILER_AUSFUEHREN_1 + lebenErhalten + HEILER_AUSFUEHREN_2);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(ereignisText);
        ereignisVerlassen(spielstandPopUp, vbox);
        spielstandPopUp.setScene(popupScene);
        spielstandPopUp.setResizable(false);
        spielstandPopUp.setAlwaysOnTop(true);
        spielstandPopUp.show();
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "Schmied" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Schmied
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void schmiedGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        kartenDeckAnzeigenSchmied(ereignis, event);
    }


    /**
     * Diese Methode formuliert aus, wie das Ereignis "Tempel" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Tempel
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void tempelGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        kartenDeckAnzeigenTempel(ereignis, event);
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "Treppe" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Treppe
     */
    public void treppeGuiAusfuehren (Ereignis ereignis)
    {
        ereignis.ausfuehren(spiel);
        spielebenenGitter.getChildren().clear();
        initialisiereEbene();
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "Truhe" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ Truhe
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void truheGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        int goldVorher = spiel.getGold();
        ereignis.ausfuehren(spiel);
        int goldNachher = spiel.getGold();
        int goldGefunden = goldNachher - goldVorher;

        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.setTitle(ereignis.getName());
        spielstandPopUp.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(POPUP_VBOX);
        TextArea ereignisText = new TextArea();
        ereignisText.setWrapText(true);
        ereignisText.setEditable(false);
        Scene popupScene = new Scene(vbox, POPUP_VBOX_HOEHE1, POPUP_VBOX_BREITE1);
        ereignisText.setText(TRUHE_AUSFUEHREN_1 + goldGefunden + TRUHE_AUSFUEHREN_2);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(ereignisText);
        ereignisVerlassen(spielstandPopUp, vbox);
        spielstandPopUp.setScene(popupScene);
        spielstandPopUp.setResizable(false);
        spielstandPopUp.setAlwaysOnTop(true);
        spielstandPopUp.show();
    }

    /**
     * Diese Methode formuliert aus, wie das Ereignis "ZufallsEreignis" in der GUI visualisiert wird
     * @param ereignis Das Ereignis vom Typ ZufallsEreignis
     * @param event Das Event, welches die Methode ausfuehrt
     */
    public void zufallsEreignisGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.setTitle(ereignis.getName());
        spielstandPopUp.getIcons().add(new Image(ICON.getAbsolutePath()));
        ZufallsEreignis zufallsEreignis = (ZufallsEreignis) ereignis;
        ereignis.ausfuehren(spiel);

        VBox vbox = new VBox(POPUP_VBOX);
        TextArea ereignisText = new TextArea();
        ereignisText.setWrapText(true);
        ereignisText.setEditable(false);
        Scene popupScene = new Scene(vbox, POPUP_VBOX_HOEHE1, POPUP_VBOX_BREITE1);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(ereignisText);

        if (zufallsEreignis.getEreignisnummer() == ZE_1)
        {
            ereignisText.setText(ZE_1_AUSGEFUEHRT);
        }
        else if (zufallsEreignis.getEreignisnummer() == ZE_2)
        {
            ereignisText.setText(ZE_2_AUSGEFUEHRT);
        }
        else if (zufallsEreignis.getEreignisnummer() == ZE_3)
        {
            ereignisText.setText(ZE_3_AUSGEFUEHRT);
        }
        else if (zufallsEreignis.getEreignisnummer() == ZE_4)
        {
            ereignisText.setText(ZE_4_AUSGEFUEHRT);
        }
        else if (zufallsEreignis.getEreignisnummer() == ZE_5)
        {
            Waffen waffe = WaffenController.generiereZufaelligeWaffe();
            ereignisText.setText(ZE_5_AUSGEFUEHRT + "\n\n\r" + waffe.getNAME()+ DOPPELPUNKT + "\n\r" + waffe.getBESCHREIBUNG() + "\n\n\r" + WAFFE_MACHT + waffe.getMACHT() + "\n\r" + WAFFE_REICHWEITE + waffe.getREICHWEITE());
            Button mitnehmenButton = new Button();
            mitnehmenButton.setText(MITNEHMEN_BUTTON);

            mitnehmenButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle (ActionEvent arg0)
                {
                    WaffenController.waffeAusruesten(spiel, waffe);
                    try
                    {
                        spielStandIO.schreibeDatei(spiel);
                    }
                    catch (IOException e)
                    {
                        KonsolenIO.ausgeben(e.getMessage());
                    }
                    spielstandPopUp.close();
                }
            });
            vbox.getChildren().add(mitnehmenButton);
        }
        ereignisVerlassen(spielstandPopUp, vbox);
        spielstandPopUp.setScene(popupScene);
        spielstandPopUp.setResizable(false);
        spielstandPopUp.setAlwaysOnTop(true);
        spielstandPopUp.show();
    }

    /**
     * Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode zugegriffen werden kann.
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override public void wechselAufloesungFullHD (Event event)
    {
        super.setStage((Stage) menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_FULLHD);
    }

    /**
     * Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode zugegriffen werden kann.
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override public void wechselAufloesungHD (Event event)
    {
        super.setStage((Stage) menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_HD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_HD);
    }

    /**
     * Methode um das Spielstandfenster aufzurufen
     * @param event Event durch welches die Methode ausgelöst wird.
     * @throws IOException Kann beim .load() des fxmlLoaders geworfen werden.
     */
    public void spielstandAnzeigen (Event event) throws IOException
    {
        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(SPIELSTAND);
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));
        File f = new File(SPIELSTAND_PFAD);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene popupScene = new Scene(fxmlLoader.load());
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }

    /**
     * Diese Methode sorgt dafuer, dass Stages, welche durch Ereignisse geoeffnet werden, wieder geschlossen werden können
     * @param popupStage Die angezeigte Stage
     * @param vbox Die angezeigte Vbox
     */
    public void ereignisVerlassen (Stage popupStage, VBox vbox)
    {
        Button gehenButton = new Button(EREIGNIS_GEHEN);
        gehenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle (ActionEvent arg0)
            {
                popupStage.close();
            }
        });
        vbox.getChildren().add(gehenButton);
    }

    /**
     * Diese Methode formuliert aus, wie der Schmied das Kartendeck anzeigt
     * @param ereignis Das Ereignis vom Typ Schmied
     * @param event Das Event, welches die Methode ausfuehrt
     */
    private void kartenDeckAnzeigenSchmied (Ereignis ereignis, ActionEvent event)
    {
        int k = spiel.getSpieldeckSpieler().size();
        int h = 0;

        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScrollPane spane = new ScrollPane();
        GridPane pane = new GridPane();
        spane.getStylesheets().add(SpielstandGuiController.class.getResource("/view/css/Spielstand.css").toExternalForm());
        spane.setContent(pane);
        for (int i = 0; h < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                VBox karte = new KarteGrossVBox(spiel.getSpieldeckSpieler().get(h));
                pane.add((karte), j, i);
                Button button = new Button(spiel.getSpieldeckSpieler().get(h).getName());
                karte.getChildren().add(button);
                int finalH = h;
                button.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle (ActionEvent actionEvent)
                    {
                        Schmied schmied = (Schmied) ereignis;
                        schmied.ausfuehren(spiel, spiel.getSpieldeckSpieler().get(finalH));
                    }
                });
                h++;
            }
        }
        int zeilenAnzahl = pane.getRowCount();
        Button schliessenButton = new Button();
        schliessenButton.setText(POPUP_BUTTON_SCHLIESSEN);
        schliessenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle (ActionEvent arg0)
            {
                spielstandPopUp.close();
            }
        });
        pane.add(schliessenButton, 2, zeilenAnzahl + 1);
        pane.setHalignment(schliessenButton, HPos.CENTER);
        Scene sc = new Scene(spane);
        spielstandPopUp.setScene(sc);
        spielstandPopUp.show();
    }

    /**
     * Diese Methode formuliert aus, wie der Tempel das Kartendeck auffruft.
     * @param ereignis Das Ereignis vom Typ Tempel
     * @param event Das Event, welches die Methode ausfuehrt
     */
    private void kartenDeckAnzeigenTempel (Ereignis ereignis, ActionEvent event)
    {
        int k = spiel.getSpieldeckSpieler().size();
        int h = 0;

        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScrollPane spane = new ScrollPane();
        GridPane pane = new GridPane();
        spane.getStylesheets().add(SpielstandGuiController.class.getResource("/view/css/Spielstand.css").toExternalForm());
        spane.setContent(pane);
        for (int i = 0; h < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                VBox karte = new KarteGrossVBox(spiel.getSpieldeckSpieler().get(h));
                pane.add((karte), j, i);
                Button button = new Button(spiel.getSpieldeckSpieler().get(h).getName());
                karte.getChildren().add(button);
                int finalH = h;
                button.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle (ActionEvent actionEvent)
                    {
                        Tempel tempel = (Tempel) ereignis;
                        tempel.ausfuehren(spiel, spiel.getSpieldeckSpieler().get(finalH));
                    }
                });
                h++;
            }
        }
        int zeilenAnzahl = pane.getRowCount();
        Button schliessenButton = new Button();
        schliessenButton.setText(POPUP_BUTTON_SCHLIESSEN);
        schliessenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle (ActionEvent arg0)
            {
                spielstandPopUp.close();
            }
        });
        pane.add(schliessenButton, 2, zeilenAnzahl + 1);
        pane.setHalignment(schliessenButton, HPos.CENTER);
        Scene sc = new Scene(spane);
        spielstandPopUp.setScene(sc);
        spielstandPopUp.show();
    }

    /**
     * Diese Methode formuliert aus, wie der Haendler das Kartendeck aufruft
     * @param ereignis Das Ereignis vom Typ Haendler
     * @param event Das Event, welches die Methode ausfuehrt
     */
    private void kartenDeckAnzeigenHaendler (Ereignis ereignis, ActionEvent event)
    {
        int k = spiel.getSpieldeckSpieler().size();
        int h = 0;
        Haendler haendler = (Haendler) ereignis;

        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ScrollPane spane = new ScrollPane();
        GridPane pane = new GridPane();
        spane.getStylesheets().add(SpielstandGuiController.class.getResource("/view/css/Spielstand.css").toExternalForm());
        spane.setContent(pane);
        try
        {
            haendler.setHaendlerDeck(kartenDeckIO.leseKartenDeck(HAENDLER_DECK_EINS_PFAD));
        }
        catch (JsonNichtLesbarException e)
        {
            e.getMessage();
        }
        for (int i = 0; h < k; i++)
        {
            for (int j = 0; j < k; j++)
            {
                VBox karte = new KarteGrossVBox(haendler.getHaendlerDeck().get(h));
                pane.add((karte), j, i);
                Button button = new Button(haendler.getHaendlerDeck().get(h).getName());
                karte.getChildren().add(button);
                int finalH = h;
                button.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle (ActionEvent actionEvent)
                    {
                        haendler.ausfuehren(spiel, haendler.getHaendlerDeck().get(finalH));
                    }
                });
                h++;
            }

            int zeilenAnzahl = pane.getRowCount();
            Button schliessenButton = new Button();
            schliessenButton.setText(POPUP_BUTTON_SCHLIESSEN);
            schliessenButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle (ActionEvent arg0)
                {
                    spielstandPopUp.close();
                }
            });
            pane.add(schliessenButton, 2, zeilenAnzahl + 1);
            pane.setHalignment(schliessenButton, HPos.CENTER);
            Scene sc = new Scene(spane);
            spielstandPopUp.setScene(sc);
            spielstandPopUp.show();
        }
    }
}
