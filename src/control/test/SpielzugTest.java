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
import static resources.Effekte.LETZTEWORTE;
import static resources.Effekte.ZURUECKWERFEN;
import static resources.Einheiten.FERNKAEMPFER;
import static resources.Strings.ZEILENUMBRUCH;
import static resources.Zahlen.ZAHL_1;

public class SpielzugTest
{
    private static final String BJOERN = "Bjoern";
    private static final String SPIELFELDBREITE = " Spielfeldbreite ";
    private static final String SPIELFELDZEILEN = " Spielfeldzeilen ";
    private static final String VORSTELLENSPIELER  = " Hallo ich bin der Spieler ";
    private static final String VORSTELLENEINHEIT = " Hallo ich bin eine beschworene Einheit und heiße";
    private static final String POSITIONSANGABENULLEINS = " ich stehe an position 0,1 ";
    private static final String POSITIONSANGABEEINSEINS = " nach dem bewegen bin ich stehe an position 1,1 ";
    private static final String POSITIONSANGABEEINHEIT = " und ich denke ich stehe in Spalte ";
    private static final String VORSTELLENGEGNER = " Halllo Ich bin der Gegner ";
    private static final String ZEILE = " und Zeile ";
    private static final String VON = " und vor dem bewegen stehe ich an position ";
    private static final String BEWEGEN = "jetzt bewege ich mich...";
    private static final String LEBENSPUNKTEFREUND =" Lebenspunkte freund einheit: ";
    private static final String POSITIONSANGABEKAMPF = "an stelle 0, 1 befindet sich vor dem Kampf";
    private static final String KAEMPFEN = "jetzt wird gerade gekaempft...";
    private static final String LEBENSPUNKTENACHKAMPF = "Lebenspunkte freund einheit nach Kampf: ";
    private static final String POSITIONSANGABENULLEINSENDE = "an stelle 0, 1 befindet sich nun ";


    public static void Spielzugtesten()
    {

        try
        {
            //Erstellen eine Spielfeldes und 2er spieldecks
            SpielFeld meinfeld = new SpielFeld(5, 5);
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
                    new Gegenspieler(BJOERN, 3, FERNKAEMPFER, 15, 15, 2, 7, 3, 1, LETZTEWORTE, ZURUECKWERFEN, 12);
            ManaTank mastertank = new ManaTank(master);


            //Beschwoeren des Helden und des Gegners auf dem Spielfeld, diese haben feste positionen
            beschwoerenHeld(spieler, meinfeld);
            beschwoerenHeld(master, meinfeld);

            //beschwoeren einer Einheit auf dem Feld
            KartenEinheitController.beschwoeren(meinehand, 2, meinfeld, 1, 0, meintank);

            //Karte gibt ihre position wieder
            System.out.println(
                    VORSTELLENEINHEIT + meinfeld.getSpielfeldplatz(1, 0).getName() +
                    POSITIONSANGABENULLEINS);
            System.out.println(POSITIONSANGABEEINHEIT + meinfeld.getSpielfeldplatz(1, 0).getPosition_x());
            System.out.println(ZEILE + meinfeld.getSpielfeldplatz(1, 0).getPosition_y() + ZEILENUMBRUCH);

            //Gegner gibt Ihre Position wieder und bewegt sich danach um anschließend wieder ihre Position wiederzugeben
            System.out.println(VORSTELLENGEGNER + meinfeld.getSpielfeldplatz(meinfeld.getFeldZeile() - ZAHL_1,
                    meinfeld.getFeldSpalte() - ZAHL_1) + VON +
                               (meinfeld.getFeldZeile()) + "," + (meinfeld.getFeldSpalte()));
            System.out.println(BEWEGEN);
            EinheitenController.bewegen(meinfeld, 1, 3, master);
            EinheitenController.bewegen(meinfeld, 1, 2, master);
            EinheitenController.bewegen(meinfeld, 1, 1, master);
            System.out.println(POSITIONSANGABEEINSEINS + meinfeld.getSpielfeldplatz(1, 1) +
                               ZEILENUMBRUCH);

            //RonWeasley gibt seine Lebenspunkte an und wird anschließend vom Gegner angegriffen
            //und gibt danach wieder seine lebenspunkte wieder
            System.out.println(LEBENSPUNKTEFREUND + meinfeld.getSpielfeldplatz(1, 0).getLebenspunkte());
            System.out.println(POSITIONSANGABEKAMPF + meinfeld.getSpielfeldplatz(1, 0));
            System.out.println(KAEMPFEN);
            einheitenAngreifenMitEinheiten(meinfeld, master, meinfeld.getSpielfeldplatz(1, 0));
            System.out.println(
                    LEBENSPUNKTENACHKAMPF + meinfeld.getSpielfeldplatz(1, 0).getLebenspunkte() +
                    ZEILENUMBRUCH);

            //daraufhin wird der zug beendet und
            RundenController.zugBeenden(meinfeld, meindeck, masterdeck);
            System.out.println(POSITIONSANGABENULLEINSENDE + meinfeld.getSpielfeldplatz(1, 0));

        } catch (KartenDeckFehlerhaftException | IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
