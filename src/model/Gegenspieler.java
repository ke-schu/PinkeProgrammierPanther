package model;

import resources.Effekte;
import resources.Einheiten;

import static resources.Zahlen.ZAHL_0;

/**
 * Diese Klasse definiert den Gegenspieler in Kaempfen mit dem Spieler
 */
public class Gegenspieler extends KarteEinheit implements Spielbar
{
    private final int mana;

    /**
     * Konstruktor der mit den uebergebenen Paramtern eine Instanz erstellt.
     * @param mana Mana-Guthaben des Gegenspielers
     */
    public Gegenspieler (String name, int level, Einheiten typ, int macht, int lebenspunkte, int beweglichkeit,
                         int reichweite, int schild, int verteidigung, Effekte effektEins, Effekte effektZwei, int mana)
    {
        super(name, level, typ, macht, lebenspunkte, ZAHL_0, beweglichkeit, reichweite, schild, verteidigung,
                effektEins, effektZwei, true, false);
        this.mana = mana;
    }

    /**
     * Gibt den Int-Wert des Attributes mana wieder.
     * @return Int-Wert des Attributes mana.
     */
    public int getMana ()
    {
        return mana;
    }
}