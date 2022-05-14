package control.test;

import control.EinheitenController;
import control.KartenEinheitController;
import control.RundenController;
import exceptions.KartenDeckFehlerhaftException;
import exceptions.SpielfeldDimensionGleichNullException;
import exceptions.SpielfeldNichtQuadratischException;
import io.KartenDeckIO;
import io.SpielStandIO;
import model.*;

import java.io.File;
import java.io.IOException;

import static control.EinheitenController.einheitenAngreifenMitEinheiten;
import static control.KartenEinheitController.beschwoerenHeld;
import static resources.Effekte.LETZTEWORTE;
import static resources.Effekte.ZURUECKWERFEN;
import static resources.Einheiten.FERNKAEMPFER;
import static resources.Zahlen.ZAHL_1;

public class Alex
{
    public static void ausfuehren()
    {

try
{
    SpielFeld meinfeld = new SpielFeld(5,5);
    KartenDeck meindeck = KartenDeckIO.leseDatei("C:\\Users\\7craz\\IdeaProjects\\PinkeProgrammierPanther\\src\\resources\\kartendecks\\Spieldeck_Spieler.json");
    KartenDeck masterdeck = KartenDeckIO.leseDatei("C:\\Users\\7craz\\IdeaProjects\\PinkeProgrammierPanther\\src\\resources\\kartendecks\\Spieldeck_Gegner.json");
    System.out.println(meindeck.toString());
    KartenHand meinehand = new KartenHand();
    KartenHand masterhand = new KartenHand();
    meinehand.handziehen(meindeck);
    masterhand.handziehen(masterdeck);
    System.out.println(meinehand);
    Spieler spieler = SpielStandIO.leseDatei().getSpieler();
    System.out.println(spieler);
    ManaTank meintank = new ManaTank(spieler);
    Gegenspieler master = new Gegenspieler("bjoern",3,FERNKAEMPFER,15, 15,2,7,3,1,LETZTEWORTE, ZURUECKWERFEN,12);
    ManaTank mastertank = new ManaTank(master);
    System.out.println(" spielfeldtiefe " + meinfeld.getFeldSpalte() + " spielfeldbreite " + meinfeld.getFeldZeile());
    beschwoerenHeld(spieler, meinfeld);
    beschwoerenHeld(master, meinfeld);
    KartenEinheitController.beschwoeren(meinehand,2,meinfeld,0,1 ,meintank);
    System.out.println("ich stehe an position 0,1 " + meinfeld.getSpielfeldplatz(0,1));
    System.out.println("und ich denke ich stehe in zeile " +meinfeld.getSpielfeldplatz(0,1).getPosition_x());
    System.out.println("und spalte " + meinfeld.getSpielfeldplatz(0,1).getPosition_y());
    System.out.println("vor dem bewegen stehe ich an position" + (meinfeld.getFeldZeile()-1)+ (meinfeld.getFeldSpalte()-1)  + meinfeld.getSpielfeldplatz(meinfeld.getFeldZeile()-ZAHL_1,meinfeld.getFeldSpalte()-ZAHL_1));
    EinheitenController.bewegen(meinfeld,1,3,master);
    EinheitenController.bewegen(meinfeld,1,2,master);
    EinheitenController.bewegen(meinfeld,1,1,master);
    System.out.println("ich stehe an position 2,3 " + meinfeld.getSpielfeldplatz(2,3));

    System.out.println("Lebenspunkte freund einheit: " + meinfeld.getSpielfeldplatz(0,1).getLebenspunkte());
    System.out.println("an stelle 0, 1 befindet sich vor dem kampf" + meinfeld.getSpielfeldplatz(0,1));
    einheitenAngreifenMitEinheiten(meinfeld,master,meinfeld.getSpielfeldplatz(0,1));
    System.out.println("Lebenspunkte freund einheit nach kampf: "+  meinfeld.getSpielfeldplatz(0,1).getLebenspunkte());

    RundenController.zugBeenden(meinfeld,meindeck,masterdeck);
    System.out.println("an stelle 0, 1 befindet sich nun " + meinfeld.getSpielfeldplatz(0,1));

}
catch(KartenDeckFehlerhaftException | IOException e)
{
    ;
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

        System.out.println("Der Stack ist "+meindeck.size()+" Elemente groß.");

        meinehand.handziehen(meindeck);

        System.out.println("habe eine hand gezogen, jetzt ist mein deck "+meindeck.size()+" Elemente groß.");
        meinehand.karteablegen(1,meindeck);
        System.out.println("habe eine karte abgelegt jetzt ist mein deck wieder  "+meindeck.size()+" Elemente groß.");

        meinehand.handablegen(meindeck);
        System.out.println("habe meine hand abgelegt jetzt ist mein deck wieder  "+meindeck.size()+" Elemente groß.");
    */}

    private static void erstelleSpielfeld () throws SpielfeldNichtQuadratischException, SpielfeldDimensionGleichNullException
    {
        /*KarteEinheit peter = new KarteEinheit(EricKarte);
        KarteEinheit karin = new KarteEinheit(StanKarte);
        Spielfeld meinspielfeld = new Spielfeld();
        KartenEinheitController.beschwoeren(peter,meinspielfeld,5,5);
        KartenEinheitController.beschwoeren(karin,meinspielfeld,5,6);
        System.out.println(meinspielfeld.getSpielfeld()[5][5]);
        System.out.println(meinspielfeld.getSpielfeld()[5][6]);
        System.out.println("nach dem zurueckewerfen");
        EffektController.zurueckwerfen(meinspielfeld.getSpielfeldplatz(5,5), meinspielfeld);
        System.out.println(meinspielfeld.getSpielfeld()[5][6]);
        System.out.println(meinspielfeld.getSpielfeld()[5][7]);*/
    }
}
