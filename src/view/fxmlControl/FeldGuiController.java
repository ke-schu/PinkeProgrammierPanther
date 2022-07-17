package view.fxmlControl;

import control.EinheitenController;
import control.KartenDeckController;
import control.KartenEinheitController;
import control.RundenController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import utility.KonsolenIO;
import utility.NetzwerkIO;
import view.components.KarteVBox;

import java.io.File;
import java.io.IOException;

import static control.ZauberEffektController.zauberKarteAusspielen;
import static resources.Konstanten.*;
import static resources.KonstantenGUI.*;
import static resources.StringsGUI.*;

/** Controller, welcher den View auf das Spielfeld kontrolliert */
public abstract class FeldGuiController extends GuiController
{
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
    @FXML GridPane spielfeldGitter;
    @FXML GridPane kartenhandGitter;
    @FXML ProgressBar Manabar;
    @FXML MenuBar menueLeiste;
    @FXML StackPane warten;
    @FXML VBox gewonnen;
    @FXML VBox verloren;
    
    /**
     Methode, welche den Helden auf das Spielfeld setzt
     @param held Held der auf das Spielfeld gestezt wird
     @param feld Spielfeld auf den der Held gesetzt wird
     */
    private static void heldEinsetzen (Karte held, StackPane feld)
    {
        KarteVBox gegenspielerKarteVBox = new KarteVBox(held);
        feld.getChildren().add(gegenspielerKarteVBox);
    }
    
    /**
     Methode, welche die für den Kampf benoetigten Objekte erstellt und den
     Spielstatus aktualisiert
     */
    public abstract void initialisieren ();
    
