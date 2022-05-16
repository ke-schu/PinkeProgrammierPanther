package control.test;

import control.EinheitenController;
import control.KartenEinheitController;
import control.RundenController;
import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import io.KonsolenIO;
import io.SpielStandIO;
import model.*;

import java.io.IOException;

import static control.EinheitenController.einheitenAngreifenMitEinheiten;
import static control.KartenEinheitController.beschwoerenHeld;
import static resources.Effekte.LETZTEWORTE;
import static resources.Effekte.ZURUECKWERFEN;
import static resources.Einheiten.FERNKAEMPFER;
import static resources.Strings.*;
import static resources.TestKonstanten.*;
import static resources.Zahlen.*;

/**
 * SpielzugTest dient zum Testen der Klassen und Methoden welche die Kartenspielmechanik implementieren
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
     *  spielzugtesten simuliert einen Spielzug und beschreibt dessen Vorgaenge mit einer Ausgabe in der Konsole
     */
    public static void spielzugtesten ()
    {
        auslesen();
        erstellen();
        haendeziehen();
        einheitenbeschwoeren();
        rumlaufen();
        kaempfen();
        zugbeenden();
    }

    /**
     * erstellen erstellt die für einen Spielzug noetigen Objekte
     */
    public static void erstellen ()
    {
        meinfeld = new SpielFeld(ZAHL_5, ZAHL_5);
        KonsolenIO.ausgeben(SPIELFELDBREITE + meinfeld.getFeldSpalte() + SPIELFELDZEILEN + meinfeld.getFeldZeile());
        meinehand = new KartenHand();
        masterhand = new KartenHand();
        meintank = new ManaTank(spieler);
        master = new Gegenspieler(BJOERN, ZAHL_3, FERNKAEMPFER, ZAHL_15, ZAHL_15, ZAHL_10, ZAHL_7, ZAHL_3, ZAHL_1,
                LETZTEWORTE, ZURUECKWERFEN, ZAHL_12);
        mastertank = new ManaTank(master);
    }

    /**
     * auslesen liest aus verschiedenen Jason-Dateien, um gespeicherte Objekte nutzbar zu machen
     */
    public static void auslesen ()
    {
        try
        {
            //Erstellen 2er Spieldecks
            meindeck = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
            masterdeck = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
            KonsolenIO.ausgeben(meindeck.toString());
            spieler = SpielStandIO.leseDatei().getSpieler();
        } catch (KartenDeckFehlerhaftException | IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * haendeziehen zieht aus den beiden Kartendecks Karten und legt diese in die jeweiligen Haende
     */
    public static void haendeziehen ()
    {
        meinehand.handziehen(meindeck);
        masterhand.handziehen(masterdeck);
        KonsolenIO.ausgeben(meinehand + ZEILENUMBRUCH);
        KonsolenIO.ausgeben(VORSTELLEN_SPIELER + spieler.getName());
    }

    /**
     * einheitenbeschwoeren beschwoert die jeweiligen einheiten auf dem Spielfeld
     */
    public static void einheitenbeschwoeren ()
    {
        beschwoerenHeld(spieler, meinfeld);
        beschwoerenHeld(master, meinfeld);
        KartenEinheitController.beschwoeren(meinehand, ZAHL_2, meinfeld, ZAHL_1, ZAHL_0, meintank);
        KonsolenIO.ausgeben(
                VORSTELLEN_EINHEIT + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getName() + POSITIONS_ANGABE_NULL_EINS);
        KonsolenIO.ausgeben(POSITIONS_ANGABE_EINHEIT + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getPosition_x());
        KonsolenIO.ausgeben(ZEILE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getPosition_y() + ZEILENUMBRUCH);
    }

    /**
     * rumlaufen laesst die Karte Master bewegungen auf dem Spielfeld auffuehren
     */
    public static void rumlaufen ()
    {
        KonsolenIO.ausgeben(meinfeld.toString());
        //Gegner gibt Ihre Position wieder und bewegt sich danach um anschließend wieder ihre Position wiederzugeben
        KonsolenIO.ausgeben(
                VORSTELLEN_GEGNER + meinfeld.getSpielfeldplatz(ZAHL_4, ZAHL_4) + VON + (meinfeld.getFeldZeile()) + "," +
                (meinfeld.getFeldSpalte()));
        KonsolenIO.ausgeben(BEWEGEN);
        EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_3, master);
        EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_2, master);
        EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_1, master);
        EinheitenController.bewegen(meinfeld, ZAHL_3, ZAHL_1, master);
        EinheitenController.bewegen(meinfeld, ZAHL_2, ZAHL_1, master);
        EinheitenController.bewegen(meinfeld, ZAHL_1, ZAHL_1, master);
        KonsolenIO.ausgeben(POSITIONS_ANGABE_EINS_EINS);
        KonsolenIO.ausgeben(meinfeld.toString());
    }

    /**
     * kaempfen laesst den master gegen eine Einheit kaempfen
     */
    public static void kaempfen ()
    {
        //RonWeasley gibt seine Lebenspunkte an und wird anschließend vom Gegner angegriffen
        //und gibt danach wieder seine lebenspunkte wieder
        KonsolenIO.ausgeben(LEBENSPUNKTE_FREUND + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getLebenspunkte());
        KonsolenIO.ausgeben(POSITIONS_ANGABE_KAMPF + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getName());
        KonsolenIO.ausgeben(KAEMPFEN);
        einheitenAngreifenMitEinheiten(meinfeld, master, meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));
        KonsolenIO.ausgeben(
                LEBENSPUNKTE_NACH_KAMPF + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getLebenspunkte() + ZEILENUMBRUCH);
    }

    /**
     * beendet den momentan laufenden Spielzug
     */
    public static void zugbeenden ()
    {
        //daraufhin wird der zug beendet und
        RundenController.zugBeenden(meinfeld, meindeck, masterdeck);
        KonsolenIO.ausgeben(POSITIONS_ANGABE_NULL_EINS_ENDE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0) + ZEILENUMBRUCH);
        KonsolenIO.ausgeben(meinfeld.toString());

    }
}
