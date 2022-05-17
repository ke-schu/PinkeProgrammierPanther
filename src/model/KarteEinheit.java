package model;

import resources.Effekte;
import resources.Einheiten;

/**
 * KarteEinheit repraesentiert eine Einheit welche als Karte auf das Spielfeld
 * gelegt werden kann Sie erbt von Karte
 */
public class KarteEinheit extends Karte
{
    private final Einheiten typ;
    private int macht;
    private int lebenspunkte;
    private int manaKosten;
    private int beweglichkeit;
    private int reichweite;
    private int schild;
    private int verteidigung;
    private Effekte effektEins;
    private Effekte effektZwei;
    private Position position = new Position();
    private boolean schlafend = true;
    private InitialisierungKarteEinheit init;
    private boolean freundlich;


    /**
     * Konstruiert eine KarteEinheit mit allen dafuer noetigen Attributen
     * @param typ Der Typ der Karte
     * @param macht Die Macht der Karte
     * @param lebenspunkte Die Lebenspunkte der Karte
     * @param manaKosten Die Manakosten der Karte
     * @param beweglichkeit Die Beweglichkeit der Karte
     * @param reichweite Die Reichweite der Karte
     * @param schild Anzahl der Schilde der Karte
     * @param verteidigung mit wie viel Punkten ein Angriff verteidigt werden
     * soll
     * @param effektEins der erste Effekt
     * @param effektZwei der zweite Effekt
     * @param schlafend ob die Einheit schlaeft
     * @param freundlich ob die Einheit freundlich
     */
    public KarteEinheit(String name, int level, Einheiten typ, int macht,
                        int lebenspunkte, int manaKosten,
                        int beweglichkeit, int reichweite, int schild,
                        int verteidigung, Effekte effektEins,
                        Effekte effektZwei, boolean schlafend,
                        boolean freundlich)
    {
        super(name, level);
        this.typ = typ;
        this.macht = macht;
        this.lebenspunkte = lebenspunkte;
        this.manaKosten = manaKosten;
        this.beweglichkeit = beweglichkeit;
        this.reichweite = reichweite;
        this.schild = schild;
        this.verteidigung = verteidigung;
        this.effektEins = effektEins;
        this.effektZwei = effektZwei;
        this.schlafend = schlafend;
        this.freundlich = freundlich;
    }

    /**
     * Getter Methode um den Wert des Attributes typ zu erhalten
     * @return gibt den Wert des Attributes typ wieder
     */
    public Einheiten getTyp()
    {
        return typ;
    }

    /**
     * Getter Methode um den Wert des Attributes macht zu erhalten
     * @return gibt den Wert des Attributes macht wieder
     */
    public int getMacht()
    {
        return macht;
    }

    /**
     * Setter Methode um das Attribut macht zu setzen
     * @param macht auf welchen das Attribut gesetzt werden soll
     */
    public void setMacht(int macht)
    {
        this.macht = macht;
    }

    /**
     * Getter Methode um den Wert des Attributes lebenspunkte zu erhalten
     * @return gibt den Wert des Attributes lebenspunkte wieder
     */
    public int getLebenspunkte()
    {
        return lebenspunkte;
    }

    /**
     * Setter Methode um das Attribut lebenspunkte zu setzen
     * @param lebenspunkte auf welchen das Attribut gesetzt werden soll
     */
    public void setLebenspunkte(int lebenspunkte)
    {
        this.lebenspunkte = lebenspunkte;
    }

    /**
     * Getter Methode um den Wert des Attributes manaKosten zu erhalten
     * @return gibt den Wert des Attributes manaKosten wieder
     */
    public int getManaKosten()
    {
        return manaKosten;
    }

    /**
     * Setter Methode um das Attribut manaKosten zu setzen
     * @param manaKosten auf welchen das Attribut gesetzt werden soll
     */
    public void setManaKosten(int manaKosten)
    {
        this.manaKosten = manaKosten;
    }

    /**
     * Getter Methode um den Wert des Attributes beweglichkeit zu erhalten
     * @return gibt den Wert des Attributes beweglichkeit wieder
     */
    public int getBeweglichkeit()
    {
        return beweglichkeit;
    }

    /**
     * Setter Methode um das Attribut beweglichkeit zu setzen
     * @param beweglichkeit auf welchen das Attribut gesetzt werden soll
     */
    public void setBeweglichkeit(int beweglichkeit)
    {
        this.beweglichkeit = beweglichkeit;
    }

    /**
     * Getter Methode um den Wert des Attributes reichweite zu erhalten
     * @return gibt den Wert des Attributes reichweite wieder
     */
    public int getReichweite()
    {
        return reichweite;
    }

    /**
     * Setter Methode um das Attribut reichweite zu setzen
     * @param reichweite auf welchen das Attribut gesetzt werden soll
     */
    public void setReichweite(int reichweite)
    {
        this.reichweite = reichweite;
    }

