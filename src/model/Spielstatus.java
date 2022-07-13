package model;

public class Spielstatus
{
    private Spieler spieler;
    private Gegenspieler gegenspieler;
    private SpielFeld spielfeld;
    private KartenDeck spielerDeck;
    private KartenDeck gegenspielerDeck;
    private int zugzaehler;
    
    /**
     Konstruiert eine Instanz der Klasse Spielstatus mit allen dafuer noetigen Attributen.
     * @param spieler
     * @param gegenspieler
     * @param spielfeld
     * @param spielerDeck
     * @param gegenspielerDeck
     * @param zugzaehler
     */
    public Spielstatus (Spieler spieler, Gegenspieler gegenspieler,
                        SpielFeld spielfeld, KartenDeck spielerDeck,
                        KartenDeck gegenspielerDeck, int zugzaehler)
    {
        this.spieler = spieler;
        this.gegenspieler = gegenspieler;
        this.spielfeld = spielfeld;
        this.spielerDeck = spielerDeck;
        this.gegenspielerDeck = gegenspielerDeck;
        this.zugzaehler = zugzaehler;
    }
    
    /**
     Getter Methode um den Wert des Attributes spieler zu erhalten.
     * @return gibt den Wert des Attributes spieler wieder.
     */
    public Spieler getSpieler ()
    {
        return spieler;
    }
    
    /**
     Getter Methode um den Wert des Attributes gegenspieler zu erhalten.
     * @return gibt den Wert des Attributes gegenspieler wieder.
     */
    public Gegenspieler getGegenspieler ()
    {
        return gegenspieler;
    }
    
    /**
     Getter Methode um den Wert des Attributes spielfeld zu erhalten.
     * @return gibt den Wert des Attributes spielfeld wieder.
     */
    public SpielFeld getSpielfeld ()
    {
        return spielfeld;
    }
    
    /**
     Getter Methode um den Wert des Attributes spielerDeck zu erhalten.
     * @return gibt den Wert des spielerDeck gegenspieler wieder.
     */
    public KartenDeck getSpielerDeck ()
    {
        return spielerDeck;
    }
    
    /**
     Getter Methode um den Wert des Attributes spielerDeck zu erhalten.
     * @return gibt den Wert des spielerDeck gegenspieler wieder.
     */
    public KartenDeck getGegenspielerDeck ()
    {
        return gegenspielerDeck;
    }
    
    /**
     Getter Methode um den Wert des Attributes zugzaehler zu erhalten.
     * @return gibt den Wert des zugzaehler wieder.
     */
    public int getZugzaehler ()
    {
        return zugzaehler;
    }
}
