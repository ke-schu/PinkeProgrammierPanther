package control;
import exceptions.SpielfeldDimensionGleichNullException;
import exceptions.SpielfeldNichtQuadratischException;
import model.*;

import java.io.IOException;

import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args) throws SpielfeldNichtQuadratischException, SpielfeldDimensionGleichNullException
    {
        erstelleDeck();
    }

    public static void erstelleDeck () throws SpielfeldNichtQuadratischException, SpielfeldDimensionGleichNullException
    {
        KartenDeck meinDeck = new KartenDeck("Krieger");

        KarteEinheit peter = new KarteEinheit(EricKarte);
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

        Spielfeld meinspielfeld = new Spielfeld(5,5);
        KartenEinheitController.beschwoeren(peter,meinspielfeld,2,2);


    }
}