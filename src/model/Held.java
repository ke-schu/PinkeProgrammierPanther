package model;

public abstract class Held
{
    private int angriffsPunkte = -1;
    private int lebensPunkte = -1;
    private Waffe startWaffe = null;

    /**
     * Konstruktor der alle Attribute der Klasse setzt.
     * @param lebensPunkte Int der in das Attribut lebensPunkte gesetzt wird.
     * @param startWaffe Waffe die in das Attribut startWaffe gelegt wird und per Methodenaufruf das Attribut angriffsPunkte setzt.
     */
    public Held(int lebensPunkte, Waffe startWaffe)
    {
        this.lebensPunkte = lebensPunkte;
        this.startWaffe = startWaffe;
        this.angriffsPunkte = startWaffe.getAngriffsPunkte();
    }

    /**
     * Methode um den Int des Attributes angriffsPunkte zu bekommen.
     * @return gibt den Int aus angriffsPunkte zurueck.
     */
    public int getAngriffsPunkte ()
    {
        return angriffsPunkte;
    }

    /**
     * Methode um den Int des Attributes angriffsPunkte zu setzen.
     * @param angriffsPunkte Int der in das Attribut angriffsPunkte gesetzt werden soll.
     */
    public void setAngriffsPunkte (int angriffsPunkte)
    {
        this.angriffsPunkte = angriffsPunkte;
    }

    /**
     * Methode um den Int des Attributes lebensPunkte zu bekommen.
     * @return gibt den Int aus lebensPunkte zurueck.
     */
    public int getLebensPunkte ()
    {
        return lebensPunkte;
    }

    /**
     * Methode um den Int des Attributes lebensPunkte zu setzen.
     * @param lebensPunkte Int der in das Attribut lebensPunkte gesetzt werden soll.
     */
    public void setLebensPunkte (int lebensPunkte)
    {
        this.lebensPunkte = lebensPunkte;
    }
}
