package model;

import utility.UtilityController;

import java.util.Random;

import static resources.Konstanten.*;
import static resources.Strings.*;

/**
 * SpielFeld repraesentiert das Spielfeld auf welchem sich Karten befinden
 */
public class SpielFeld
{

    private KarteEinheit[][] spielfeld = null;
    private int zeilen = 0;
    private int spalten = 0;

    /**
     * Konstruktor der durch den Aufruf der Methode geniereSpielfeldGroesse
     * eine Instanz mit einer zufaelligen Spielfeldgroesse
     * erzeugt.
     */
    public SpielFeld()
    {
        this.spalten = UtilityController.randomzahlmitbereich(SPIELFELD_GENERATOR_MIN, SPIELFELD_GENERATOR_MAX);
        this.zeilen = UtilityController.randomzahlmitbereich(SPIELFELD_GENERATOR_MIN, SPIELFELD_GENERATOR_ZEILEN_MAX);
        KarteEinheit[][] spielfeld = new KarteEinheit[this.zeilen][this.spalten];
        this.spielfeld = spielfeld;
    }

    /**
     * Konstruktor der mit den uebergebenen Parametern eine Instanz erzeugt.
     * @param x Spaltenanzahl des Spielfeldes.
     * @param y Zeilenanzahl des Spielfeldes.
     */
    public SpielFeld(int x, int y)
    {
        this.spalten = x;
        this.zeilen = y;
        KarteEinheit[][] spielfeld =
                new KarteEinheit[this.zeilen][this.spalten];
        this.spielfeld = spielfeld;
    }


    /**
     * Methode, die die Namen der Instanzen der Klasse KarteEinheit aus dem
     * Attribut spielfeld in der Konsole formatiert ausgibt.
     * @return gibt einen String der die Matrix des Attributes ebene spielfeld
     * wieder.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.zeilen; i++)
        {
            for (int j = 0; j < this.spalten; j++)
            {
                if (this.spielfeld[i][j] != null)
                {
                    sb.append(this.spielfeld[i][j].getName());
                }
                else
                {
                    sb.append(SPIELFELD_LEERES_FELD);
                }
                sb.append(LEERZEICHEN);
                sb.append(SENKRECHTER_STRICH);
                sb.append(LEERZEICHEN);
            }
            sb.append(ZEILENUMBRUCH);
        }
        return sb.toString();
    }

    /**
     * Methode die einem den Wert des Attributes spielfeld gibt.
     * @return gibt einen 2D-Array mit Instanzen der Klasse KarteEinheit.
     */
    public KarteEinheit[][] getSpielfeld()
    {
        return this.spielfeld;
    }

    /**
     * Methode die ein 2D-Array mit Instanzen der Klasse KarteEinheit in das
     * Attribut spielfeld setzt.
     * @param spielfeld 2D-Array mit Instanzen der Klasse KarteEinheit der in
     * das Attribut spielfeld gesetzt werden soll.
     */
    public final void setSpielfeld(KarteEinheit[][] spielfeld)
    {
        this.spielfeld = spielfeld;
    }

    /**
     * Methode die den Int-Wert des Attributes feldSpalte liefert.
     * @return gibt den Int-Wert des Attributes feldSpalte wieder.
     */
    public int getSpalten()
    {
        return this.spalten;
    }

    /**
     * Methode die den Int-Wert des Attributes feldZeile liefert.
     * @return gibt den Int-Wert des Attributes feldSpalte wieder.
     */
    public int getZeilen()
    {
        return this.zeilen;
    }

    /**
     * Methode die eine Instanz der Klasse KarteEinheit an einer bestimmten
     * Position im 2D-Array des Attributes spielfeld einsetzt.
     * @param x X-Koordinate als Int-Wert, an welcher die Instanz der Klasse
     * KarteEinheit eingesetzt werden soll.
     * @param y Y-Koordinate als Int-Wert, an welcher die Instanz der Klasse
     * KarteEinheit eingesetzt werden soll.
     * @param einheit Instanz der Klasse KarteEinheit, welche in das Spielfeld
     * eingesetzt werden soll.
     */
    public void einheitEinsetzten(int x, int y, KarteEinheit einheit)
    {
        if (einheit == null)
        {
            this.spielfeld[y][x] = einheit;
        } else
        {
            this.spielfeld[y][x] = einheit;
            einheit.setPosition(x, y);
        }

    }

    /**
     * Diese Methode gibt den Inhalt des Attributes spielfeld an einer
     * spezifischen Position wieder.
     * @param x X-Koordinate an welcher der Inhalt des Attributes spielfeld
     * wieder gegeben werden soll.
     * @param y Y-Koordinate an welcher der Inhalt des Attributes spielfeld
     * wieder gegeben werden soll.
     * @return Gibt den Inhalt des Attributes spielfeld an einer bestimmten
     * Stelle wieder.
     */
    public KarteEinheit getSpielfeldplatz(int x, int y)
    {
        if (x < 0 || x >= this.spalten || y < 0 || y >= this.zeilen)
        {
            return null;
        }
        return this.spielfeld[y][x];
    }

    /**
     * Diese Methode setzt ein einer spezifischen Stelle im 2D-Array des
     * Attributes spielfeld ein `null` Literal ein.
     * @param x X-Koordiante an welcher das `null` Literal eingesetzt werden
     * soll.
     * @param y Y-Koordiante an welcher das `null` Literal eingesetzt werden
     * soll.
     */
    public void einheitloeschen(int x, int y)
    {
        this.spielfeld[y][x] = null;
    }
}