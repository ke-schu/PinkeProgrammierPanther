package model;

public abstract class Karte
{
    private static int instanzZaehler = 0;
    private int id;
    private String name;
    private int level;
    private String klasse = this.getClass().getCanonicalName();

    public Karte ()
    {

    }
    public Karte(String name, int level)
    {
        this.id = instanzZaehler;
        this.name = name;
        this.level = level;
        this.klasse = klasse;
        instanzZaehler++;
    }

    /**
     * Ueberlagerung der toString Methode um das Attribut id richtig als string wiederzugeben.
     * @return gibt einen String aus dem Attribut id zurueck.
     */
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + " " + this.getId();
    }

    public int getId()
    {
        return id;
    }
}
