package control;
import exceptions.SpielfeldDimensionGleichNullException;
import exceptions.SpielfeldNichtQuadratischException;
import model.*;
import java.io.File;
import java.io.IOException;
import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args) throws SpielfeldNichtQuadratischException
    {
        //erstelleDeck();
        try
        {
            erstelleSpielfeld();
        }
        catch (SpielfeldNichtQuadratischException | SpielfeldDimensionGleichNullException a)
        {

        }

        //waehleKlasse();
    /*    try
        {
            leseSpielstand();
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }*/
    }

    private static void leseSpielstand() throws IOException
    {
        SpielStand meinSpielStand = SpielStandController.leseDatei();
        System.out.println(meinSpielStand.getSpieldeck().getDeckBezeichnung());
    }

    private static void speichereSpielstand() throws IOException
    {
        SpielStand meinSpielStand = new SpielStand(
                10,
                new KartenDeck("testdeck"),
                new Level(),
                47);
        SpielStandController.schreibeDatei(meinSpielStand);
    }

    private static void waehleKlasse()
    {

        try
        {
            System.out.println(CharakterklasseController
                    .erstelleCharakterklasse("Magier", 125)
                    .getDeck(3)
                    .toString());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck(new File("resources/carddecks/Magier2.json"), "irgendeinName");;
        meinDeck.add(new KarteEinheit(EricKarte));
        meinDeck.add(new KarteEinheit(KennyKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(StanKarte));

        System.out.println(meinDeck);
        KartenDeckController.mischen(meinDeck);

        try
        {
            KartenDeckController.schreibeDatei(meinDeck);
            KartenDeck meinDeck2 = KartenDeckController.leseDatei("resources/carddecks/Krieger.json");
            System.out.println(meinDeck2.toString());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
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