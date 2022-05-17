package model.ereignisse;

import io.KonsolenIO;
import model.SpielStand;

import static resources.StringsEreignisse.EINGABE_BOOLEAN;

/**
 * Diese Klasse legt fest, was jedes Ereignis gemeinsam hat. Ereignisse treten
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

    /**
     * Der Konstruktor von Ereignis
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Ereignis(String name, String beschreibung)
    {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    /**
     * Diese Methode dient als Getter um den Namen des Ereignisses zugaenglich
     * zu machen.
     * @return Name des jeweiligen Ereignisses
     */
    public String getName()
    {
        return name;
    }

    /**
     * Diese Methode dient als Getter um die Beschreibung des Ereignisses
     * zugaenglich zu machen.
     * @return Beschreibung des jeweiligen Ereignisses
     */
    public String getBeschreibung()
    {
        return beschreibung;
    }

    /**
     * Diese Methode dient als Getter um die Wahl des Spielers zugaenglich zu
     * machen.
     * @return Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public boolean isAuswahl()
    {
        return auswahl;
    }

    /**
     * Diese Methode dient als Setter um die Wahl des Spielers umzusetzen.
     * @param auswahl: die Wahl des Spielers
     */
    public void setAuswahl(boolean auswahl)
    {
        this.auswahl = auswahl;
    }

    /**
     * Diese Methode dient als Auswahl fuer den Spieler. Ueber eine
     * Benutzereingabe, wird ausgewaehlt, ob das Ereignis ausgefuehrt werden
     * soll oder nicht.
     */
    public void auswaehlen()
    {
        KonsolenIO.ausgeben(EINGABE_BOOLEAN);
        auswahl = KonsolenIO.eingabeBoolean();
    }

    /**
     * Diese Methode ist die zentrale Methode der Ereignisse. Sie wird von
     * jeder Subklasse ueberlagert und bestimmt, was bei welchem Ereignis
     * ausgefuehrt wird.
     * @param spielStand der aktuelle Spielstand mit seinen Attributen
     */
    public void ausfuehren(SpielStand spielStand)
    {
    }
}
