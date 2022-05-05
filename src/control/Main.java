package control;
import exceptions.SpielfeldDimensionGleichNullException;
import exceptions.SpielfeldNichtQuadratischException;
import model.*;

import java.io.File;
import java.io.IOException;

import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args)
    {
        //erstelleDeck();
        //erstelleSpielfeld();
        waehleKlasse();
    }

    private static void waehleKlasse()
    {

        try
        {
            System.out.println(CharakterklasseController
                    .erstelleCharakterklasse("Magier", 125)
                    .getDeck(2)
                    .toString());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck(new File("src/carddecks/Magier3.json"), "irgendeinName");;
        meinDeck.add(new KarteEinheit(EricKarte));
        meinDeck.add(new KarteEinheit(KennyKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(StanKarte));

        System.out.println(meinDeck);
        KartenDeckController.mischen(meinDeck);

        try
        {
            KartenDeckController.schreibeDatei(meinDeck);
            KartenDeck meinDeck2 = KartenDeckController.leseDatei("src/carddecks/Krieger.json");
            System.out.println(meinDeck2.toString());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void erstelleSpielfeld () throws SpielfeldNichtQuadratischException, SpielfeldDimensionGleichNullException
    {
        KarteEinheit peter = new KarteEinheit(EricKarte);
        Spielfeld meinspielfeld = new Spielfeld(5,5);
        KartenEinheitController.beschwoeren(peter,meinspielfeld,2,2);
    }
}