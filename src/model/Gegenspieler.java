package model;

import resources.Effekte;
import resources.Einheiten;

public class Gegenspieler extends KarteEinheit
{
    private final int mana;
    private int positionInEbeneX;
    private int positionInEbeneY;

    public Gegenspieler(String name, int level, Einheiten typ, int macht, int lebenspunkte, int manaKosten, int beweglichkeit, int reichweite, int verteidigung, Effekte effektEins, Effekte effektZwei, int mana, int positionInEbeneX, int positionInEbeneY)
    {
        super(name, level, typ, macht, lebenspunkte, manaKosten, beweglichkeit, reichweite, verteidigung, effektEins, effektZwei);
        this.mana = mana;
        this.positionInEbeneX = positionInEbeneX;
        this.positionInEbeneY = positionInEbeneY;
    }

    public int getMana()
    {
        return mana;
    }

    public int getPositionInEbeneX()
    {
        return positionInEbeneX;
    }

    public void setPositionInEbeneX(int positionInEbeneX)
    {
        this.positionInEbeneX = positionInEbeneX;
    }

    public int getPositionInEbeneY()
    {
        return positionInEbeneY;
    }

    public void setPositionInEbeneY(int positionInEbeneY)
    {
        this.positionInEbeneY = positionInEbeneY;
    }
}
