package model;

import exceptions.KartenDeckFehlerhaftException;
import io.EbeneIO;
import io.KartenDeckIO;

import java.io.File;
import java.io.IOException;

import static resources.Strings.*;

public class SpielStand
{
    private int gold;
    private int erfahrungspunkte;
    private Spieler spieler;
    private transient KartenDeck spieldeckSpieler;
    private transient KartenDeck spieldeckGegner;
    private transient Ebene aktuelleEbene;

    public SpielStand(int gold, int erfahrungspunkte, Spieler spieler) throws KartenDeckFehlerhaftException, IOException
    {
        this.gold = gold;
        this.erfahrungspunkte = erfahrungspunkte;
        this.spieler = spieler;
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene = EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));
    }

    public SpielStand (SpielStand stand) throws KartenDeckFehlerhaftException, IOException
    {
        this(stand.getGold(), stand.getErfahrungspunkte(), stand.getSpieler());
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.aktuelleEbene = EbeneIO.leseDatei(new File("AKTUELLE_EBENE_PFAD"));
    }

    public Ebene getAktuelleEbene()
    {
        return aktuelleEbene;
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public int getErfahrungspunkte()
    {
        return erfahrungspunkte;
    }

    public void setErfahrungspunkte(int erfahrungspunkte)
    {
        this.erfahrungspunkte = erfahrungspunkte;
    }

    public Spieler getSpieler()
    {
        return spieler;
    }

    public void setSpieler(Spieler spieler)
    {
        this.spieler = spieler;
    }

    public KartenDeck getSpieldeckSpieler()
    {
        return spieldeckSpieler;
    }

    public void setSpieldeckSpieler(KartenDeck spieldeckSpieler)
    {
        this.spieldeckSpieler = spieldeckSpieler;
    }

    public KartenDeck getSpieldeckGegner()
    {
        return spieldeckGegner;
    }

    public void setSpieldeckGegner(KartenDeck spieldeckGegner)
    {
        this.spieldeckGegner = spieldeckGegner;
    }
}
