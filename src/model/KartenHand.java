package model;

import java.util.Arrays;

import static resources.Zahlen.ZAHL_5;

public class KartenHand
{
    private Karte[] hand = null;
    private final int HANDGROESSE = ZAHL_5;

    public KartenHand()
    {
        this.hand = new Karte [HANDGROESSE];
    }

    public Karte getelement (int position)
    {
        return this.hand [position];
    }

    public void setElement (int position, Karte karte)
    {
        this.hand [position] = karte;
    }

    @Override
    public String toString()
    {
        return "KartenHand{" + "hand=" + Arrays.toString(hand) + '}';
    }

    public void handziehen (KartenDeck kartendeck)
    {
        for(int i = 0; i < hand.length; i++)
        {
            this.hand[i] = kartendeck.pop();
        }
    }

    public void handablegen (KartenDeck kartendeck)
    {
        for(int i = 0; i < hand.length; i++)
        {
            if (this.hand[i] != null)
            kartendeck.push(this.hand[i]);
            else
                continue;
        }
    }

    public void karteablegen (int positionkarte, KartenDeck kartendeck)
    {
        kartendeck.push(this.hand[positionkarte]);
        this.hand[positionkarte] = null;
    }
}
