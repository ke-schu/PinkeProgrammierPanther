package model;

import resources.Zaubereffekte;

/**
 KarteZauber repraesentiert eine Zauberkarte welche einen Effekt ausloesen
 kann
 Sie erbt von Karte. */
public class KarteZauber extends Karte
{
    private int macht;
    private Zaubereffekte zeffekt;
    
    /**
     Konstruiert eine Instanz der Klasse mit allen relevanten Attributen.
     * @param name Name der Karte.
     * @param level Level der Karte.
     * @param macht Macht der Karte.
     * @param zeffekt Zaubereffekt der Karte.
     */
    public KarteZauber (String name, int level, int macht,
                        Zaubereffekte zeffekt)
    {
        super(name, level);
        this.macht = macht;
        this.zeffekt = zeffekt;
    }
    
    /**
     Methoden, welche den Wert des Attributs macht liefert.
     @return gibt den Wert macht als Int wieder.
     */
    public int getMacht ()
    {
        return macht;
    }
    
    /**
     Methode die einen Int-Wert in das Attribut macht setzt.
     @param macht Int-Wert, welcher in das Attribut macht gesetzt wird.
     */
    public void setMacht (int macht)
    {
        this.macht = macht;
    }
    
    /**
     Methoden, welche den Wert des Attributs zeffekt liefert.
     * @return gibt den int des Attributes zeffekt.
     */
    public Zaubereffekte getZeffekt ()
    {
        return zeffekt;
    }
}
