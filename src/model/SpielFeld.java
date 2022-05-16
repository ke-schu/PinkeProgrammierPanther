package model;

import java.util.Random;

import static resources.Strings.*;
import static resources.Zahlen.*;

/**
 * SpielFeld repraesentiert das Spielfeld auf welchem sich Karten befinden
 */
public class SpielFeld
{

    private KarteEinheit[][] spielfeld = null;
    private int feldZeile = ZAHL_0;
    private int feldSpalte = ZAHL_0;

    /**
     * Konstruktor der durch den Aufruf der Methode geniereSpielfeldGroesse eine Instanz mit
     * einer zufaelligen Spielfeldgroesse zwischen 4 und 6 erzeugt.
     */
    public SpielFeld ()
    {
        this.feldSpalte = generiereSpielfeldGroesse();
        this.feldZeile = generiereSpielfeldGroesse();
        KarteEinheit[][] spielfeld = new KarteEinheit[this.feldZeile][this.feldSpalte];
        this.spielfeld = spielfeld;
    }

    /**
     * Konstruktor der mit den uebergebenen Parametern eine Instanz erzeugt.
     * @param x Spaltenanzahl des Spielfeldes.
     * @param y Zeilenanzahl des Spielfeldes.
     */
    public SpielFeld (int x, int y)
    {
        this.feldSpalte = x;
        this.feldZeile = y;
        KarteEinheit[][] spielfeld = new KarteEinheit[this.feldZeile][this.feldSpalte];
        this.spielfeld = spielfeld;
    }

    /**
     * Methode die eine zufaellige Zahl zwischen 4 und 6 generiert.
     * @return Gibt einen Int-Wert zwischen 4 und 6.
     */
    private static int generiereSpielfeldGroesse ()
    {
        int spielfeldGroesse = ZAHL_0;
        int i = ZAHL_0;
        Random zufall = new Random();
        i = zufall.nextInt(ZAHL_3);
        if (i == ZAHL_0)
        {
            spielfeldGroesse = ZAHL_4;
        }
        else if ( i == ZAHL_1)
        {
            spielfeldGroesse = ZAHL_5;
        }
        else
        {
            spielfeldGroesse = ZAHL_6;
        }
        return spielfeldGroesse;
    }

    /**
     * Methode, die die Namen der Instanzen der Klasse KarteEinheit aus dem Attribut spielfeld
     * in der Konsole formatiert ausgibt.
     * @return gibt einen String der die Matrix des Attributes ebene spielfeld wieder.
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.feldZeile; i++)
        {
            for (int j = 0; j < this.feldSpalte; j++)
            {
                if (this.spielfeld[i][j] != null)
                {
                    sb.append(this.spielfeld[i][j].getName());
                    sb.append(LEERZEICHEN);
                    sb.append(SENKRECHTER_STRICH);
                    sb.append(LEERZEICHEN);
                }
                else
                {
                    sb.append(ZAHL_0);
                    sb.append(LEERZEICHEN);
                    sb.append(SENKRECHTER_STRICH);
                    sb.append(LEERZEICHEN);
                }

            }
            sb.append(ZEILENUMBRUCH);
        }
        return sb.toString();
    }

    /**
     * Methode die einem den Wert des Attributes spielfeld gibt.
     * @return gibt einen 2D-Array mit Instanzen der Klasse KarteEinheit.
     */
    public KarteEinheit[][] getSpielfeld ()
    {
        return this.spielfeld;
    }

    /**
     * Methode die ein 2D-Array mit Instanzen der Klasse KarteEinheit in das Attribut spielfeld setzt.
     * @param spielfeld 2D-Array mit Instanzen der Klasse KarteEinheit der in das Attribut spielfeld gesetzt werden soll.
     */
    public final void setSpielfeld (KarteEinheit[][] spielfeld)
    {
        this.spielfeld = spielfeld;
    }

    /**
     * Methode die den Int-Wert des Attributes feldSpalte liefert.
     * @return gibt den Int-Wert des Attributes feldSpalte wieder.
     */
    public int getFeldSpalte ()
    {
        return this.feldSpalte;
    }

    /**
     * Methode die den Int-Wert des Attributes feldZeile liefert.
     * @return gibt den Int-Wert des Attributes feldSpalte wieder.
     */
    public int getFeldZeile ()
    {
        return this.feldZeile;
    }

    /**
     * Methode die eine Instanz der Klasse KarteEinheit an einer bestimmten Position
     * im 2D-Array des Attributes spielfeld einsetzt.
     * @param x X-Koordinate als Int-Wert, an welcher die Instanz der Klasse KarteEinheit eingesetzt werden soll.
     * @param y Y-Koordinate als Int-Wert, an welcher die Instanz der Klasse KarteEinheit eingesetzt werden soll.
     * @param einheit Instanz der Klasse KarteEinheit, welche in das Spielfeld eingesetzt werden soll.
     */
    public void einheitEinsetzten (int x, int y, KarteEinheit einheit )
    {
        if (einheit == null)
        {
            this.spielfeld[y][x] = einheit;
        }
        else
        {
            this.spielfeld[y][x] = einheit;
            einheit.setPosition(x,y);
        }

    }

    /**
     * Diese Methode gibt den Inhalt des Attributes spielfeld an einer spezifischen Position wieder.
     * @param x X-Koordinate an welcher der Inhalt des Attributes spielfeld wieder gegeben werden soll.
     * @param y Y-Koordinate an welcher der Inhalt des Attributes spielfeld wieder gegeben werden soll.
     * @return Gibt den Inhalt des Attributes spielfeld an einer bestimmten Stelle wieder.
     */
    public KarteEinheit getSpielfeldplatz (int x, int y)
    {
        if(x<0 || x>=this.feldSpalte || y<0 || y>=this.feldZeile)
        {
            return null;
        }
        return this.spielfeld[y][x];
    }

    /**
     * Diese Methode setzt ein einer spezifischen Stelle im 2D-Array des Attributes spielfeld ein `null` Literal ein.
     * @param x X-Koordiante an welcher das `null` Literal eingesetzt werden soll.
     * @param y Y-Koordiante an welcher das `null` Literal eingesetzt werden soll.
     */
    public void einheitloeschen (int x, int y)
    {
        this.spielfeld[y][x] = null;
    }
}