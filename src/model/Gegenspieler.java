package model;

import resources.Effekte;
import resources.Einheiten;

/**
 * Diese Klasse definiert den Gegenspieler in Kaempfen mit dem Spieler
 */
public class Gegenspieler extends KarteEinheit implements Spielbar
{
    private final int mana;

    public Gegenspieler (String name, int level, Einheiten typ, int macht, int lebenspunkte, int beweglichkeit,
                         int reichweite, int schild, int verteidigung, Effekte effektEins, Effekte effektZwei, int mana)
    {
        super(name, level, typ, macht, lebenspunkte, 0, beweglichkeit, reichweite, schild, verteidigung, effektEins,
              effektZwei, true, false);
        this.mana = mana;
    }

    /**
     * Gibt den Int-Wert des Attributes mana wieder.
     *
     * @return Int-Wert des Attributes mana.
     */
    public int getMana ()
    {
        return mana;
    }
}