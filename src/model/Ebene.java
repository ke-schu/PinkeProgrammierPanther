package model;

public class Ebene extends Level
{
    private Raeume[][] ebene = null;
    private int ebenenSpalte = 0;
    private int ebenenZeile = 0;

    public Ebene (int ebenenZeile, int ebenenSpalte)
    {
        this.setEbene(new Raeume[ebenenZeile][ebenenSpalte]);
        this.setEbenenZeile(ebenenZeile);
        this.setEbenenSpalte(ebenenSpalte);
    }

    public Raeume[][] getEbene()
    {
        return this.ebene;
    }

    public final void setEbene(Raeume[][] ebene)
    {
        this.ebene = ebene;
    }


    public int getEbenenZeile()
    {
        return this.ebenenZeile;
    }

    public final void setEbenenZeile(int ebenenZeile)
    {
        this.ebenenZeile=ebenenZeile;
    }


    public int getEbenenSpalte()
    {
        return this.ebenenSpalte;
    }

    public final void setEbenenSpalte(int ebenenSpalte)
    {
        this.ebenenSpalte=ebenenSpalte;
    }


    public void raumereinsetzen(int spalte, int zeile, Raeume raum)
    {
        this.ebene[zeile][spalte] = raum;
    }

    public void raumloeschen (int spalte, int zeile)
    {
        this.ebene[zeile][spalte] = null;
    }

    public Raeume getRaumAnPosition(int spalte, int zeile)
    {
        return this.ebene[zeile][spalte];
    }
}

