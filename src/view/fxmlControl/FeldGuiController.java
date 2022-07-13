package view.fxmlControl;

import control.*;
import exceptions.JsonNichtLesbarException;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import control.KartenEinheitController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import utility.KonsolenIO;
import utility.NetzwerkIO;
import view.components.KarteVBox;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static control.KartenDeckController.mischen;
import static control.ZauberEffektController.zauberKarteAusspielen;
import static resources.Konstanten.HANDGROESSE;
import static resources.Konstanten.spielStandIO;
import static resources.KonstantenGUI.*;
import static resources.StringsGUI.*;

public abstract class FeldGuiController extends GuiController
{
    @FXML GridPane spielfeldGitter;
    @FXML GridPane kartenhandGitter;
    @FXML ProgressBar Manabar;
    @FXML MenuBar menueLeiste;
    @FXML StackPane warten;
    @FXML VBox gewonnen;
    @FXML VBox verloren;
    protected NetzwerkIO<Spielstatus> SpielstatusKommunikation;
    protected StackPane quellePaneFeld;
    protected StackPane quellePaneHand;
    protected SpielFeld spielfeld;
    protected KartenDeck spielerDeck;
    protected KartenDeck gegenspielerDeck;
    protected KartenHand kartenHand;
    protected Spieler spieler;
    protected Gegenspieler gegenspieler;
    protected ManaTank manaTank;
    protected double manaMaximum;
    protected boolean binSpieler;

    /**
     * Methode, welche die für den Kampf benötigten Objekte erstellt
     */
    public abstract void initalisieren ();

    protected void updateSpielStatus(Spielstatus status)
    {
        spielfeld = status.getSpielfeld();

        spieler = status.getSpieler();
        manaTank = new ManaTank(status.getSpieler());
        manaMaximum = manaTank.getMana();
        double manaWert = manaMaximum / manaTank.getMana();
        Manabar.setProgress(manaWert);
        gegenspieler = status.getGegenspieler();

        spielerDeck = status.getSpielerDeck();
        gegenspielerDeck = status.getGegenspielerDeck();

        RundenController.setZugZaehler(status.getZugzaehler());
        ladeSpielfeld(spielfeld, false);
        pruefeGewonnenOderVerloren();
        //hier auch kartenhand updaten
    }

    protected void ladeSpielfeld(SpielFeld spielfeld, boolean initFirstTime)
    {
        spielfeldGitter.getChildren().clear();

        for (int i = 0; i < spielfeld.getZeilen(); i++)
        {
            spielfeldGitter.addRow(0);
        }
        for (int i = 0; i < spielfeld.getSpalten(); i++)
        {
            spielfeldGitter.addColumn(0);
            for (int j = 0; j < spielfeld.getZeilen(); j++)
            {
                StackPane feld = feldErstellen();
                if(spielfeld.getSpielfeldplatz(i,j) != null)
                {
                    KarteVBox karteVBox = new KarteVBox(spielfeld.getSpielfeldplatz(i,j));
                    feld.getChildren().add(karteVBox);
                }
                if(j == 0 && i == 0 && initFirstTime)
                {
                    heldEinsetzen(gegenspieler, feld );
                }

                if
                (j == spielfeld.getZeilen() - 1 &&
                 i == spielfeld.getSpalten() - 1 && initFirstTime)
                {
                    heldEinsetzen(spieler, feld);
                }
                spielfeldGitter.add(feld, i, j);
            }
        }
        System.out.println(spielfeld);
        warten.setVisible(!(RundenController.getDran() == binSpieler));
    }

    private static void heldEinsetzen(Karte held, StackPane feld)
    {
        KarteVBox gegenspielerKarteVBox = new KarteVBox(held);
        feld.getChildren().add(gegenspielerKarteVBox);
    }

    protected StackPane feldErstellen ()
    {
        StackPane feld = new StackPane();
        feld.setId("feld");
        dragAndDropTarget(feld);
        dragAndDropSource(feld, true);
        return feld;
    }

