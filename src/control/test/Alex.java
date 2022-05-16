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
import static resources.Zahlen.ZAHL_1;

public class Alex
{
    public static void ausfuehren()
    {

        try
        {
            //Erstellen eine Spielfeldes und 2er spieldecks
            SpielFeld meinfeld = new SpielFeld(5, 5);
            KonsolenIO.ausgeben(" spielfeldtiefe " + meinfeld.getFeldSpalte() + " spielfeldbreite " + meinfeld.getFeldZeile());
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
            KonsolenIO.ausgeben("Hallo ich bin der Spieler " + spieler.getName());

            //Erstellen von Manatank instanzen für Spieler und Gegenspieler
            ManaTank meintank = new ManaTank(spieler);
            Gegenspieler master = new Gegenspieler("bjoern", 3, FERNKAEMPFER, 15, 15, 2, 7, 3, 1, LETZTEWORTE,
                    ZURUECKWERFEN, 12);
            ManaTank mastertank = new ManaTank(master);


            //Beschwoeren des Helden und des Gegners auf dem Spielfeld, diese haben feste positionen
            beschwoerenHeld(spieler, meinfeld);
            beschwoerenHeld(master, meinfeld);

            //beschwoeren einer Einheit auf dem Feld
            KartenEinheitController.beschwoeren(meinehand, 2, meinfeld, 1, 0, meintank);

            //Karte gibt ihre position wieder
            KonsolenIO.ausgeben("Hallo ich bin eine beschworene Einheit und heiße " + meinfeld.getSpielfeldplatz(1, 0).getName() + " ich stehe an position 0,1 ");
            KonsolenIO.ausgeben("und ich denke ich stehe in Spalte " + meinfeld.getSpielfeldplatz(1, 0).getPosition_x());
            KonsolenIO.ausgeben("und Zeile " + meinfeld.getSpielfeldplatz(1, 0).getPosition_y() + ZEILENUMBRUCH);

            //Gegner gibt Ihre Position wieder und bewegt sich danach um anschließend wieder ihre Position wiederzugeben
            KonsolenIO.ausgeben("Ich bin der Gegner " + meinfeld.getSpielfeldplatz(meinfeld.getFeldZeile() - ZAHL_1,
                    meinfeld.getFeldSpalte() - ZAHL_1) + " und vor dem bewegen stehe ich an position " + (meinfeld.getFeldZeile()) + "," + (meinfeld.getFeldSpalte()));
            KonsolenIO.ausgeben("jetzt bewege ich mich...");
            EinheitenController.bewegen(meinfeld, 1, 3, master);
            EinheitenController.bewegen(meinfeld, 1, 2, master);
            EinheitenController.bewegen(meinfeld, 1, 1, master);
            KonsolenIO.ausgeben("nach dem bewegen bin ich stehe an position 1,1 " + meinfeld.getSpielfeldplatz(1, 1) + ZEILENUMBRUCH);

            //RonWeasley gibt seine Lebenspunkte an und wird anschließend vom Gegner angegriffen
            //und gibt danach wieder seine lebenspunkte wieder
            KonsolenIO.ausgeben("Lebenspunkte freund einheit: " + meinfeld.getSpielfeldplatz(1, 0).getLebenspunkte());
            KonsolenIO.ausgeben("an stelle 0, 1 befindet sich vor dem kampf" + meinfeld.getSpielfeldplatz(1, 0));
            KonsolenIO.ausgeben("jetzt wird gerade gekaempft...");
            einheitenAngreifenMitEinheiten(meinfeld, master, meinfeld.getSpielfeldplatz(1, 0));
            KonsolenIO.ausgeben("Lebenspunkte freund einheit nach Kampf: " + meinfeld.getSpielfeldplatz(1, 0).getLebenspunkte() + ZEILENUMBRUCH);

            //daraufhin wird der zug beendet und
            RundenController.zugBeenden(meinfeld, meindeck, masterdeck);
            KonsolenIO.ausgeben("an stelle 0, 1 befindet sich nun " + meinfeld.getSpielfeldplatz(1, 0));

        } catch (KartenDeckFehlerhaftException | IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

    }

    private static void erstelleKartenHand()
    {
        /*Karte karteeins = new KarteEinheit(EricKarte);
        Karte kartezwei = new KarteEinheit(KennyKarte);
        Karte kartedrei = new KarteEinheit(KyleKarte);
        Karte kartevier = new KarteEinheit(KyleKarte);
        Karte kartefuenf = new KarteEinheit(StanKarte);

        KartenDeck meindeck = new KartenDeck("Test");
        Kartenhand meinehand = new Kartenhand();


        meindeck.push(karteeins);
        meindeck.push(kartezwei);
        meindeck.push(kartedrei);
        meindeck.push(kartevier);
        meindeck.push(kartefuenf);

        KonsolenIO.ausgeben("Der Stack ist "+meindeck.size()+" Elemente groß.");

        meinehand.handziehen(meindeck);

        KonsolenIO.ausgeben("habe eine hand gezogen, jetzt ist mein deck "+meindeck.size()+" Elemente groß.");
        meinehand.karteablegen(1,meindeck);
        KonsolenIO.ausgeben("habe eine karte abgelegt jetzt ist mein deck wieder  "+meindeck.size()+" Elemente groß.");

        meinehand.handablegen(meindeck);
        KonsolenIO.ausgeben("habe meine hand abgelegt jetzt ist mein deck wieder  "+meindeck.size()+" Elemente groß.");
    */
    }


    {
        /*KarteEinheit peter = new KarteEinheit(EricKarte);
        KarteEinheit karin = new KarteEinheit(StanKarte);
        Spielfeld meinspielfeld = new Spielfeld();
        KartenEinheitController.beschwoeren(peter,meinspielfeld,5,5);
        KartenEinheitController.beschwoeren(karin,meinspielfeld,5,6);
        KonsolenIO.ausgeben(meinspielfeld.getSpielfeld()[5][5]);
        KonsolenIO.ausgeben(meinspielfeld.getSpielfeld()[5][6]);
        KonsolenIO.ausgeben("nach dem zurueckewerfen");
        EffektController.zurueckwerfen(meinspielfeld.getSpielfeldplatz(5,5), meinspielfeld);
        KonsolenIO.ausgeben(meinspielfeld.getSpielfeld()[5][6]);
        KonsolenIO.ausgeben(meinspielfeld.getSpielfeld()[5][7]);*/
    }
}
