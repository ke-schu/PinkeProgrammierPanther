package model;

public abstract class Karte
{
    private static int instanzZaehler = 0;
    private int id;
    private String name;
    private int level;
    private String klasse = this.getClass().getCanonicalName();

    /**
     * Defaultkonstruktor für die Klasse Karte
     */
    public Karte ()
    {
    }

    /**
     * Konstruktor der Klasse Karte, welcher alle Attribute setzt.
     * @param name String der in das Attribut name gesetzt wird.
     * @param level Int der in das Attribut
     */
    public Karte (String name, int level)
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
    public String toString ()
    {
        return this.getClass().getSimpleName() + " " + this.getId();
    }

    /**
     * Methode die den Int des Attributes id liefert.
     * @return gibt den Int-Wert des Attributes id.
     */
    public int getId ()
    {
        return id;
    }
}
