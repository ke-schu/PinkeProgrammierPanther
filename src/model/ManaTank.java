package model;

public class ManaTank
{
    private int mana;

    public ManaTank (Spieler spieler)
    {
        this.mana = spieler.getMana();
    }

    public int getMana ()
    {
        return this.mana;
    }
    public void setMana (int mana)
    {
        this.mana = mana;
    }
}