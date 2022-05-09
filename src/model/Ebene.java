package model;

//import model.ereignisse.Ereignis;
import model.ereignisse.*;

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


    public void raumEinsetzen(int spalte, int zeile, Raeume raum)
    {
        this.ebene[zeile][spalte] = raum;
    }

    public void raumLoeschen (int spalte, int zeile)
    {
        this.ebene[zeile][spalte] = null;
    }

    public Raeume getRaumAnPosition(int spalte, int zeile)
    {
        return this.ebene[zeile][spalte];
    }

    private void erstelleEbene(int stufe)
    {
        switch (stufe)
        {
            case 1 :
                Gegner Platzhalter1 = new Gegner("Babo", "Dies ist eine Beispielbeschreibung");
                Raeume Gegner1 = new Raeume(Platzhalter1);
                Gegner Platzhalter2 = new Gegner("Alex", "Dies ist Alex");
                Raeume Gegner2 = new Raeume(Platzhalter2);
                Gegner BossErstesLevel = new Gegner("Keno der Unschlagbare","Dies ist der Boss der ersten Ebene");
                Raeume Boss = new Raeume(BossErstesLevel);
                Treppe TreppeErsteEbene = new Treppe("Treppe der ersten Ebene","Dies ist die Treppe der Ersten Ebene");
                Raeume Treppe1 = new Raeume (TreppeErsteEbene);
                this.ebene[0][1]= Gegner1 ;
                this.ebene[0][3]= Boss;
                this.ebene[0][4]= Treppe1;
                this.ebene[1][2]= Gegner2;




            case 2 : //3443;

            case 3 : ;

            case 4 : ;

            case 5 : ;

            case 6 : ;

            case 7 : ;
        }
    }
}

