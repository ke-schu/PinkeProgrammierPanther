package model;

import model.ereignisse.Ereignis;

public class Raum
{
    private Ereignis ereignis = null;

    /**
     * Konstruktor fuer die Klasse Raume die direkt ein Ereignis in das Attribut ereignis setzt.
     * @param ereignis die Instanz die in das Attribut ereignis gesetzt werden soll.
     */
    public Raum(Ereignis ereignis)
    {
        this.ereignis = ereignis;
    }

    public Raum()
    {

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
