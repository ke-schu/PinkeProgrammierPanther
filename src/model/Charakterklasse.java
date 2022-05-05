package model;


public class Charakterklasse
{
    private String name;
    private int preis;
    private String held;
    private final int anzahlDecks = 3;
    private KartenDeck deck[] = new KartenDeck[anzahlDecks];

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
                ", kartenblaetter=" + deck +
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
}
