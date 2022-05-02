package Alex;

import java.util.Stack;

public class Kartenhand
{
    private Karte[] hand = null;
    private final int HANDGROESSE = 5;

    public Kartenhand ()
    {
        this.hand = new Karte [HANDGROESSE];
    }


    public Karte getelement(int position)
    {
        return this.hand [position];
    }

    public void setElement(int position, Karte karte)
    {
        this.hand [position] = karte;
    }

    public void handziehen(Deck kartendeck)
    {
        for(int i = 0; i < hand.length; i++)
        {
            this.hand[i] = kartendeck.pop();
        }
    }

    public void handablegen(Deck kartendeck)
    {
        for(int i = 0; i < hand.length; i++)
        {
            if (this.hand[i] != null)
            kartendeck.push(this.hand[i]);
            else
                continue;
        }
    }

    public void karteablegen(int positionkarte,Deck kartendeck)
    {
        kartendeck.push(this.hand[positionkarte]);
        this.hand[positionkarte] = null;
    }




}
