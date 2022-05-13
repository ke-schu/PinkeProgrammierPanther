package model;

import static resources.Strings.*;
import static resources.Zahlen.*;
import java.util.Random;

public class SpielFeld
{

    private KarteEinheit[][] spielfeld = null;
    private int feldZeile = 0;
    private int feldSpalte = 0;


    public SpielFeld() //throws SpielfeldDimensionGleichNullException, SpielfeldNichtQuadratischException
    {
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

    /**
     * Methode, die die Namen Instanzen der Klasse KarteEinheit aus dem Attribut spielfeld
     * in der Konsole formatiert ausgibt.
     * @return gibt einen String der die Matrix des Attributes ebene spielfeld wieder.
     */
    @Override
    public String toString()
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

    public KarteEinheit[][] getSpielfeld()
    {
        return this.spielfeld;
    }

    public final void setSpielfeld(KarteEinheit[][] spielfeld)
    {
        this.spielfeld = spielfeld;
    }

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
        if (einheit == null)
        {
            this.spielfeld[x][y] = einheit;
        }
        else
        {
            this.spielfeld[x][y] = einheit;
            einheit.setPosition(x,y);
        }

    }

    public KarteEinheit getSpielfeldplatz (int x, int y)
    {
        if(x<0 || x>=this.feldZeile || y<0 || y>=this.feldSpalte) {
            return null;
        }
        return this.spielfeld[x][y];
    }

    public void einheitloeschen (int x, int y)
    {
        this.spielfeld[x][y] = null;
    }
}