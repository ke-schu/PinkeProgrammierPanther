package control.test;

import control.EffektController;
import control.KartenEinheitController;
import exceptions.SpielfeldDimensionGleichNullException;
import exceptions.SpielfeldNichtQuadratischException;
import model.*;

import static model.KartenEinheitType.*;

public class Alex
{
    public static void ausfuehren()
    {
        try
        {
            erstelleSpielfeld();
        }
        catch (SpielfeldNichtQuadratischException | SpielfeldDimensionGleichNullException e)
        {
            System.out.println(e.getMessage());
        }
        erstelleKartenHand();
    }

    private static void erstelleKartenHand()
    {
        Karte karteeins = new KarteEinheit(EricKarte);
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
    }

    private static void erstelleSpielfeld () throws SpielfeldNichtQuadratischException, SpielfeldDimensionGleichNullException
    {
        KarteEinheit peter = new KarteEinheit(EricKarte);
        KarteEinheit karin = new KarteEinheit(StanKarte);
        Spielfeld meinspielfeld = new Spielfeld(10,10);
        KartenEinheitController.beschwoeren(peter,meinspielfeld,5,5);
        KartenEinheitController.beschwoeren(karin,meinspielfeld,5,6);
        System.out.println(meinspielfeld.getSpielfeld()[5][5]);
        System.out.println(meinspielfeld.getSpielfeld()[5][6]);
        System.out.println("nach dem zurueckewerfen");
        EffektController.zurueckwerfen(meinspielfeld.getSpielfeldplatz(5,5), meinspielfeld);
        System.out.println(meinspielfeld.getSpielfeld()[5][6]);
        System.out.println(meinspielfeld.getSpielfeld()[5][7]);
    }
}