    /**
     * Getter Methode um den Wert des Attributes effektEins zu erhalten
     * @return gibt den Wert des Attributes effektEins wieder
     */
    public Effekte getEffektEins()
    {
        return effektEins;
    }

    /**
     * Getter Methode um den Wert des Attributes effektZwei zu erhalten
     * @return gibt den Wert des Attributes effektZwei wieder
     */
    public Effekte getEffektZwei()
    {
        return effektZwei;
    }

    /**
     * Getter Methode um den Wert des Attributes verteidigung zu erhalten
     * @return gibt den Wert des Attributes verteidigung wieder
     */
    public int getVerteidigung()
    {
        return verteidigung;
    }

    /**
     * Setter Methode um das Attribut verteidigung zu setzen
     * @param verteidigung auf welchen das Attribut gesetzt werden soll
     */
    public void setVerteidigung(int verteidigung)
    {
        this.verteidigung = verteidigung;
    }

    /**
     * Getter Methode um den Wert des Attributes schild zu erhalten
     * @return gibt den Wert des Attributes schild wieder
     */
    public int getSchild()
    {
        return schild;
    }

    /**
     * Setter Methode um das Attribut schild zu setzen
     * @param schild auf welchen das Attribut gesetzt werden soll
     */
    public void setSchild(int schild)
    {
        this.schild = schild;
    }

    /**
     * Getter Methode um den Wert des Attributes position zu erhalten
     * @return gibt den Wert des Attributes position wieder
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Getter Methode um den Wert des Attributes schild zu erhalten
     * @return gibt den Wert des Attributes schild wieder
     */
    public int getPositionX()
    {
        return this.position.getX();
    }

    /**
     * Getter Methode um den Wert des Attributes position und dessen Attribut
     * y zu erhalten
     * @return gibt den Wert des Attributes  position únd dessen Attribut y
     * wieder
     */
    public int getPositionY()
    {
        return this.position.getY();
    }


    /**
     * Setter Methode um das Attribut position zu setzen
     * @param x auf welchen das Attribut gesetzt werden soll
     * @param y auf welchen das Attribut gesetzt werden soll
     */
    public void setPosition(int x, int y)
    {
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * Setter Methode um das Attribut position zu setzen
     * @param posi auf welchen das Attribut gesetzt werden soll
     */
    public void setPosition(Position posi)
    {
        this.position = posi;
    }

    /**
     * Getter Methode um den Wert des Attributes schlafend zu erhalten
     * @return gibt den Wert des Attributes schlafend wieder
     */
    public boolean getSchlafend()
    {
        return schlafend;
    }

    /**
     * Setter Methode um das Attribut schlafend zu setzen
     * @param schlafend auf welchen das Attribut gesetzt werden soll
     */
    public void setSchlafend(boolean schlafend)
    {
        this.schlafend = schlafend;
    }

    /**
     * Getter Methode um den Wert des Attributes schlafend zu erhalten
     * @return gibt den Wert des Attributes schlafend wieder
     */
    public boolean getFreundlich()
    {
        return freundlich;
    }

    /**
     * Setter Methode um das Attribut freundlich zu setzen
     * @param freundlich auf welchen das Attribut gesetzt werden soll
     */
    public void setFreundlich(boolean freundlich)
    {
        this.freundlich = freundlich;
    }

    /**
     * methdoe welche es der Karte ermoeglicht Schaden zu nehmen
     * @param schaden welcher von den Lebenspunkten abgezogen werden soll
     */
    public void schadenNehmen(int schaden)
    {
        this.lebenspunkte = lebenspunkte - schaden;
    }

    /**
     * speichert die im Kampfgeschehen veraenderbaren Startwerte einer Karte
     */
    public void startwertespeichern()
    {
        this.init = new InitialisierungKarteEinheit();
        this.init.setMacht(macht);
        this.init.setLebenspunkte(lebenspunkte);
        this.init.setBeweglichkeit(beweglichkeit);
        this.init.setReichweite(reichweite);
        this.init.setSchild(schild);
        this.init.setVerteidigung(verteidigung);
        position = null;
        schlafend = true;
    }

    /**
     * gibt einer Karte ihre Initialwerte zurueck
     */
    public void initialisieren()
    {
        this.macht = init.getMacht();
        this.lebenspunkte = init.getLebenspunkte();
        this.beweglichkeit = init.getBeweglichkeit();
        this.reichweite = init.getReichweite();
        this.schild = init.getSchild();
        this.verteidigung = init.getVerteidigung();
        this.position = init.getPosition();
        this.schlafend = init.getSchlafend();
    }

    /**
     * Getter Methode um den Wert des Attributes init zu erhalten
     * @return gibt den Wert des Attributes init wieder
     */
    public InitialisierungKarteEinheit getInit()
    {
        return init;
    }

    /**
     * Setter Methode um das Attribut init zu setzen
     * @param init auf welchen das Attribut gesetzt werden soll
     */
    public void setInit(InitialisierungKarteEinheit init)
    {
        this.init = init;
    }
}

