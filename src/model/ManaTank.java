package model;

public class ManaTank
{
    private int startwertmana = 5;
    private int mana;

    public ManaTank()
    {
        this.mana = startwertmana;
    }

    public int getMana()
    {
        return this.mana;
    }
    public void setMana(int mana)
    {
        this.mana = mana;
    }
}
