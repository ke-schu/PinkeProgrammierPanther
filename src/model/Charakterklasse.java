package model;

public class Charakterklasse
{
    private String name;
    private int preis;
    private String held;
    private final int anzahlDecks = 3;
    private KartenDeck deck[] = new KartenDeck[anzahlDecks];

    public Charakterklasse() {

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
}
