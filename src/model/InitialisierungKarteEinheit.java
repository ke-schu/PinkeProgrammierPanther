package model;

/**
 * InitialisierungKarteEinheit ist verantwordlich fuer das Zwischenspeichern
 * von Werten welche fuer das initialisieren von Kartenattributen gebraucht
 * werden.
 */
public class InitialisierungKarteEinheit
{
    private int macht;
    private int lebenspunkte;
    private int beweglichkeit;
    private int reichweite;
    private int schild;
    private int verteidigung;
    private Position position;
    private boolean schlafend = true;


    /**
     * Gibt den Int-Wert des Attributes macht wieder.
     * @return Int-Wert des Attributes macht.
     */
    public int getMacht()
    {
        return macht;
    }

    /**
     * Methode um einen Int-Wert in das Attribut macht zu setzen.
     * @param macht Int-Wert, welcher in das Attribut macht gesetzt werden
     * soll.
     */
    public void setMacht(int macht)
    {
        this.macht = macht;
    }

    /**
     * Gibt den Int-Wert des Attributes lebenspunkte wieder.
     * @return Int-Wert des Attributes lebenspunkte.
     */
    public int getLebenspunkte()
    {
        return lebenspunkte;
    }

    /**
     * Methode um einen Int-Wert in das Attribut lebenspunkte zu setzten.
     * @param lebenspunkte Int-Wert, welcher in das Attribut gesetzt werden
     * soll.
     */
    public void setLebenspunkte(int lebenspunkte)
    {
        this.lebenspunkte = lebenspunkte;
    }

    /**
     * Gibt den Int-Wert des Attributes beweglichkeit wieder.
     * @return Int-Wert des Attributes beweglichkeit.
     */
    public int getBeweglichkeit()
    {
        return beweglichkeit;
    }

    /**
     * Methode um einen Int-Wert in das Attribut beweglichkeit zu setzen.
     * @param beweglichkeit Int-Wert der in das Attribut beweglichkeit gesetzt
     * werden soll.
     */
    public void setBeweglichkeit(int beweglichkeit)
    {
        this.beweglichkeit = beweglichkeit;
    }

    /**
     * Gibt den Int-Wert des Attributes reichweite wieder.
     * @return Int-Wert des Attributes reichweite.
     */
    public int getReichweite()
    {
        return reichweite;
    }

    /**
     * Methode um einen Int-Wert in das Attribut reichweite zu setzen.
     * @param reichweite Int-Wert, welcher in das Attribut reichweite gesetzt
     * werden soll.
     */
    public void setReichweite(int reichweite)
    {
        this.reichweite = reichweite;
    }

    /**
     * Gibt den Int-Wert des Attributes schild wieder.
     * @return Int-Wert des Attributes schild.
     */
    public int getSchild()
    {
        return schild;
    }

    /**
     * Methode um einen Int-Wert in das Attribut schild zu setzen.
     * @param schild Int-Wert, der in das Attribut schild gesetzt werden
     * soll.
     */
    public void setSchild(int schild)
    {
        this.schild = schild;
    }

    /**
     * Gibt den Int-Wert des Attributes verteidigung wieder.
     * @return Int-Wert des Attributes verteidigung.
     */
    public int getVerteidigung()
    {
        return verteidigung;
    }

    /**
     * Methode um einen Int-Wert in das Attribut verteidigung zu setzen.
     * @param verteidigung Int-Wert der in das Attribut verteidigung gesetzt
     * werden soll.
     */
    public void setVerteidigung(int verteidigung)
    {
        this.verteidigung = verteidigung;
    }

    /**
     * Methode um eine Instanz der Klasse Position aus dem Attribut position
     * zu bekommen.
     * @return gibt eine Instanz der Klasse Position aus dem Attribut position
     * wieder.
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Methode um eine Instanz der Klasse Position in das Attribut position zu
     * setzen.
     * @param position Instanz der Klasse Position die in das Attribut
     * position gesetzt werden soll.
     */
    public void setPosition(Position position)
    {
        this.position = position;
    }

    /**
     * Gibt den booleschen Wert des Attributes schlafend wieder.
     * @return booleschen Wert des Attributes schlafend.
     */
    public boolean getSchlafend()
    {
        return schlafend;
    }

    /**
     * Methode um einen booleschen Wert in das Attribut schlafend zu setzen.
     * @param schlafend boolescher Wert, welcher in das Attribut schlafend
     * gesetzt werden soll.
     */
    public void setSchlafend(boolean schlafend)
    {
        this.schlafend = schlafend;
    }

}

