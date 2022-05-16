package control.test;

import control.EinheitenController;
import control.KartenEinheitController;
import control.RundenController;
import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import io.SpielStandIO;
import model.*;

import java.io.IOException;

import static control.EinheitenController.einheitenAngreifenMitEinheiten;
import static control.KartenEinheitController.beschwoerenHeld;
import static resources.Effekte.*;
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
            System.out.println(
                    SPIELFELDBREITE + meinfeld.getFeldSpalte() + SPIELFELDZEILEN + meinfeld.getFeldZeile());
            KartenDeck meindeck = KartenDeckIO.leseDatei("src\\resources\\kartendecks\\Spieldeck_Spieler.json");
            KartenDeck masterdeck = KartenDeckIO.leseDatei("src\\resources\\kartendecks\\Spieldeck_Gegner.json");
            System.out.println(meindeck.toString());

            //Erstellen 2er haende und befuellen dieser
            KartenHand meinehand = new KartenHand();
            KartenHand masterhand = new KartenHand();
            meinehand.handziehen(meindeck);
            masterhand.handziehen(masterdeck);
            System.out.println(meinehand + ZEILENUMBRUCH);
            Spieler spieler = SpielStandIO.leseDatei().getSpieler();
            System.out.println(VORSTELLENSPIELER + spieler.getName());

            //Erstellen von Manatank instanzen für Spieler und Gegenspieler
            ManaTank meintank = new ManaTank(spieler);
            Gegenspieler master =
                    new Gegenspieler(BJOERN, ZAHL_3, FERNKAEMPFER, ZAHL_15, ZAHL_15, ZAHL_2, ZAHL_7, ZAHL_3, ZAHL_1, LETZTEWORTE, ZURUECKWERFEN, ZAHL_12);
            ManaTank mastertank = new ManaTank(master);


            //Beschwoeren des Helden und des Gegners auf dem Spielfeld, diese haben feste positionen
            beschwoerenHeld(spieler, meinfeld);
            beschwoerenHeld(master, meinfeld);

            //beschwoeren einer Einheit auf dem Feld
            KartenEinheitController.beschwoeren(meinehand, ZAHL_2, meinfeld, ZAHL_1, ZAHL_0, meintank);

            //Karte gibt ihre position wieder
            System.out.println(
                    VORSTELLENEINHEIT + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getName() +
                    POSITIONSANGABENULLEINS);
            System.out.println(POSITIONSANGABEEINHEIT + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getPosition_x());
            System.out.println(ZEILE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getPosition_y() + ZEILENUMBRUCH);

            //Gegner gibt Ihre Position wieder und bewegt sich danach um anschließend wieder ihre Position wiederzugeben
            System.out.println(VORSTELLENGEGNER + meinfeld.getSpielfeldplatz(meinfeld.getFeldZeile() - ZAHL_1,
                    meinfeld.getFeldSpalte() - ZAHL_1) + VON +
                               (meinfeld.getFeldZeile()) + "," + (meinfeld.getFeldSpalte()));
            System.out.println(BEWEGEN);
            EinheitenController.bewegen(meinfeld, ZAHL_1, ZAHL_3, master);
            EinheitenController.bewegen(meinfeld, ZAHL_1, ZAHL_2, master);
            EinheitenController.bewegen(meinfeld, ZAHL_1, ZAHL_1, master);
            System.out.println(POSITIONSANGABEEINSEINS + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_1) +
                               ZEILENUMBRUCH);

            //RonWeasley gibt seine Lebenspunkte an und wird anschließend vom Gegner angegriffen
            //und gibt danach wieder seine lebenspunkte wieder
            System.out.println(LEBENSPUNKTEFREUND + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getLebenspunkte());
            System.out.println(POSITIONSANGABEKAMPF + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));
            System.out.println(KAEMPFEN);
            einheitenAngreifenMitEinheiten(meinfeld, master, meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));
            System.out.println(
                    LEBENSPUNKTENACHKAMPF + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0).getLebenspunkte() +
                    ZEILENUMBRUCH);

            //daraufhin wird der zug beendet und
            RundenController.zugBeenden(meinfeld, meindeck, masterdeck);
            System.out.println(POSITIONSANGABENULLEINSENDE + meinfeld.getSpielfeldplatz(ZAHL_1, ZAHL_0));

        } catch (KartenDeckFehlerhaftException | IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
