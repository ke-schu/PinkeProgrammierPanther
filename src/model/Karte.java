package model;

import static resources.Strings.LEERZEICHEN;

/**
 * Karte ist eine abstrakte Superklasse fuer genauer definierte Karten
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
     * @param name der Name der Karte.
     * @param level das Kartenlevel.
     */
    public Karte(String name, int level)
    {
        this.id = instanzZaehler;
        this.name = name;
        this.level = level;
        instanzZaehler++;
    }

    /**
     * Leerer Konstruktor der Klasse Karte.
     */
    public Karte()
    {
    }

    /**
     * Ueberlagerung der toString Methode um das Attribut id richtig als
     * string wiederzugeben.
     * @return gibt einen String aus dem Attribut id zurueck.
     */
    @Override
    public String toString()
    {
        return this.getName() + LEERZEICHEN + this.getId();
    }

    /**
     * Methode die den Int des Attributes id liefert.
     * @return gibt den Int-Wert des Attributes id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Methode um den String des Attributes name zu bekommen.
     * @return gibt den String des Attributes name wieder.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Mehtode um den Int-Wert des Attributes level zu bekommen.
     * @return gibt den Int-Wert des Attributes level wieder.
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Methode um einen Int-Wert in das Attribut level zu setzen.
     * @param level Int-Wert, der in das Attribut level gesetzt werden soll.
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
}
