package model.ereignisse;

public abstract class EreignisKlasse
{

    protected String name;              //Name des Ereignisses (Schmied, Treppe, etc.)
    protected String beschreibung;      //Beschreibung des Ereignisses. Später in der GUI sichtbar für den Spieler.
    protected boolean auswahl = false;  //Abfrage ob der Spieler, dass Ereignis ausführt (Kampf ablehnen etc.)

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
}
