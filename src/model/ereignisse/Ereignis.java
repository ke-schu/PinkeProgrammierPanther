package model.ereignisse;

import java.util.Scanner;

/**
 * Diese Klasse legt fest, was jedes Ereignis gemeinsam hat. Ereignisse treten in Raeumen in der aktuellen
 * Ebene auf, auf der sich der Spieler befindet.
 */
public abstract class Ereignis
{
    private String name;                //Name des Ereignisses (Schmied, Treppe, etc.)
    private String beschreibung;        //Beschreibung des Ereignisses. Später in der GUI sichtbar für den Spieler.
    protected boolean auswahl = false;  //Abfrage ob der Spieler, dass Ereignis ausführt (Kampf ablehnen etc.)
    private String klasse = this.getClass().getCanonicalName();

    /**
     *
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Ereignis(String name, String beschreibung)
    {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    /**
     * Diese Methode dient als Getter um den Namen des Ereignisses zugänglich zu machen.
     * @return Name des jeweiligen Ereignisses
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Diese Methode dient als Getter um die Beschreibung des Ereignisses zugänglich zu machen.
     * @return Beschreibung des jeweiligen Ereignisses
     */
    public String getBeschreibung ()
    {
        return beschreibung;
    }

    /**
     * Diese Methode dient als Getter um die Wahl des Spielers zugänglich zu machen.
     * @return Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public boolean isAuswahl ()
    {
        return auswahl;
    }

    /**
     * Diese Methode dient als Setter um die Wahl des Spielers umzusetzen.
     * @param auswahl: die Wahl des Spielers
     */
    public void setAuswahl (boolean auswahl)
    {
        this.auswahl = auswahl;
    }

    /**
     * Diese Methode dient als Auswahl fuer den Spieler. Ueber eine Benutzereingabe, wird ausgewaehlt,
     * ob das Ereignis ausgefuehrt werden soll oder nicht.
     */
    public void auswaehlen ()
    {
        System.out.println("Auswahl: Entweder true oder false, um Ereignis auszufuehren.");
        Scanner playerWahl = new Scanner(System.in);
        auswahl = playerWahl.nextBoolean();
    }
}
