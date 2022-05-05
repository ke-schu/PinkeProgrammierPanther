package model;

public class Charakterklasse
{
    private String name;
    private int preis;
    private String held;
    private final int anzahlDecks = 3;
    private KartenDeck deck[] = new KartenDeck[anzahlDecks];

    public Charakterklasse(String name, int preis)
    {
        this.name = name;
        this.preis = preis;
    }

    @Override
    public String toString()
    {
        return "Charakterklasse{" +
                "name='" + name + '\'' +
                ", preis=" + preis +
                ", held='" + held + '\'' +
                ", kartenblaetter=" + deck +
                '}';
    }

    public String getName()
    {
        return name;
    }
}