    @FXML
    public void zugBeenden()
    {
        if(RundenController.getDran())
        {
            kartenHand.handAblegen(spielerDeck);
            kartenHand.handZiehen(spielerDeck);
            KartenDeckController.mischen(spielerDeck);
            karteInHandEinfuegen();
        }

        else
        {
            kartenHand.handAblegen(gegenspielerDeck);
            kartenHand.handZiehen(gegenspielerDeck);
            KartenDeckController.mischen(gegenspielerDeck);
            karteInHandEinfuegen();
        }
        RundenController.zugBeenden(spielfeld);
        warten.setVisible(!(RundenController.getDran() == binSpieler));
        //warten.setVisible(!RundenController.getDran());

        aktualisierungsenden ();

        KonsolenIO.ausgeben("Wir sind in Zug: "
                            + RundenController.getZugZaehler());

    }

    public void dragAndDropSource (StackPane feld, boolean spielfeld)
    {
        feld.setOnMousePressed(event ->
        {
            feld.setMouseTransparent(true);
            event.setDragDetect(true);
            if (spielfeld == true)
            {
                quellePaneFeld = feld;
            }
            else if (spielfeld == false)
            {
                quellePaneHand = feld;
            }
        });

        feld.setOnMouseReleased(event -> feld.setMouseTransparent(false));

        feld.setOnMouseDragged(event -> event.setDragDetect(false));

        feld.setOnDragDetected(event -> feld.startFullDrag());
    }

    public void dragAndDropTarget (StackPane zielFeld)
    {
        zielFeld.setOnMouseDragEntered(event ->
        {
        });

        zielFeld.setOnMouseDragOver(event ->
        {
        });
        zielFeld.setOnMouseDragReleased(event ->
        {
            if(quellePaneFeld != null)
            {
                System.out.println("lulz");
                if(spielfeld.getSpielfeldplatz(bekommeposition(zielFeld)) != null)
                {
                    System.out.println("ichwillangreifen");
                    einheitangreifen(zielFeld);
                }
                else
                {
                    einheitBewegen(zielFeld);
                }
            }

            if(quellePaneHand != null)
            {
                if(spielfeld.getSpielfeldplatz(bekommeposition(zielFeld)) != null)
                {
                    einheitangreifenzauber(zielFeld);
                }
                else
                {
                    einheitBeschwoeren(zielFeld);
                }

            }

        });

        zielFeld.setOnMouseDragExited(event ->
        {
        });
    }

    private Position bekommeposition(StackPane feld)
    {
        return new Position(spielfeldGitter.getColumnIndex(feld),spielfeldGitter.getRowIndex(feld));
    }

    private void paneQuellenNullNetzen()
    {
        quellePaneFeld = null;
        quellePaneHand = null;
    }


    protected void einheitangreifenzauber(StackPane zielFeld)
    {
        int angreiferposition = spielfeldGitter.getColumnIndex(quellePaneHand);
        Karte angreifer = kartenHand.getElement( angreiferposition);
        KarteEinheit verteidiger = spielfeld.getSpielfeldplatz(bekommeposition(zielFeld));

        if(angreifer instanceof KarteZauber)
        {
            zauberKarteAusspielen((KarteZauber) angreifer, verteidiger,kartenHand, angreiferposition,  spielfeld, spielerDeck, gegenspielerDeck );
        }
        aktualisierungsenden ();
        ladeSpielfeld(spielfeld, false);
        kartenhandGitter.getChildren().remove(quellePaneHand);

    }

    protected void einheitangreifen(StackPane zielFeld)
    {
        KarteEinheit angreifer = spielfeld.getSpielfeldplatz(bekommeposition(quellePaneFeld));
        KarteEinheit verteidiger = spielfeld.getSpielfeldplatz(bekommeposition(zielFeld));

        int rückmeldung = EinheitenController.einheitenAngreifenMitEinheiten(binSpieler, spielfeld, spielerDeck, gegenspielerDeck
                                                            ,angreifer, verteidiger);

        //überprüfen ob angriff erfolgreich war
        if(rückmeldung==RUECKMELDUNG_SCHADEN)
        {
            FXeffectsController.glowangriff(zielFeld);
        }
        if(rückmeldung==RUECKMELDUNG_SCHILDBREAK)
        {
            FXeffectsController.glowschildbreak(zielFeld);
        }
        if(rückmeldung==RUECKMELDUNG_GESTORBEN)
        {
            ladeSpielfeld(spielfeld, false);
        }
        aktualisierungsenden();

    }

