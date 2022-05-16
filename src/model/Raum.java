package model;

import model.ereignisse.Ereignis;

/**
 * Diese Klasse definiert die Raeume, welche sich in Ebenen befinden.
 */
public class Raum {
    private Ereignis ereignis = null;

    private transient SpielfigurEbene spielfigur = null;

    /**
     * Konstruktor fuer die Klasse Raume die direkt ein Ereignis in das Attribut ereignis setzt.
     * @param ereignis die Instanz die in das Attribut ereignis gesetzt werden soll.
     */
    public Raum (Ereignis ereignis) {
        this.ereignis = ereignis;
    }

    /**
     * Leerer Konstruktor fuer Raum.
     */
    public Raum ()
    {

    }

    /**
     * Methode um das Ereignis aus dem Attribut ereignis zu bekommen.
     * @return gibt das den Inhalt des Attributes ereignis wieder.
     */
    public Ereignis getEreignis () {
        return ereignis;
    }

    /**
     * Getter um zu ueberpruefen, ob eine Spielfigur im Raum ist.
     * @return gibt an, ob eine Spielfigur im Raum ist.
     */
    public SpielfigurEbene getSpielfigur ()
    {
        return spielfigur;
    }

    /**
     * Setter um eine Spielfigur in einen Raum zu setzen.
     * @param spielfigur Spielfigur, die in den Raum gesetzt werden soll.
     */
    public void setSpielfigur (SpielfigurEbene spielfigur)
    {
        this.spielfigur = spielfigur;
    }
}
