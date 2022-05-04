package control;

import model.Karte;
import model.Kartendeck;

public class Main
{
    public static void main (String[] args)
    {
        Kartendeck meinDeck = new Kartendeck("Krieger");

        meinDeck.add(new Karte("tommy"));
        meinDeck.add(new Karte("paul"));
        meinDeck.add(new Karte("lisa"));
        meinDeck.add(new Karte("lena"));
        meinDeck.add(new Karte("hermann"));
        meinDeck.add(new Karte("michael"));
        meinDeck.add(new Karte("jannes"));
        meinDeck.add(new Karte("nils"));
        meinDeck.add(new Karte("dennis"));
        meinDeck.add(new Karte("pierre"));

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