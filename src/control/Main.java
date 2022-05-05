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
        KartenDeck meinDeck = new KartenDeck("Krieger");

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
}