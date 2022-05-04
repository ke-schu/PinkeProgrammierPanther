package control;

import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args)
    {
        erstelleDeck();
    }

    public static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck("Krieger", "src/resources");

        meinDeck.add(new KarteEinheit(EricKarte));
        meinDeck.add(new KarteEinheit(KennyKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(StanKarte));

        KartenDeckController.mischen(meinDeck);
        System.out.println(meinDeck);

        try
        {
            KartenDeckController.schreibeInDatei(meinDeck);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}