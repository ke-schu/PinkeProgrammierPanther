package model;

import exceptions.JsonNichtLesbarException;
import io.EbeneIO;
import io.KartenDeckIO;

import java.io.File;

import static resources.Strings.*;

/**
 * Modelliert einen Spielstand, mit den abzuspeichernden Attributen
 */
public class SpielStand
{
    private int gold;
    private Spieler spieler;
    private transient KartenDeck spieldeckSpieler;
    private transient KartenDeck spieldeckGegner;
    private transient Ebene aktuelleEbene;
    private int lautstaerke = 10;
    private int aufloesungX = 1280;
    private int aufloesungY = 720;

    /**
     * Konstruiert einen neuen Spielstand mit den notwendigen Attributen
     * @param gold das Guthaben des Spielers
     * @param spieler der kaempfende Spieler
     * @throws JsonNichtLesbarException wenn die Kartendecks des Spielers
     * oder des Gegners Fehler enthalten
     */
    public SpielStand(int gold, Spieler spieler)
            throws JsonNichtLesbarException
    {
        this.gold = gold;
        this.spieler = spieler;
        this.spieldeckSpieler =
                KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene = EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));
    }

    /**
     * Konstruiert einen neuen Spielstand mit einem bestehenden Spielstand, um
     * die nicht abgespeicherten Kartendecks und Ebenen aus den anderen
     * json-Dateien in die Instanz zu binden.
     * @param stand der Spielstand
     * @throws JsonNichtLesbarException wenn die Kartendecks des Spielers
     * oder des Gegners Fehler enthalten
     */
    public SpielStand(SpielStand stand)
            throws JsonNichtLesbarException
    {
        this(stand.getGold(), stand.getSpieler());
        this.spieldeckSpieler =
                KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene = EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));
    }

    /**
     * Gibt die aktuelle Spielebene wieder
     * @return die Spielebene
     */
    public Ebene getAktuelleEbene()
    {
        return aktuelleEbene;
    }

    /**
     * Gibt das Gold des Spielstandes wieder
     * @return das Gold
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Setzt das Gold des Spielstandes auf einen neuen Wert
     * @param gold das neue Guthaben
     */
    public void setGold(int gold)
    {
        this.gold = gold;
    }

    /**
     * Gibt den Spieler wieder
     * @return der Spieler
     */
    public Spieler getSpieler()
    {
        return spieler;
    }

    /**
     * Setzt den Spieler des Spielstandes neu
     * @param spieler der Spieler
     */
    public void setSpieler(Spieler spieler)
    {
        this.spieler = spieler;
    }

    /**
     * Gibt das Spieldeck des Spielers wieder
     * @return das Kartendeck des Spielers
     */
    public KartenDeck getSpieldeckSpieler()
    {
        return spieldeckSpieler;
    }

    /**
     * Gibt das Spieldeck des Gegners wieder
     * @return das Kartendeck des Gegners
     */
    public KartenDeck getSpieldeckGegner()
    {
        return spieldeckGegner;
    }

    public int getLautstaerke()
    {
        return lautstaerke;
    }

    public void setLautstaerke(int lautstaerke)
    {
        this.lautstaerke = lautstaerke;
    }

    public int getAufloesungX()
    {
        return aufloesungX;
    }

    public void setAufloesungX(int aufloesungX)
    {
        this.aufloesungX = aufloesungX;
    }

    public int getAufloesungY()
    {
        return aufloesungY;
    }

    public void setAufloesungY(int aufloesungY)
    {
        this.aufloesungY = aufloesungY;
    }
}
