package model;

import java.util.Arrays;

import static resources.Zahlen.ZAHL_5;

/**
 *KartenHand repraesentiert die Kartenhand von welcher Karten ausgespielt werden koennen
 */
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

    /**
     * Zieht aus einem Kartendeck Karten und legt diese in das hand Array
     * @param kartendeck, Kartendeck aus welchem gezogen wird
     */
    public void handziehen (KartenDeck kartendeck)
    {
        for(int i = 0; i < hand.length; i++)
        {
            this.hand[i] = kartendeck.pop();
        }
    }

    /**
     * Legt karten aus der Hand zurueck ins Kartendeck
     * @param kartendeck, Kartendeck in welches Karten gelegt werden
     */
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

    /**
     * Legt einzelne Karte zurueck in ein Kartendeck
     * @param positionkarte, position der Karte in der Hand
     * @param kartendeck, Kartendeck in welches Karte gelegt wird
     */
    public void karteablegen (int positionkarte, KartenDeck kartendeck)
    {
        kartendeck.push(this.hand[positionkarte]);
        this.hand[positionkarte] = null;
    }
}
