package model;

import exceptions.JsonNichtLesbarException;
import utility.EbeneIO;
import utility.KartenDeckIO;

import java.io.File;

import static resources.Strings.*;

/**
 * Modelliert einen Spielstand, mit den abzuspeichernden Attributen.
 */
public class SpielStand
{
    private int gold;
    private Spieler spieler;
    private Gegenspieler gegenSpieler;
    private transient KartenDeck spieldeckSpieler;
    private transient KartenDeck spieldeckGegner;
    private transient Ebene aktuelleEbene;
    private int aktuelleEbeneNummer = 1;
    private double lautstaerkeMusik = 10;
    private double laustaerkeEffekte = 10;
    private int aufloesungX = 1280;
    private int aufloesungY = 720;

    /**
     * Konstruiert einen neuen Spielstand mit den notwendigen Attributen.
     *
     * @param gold    das Guthaben des Spielers.
     * @param spieler der kaempfende Spieler.
     * @throws JsonNichtLesbarException wenn die Kartendecks des Spielers
     *                                  oder des Gegners Fehler enthalten.
     */
    public SpielStand(int gold, Spieler spieler, Gegenspieler gegenSpieler) throws JsonNichtLesbarException
    {
        this.gold             = gold;
        this.spieler          = spieler;
        this.gegenSpieler     = gegenSpieler;
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner  = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene    =
                EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));
    }

    /**
     * Konstruiert einen neuen Spielstand mit einem bestehenden Spielstand, um
     * die nicht abgespeicherten Kartendecks und Ebenen aus den anderen
     * json-Dateien in die Instanz zu binden.
     *
     * @param stand der Spielstand.
     * @throws JsonNichtLesbarException wenn die Kartendecks des Spielers
     *                                  oder des Gegners Fehler enthalten.
     */
    public SpielStand(SpielStand stand) throws JsonNichtLesbarException
    {
        this(stand.getGold(), stand.getSpieler(), stand.getGegenSpieler());
        this.lautstaerkeMusik  = stand.getLautstaerkeMusik();
        this.laustaerkeEffekte = stand.getLaustaerkeEffekte();
        this.aktuelleEbeneNummer = stand.getAktuelleEbeneNummer();
        this.spieldeckSpieler  =
                KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner   = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene     =
                EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));
    }

    /**
     * Gibt die aktuelle Spielebene wieder.
     *
     * @return die Spielebene.
     */
    public Ebene getAktuelleEbene()
    {
        return aktuelleEbene;
    }

    /**
     * Gibt das Gold des Spielstandes wieder.
     *
     * @return das Gold.
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Setzt das Gold des Spielstandes auf einen neuen Wert.
     *
     * @param gold das neue Guthaben.
     */
    public void setGold(int gold)
    {
        this.gold = gold;
    }

    /**
     * Gibt den Spieler wieder.
     *
     * @return der Spieler.
     */
    public Spieler getSpieler()
    {
        return spieler;
    }

    /**
     * Setzt den Spieler des Spielstandes neu.
     *
     * @param spieler der Spieler.
     */
    public void setSpieler(Spieler spieler)
    {
        this.spieler = spieler;
    }

    public Gegenspieler getGegenSpieler()
    {
        return gegenSpieler;
    }

    public void setGegenSpieler(Gegenspieler gegenSpieler)
    {
        this.gegenSpieler = gegenSpieler;
    }

    public int getAktuelleEbeneNummer()
    {
        return aktuelleEbeneNummer;
    }

    public void setAktuelleEbeneNummer(int aktuelleEbeneNummer)
    {
        this.aktuelleEbeneNummer = aktuelleEbeneNummer;
    }

    /**
     * Gibt das Spieldeck des Spielers wieder.
     *
     * @return das Kartendeck des Spielers.
     */
    public KartenDeck getSpieldeckSpieler()
    {
        return spieldeckSpieler;
    }

    /**
     * Gibt das Spieldeck des Gegners wieder.
     *
     * @return das Kartendeck des Gegners.
     */
    public KartenDeck getSpieldeckGegner()
    {
        return spieldeckGegner;
    }

    /**
     * Liefert den double des Attributes lautstaerkeMusik.
     *
     * @return gibt den double des Attributes lautstaerkeMusik wieder.
     */
    public double getLautstaerkeMusik()
    {
        return lautstaerkeMusik;
    }

    /**
     * Setzt einen double in das Attribut lautstaerkeMusik.
     *
     * @param neueLautstaerke double, der in das Attribut lautstaerkeMusik
     *                        eingesetzt wird.
     */
    public void setLautstaerkeMusik(double neueLautstaerke)
    {
        this.lautstaerkeMusik = neueLautstaerke;
    }

    /**
     * Liefert den int des Attributes aufloesungX.
     *
     * @return gibt den int des Attributes aufloesungX.
     */
    public int getAufloesungX()
    {
        return this.aufloesungX;
    }

    /**
     * Setzt einen int in das Attribut aufloesungX.
     *
     * @param neueAufloesungX der int, welcher in das Attribut aufloesungX
     *                        gesetzt werden soll.
     */
    public void setAufloesungX(int neueAufloesungX)
    {
        this.aufloesungX = neueAufloesungX;
    }

    /**
     * Liefert den int des Attributes aufloesungY.
     *
     * @return gibt den int des Attributes aufloesungY.
     */
    public int getAufloesungY()
    {
        return this.aufloesungY;
    }

    /**
     * Setzt einen int in das Attribut aufloesungY.
     *
     * @param neueAufloesungY der int, welcher in das Attribut aufloesungY
     *                        gesetzt werden soll.
     */
    public void setAufloesungY(int neueAufloesungY)
    {
        this.aufloesungY = neueAufloesungY;
    }

    /**
     * Liefert den double des Attributes lautstaerkeMusik.
     *
     * @return gibt den double des Attributes lautstaerkeMusik wieder.
     */
    public double getLaustaerkeEffekte()
    {
        return laustaerkeEffekte;
    }

    /**
     * Setzt einen double in das Attribut laustaerkeEffekte.
     *
     * @param laustaerkeEffekte double, der in das Attribut laustaerkeEffekte
     *                          eingesetzt wird.
     */
    public void setLaustaerkeEffekte(double laustaerkeEffekte)
    {
        this.laustaerkeEffekte = laustaerkeEffekte;
    }
}
