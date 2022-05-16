package model;

/**
 * KarteZauber repraesentiert eine Zauberkarte welche einen Effekt ausloesen kann
 * Sie erbt von Karte
 */
public class KarteZauber extends Karte
{
    private int macht;

    public KarteZauber (String name, int level)
    {
        super(name, level);
    }

    /**
     * Methoden, welche den Wert des Attributs macht liefert.
     * @return gibt den Wert macht als Int wieder.
     */
    public int getMacht ()
    {
        return macht;
    }

    /**
     * Methode die einen Int-Wert in das Attribut macht setzt.
     * @param macht Int-Wert, welcher in das Attribut macht gesetzt wird.
     */
    public void setMacht(int macht)
    {
        this.macht = macht;
    }
}
