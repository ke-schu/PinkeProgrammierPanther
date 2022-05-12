package model;

import model.ereignisse.EreignisKlasse;

public class Raeume
{
    private EreignisKlasse ereignis = null;
    private Spieler spieler = null;


    /**
     * Konstruktor fuer die Klasse Raume die direkt ein Ereignis in das Attribut ereignis setzt.
     * @param ereignis die Instanz die in das Attribut ereignis gesetzt werden soll.
     */
    public Raeume (EreignisKlasse ereignis)
    {
        this.ereignis = ereignis;
    }

    /**
     * Methode um das Ereignis aus dem Attribut ereignis zu bekommen.
     * @return gibt das den Inhalt des Attributes ereignis wieder.
     */
    public EreignisKlasse getEreignis ()
    {
        return ereignis;
    }
}
