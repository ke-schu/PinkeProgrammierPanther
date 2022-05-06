package model.ereignisse;

public abstract class Ereignis {

    protected String name;              //Name des Ereignisses (Schmied, Treppe, etc.)
    protected String beschreibung;      //Beschreibung des Ereignisses. Später in der GUI sichtbar für den Spieler.
    protected int position;             //Position des Ereignisses auf der Oberkarte
    protected boolean auswahl = false;  //Abfrage ob der Spieler, dass Ereignis ausführt (Kampf ablehnen etc.)

    /**
     * Diese Methode dient als Getter um den Namen des Ereignisses zugänglich zu machen.
     * @return
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Diese Methode dient als Getter um die Position des Ereignisses zugänglich zu machen.
     * @return
     */
    public int getPosition ()
    {
        return position;
    }

    /**
     * Diese Methode dient als Getter um die Beschreibung des Ereignisses zugänglich zu machen.
     * @return
     */
    public String getBeschreibung ()
    {
        return beschreibung;
    }

    /**
     * Diese Methode dient als Getter um die Wahl des Spielers zugänglich zu machen.
     * @return
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
     * Diese Methode wird von den Subklassen von Ereignis ausformuliert. Sie ist die zentrale Methode des
     * jeweiligen Ereignisses.
     */
    public abstract void ausfuehren ();
}
