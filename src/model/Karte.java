package model;

public class Karte
{
    private String name;

    public Karte (String name)
    {
        this.name = name;
    }

    @Override
    public String toString ()
    {
        return this.name;
    }
}
