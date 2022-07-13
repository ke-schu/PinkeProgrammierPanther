package model.ereignisse;

import model.SpielStand;

/**
 Diese Klasse legt fest, was jedes Ereignis gemeinsam hat. Ereignisse treten.
 in Raeumen in der aktuellen Ebene auf, auf der sich der Spieler befindet. */
public abstract class Ereignis
{
    
    protected boolean auswahl = false;
    protected boolean ausgefuehrt = false;
    private String name;
    private String beschreibung;
    private String klasse = this.getClass().getCanonicalName();
    
    /**
     Der Konstruktor von Ereignis.
     @param name: Der Name des Ereignisses.
     @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Ereignis (String name, String beschreibung)
    {
        this.name = name;
        this.beschreibung = beschreibung;
    }
    
    /**
     Diese Methode dient als Getter um den Namen des Ereignisses
     zugaenglich zu
     machen.
     @return Name des jeweiligen Ereignisses.
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     Diese Methode dient als Getter um die Beschreibung des Ereignisses
     zugaenglich zu machen.
     @return Beschreibung des jeweiligen Ereignisses.
     */
    public String getBeschreibung ()
    {
        return beschreibung;
    }
    
    /**
     Diese Methode dient als Getter um die Wahl des Spielers zugaenglich zu
     machen.
     @return Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt.
     */
    public boolean isAuswahl ()
    {
        return auswahl;
    }
    
    /**
     Diese Methode dient als Setter um die Wahl des Spielers umzusetzen.
     @param auswahl: die Wahl des Spielers.
     */
    public void setAuswahl (boolean auswahl)
    {
        this.auswahl = auswahl;
    }
    
    /**
     Gibt den booleschen Wert des Attributes ausgefuehrt wieder.
     * @return gibt den booleschen Wert des Attributes ausgefuehrt wieder.
     */
    public boolean isAusgefuehrt ()
    {
        return ausgefuehrt;
    }
    
    /**
     Mehtode um einen booleschen Wert in das Attribut ausgefuehrt zu setzen.
     * @param ausgefuehrt boolescher Wert der in das Attribut ausgefuehrt gesetzt werden soll.
     */
    public void setAusgefuehrt (boolean ausgefuehrt)
    {
        this.ausgefuehrt = ausgefuehrt;
    }
    
    /**
     Diese Methode ist die zentrale Methode der Ereignisse. Sie wird von jeder
     Subklasse ueberlagert und bestimmt, was bei welchem Ereignis ausgefuehrt
     wird.
     @param spielStand der aktuelle Spielstand mit seinen Attributen.
     */
    public void ausfuehren (SpielStand spielStand)
    {
    }
}
