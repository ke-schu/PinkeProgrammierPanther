package model;

import static resources.Strings.*;

/**
 * Karte ist eine abstrakte Superklasse für genauer definierte Karten
 */
public abstract class Karte
{
    private static int instanzZaehler = 0;
    private int id;
    private String name;
    private int level;
    private String klasse = this.getClass().getCanonicalName();

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
        instanzZaehler++;
    }

    public Karte ()
    {

    }

    /**
     * Ueberlagerung der toString Methode um das Attribut id richtig als string wiederzugeben.
     * @return gibt einen String aus dem Attribut id zurueck.
     */
    @Override
    public String toString ()
    {
        return this.getName() + LEERZEICHEN + this.getId();
    }

    /**
     * Methode die den Int des Attributes id liefert.
     * @return gibt den Int-Wert des Attributes id.
     */
    public int getId ()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}
