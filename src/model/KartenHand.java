package model;

import exceptions.KartenDeckZuKleinException;

import java.util.Arrays;

import static resources.Zahlen.ZAHL_5;

/**
 * KartenHand repraesentiert die Kartenhand von welcher Karten ausgespielt
 * werden koennen
 */
public class KartenHand
{
    private Karte[] hand = null;
    private final int HANDGROESSE = ZAHL_5;

    /**
     * Erstellt eine KartenHand mit 5 Plaetzen
     */
    public KartenHand()
    {
        this.hand = new Karte[HANDGROESSE];
    }

    /**
     * Getter welche Karte aus der Hand wiedergibt
     * @param position gibt die Position in der KartenHand an, von welcher die
     * Karte wiedergegeben werden soll
     * @return gibt eine Karte aus der Kartenhand wieder
     */
    public Karte getelement(int position)
    {
        return this.hand[position];
    }

    /**
     * Setter welcher eine Karte in eine bestimmte Position in der Hand
     * einsetzt
     * @param position gibt die Position in der KartenHand an, in welche die
     * Karte gelegt werden soll
     * @param karte ist die Karte welche in die hand gelegt werden soll
     */
    public void setElement(int position, Karte karte)
    {
        this.hand[position] = karte;
    }

    /**
     * @return gibt die Kartenhand als String wieder
     */
    @Override
    public String toString()
    {
        return "KartenHand{" + "hand=" + Arrays.toString(hand) + '}';
    }

    /**
     * Zieht aus einem Kartendeck Karten und legt diese in das hand Array
     * @param kartendeck, Kartendeck aus welchem gezogen wird
     */
    public void handziehen(KartenDeck kartendeck)
    {
        if (kartendeck.size() > hand.length)
        {
            for (int i = 0; i < hand.length; i++)
            {
                this.hand[i] = kartendeck.pop();
            }
        }
        else
        {
            throw new KartenDeckZuKleinException();
        }
    }

    /**
     * Legt karten aus der Hand zurueck ins Kartendeck
     * @param kartendeck, Kartendeck in welches Karten gelegt werden
     */
    public void handablegen(KartenDeck kartendeck)
    {
        for (int i = 0; i < hand.length; i++)
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
    public void karteablegen(int positionkarte, KartenDeck kartendeck)
    {
        kartendeck.push(this.hand[positionkarte]);
        this.hand[positionkarte] = null;
    }
}
