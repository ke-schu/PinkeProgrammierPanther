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
import static resources.Strings.ZEILENUMBRUCH;
import static resources.TestKonstanten.*;
import static resources.Zahlen.*;

public class SpielzugTest
{

    public static void Spielzugtesten()
    {

        try
        {
            //Erstellen eine Spielfeldes und 2er spieldecks
            SpielFeld meinfeld = new SpielFeld(ZAHL_5, ZAHL_5);
            KonsolenIO.ausgeben(
                    SPIELFELDBREITE + meinfeld.getFeldSpalte() + SPIELFELDZEILEN + meinfeld.getFeldZeile());
            KartenDeck meindeck = KartenDeckIO.leseDatei("src\\resources\\kartendecks\\Spieldeck_Spieler.json");
            KartenDeck masterdeck = KartenDeckIO.leseDatei("src\\resources\\kartendecks\\Spieldeck_Gegner.json");
            KonsolenIO.ausgeben(meindeck.toString());

            //Erstellen 2er haende und befuellen dieser
            KartenHand meinehand = new KartenHand();
            KartenHand masterhand = new KartenHand();
            meinehand.handziehen(meindeck);
            masterhand.handziehen(masterdeck);
            KonsolenIO.ausgeben(meinehand + ZEILENUMBRUCH);
            Spieler spieler = SpielStandIO.leseDatei().getSpieler();
            KonsolenIO.ausgeben(VORSTELLENSPIELER + spieler.getName());

            //Erstellen von Manatank instanzen für Spieler und Gegenspieler
            ManaTank meintank = new ManaTank(spieler);
            Gegenspieler master =
                    new Gegenspieler(BJOERN, ZAHL_3, FERNKAEMPFER, ZAHL_15, ZAHL_15, ZAHL_10, ZAHL_7, ZAHL_3, ZAHL_1, LETZTEWORTE, ZURUECKWERFEN, ZAHL_12);
            ManaTank mastertank = new ManaTank(master);


            //Beschwoeren des Helden und des Gegners auf dem Spielfeld, diese haben feste positionen
            beschwoerenHeld(spieler, meinfeld);
            beschwoerenHeld(master, meinfeld);

            //beschwoeren einer Einheit auf dem Feld
            KartenEinheitController.beschwoeren(meinehand, ZAHL_2, meinfeld, ZAHL_1, ZAHL_0, meintank);

            //Karte gibt ihre position wieder
            KonsolenIO.ausgeben(
                    VORSTELLENEINHEIT + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getName() +
                    POSITIONSANGABENULLEINS);
            System.out.println(POSITIONSANGABEEINHEIT + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getPosition_x());
            System.out.println(ZEILE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getPosition_y() + ZEILENUMBRUCH);

            //Gegner gibt Ihre Position wieder und bewegt sich danach um anschließend wieder ihre Position wiederzugeben
            System.out.println(VORSTELLENGEGNER + meinfeld.getSpielfeldplatz(ZAHL_4, ZAHL_4) + VON +
                               (meinfeld.getFeldZeile()) + "," + (meinfeld.getFeldSpalte()));
            KonsolenIO.ausgeben(BEWEGEN);

            EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_3, master);
            EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_2, master);
            EinheitenController.bewegen(meinfeld, ZAHL_4, ZAHL_1, master);
            EinheitenController.bewegen(meinfeld, ZAHL_3, ZAHL_1, master);
            EinheitenController.bewegen(meinfeld, ZAHL_2, ZAHL_1, master);
            EinheitenController.bewegen(meinfeld, ZAHL_1, ZAHL_1, master);
            KonsolenIO.ausgeben(POSITIONSANGABEEINSEINS + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_1) +
                               ZEILENUMBRUCH);

            //RonWeasley gibt seine Lebenspunkte an und wird anschließend vom Gegner angegriffen
            //und gibt danach wieder seine lebenspunkte wieder
            KonsolenIO.ausgeben(LEBENSPUNKTEFREUND + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getLebenspunkte());
            KonsolenIO.ausgeben(POSITIONSANGABEKAMPF + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));
            KonsolenIO.ausgeben(KAEMPFEN);
            einheitenAngreifenMitEinheiten(meinfeld, master, meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));
            KonsolenIO.ausgeben(
                    LEBENSPUNKTENACHKAMPF + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getLebenspunkte() +
                    ZEILENUMBRUCH);

            //daraufhin wird der zug beendet und
            RundenController.zugBeenden(meinfeld, meindeck, masterdeck);
            KonsolenIO.ausgeben(POSITIONSANGABENULLEINSENDE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));

        } catch (KartenDeckFehlerhaftException | IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

    }
}
