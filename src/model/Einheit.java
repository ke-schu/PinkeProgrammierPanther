package model;

public class Einheit
{
    private KartenEinheitType type;
    private String name;
    private int level;
    private String klasse;
    private int macht;
    private int lebenspunkte;
    private int mana;

    private int beweglichkeit;
    private int reichweite;

    private  EffektTyp effekt;

    private int position_x = 0;
    private int position_y = 0;

    /**
     * Konstruktor der alle Attribute aus der uebergebenen KarteEinheit übernimmt.
     * @param karte dessen Attribute uebernommen werden.
     */
   public Einheit (KarteEinheit karte)
    {
        this.type = karte.gettype();
        this.name=karte.getKartenName();
        this.level = karte.getKartenLevel();
        this.klasse = karte.getKlasse();
        this.macht = karte.getKartenMacht();
        this.lebenspunkte = karte.getKartenLebenspunkte();
        this.mana = karte.getKartenMana();
        this.beweglichkeit = karte.getKartenBeweglichkeit();
        this.reichweite = karte.getKartenReichweite();
        this.effekt = karte.getKartenEffekt();
    }

    /**
     * Methode um aus einer Instanz der Klasse KarteEinheit bei entsprechend großem Wert in dem Attribut mana einer Instanz
     * von ManaTank eine Instanz Einheit zurückzugeben.
     * @param karte Instanz von KarteEinheit dessen Werte an die zu erstellende Einheit uebergeben werden.
     * @param reserve Instanz von ManaTank um zu pruefen, ob das Attribut Mana groß genug ist.
     * @return gibt die neue Instanz aus Einheit zurueck mit den Werten der uebergebenen Instanz von KarteEinheit.
     */
    public Einheit erschaffen(KarteEinheit karte, ManaTank reserve)
    {
        if(karte.getKartenMana()<= reserve.getMana())
        {
            return new Einheit(karte);
        }
        else return null;
    }

    /**
     * Methode um das Attribut lebenspunkte einer Instanz von Einheit um den Betrag von schaden zu reduzieren.
     * @param schaden betrag um den die lebenspunkte der Instanz reduziert werden soll.
     */
    public void schadenNehmen(int schaden)
    {
       this.lebenspunkte = lebenspunkte-schaden;
    }

    /**
     * Methode um den Wert des Attributes typ zu bekommen.
     * @return gibt den KartenEinheitType des Attributes typ zurueck.
     */
    public KartenEinheitType getType()
    {
        return type;
    }

    /**
     * Methode um den String des Attributes name zu bekommen.
     * @return gibt den String aus name zurueck.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Methode um den Int des Attributes level zu bekommen.
     * @return gibt den Int aus level zurueck.
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Methode um den Int des Attributes level zu setzen.
     * @param level Int der in das Attribut level gesetzt werden soll.
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * Methode um den String des Attributes klasse zu bekommen.
     * @return gibt den String aus klasse zurueck.
     */
    public String getKlasse()
    {
        return klasse;
    }

    /**
     * Methode um den Int des Attributes macht zu bekommen.
     * @return gibt den Int aus macht zurueck.
     */
    public int getMacht()
    {
        return macht;
    }

    /**
     * Methode um den Int des Attributs macht zu setzen.
     * @param macht Int der in das Attribut macht gesetzt werden soll.
     */
    public void setMacht(int macht)
    {
        this.macht = macht;
    }

    /**
     * Methode um den Int des Attributes lebenspunkte zu bekommen.
     * @return gibt den Int aus lebenspunkte zurueck.
     */
    public int getLebenspunkte()
    {
        return lebenspunkte;
    }

    /**
     * Methode um den Int des Attributes lebenspunkte zu setzen.
     * @param lebenspunkte Int der in das Attribut lebenspunkte gesetzt werden soll.
     */
    public void setLebenspunkte(int lebenspunkte)
    {
        this.lebenspunkte = lebenspunkte;
    }

    /**
     * Methode um den Int des Attributes mana zu bekommen.
     * @return gibt den Int aus mana zurueck.
     */
    public int getMana()
    {
        return mana;
    }

    /**
     * Methode um den Int des Attributes mana zu setzen.
     * @param mana Int der in das Attribut mana gesetzt werden soll.
     */
    public void setMana(int mana)
    {
        this.mana = mana;
    }

    /**
     * Methode um den Int des Attributes beweglichkeit zu bekommen.
     * @return gibt den int aus beweglichkeit zurueck.
     */
    public int getBeweglichkeit()
    {
        return beweglichkeit;
    }

    /**
     * Methode um den Int des Attributes beweglichkeit zu setzen.
     * @param beweglichkeit Int der in das Attribut beweglichkeit gesetzt werden soll.
     */
    public void setBeweglichkeit(int beweglichkeit)
    {
        this.beweglichkeit = beweglichkeit;
    }

    /**
     * Methode um den Int des Attributes reichweite zu bekommen.
     * @return gibt den Int aus reichweite zurueck.
     */
    public int getReichweite()
    {
        return reichweite;
    }

    /**
     * Methode um den Int des Attributes reichweite zu setzen.
     * @param reichweite Int der in das Attribut reichweite gesetzt werden soll.
     */
    public void setReichweite(int reichweite)
    {
        this.reichweite = reichweite;
    }

    /**
     * Methode um den Int des Attributes position_x zu bekommen.
     * @return gibt den Int aus position_x zurueck.
     */
    public int getPosition_x()
    {
        return this.position_x;
    }

    /**
     * Methode um den Int des Attributes position_x zu setzen.
     * @param getPosition_x Int der in das Attribut position_x gesetzt werden soll.
     */
    public void setPosition_x(int getPosition_x)
    {
        this.position_x = getPosition_x;
    }

    /**
     * Methode um den Int des Attributes position_y zu bekommen.
     * @return gibt den Int aus position_y zurueck.
     */
    public int getPosition_y()
    {
        return this.position_y;
    }

    /**
     * Methode um den Int des Attributes position_y zu setzen.
     * @param getPosition_y Int der in das Attribut position_x gesetzt werden soll.
     */
    public void setPosition_y(int getPosition_y)
    {
        this.position_x = getPosition_y;
    }

    public EffektTyp getEffekt()
    {
        return effekt;
    }
}
