package view.fxmlControl;
import control.EinheitenController;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import control.KartenEinheitController;
import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import model.*;
import utility.KonsolenIO;
import utility.SpielStandIO;
import view.components.KarteVBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.HANDGROESSE;
import static resources.KonstantenGUI.*;
import static resources.StringsGUI.*;

public class SpielfeldGuiController extends GuiController
        implements Initializable
{
    @FXML  GridPane spielfeldGitter;
    @FXML  GridPane kartenhandGitter;

    @FXML ProgressBar Manabar;
    @FXML MenuBar menueLeiste;
    private   StackPane sourcePaneFeld;
    private   StackPane sourcePaneHand;
    private  SpielFeld spielfeld;
    private  KartenDeck spieldeck;
    private  KartenHand kartenhand;
    private  Spieler spieler;
    private  ManaTank manaTankSpieler;
    private double manaMaximum;
    private final int FELDGROESSE = 80;
    private final int KARTENHAND_GROESSE = 100;

    /**
     * Wird aufgerufen, um diesen Controller zu initialisieren.
     *
     * @param url            Der Standort, der zum Auflösen relativer Pfade
     *                       für das Root-Objekt verwendet wird.
     * @param resourceBundle Die zum Lokalisieren des Root-Objekts
     *                       verwendeten Ressourcen.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle)
    {
        spielfeldhintergrundfestlegen();
        erstellenspielfeldumgebung();

        for (int i = 0; i < spielfeld.getZeilen(); i++)
        {
            spielfeldGitter.addRow(0);
        }

        for (int i = 0; i < spielfeld.getSpalten(); i++)
        {
            spielfeldGitter.addColumn(0);
            for (int j = 0; j < spielfeld.getZeilen(); j++)
            {
                /*StackPane feld = new StackPane();
                feld.setPrefWidth(FELDGROESSE);
                feld.setPrefHeight(FELDGROESSE);
                draganddroptarget(feld);
                draganddropsource(feld, true);*/
                StackPane feld = felderstellen();

                if
                (j == spielfeld.getZeilen() - 1 &&
                 i == spielfeld.getSpalten() - 1)
                {
                    KartenEinheitController.beschwoerenHelden(spieler,spielfeld);
                    KarteVBox spielerKarteVBox = new KarteVBox(spieler);
                    KonsolenIO.ausgeben(spielfeld.toString());
                    feld.getChildren().add(spielerKarteVBox);
                }
                spielfeldGitter.add(feld, i, j);
            }
        }
    }

    private StackPane felderstellen()
    {
        StackPane feld = new StackPane();
        feld.setPrefWidth(FELDGROESSE);
        feld.setPrefHeight(FELDGROESSE);
        draganddroptarget(feld);
        draganddropsource(feld, true);
        return feld;
    }

    /**
     * Methode welche die für den kampf benötigten objekte4 erstellt
     */
    private void erstellenspielfeldumgebung()
    {
        try
        {
            spiel      = SpielStandIO.leseDatei();
            spieler    = spiel.getSpieler();
            spieldeck  = spiel.getSpieldeckSpieler();
            kartenhand = new KartenHand(spieler);
            kartenhand.handZiehen(spieldeck);
            spielfeld = new SpielFeld();
            manaTankSpieler = new ManaTank(spieler);

            Manabar.setStyle("-fx-accent: blue;");
            manaMaximum = manaTankSpieler.getMana();
            double manaWert = manaMaximum/manaMaximum;
            Manabar.setProgress(manaWert);
            Karteinhandeinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

    }
    //Werte von sorchpane feld und hand beim bewegen im debugger angucken
    public void draganddropsource (StackPane feld, boolean spielfeld)
    {
        feld.setOnMousePressed(event ->
        {
            System.out.println("maus wurde geklickt");
            feld.setMouseTransparent(true);
            event.setDragDetect(true);
            if (spielfeld == true)
            {
                sourcePaneFeld = feld;
            }
            else if (spielfeld == false)
            {
                sourcePaneHand = feld;
            }

        });

        feld.setOnMouseReleased(event -> feld.setMouseTransparent(false));

        feld.setOnMouseDragged(event -> event.setDragDetect(false));

        feld.setOnDragDetected(event -> feld.startFullDrag());
    }

    public void draganddroptarget (StackPane targetfeld)
    {
        targetfeld.setOnMouseDragEntered(event ->
        {
        });

        targetfeld.setOnMouseDragOver(event ->
        {
        });
        // karten lassen sich nicht auf feld bewegen oder beschwören auf dem zuvor eine andere karte war
        targetfeld.setOnMouseDragReleased(event ->
        {
            if(sourcePaneFeld != null)
            {
                einheitBewegen(targetfeld);
            }

            if(sourcePaneHand != null)
            {
                einheitBeschwoeren(targetfeld);
            }
        });

        targetfeld.setOnMouseDragExited(event ->
        {
        });
    }

    private void einheitBewegen(StackPane targetfeld)
    {
        int feldspaltenindex =spielfeldGitter.getColumnIndex(targetfeld);
        int feldzeilenindex =spielfeldGitter.getRowIndex(targetfeld);

        Integer feldspaltenindexmove =spielfeldGitter.getColumnIndex(sourcePaneFeld);
        Integer feldzeilenindexmove =spielfeldGitter.getRowIndex(sourcePaneFeld);

        KarteEinheit aktuellekarteausfeld = spielfeld.getSpielfeldplatz(feldspaltenindexmove, feldzeilenindexmove);

        Boolean bewegenerfolgreich = EinheitenController.bewegen(spielfeld, feldspaltenindex,
                feldzeilenindex, aktuellekarteausfeld);
        if (bewegenerfolgreich)
        {
            System.out.println(spielfeld);
            spielfeldGitter.getChildren().remove(sourcePaneFeld);
            spielfeldGitter.add(felderstellen(),feldspaltenindexmove,feldzeilenindexmove);

            KarteVBox kartemoveVBox = new KarteVBox(aktuellekarteausfeld);
            KonsolenIO.ausgeben(spielfeld.toString());
            targetfeld.getChildren().add(kartemoveVBox);
            sourcePaneFeld = null;
        }
    }
    private void einheitBeschwoeren(StackPane targetfeld)
    {
        int feldspaltenindex =spielfeldGitter.getColumnIndex(targetfeld);
        int feldzeilenindex =spielfeldGitter.getRowIndex(targetfeld);

        int handindex = kartenhandGitter.getColumnIndex(sourcePaneHand);
        Karte aktuellekarteaushand = kartenhand.getElement(handindex);

         Karte karteaushand = kartenhand.getElement(handindex);
        manaTankSpieler = KartenEinheitController.beschwoeren(kartenhand, handindex,
                spielfeld, feldspaltenindex,
                feldzeilenindex, manaTankSpieler);
        if (KartenEinheitController.moveerfolgreich(spielfeld, karteaushand,feldspaltenindex, feldzeilenindex))
        {
            kartenhandGitter.getChildren().remove(sourcePaneHand);
            KarteVBox karteVBox = new KarteVBox(aktuellekarteaushand);
            KonsolenIO.ausgeben(spielfeld.toString());
            targetfeld.getChildren().add(karteVBox);
            sourcePaneHand = null;
            double manaWert = manaTankSpieler.getMana();
            double barwert = manaWert/manaMaximum;
            Manabar.setProgress(barwert);
        }
    }

    /**
     * Methode welche die kartenhand visualisiert
     */
    private void Karteinhandeinfuegen()
    {
        for (int i = 0; i < HANDGROESSE; i++)
        {
            StackPane feld = new StackPane();
            feld.setPrefWidth(KARTENHAND_GROESSE);
            feld.setPrefHeight(KARTENHAND_GROESSE);

            Karte aktuellekarte = kartenhand.getElement(i);
            KarteVBox aktuellekartevbox = new KarteVBox(aktuellekarte);
            feld.getChildren().add(aktuellekartevbox);
            draganddropsource(feld, false);
            //sourcePaneFeld = feld;
            kartenhandGitter.add(feld, i, 0);
        }
    }

    /**
     * Legt das Hintergrundbild des Spielfeldes fest
     */
    private void spielfeldhintergrundfestlegen()
    {
        File meinhintergrund =
                new File(BILDER_PFAD + "Spielplatz" + PNG_DATEI_ENDUNG);
        BackgroundSize backroundsize =
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                                   true, true, true, true);
        Background hintergrund = new Background(new BackgroundImage(
                new Image(meinhintergrund.getAbsolutePath()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backroundsize));
        spielfeldGitter.setBackground(hintergrund);
    }

    /**
     * Speichert den aktuellen Spielstand
     *
     * @param event Event durch das die Methode ausgeloest wird
     */
    @FXML public void spielSpeichern(ActionEvent event)
    {
        try
        {
            SpielStandIO.schreibeDatei(spiel);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Ueberlagern der Methode wechselZu damit durch die MenueLeiste auf die
     * Methode zugegriffen werden kann.
     *
     * @param event Event durch welches die Methode ausgeloest wird.
     * @param pfad  String mit dem Pfad der .fxml Datei welche geladen werden
     *             soll.
     * @throws IOException Wirft die Exception, welche durch das .load()
     *                     erursacht werden kann weiter.
     */
    @Override protected void wechselZu(ActionEvent event, String pfad)
            throws IOException
    {
        File f = new File(pfad);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        super.setStage(((Stage) menueLeiste.getScene().getWindow()));
        super.getStage().setScene(scene);
        super.getStage().show();
    }

    /**
     * Methode durch welche, ueber die MenueLeiste zum Hauptmenue gewechselt
     * werden kann
     *
     * @param event Event durch welches die Methode ausgeloest wird.
     */
    @FXML public void zurueckHauptmenue(ActionEvent event)
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
     * Ermöglicht das einstellen der Aufloesung in der MenueLeiste
     *
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override public void wechselAufloesungFullHD(Event event)
    {
        super.setStage((Stage) menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_FULLHD);
    }

    /**
     * Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode
     * zugegriffen werden kann.
     *
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override public void wechselAufloesungHD(Event event)
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
    public void spielstandAnzeigen(Event event) throws IOException
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
}