package model;

import exceptions.KartenDeckFehlerhaftException;
import io.EbeneIO;
import io.KartenDeckIO;

import java.io.File;
import java.io.IOException;

import static resources.Strings.*;

/**
 * Modelliert einen Spielstand, mit den abzuspeichernden Attributen
 */
public class SpielStand
{
    private int gold;
    private int erfahrungspunkte;
    private Spieler spieler;
    private transient KartenDeck spieldeckSpieler;
    private transient KartenDeck spieldeckGegner;
    private transient Ebene aktuelleEbene;

    /**
     * Konstruiert einen neuen Spielstand mit den notwendigen Attributen
     * @param gold das Guthaben des Spielers
     * @param erfahrungspunkte die gesammelten Erfahrungspunkte
     * @param spieler der kämpfende Spieler
     * @throws KartenDeckFehlerhaftException wenn die Kartendecks des Spielers oder des Gegners Fehler enthalten
     * @throws IOException wenn die Spielstanddatei nur falsch oder gar nicht gelesen werden kann
     */
    public SpielStand(int gold, int erfahrungspunkte, Spieler spieler) throws KartenDeckFehlerhaftException, IOException
    {
        this.gold = gold;
        this.erfahrungspunkte = erfahrungspunkte;
        this.spieler = spieler;
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene = EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));
    }

    /**
     * Konstruiert einen neuen Spielstand mit einem bestehenden Spielstand, um die nicht abgespeicherten
     * Kartendecks und Ebenen aus den anderen json-Dateien in die Instanz zu binden.
     * @param stand der Spielstand
     * @throws KartenDeckFehlerhaftException wenn die Kartendecks des Spielers oder des Gegners Fehler enthalten
     * @throws IOException wenn die Spielstanddatei nur falsch oder gar nicht gelesen werden kann
     */
    public SpielStand (SpielStand stand) throws KartenDeckFehlerhaftException, IOException
    {
        this(stand.getGold(), stand.getErfahrungspunkte(), stand.getSpieler());
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
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
     * Gibt die Erfahrungspunkte des Spielstandes wieder
     * @return die Erfahrungspunkte
     */
    public int getErfahrungspunkte()
    {
        return erfahrungspunkte;
    }

    /**
     * Setzt die Erfahrungspunkte des Spielstandes auf einen neuen Wert
     * @param erfahrungspunkte die neuen Erfahrungspunkte
     */
    public void setErfahrungspunkte(int erfahrungspunkte)
    {
        this.erfahrungspunkte = erfahrungspunkte;
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
}
