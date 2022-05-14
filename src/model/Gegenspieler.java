package model;

import resources.Effekte;
import resources.Einheiten;

public class Gegenspieler extends KarteEinheit implements Spielbar
{
    private final int mana;

    public Gegenspieler(String name, int level, Einheiten typ, int macht, int lebenspunkte, int manaKosten, int beweglichkeit, int reichweite, int verteidigung, Effekte effektEins, Effekte effektZwei, int mana)
    {
        super(name, level, typ, macht, lebenspunkte, manaKosten, beweglichkeit, reichweite, verteidigung, effektEins, effektZwei);
        this.mana = mana;
    }

    public int getMana()
    {
        return mana;
    }
}