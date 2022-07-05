package view.fxmlControl;

import control.EinheitenController;
import control.RundenController;
import control.Spielstatus;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import control.KartenEinheitController;
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
import utility.NetzwerkIO;
import utility.SpielStandIO;
import view.components.KarteVBox;

import java.io.File;
import java.io.IOException;

import static resources.Konstanten.HANDGROESSE;
import static resources.KonstantenGUI.*;
import static resources.StringsGUI.*;

public abstract class FeldGuiController extends GuiController
        implements Initializable
{
    @FXML GridPane spielfeldGitter;
    @FXML GridPane kartenhandGitter;
    @FXML Button zugBeendenButton;
    @FXML ProgressBar Manabar;
    @FXML MenuBar menueLeiste;
    protected NetzwerkIO<Spielstatus> SpielstatusKommunikation;
    protected StackPane sourcePaneFeld;
    protected StackPane sourcePaneHand;
    protected SpielFeld spielfeld;
    protected KartenDeck spielerDeck;
    protected KartenDeck gegenspielerDeck;
    protected KartenHand kartenhandSpieler;
    protected Spielbar spieler;
    protected Spielbar gegenspieler;
    protected ManaTank manaTankSpieler;
    protected int zugZaehler = 0;
    protected double manaMaximumSpieler;

    /**
     * Methode, welche die für den Kampf benötigten Objekte erstellt
     */
    public abstract void erstelleSpielfeldUmgebung();

    /**
     * Initialisiert die Kommunikation übers Netzwerk
     */
    public abstract void initNetzwerk ();

    protected void aktualisiereSpielStatus()
    {
        spielfeld = SpielstatusKommunikation.getPostEingang().getSpielfeld();

        gegenspieler = SpielstatusKommunikation.getPostEingang().getSpieler();
        spieler = SpielstatusKommunikation.getPostEingang().getGegenspieler();

        gegenspielerDeck = SpielstatusKommunikation.getPostEingang().getSpielerDeck();
        spielerDeck = SpielstatusKommunikation.getPostEingang().getGegenspielerDeck();

        zugZaehler = SpielstatusKommunikation.getPostEingang().getZugzaehler();

        if (zugZaehler > 0)
        {
            for (int i = 0; i < spielfeld.getSpalten(); i++)
            {
                for (int j = 0; j < spielfeld.getZeilen(); j++)
                {
                    /*Node meinenode = getNodeByRowColumnIndex(i, j,spielfeldGitter);
                    if(meinenode != null)
                    {
                        spielfeldGitter.getChildren().remove(meinenode);
                    }*/
                    removeNodeByRowColumnIndex(i,j,spielfeldGitter);

                    StackPane feld = feldErstellen();
                    if(spielfeld.getSpielfeldplatz(i,j) != null)
                    {
                        KarteVBox karteVBox = new KarteVBox(spielfeld.getSpielfeldplatz(i,j));
                        feld.getChildren().add(karteVBox);
                    }
                    spielfeldGitter.add(feld, i, j);
                }
            }
        }

    }

    protected StackPane feldErstellen ()
    {
        StackPane feld = new StackPane();
        feld.setPrefWidth(FELD_GROESSE);
        feld.setPrefHeight(FELD_GROESSE);
        dragAndDropTarget(feld);
        dragAndDropSource(feld, true);
        return feld;
    }

    public void dragAndDropSource (StackPane feld, boolean spielfeld)
    {
        //Werte von sorchpane feld und hand beim bewegen im debugger angucken
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

    public void dragAndDropTarget (StackPane targetfeld)
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

    public void initZugBeendenButton ()
    {
        zugBeendenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent arg0)
            {
                RundenController.zugBeenden(spielfeld, spielerDeck, gegenspielerDeck);
                zugZaehler++;
                kartenhandSpieler.handAblegen(spielerDeck);
                kartenhandSpieler.handZiehen(spielerDeck);

                SpielstatusKommunikation.senden(new Spielstatus(
                        (Spieler) spieler, (Gegenspieler) gegenspieler,
                        spielfeld, spielerDeck,
                        gegenspielerDeck, zugZaehler));

                KonsolenIO.ausgeben("spieler ist dran:");
                KonsolenIO.ausgeben("wir sind in zug: " + zugZaehler);
            }
        });
    }


    protected void einheitBewegen(StackPane targetfeld)
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
            spielfeldGitter.add(feldErstellen(),feldspaltenindexmove,feldzeilenindexmove);

            KarteVBox kartemoveVBox = new KarteVBox(aktuellekarteausfeld);
            KonsolenIO.ausgeben(spielfeld.toString());
            targetfeld.getChildren().add(kartemoveVBox);
            sourcePaneFeld = null;
        }
    }
    protected void einheitBeschwoeren(StackPane targetfeld)
    {
        int feldspaltenindex =spielfeldGitter.getColumnIndex(targetfeld);
        int feldzeilenindex =spielfeldGitter.getRowIndex(targetfeld);

        int handindex = kartenhandGitter.getColumnIndex(sourcePaneHand);
        Karte aktuellekarteaushand = kartenhandSpieler.getElement(handindex);

         Karte karteaushand = kartenhandSpieler.getElement(handindex);
        manaTankSpieler = KartenEinheitController.beschwoeren(kartenhandSpieler, handindex,
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
            double barwert = manaWert / manaMaximumSpieler;
            Manabar.setProgress(barwert);
        }
    }

    /**
     * Methode welche die kartenhand visualisiert
     */
    protected void karteInHandEinfuegen ()
    {
        for (int i = 0; i < HANDGROESSE; i++)
        {
            StackPane feld = new StackPane();
            feld.setPrefWidth(KARTENHAND_GROESSE);
            feld.setPrefHeight(KARTENHAND_GROESSE);

            Karte aktuellekarte = kartenhandSpieler.getElement(i);
            KarteVBox aktuellekartevbox = new KarteVBox(aktuellekarte);
            feld.getChildren().add(aktuellekartevbox);
            dragAndDropSource(feld, false);
            //sourcePaneFeld = feld;
            kartenhandGitter.add(feld, i, 0);
        }
    }

    /**
     * Legt das Hintergrundbild des Spielfeldes fest
     */
    protected void hintergrundFestlegen ()
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
    @FXML protected void spielSpeichern(ActionEvent event)
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