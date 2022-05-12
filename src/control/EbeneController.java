package control;

import model.Ebene;
import model.Raeume;
import model.ereignisse.Gegner;
import model.ereignisse.Treppe;

public class EbeneController
{
    public static Ebene fuelleEbene(int stufe)
    {
        int spalten = 5;
        int zeilen = 5;
        Raeume[][] meineRaeume = new Raeume[spalten][zeilen];
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
                meineRaeume[0][1]= Gegner1 ;
                meineRaeume[0][3]= Boss;
                meineRaeume[0][4]= Treppe1;
                meineRaeume[1][2]= Gegner2;

            case 2 : //3443;

            case 3 : ;

            case 4 : ;

            case 5 : ;

            case 6 : ;

            case 7 : ;
        }
        return new Ebene(zeilen, spalten, meineRaeume);
    }
}
