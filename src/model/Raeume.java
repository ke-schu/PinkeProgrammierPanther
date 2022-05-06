package model;

import model.ereignisse.Ereignis;

public class Raeume extends Ebenen
{
    private Ereignis ereignis = null;

    /**
     * Konstruktor fuer die Klasse Raume die direkt ein Ereignis in das Attribut ereignis setzt.
     * @param ereignis die Instanz die in das Attribut ergnis gesetzt werden soll.
     */
    public Raeume (Ereignis ereignis)
    {
        this.ereignis = ereignis;
    }

    /**
     * Methode um das Ereignis aus dem Attribut ereignis zu bekommen.
     * @return gibt das den Inhalt des Attributes ereignis wieder.
     */
    public Ereignis getEreignis ()
    {
        return ereignis;
    }
}
