package control;

import model.*;

import java.io.IOException;

import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args)
    {
        erstelleDeck();
    }

    public static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck(
                "Krieger", new Charakterklasse("Testklasse", 10));

        meinDeck.add(new KarteEinheit(EricKarte));
        meinDeck.add(new KarteEinheit(KennyKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(StanKarte));

        KartenDeckController.mischen(meinDeck);
        System.out.println(meinDeck);

        try
        {
            KartenDeckController.schreibeDatei(meinDeck);
            System.out.println(KartenDeckController.leseDatei("src/carddecks/Krieger.json").toString());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}