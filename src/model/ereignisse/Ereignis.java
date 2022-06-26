package model.ereignisse;

import model.SpielStand;

/**
 * Diese Klasse legt fest, was jedes Ereignis gemeinsam hat. Ereignisse treten.
 * in Raeumen in der aktuellen Ebene auf, auf der sich der Spieler befindet.
 */
public abstract class Ereignis
{
    private String name;
    //Name des Ereignisses (Schmied, Treppe, etc.)
    private String beschreibung;
    //Beschreibung des Ereignisses. Spaeter in der GUI sichtbar fuer den
    // Spieler.
    protected boolean auswahl = false;
    //Abfrage ob der Spieler, dass Ereignis ausfuehrt (Kampf ablehnen etc.)
    private String klasse = this.getClass().getCanonicalName();

    protected boolean ausgefuehrt = false;
    //Abfrage, ob das Ereignis bereits ausgefuehrt wurde, falls relevant

    /**
     * Der Konstruktor von Ereignis.
     *
     * @param name:         Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Ereignis(String name, String beschreibung)
    {
        this.name         = name;
        this.beschreibung = beschreibung;
    }

    /**
     * Diese Methode dient als Getter um den Namen des Ereignisses zugaenglich
     * zu machen.
     *
     * @return Name des jeweiligen Ereignisses.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Diese Methode dient als Getter um die Beschreibung des Ereignisses
     * zugaenglich zu machen.
     *
     * @return Beschreibung des jeweiligen Ereignisses.
     */
    public String getBeschreibung()
    {
        return beschreibung;
    }

    /**
     * Diese Methode dient als Getter um die Wahl des Spielers zugaenglich zu
     * machen.
     *
     * @return Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt.
     */
    public boolean isAuswahl()
    {
        return auswahl;
    }

    public boolean isAusgefuehrt ()
    {
        return ausgefuehrt;
    }

    public void setAusgefuehrt (boolean ausgefuehrt)
    {
        this.ausgefuehrt = ausgefuehrt;
    }

    /**
     * Diese Methode dient als Setter um die Wahl des Spielers umzusetzen.
     *
     * @param auswahl: die Wahl des Spielers.
     */
    public void setAuswahl(boolean auswahl)
    {
        this.auswahl = auswahl;
    }

    /**
     * Diese Methode ist die zentrale Methode der Ereignisse.
     * Sie wird von jeder Subklasse ueberlagert und bestimmt,
     * was bei welchem Ereignis ausgefuehrt wird.
     *
     * @param spielStand der aktuelle Spielstand mit seinen Attributen.
     */
    public void ausfuehren(SpielStand spielStand)
    {
    }
}
