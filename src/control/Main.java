package control;

import model.*;
import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args)
    {
        erstelleDeck();
    }

    public static void erstelleDeck ()
    {
        Kartendeck meinDeck = new Kartendeck("Krieger");

        meinDeck.add(new KarteEinheit(EricKarte));
        meinDeck.add(new KarteEinheit(KennyKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(StanKarte));

        //System.out.println(meinDeck);
        meinDeck.mischen();
        System.out.println(meinDeck);
        meinDeck.mischen();
        System.out.println(meinDeck);
        meinDeck.mischen();
        System.out.println(meinDeck);
        System.out.println();
        System.out.println(meinDeck.serialisieren());
    }
}