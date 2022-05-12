package model;

import static resources.Zahlen.*;

import java.util.Random;

public class SpielFeld
{

    private KarteEinheit[][] spielfeld = null;
    private int feldZeile = 0;
    private int feldSpalte = 0;


    public SpielFeld() //throws SpielfeldDimensionGleichNullException, SpielfeldNichtQuadratischException
    {
        /*
        if (feldSpalte <= 0 && feldZeile <= 0)
        {
            throw new SpielfeldDimensionGleichNullException();
        }
        //Exception für ein Null Spielfeld
        */
        /* Spielfelder können jetzt Rechtecke sein! 4x5 4x6 6x5,
        if (feldSpalte != feldZeile)
        {
            throw new SpielfeldNichtQuadratischException();
        }
        //Exception für ein nicht quadratisches Spielfeld
        */
        this.feldSpalte = generiereSpielfeldGroesse();
        this.feldZeile = generiereSpielfeldGroesse();
        KarteEinheit[][] spielfeld = new KarteEinheit[this.feldZeile][this.feldSpalte];
        this.spielfeld = spielfeld;

    }

    private static int generiereSpielfeldGroesse()
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


    public KarteEinheit[][] getSpielfeld()
    {
        return this.spielfeld;
    }

    public final void setSpielfeld(KarteEinheit[][] spielfeld)
    {
        this.spielfeld = spielfeld;
    }
    //get- und set-Methoden für das Spielfeld


    public int getFeldSpalte()
    {
        return this.feldSpalte;
    }


    public int getFeldZeile ()
    {
        return this.feldZeile;
    }


    public void einheiteinsetzten (int x, int y, KarteEinheit einheit )
    {
        this.spielfeld[x][y] = einheit;
    }

    public KarteEinheit getSpielfeldplatz (int x, int y)
    {
        return this.spielfeld[x][y];
    }

    public void einheitloeschen (int x, int y)
    {
        this.spielfeld[x][y] = null;
    }
}