package model;

public class Waffe
{
    private String name;
    private int angriffsPunkte = -1;

    public Waffe(String name, int angriffsPunkte)
    {
        this.name = name;
        this.angriffsPunkte = angriffsPunkte;
    }

    public String getName()
    {
        return name;
    }

    public int getAngriffsPunkte ()
    {
        return angriffsPunkte;
    }
}