    protected void einheitBewegen(StackPane zielFeld)
    {
        int zielSpaltenIndex = spielfeldGitter.getColumnIndex(zielFeld);
        int zielZeilenIndex = spielfeldGitter.getRowIndex(zielFeld);
        int quelleSpaltenIndex = spielfeldGitter.getColumnIndex(quellePaneFeld);
        int quelleZeilenIndex = spielfeldGitter.getRowIndex(quellePaneFeld);

        KarteEinheit aktuelleKarteAusFeld = spielfeld.getSpielfeldplatz(quelleSpaltenIndex, quelleZeilenIndex);

        if (EinheitenController.bewegen(binSpieler,
                spielfeld, zielSpaltenIndex,
                zielZeilenIndex, aktuelleKarteAusFeld))
        {
            spielfeldGitter.getChildren().remove(quellePaneFeld);
            spielfeldGitter.add(feldErstellen(),quelleSpaltenIndex,quelleZeilenIndex);

            KarteVBox zielVBox = new KarteVBox(aktuelleKarteAusFeld);
            zielFeld.getChildren().add(zielVBox);
            quellePaneFeld = null;
            aktualisierungsenden ();

        }
    }

    protected void aktualisierungsenden ()
    {
        pruefeGewonnenOderVerloren();
        if(SpielstatusKommunikation != null)
        {
            SpielstatusKommunikation.senden(new Spielstatus(
                    spieler,gegenspieler,
                    spielfeld, spielerDeck,
                    gegenspielerDeck,  RundenController.getZugZaehler()));
        }
    }

    private void pruefeGewonnenOderVerloren()
    {
        RundenController.synchronisiereFeldUndHelden(spielfeld, spieler, gegenspieler);
        boolean spielerTod = spieler.getLebenspunkte() < 0;
        boolean gegnerTod = gegenspieler.getLebenspunkte() < 0;

        gewonnen.setVisible(
                binSpieler &&  gegnerTod||
                !binSpieler && spielerTod);
        verloren.setVisible(
                !binSpieler && gegnerTod ||
                binSpieler && spielerTod);

        if((spielerTod || gegnerTod) && binSpieler)
        {
            neuenSpielstandSpeichern();
        }
    }

    protected void einheitBeschwoeren(StackPane zielFeld)
    {
        int zielSpaltenIndex = spielfeldGitter.getColumnIndex(zielFeld);
        int zielZeilenIndex = spielfeldGitter.getRowIndex(zielFeld);

        int handIndex = kartenhandGitter.getColumnIndex(quellePaneHand);

        Karte aktuelleKarteAusHand = kartenHand.getElement(handIndex);
        Karte karteAusHand = kartenHand.getElement(handIndex);

        manaTank = KartenEinheitController.beschwoeren(
                     kartenHand, handIndex,
                     spielfeld, zielSpaltenIndex,
                     zielZeilenIndex,
                     manaTank);

        if (KartenEinheitController.bewegenErfolgreich(
                spielfeld, karteAusHand, zielSpaltenIndex, zielZeilenIndex))
        {
            kartenhandGitter.getChildren().remove(quellePaneHand);
            KarteVBox karteVBox = new KarteVBox(aktuelleKarteAusHand);
            zielFeld.getChildren().add(karteVBox);
            quellePaneHand = null;
            double manaWert = manaTank.getMana();
            double barWert = manaWert / manaMaximum;
            Manabar.setProgress(barWert);
            aktualisierungsenden ();

        }
    }

    /**
     * Methode welche die kartenhand visualisiert
     */
    protected void karteInHandEinfuegen ()
    {
        kartenhandGitter.getChildren().clear();
        for (int i = 0; i < HANDGROESSE; i++)
        {
            StackPane feld = new StackPane();
            feld.setPrefWidth(KARTENHAND_GROESSE);
            feld.setPrefHeight(KARTENHAND_GROESSE);

            KarteVBox karteVBox =
                    new KarteVBox(kartenHand.getElement(i));
            feld.getChildren().add(karteVBox);

            dragAndDropSource(feld, false);
            kartenhandGitter.add(feld, i, 0);
        }
    }

    /**
     * Speichert den aktuellen Spielstand
     *
     * @param event Event durch das die Methode ausgeloest wird
     */
    @FXML protected void spielSpeichern(ActionEvent event)
    {
        neuenSpielstandSpeichern();
    }

    private void neuenSpielstandSpeichern()
    {
        try
        {
            spiel = new SpielStand(spiel.getGold(), spieler, spiel.getGegenSpieler());
            spielStandIO.schreibeDatei(spiel);
        }
        catch (IOException e)
        {
            e.printStackTrace();
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