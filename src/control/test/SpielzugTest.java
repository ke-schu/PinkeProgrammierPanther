package control.test;

import control.KartenEinheitController;
import control.RundenController;
import exceptions.JsonNichtLesbarException;
import model.*;
import utility.KonsolenIO;

import static control.EinheitenController.einheitenAngreifenMitEinheiten;
import static control.KartenEinheitController.beschwoerenHelden;
import static control.test.TestKonstanten.*;
import static control.test.TestZahlen.*;
import static resources.Effekte.LETZTEWORTE;
import static resources.Effekte.ZURUECKWERFEN;
import static resources.Einheiten.FERNKAEMPFER;
import static resources.Konstanten.kartenDeckIO;
import static resources.Konstanten.spielStandIO;
import static resources.Strings.*;

/**
 * SpielzugTest dient zum Testen der Klassen und Methoden welche die
 * Kartenspielmechanik implementieren
 */
public class SpielzugTest
{
    static SpielFeld meinfeld;
    static KartenHand meinehand;
    static KartenHand masterhand;
    static KartenDeck meindeck;
    static KartenDeck masterdeck;
    static Spieler spieler;
    static ManaTank meintank;
    static Gegenspieler master;
    static ManaTank mastertank;

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielzugTest()
    {
    }

    /**
     * spielzugtesten simuliert einen Spielzug und beschreibt dessen Vorgaenge
     * mit einer Ausgabe in der Konsole
     */
    public static void spielzugTesten()
    {
        auslesen();
        erstellen();
        haendeZiehen();
        einheitenBeschwoeren();
        rumlaufen();
        kaempfen();
        zugBeenden();
    }

    /**
     * Erstellen erstellt die fuer einen Spielzug noetigen Objekte
     */
    public static void erstellen()
    {
        meinfeld = new SpielFeld(ZAHL_5, ZAHL_5);
        KonsolenIO.ausgeben(
                SPIELFELDBREITE + meinfeld.getSpalten() + SPIELFELDZEILEN +
                meinfeld.getZeilen());
        meinehand  = new KartenHand(spieler);
        meintank   = new ManaTank(spieler);
        master     =
                new Gegenspieler(BJOERN, ZAHL_3, FERNKAEMPFER, ZAHL_15, ZAHL_15,
                                 ZAHL_10, ZAHL_7, ZAHL_3, ZAHL_1, LETZTEWORTE,
                                 ZURUECKWERFEN, ZAHL_12);
        masterhand = new KartenHand(master);
        mastertank = new ManaTank(master);
    }

    /**
     * auslesen liest aus verschiedenen Json-Dateien, um gespeicherte Objekte
     * nutzbar zu machen
     */
    public static void auslesen()
    {
        try
        {
            //Erstellen 2er Spieldecks
            meindeck   = kartenDeckIO.leseKartenDeck(SPIEL_DECK_SPIELER_PFAD);
            masterdeck = kartenDeckIO.leseKartenDeck(SPIEL_DECK_GEGNER_PFAD);
            KonsolenIO.ausgeben(meindeck.toString());
            spieler = spielStandIO.leseSpielstand().getSpieler();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * haendeziehen zieht aus den beiden Kartendecks Karten und legt diese in
     * die jeweiligen Haende
     */
    public static void haendeZiehen()
    {
        meinehand.handZiehen(meindeck);
        masterhand.handZiehen(masterdeck);
        KonsolenIO.ausgeben(meinehand + ZEILENUMBRUCH);
        KonsolenIO.ausgeben(VORSTELLEN_SPIELER + spieler.getName());
    }

    /**
     * einheitenbeschwoeren beschwoert die jeweiligen Einheiten auf dem
     * Spielfeld
     */
    public static void einheitenBeschwoeren()
    {
        beschwoerenHelden(spieler, meinfeld);
        beschwoerenHelden(master, meinfeld);
        KartenEinheitController.beschwoeren(meinehand, ZAHL_2, meinfeld, ZAHL_1,
                                            ZAHL_0, meintank);
        KonsolenIO.ausgeben(VORSTELLEN_EINHEIT +
                            meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0)
                                    .getName() + POSITIONS_ANGABE_NULL_EINS);
        KonsolenIO.ausgeben(POSITIONS_ANGABE_EINHEIT +
                            meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0)
                                    .getPositionX());
        KonsolenIO.ausgeben(ZEILE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0)
                                            .getPositionY() + ZEILENUMBRUCH);
    }

    /**
     * rumlaufen laesst die Karte Master Bewegungen auf dem Spielfeld
     * auffuehren
     */
    public static void rumlaufen()
    {
        KonsolenIO.ausgeben(meinfeld.toString());
        /*
        Gegner gibt Ihre Position wieder und bewegt sich
        danach um anschliessend wieder ihre Position wiederzugeben
         */
        KonsolenIO.ausgeben(
                VORSTELLEN_GEGNER + meinfeld.getSpielfeldplatz(ZAHL_4, ZAHL_4) +
                VON + (meinfeld.getSpielfeldplatz(4, 4).getPositionX()) + "," +
                (meinfeld.getSpielfeldplatz(4, 4).getPositionY()));
        KonsolenIO.ausgeben(BEWEGEN);
       /* EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_3, master);
        EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_2, master);
        EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_1, master);
        EinheitenController.bewegen(meinfeld, ZAHL_3, ZAHL_1, master);
        EinheitenController.bewegen(meinfeld, ZAHL_2, ZAHL_1, master);
        EinheitenController.bewegen(meinfeld, ZAHL_1, ZAHL_1, master);*/
        KonsolenIO.ausgeben(POSITIONS_ANGABE_EINS_EINS);
        KonsolenIO.ausgeben(meinfeld.toString());
    }

    /**
     * kaempfen laesst den master gegen eine Einheit kaempfen
     */
    public static void kaempfen()
    {
        /*
        RonWeasley gibt seine Lebenspunkte an und wird anschliessend vom
        Gegner angegriffen und gibt danach wieder seine lebenspunkte wieder
         */
        KonsolenIO.ausgeben(LEBENSPUNKTE_FREUND +
                            meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0)
                                    .getLebenspunkte());
        KonsolenIO.ausgeben(POSITIONS_ANGABE_KAMPF +
                            meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0)
                                    .getName());
        KonsolenIO.ausgeben(KAEMPFEN);
        einheitenAngreifenMitEinheiten(meinfeld, meindeck, masterdeck, master,
                                       meinfeld.getSpielfeldplatz(ZAHL_1,
                                                                  ZAHL_0));
        /*KonsolenIO.ausgeben(
                LEBENSPUNKTE_NACH_KAMPF +
                meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0)
                        .getLebenspunkte() + ZEILENUMBRUCH);         */
    }

    /**
     * beendet den momentan laufenden Spielzug
     */
    public static void zugBeenden()
    {
        //daraufhin wird der zug beendet und
        RundenController.zugBeenden(meinfeld, meindeck, masterdeck);
        KonsolenIO.ausgeben(POSITIONS_ANGABE_NULL_EINS_ENDE +
                            meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0) +
                            ZEILENUMBRUCH);
        KonsolenIO.ausgeben(meinfeld.toString());

    }
}