    protected void updateSpielStatus (Spielstatus status)
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
    }
    
    /**
     Methode, welche das Spielfeld visualisiert
     @param spielfeld das Spielfeld, welches geladen wird
     @param initFirstTime ob das Spielfeld zum ersten Mal initialisiert wird.
     */
    protected void ladeSpielfeld (SpielFeld spielfeld, boolean initFirstTime)
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
                if (spielfeld.getSpielfeldplatz(i, j) != null)
                {
                    KarteVBox karteVBox =
                            new KarteVBox(spielfeld.getSpielfeldplatz(i, j));
                    feld.getChildren().add(karteVBox);
                }
                if (j == 0 && i == 0 && initFirstTime)
                {
                    heldEinsetzen(gegenspieler, feld);
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
    
    /**
     Methode, welche ein einzelnes Feld des Spielfeldes erstellt
     @return feld welches zurueckgegeben wird
     */
    protected StackPane feldErstellen ()
    {
        StackPane feld = new StackPane();
        feld.setId(FELD);
        dragAndDropTarget(feld);
        dragAndDropSource(feld, true);
        return feld;
    }
    
    /**
     Methode, welche den aktuellen Spielzug beendet und alle noetigen Aktionen
     beim Zug beenden ausfuehrt
     */
    @FXML
    public void zugBeenden ()
    {
        if (RundenController.getDran())
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
        warten.setVisible((RundenController.getDran() != binSpieler));
        
        aktualisierungsenden();
        
        KonsolenIO.ausgeben(WIR_SIND_AM_ZUG
                            + RundenController.getZugZaehler());
    }
    
    /**
     Methode, welche den aktuellen Spielstatus an den Mitspieler sendet
     */
    protected void aktualisierungsenden ()
    {
        pruefeGewonnenOderVerloren();
        if (SpielstatusKommunikation != null)
        {
            SpielstatusKommunikation.senden(new Spielstatus(
                    spieler, gegenspieler,
                    spielfeld, spielerDeck,
                    gegenspielerDeck, RundenController.getZugZaehler()));
        }
    }
    
    /**
     Methode, welche ueberprueft ob der Aktuelle spieler die Runde gewonnen
     oder verloren hat
     */
    private void pruefeGewonnenOderVerloren ()
    {
        RundenController.synchronisiereFeldUndHelden(spielfeld, spieler,
                                                     gegenspieler);
        boolean spielerTod = spieler.getLebenspunkte() < 0;
        boolean gegnerTod = gegenspieler.getLebenspunkte() < 0;
        
        gewonnen.setVisible(
                binSpieler && gegnerTod ||
                !binSpieler && spielerTod);
        verloren.setVisible(
                !binSpieler && gegnerTod ||
                binSpieler && spielerTod);
        if (gegnerTod)
        {
            spieler.setErfahrungspunkte(
                    spieler.getErfahrungspunkte() + EP_VON_GEGNER);
            spieler.berechneLevelUp();
        }
        
        if ((spielerTod || gegnerTod) && binSpieler)
        {
            neuenSpielstandSpeichern();
        }
    }
    
    /**
     Speichert den neuen Spielstand beim Ende einer Runde
     */
    private void neuenSpielstandSpeichern ()
    {
        try
        {
            SpielStand altesSpiel = spielStandIO.leseSpielstand();
            Gegenspieler gegner = altesSpiel.getGegenSpieler();
            int gold = altesSpiel.getGold() + GOLD_VON_GEGNER;
            spiel = new SpielStand(gold, spieler, gegner);
            spielStandIO.schreibeDatei(spiel);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     Methode, welche die Kartenhand visualisiert
     */
    protected void karteInHandEinfuegen ()
    {
        kartenhandGitter.getChildren().clear();
        for (int i = 0; i < HANDGROESSE; i++)
        {
            StackPane feld = new StackPane();
            feld.setPrefWidth(KARTENHAND_GROESSE);
            feld.setPrefHeight(KARTENHAND_GROESSE);
            if (kartenHand != null)
            {
                KarteVBox karteVBox =
                        new KarteVBox(kartenHand.getElement(i));
                feld.getChildren().add(karteVBox);
                
                dragAndDropSource(feld, false);
                kartenhandGitter.add(feld, i, 0);
            }
            
        }
    }
    
    /**
     Methode, welche die Drag and Drop funktionen der Maus implementiert
     @param feld Feld welches mit der maus angeklickt wird
     @param spielfeld auf dem gespielt wird
     */
    public void dragAndDropSource (StackPane feld, boolean spielfeld)
    {
        feld.setOnMousePressed(event ->
               {
                   feld.setMouseTransparent(true);
                   event.setDragDetect(true);
                   if (spielfeld)
                   {
                       quellePaneFeld = feld;
                   }
                   else
                   {
                       quellePaneHand = feld;
                   }
               });
        
        feld.setOnMouseReleased(event -> feld.setMouseTransparent(false));
        feld.setOnMouseDragged(event -> event.setDragDetect(false));
        feld.setOnDragDetected(event -> feld.startFullDrag());
    }
    
    /**
     Methode, welche die Drag and Drop funktionen der Maus implementiert
     @param zielFeld feld in welchem die Maustaste losgelassen wird
     */
    public void dragAndDropTarget (StackPane zielFeld)
    {
        zielFeld.setOnMouseDragEntered(event -> {});
        zielFeld.setOnMouseDragOver(event -> {});
        zielFeld.setOnMouseDragReleased(event ->
                {
                    if (quellePaneFeld != null)
                    {
                        if (spielfeld.getSpielfeldplatz(bekommePosition(
                                        zielFeld)) != null)
                        {
                            einheitangreifen(zielFeld);
                        }
                        else
                        {
                            einheitBewegen(zielFeld);
                        }
                    }

                    if (quellePaneHand != null)
                    {
                        if (spielfeld.getSpielfeldplatz(bekommePosition(
                                        zielFeld)) != null)
                        {
                            einheitangreifenzauber(zielFeld);
                        }
                        else
                        {
                            einheitBeschwoeren(zielFeld);
                        }
                    }
                });
        zielFeld.setOnMouseDragExited(event -> {});
    }
    
    /**
     Methode, welche die Position eines Feldes in einem Grid-Pane zurueckgibt
     @param feld das Feld, von dem die Position genommen werden soll.
     @return die Position des Stack-Panes
     */
    private Position bekommePosition (StackPane feld)
    {
        return new Position(spielfeldGitter.getColumnIndex(feld),
                            spielfeldGitter.getRowIndex(feld));
    }
    
    /**
     Methode, welche Die von der Maus angewaehlten Felder null setzt
     */
    private void paneQuellenNullNetzen ()
    {
        quellePaneFeld = null;
        quellePaneHand = null;
    }
    
    /**
     Methode, welche das Angreifen mit einer zauberkarte ausfuehrt und die
     visuelle Rueckmeldung steuert
     @param zielFeld Feld in dem sich das Ziel des Angriffes befindet
     */
    protected void einheitangreifenzauber (StackPane zielFeld)
    {
        int angreiferPosition =
                spielfeldGitter.getColumnIndex(quellePaneHand);
        Karte angreifer = kartenHand.getElement(angreiferPosition);
        KarteEinheit verteidiger =
                spielfeld.getSpielfeldplatz(bekommePosition(zielFeld));
        
        if (angreifer instanceof KarteZauber)
        {
            int rueckmeldung;
            rueckmeldung = zauberKarteAusspielen(
                    (KarteZauber) angreifer, verteidiger, kartenHand,
                    angreiferPosition, spielfeld,
                    spielerDeck, gegenspielerDeck);
            
            if (rueckmeldung == RUECKMELDUNG_SCHADEN)
            {
                kartenhandGitter.getChildren().remove(quellePaneHand);
                FXeffectsController.glowAngriff(this, zielFeld, verteidiger);
            }
            if (rueckmeldung == RUECKMELDUNG_GESTORBEN)
            {
                ladeSpielfeld(spielfeld, false);
                kartenhandGitter.getChildren().remove(quellePaneHand);
            }
        }
        aktualisierungsenden();
        paneQuellenNullNetzen();
    }
    
    /**
     Methode, welche das Angreifen mit einer KarteEinheit ausfuehrt und die
     visuelle Rueckmeldung steuert
     @param zielFeld Feld in dem sich das Ziel des Angriffes befindet
     */
    protected void einheitangreifen (StackPane zielFeld)
    {
        KarteEinheit angreifer =
                spielfeld.getSpielfeldplatz(bekommePosition(quellePaneFeld));
        KarteEinheit verteidiger =
                spielfeld.getSpielfeldplatz(bekommePosition(zielFeld));
        
        int rueckmeldung = EinheitenController.einheitenAngreifenMitEinheiten(
                        binSpieler, spielfeld, spielerDeck,
                        gegenspielerDeck, angreifer, verteidiger);
        
        if (rueckmeldung == RUECKMELDUNG_SCHADEN)
        {
            FXeffectsController.glowAngriff(this,zielFeld, verteidiger);
        }
        if (rueckmeldung == RUECKMELDUNG_SCHILDBREAK)
        {
            FXeffectsController.glowSchildBrechen(zielFeld);
        }
        if (rueckmeldung == RUECKMELDUNG_GESTORBEN)
        {
            ladeSpielfeld(spielfeld, false);
        }
        aktualisierungsenden();
        paneQuellenNullNetzen();
    }
    
    /**
     Methode, das bewegen einer Einheit ausfuehrt und die visuelle
     Rueckmeldung steuert
     @param zielFeld Feld in das sich bewegt werden soll
     */
    protected void einheitBewegen (StackPane zielFeld)
    {
        int zielSpaltenIndex = spielfeldGitter.getColumnIndex(zielFeld);
        int zielZeilenIndex = spielfeldGitter.getRowIndex(zielFeld);
        int quelleSpaltenIndex =
                spielfeldGitter.getColumnIndex(quellePaneFeld);
        int quelleZeilenIndex = spielfeldGitter.getRowIndex(quellePaneFeld);
        
        KarteEinheit aktuelleKarteAusFeld =
                spielfeld.getSpielfeldplatz(quelleSpaltenIndex,
                                            quelleZeilenIndex);
        
        if (EinheitenController.bewegen(binSpieler,
                                        spielfeld, zielSpaltenIndex,
                                        zielZeilenIndex,
                                        aktuelleKarteAusFeld))
        {
            spielfeldGitter.getChildren().remove(quellePaneFeld);
            spielfeldGitter.add(feldErstellen(), quelleSpaltenIndex,
                                quelleZeilenIndex);
            
            KarteVBox zielVBox = new KarteVBox(aktuelleKarteAusFeld);
            zielFeld.getChildren().add(zielVBox);
            quellePaneFeld = null;
            aktualisierungsenden();
            paneQuellenNullNetzen();
        }
    }
    
    /**
     Methode, welche das Beschwoeren einer Einheit ausfuehrt und die visuelle
     Rueckmeldung steuert
     @param zielFeld das Feld, auf dem die Einheit beschworen werden soll.
     */
    protected void einheitBeschwoeren (StackPane zielFeld)
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
            aktualisierungsenden();
            paneQuellenNullNetzen();
        }
    }
    
    /**
     Speichert den aktuellen Spielstand
     @param event Event durch das die Methode ausgeloest wird
     */
    @FXML protected void spielSpeichern (ActionEvent event)
    {
        neuenSpielstandSpeichern();
    }
    
    /**
     Ueberlagern der Methode wechselZu damit durch die MenueLeiste auf die
     Methode zugegriffen werden kann.
     @param event Event durch welches die Methode ausgeloest wird.
     @param pfad String mit dem Pfad der .fxml Datei welche geladen werden
     soll.
     @throws IOException Wirft die Exception, welche durch das .load()
     erursacht werden kann weiter.
     */
    @Override protected void wechselZu (ActionEvent event, String pfad)
            throws IOException
    {
        File f = new File(pfad);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        super.setStage(((Stage) menueLeiste.getScene().getWindow()));
        super.getStage().setScene(scene);
        super.getStage().show();
        SpielstatusKommunikation.beenden();
    }
    
    /**
     Ermoeglicht das Einstellen der Aufloesung in der MenueLeiste
     @param event ActionEvent, welches diese Methode ausloest.
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
     Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode
     zugegriffen werden kann.
     @param event ActionEvent, welches diese Methode ausloest.
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
     Methode durch welche, ueber die MenueLeiste zum Hauptmenue gewechselt
     werden kann
     @param event Event durch welches die Methode ausgeloest wird.
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
     Methode um das Spielstandfenster aufzurufen
     @param event Event durch welches die Methode ausgeloest wird.
     @throws IOException Kann beim .load() des fxmlLoaders geworfen werden.
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
}