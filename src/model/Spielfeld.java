package model;

import exceptions.*;

public class Spielfeld extends Level
{

    private Einheit[][] spielfeld = null;
    private int feldZeile = 0;
    private int feldSpalte = 0;


    public Spielfeld (int feldZeile, int feldSpalte) throws SpielfeldDimensionGleichNullException, SpielfeldNichtQuadratischException
    {
        if (feldSpalte <= 0 && feldZeile <= 0)
        {
            throw new SpielfeldDimensionGleichNullException();
        }
        //Exception für ein Null Spielfeld

        if (feldSpalte != feldZeile)
        {
            throw new SpielfeldNichtQuadratischException();
        }
        //Exception für ein nicht quadratisches Spielfeld

        this.setSpielfeld(new Einheit[feldZeile][feldSpalte]);
        this.setFeldZeile(feldZeile);
        this.setFeldSpalte(feldSpalte);

    }



    public Einheit[][] getSpielfeld()
    {
        return this.spielfeld;
    }

    public final void setSpielfeld(Einheit[][] spielfeld)
    {
        this.spielfeld = spielfeld;
    }
    //get- und set-Methoden für das Spielfeld


    public int getFeldSpalte()
    {
        return this.feldSpalte;
    }

    public final void setFeldSpalte(int feldSpalte)
    {
        this.feldSpalte = feldSpalte;
    }
    //get- und set-Methoden für die FeldSpalte

    public int getFeldZeile ()
    {
        return this.feldZeile;
    }

    public final void setFeldZeile (int feldZeile)
    {
        this.feldZeile = feldZeile;
    }
    //get- und set-Methoden für die FeldZeile

    public void einheiteinsetzten (int x, int y, Einheit einheit )
    {
        this.spielfeld[x][y] = einheit;
    }

    public Einheit getSpielfeldplatz (int x, int y)
    {
        return this.spielfeld[x][y];
    }

    public void einheitloeschen (int x, int y)
    {
        this.spielfeld[x][y] = null;
    }
}