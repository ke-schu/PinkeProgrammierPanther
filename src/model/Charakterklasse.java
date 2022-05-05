package model;


public class Charakterklasse
{
    private String name;
    private int preis;
    private String held;
    private final int anzahlDecks = 3;
    private KartenDeck beinhalteteDecks[] = new KartenDeck[anzahlDecks];

    /**
     * Konstruktor der gleichzeitig name und preis setzt.
     * @param name String der in name der Instanz gesetzt wird.
     * @param preis Int der in preis der Instanz gesetzt wird.
     */
    public Charakterklasse(String name, int preis)
    {
        this.name = name;
        this.preis = preis;
    }

    /**
     * Überlagerung der toString Methode um die Attribute name, preis, held, und kartenblaetter als einen String zu returnen.
     * @return Gibt die name, preis, held, und kartenblaetter wieder.
     */
    @Override
    public String toString()
    {
        return "Charakterklasse{" +
                "name='" + name + '\'' +
                ", preis=" + preis +
                ", held='" + held + '\'' +
                ", kartenblaetter=" + beinhalteteDecks +
                '}';
    }

    /**
     * Gibt den String des Attribut name wieder.
     * @return gibt das Attribut name wieder.
     */
    public String getName()
    {
        return name;
    }

    public KartenDeck getDeck(int nummer)
    {
        return beinhalteteDecks[nummer-1];
    }

    public boolean setDeck(KartenDeck deck, int nummer)
    {
        if (anzahlDecks >= nummer)
        {
            beinhalteteDecks[nummer-1] = deck;
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getAnzahlDecks()
    {
        return anzahlDecks;
    }
}
