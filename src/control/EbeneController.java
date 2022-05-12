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
        switch (EbenenStufe)
        {
            case ZAHL_1:
                Gegner Platzhalter10 = new Gegner("Gegner", "Dies ist eine Beispielbeschreibung");
                Raeume Gegner10 = new Raeume(Platzhalter10);
                Gegner Platzhalter11 = new Gegner("Gegner", "Dies ist Gegner");
                Raeume Gegner11 = new Raeume(Platzhalter11);
                Gegner BossErstesLevel = new Gegner("Gegner der Unschlagbare","Dies ist der Boss der ersten Ebene");
                Raeume Boss = new Raeume(BossErstesLevel);
                Treppe TreppeErsteEbene = new Treppe("Treppe der ersten Ebene","Dies ist die Treppe der Ersten Ebene");
                Raeume Treppe1 = new Raeume (TreppeErsteEbene);
                meineRaeume[0][1]= Gegner10;
                meineRaeume[0][3]= Boss;
                meineRaeume[0][4]= Treppe1;
                meineRaeume[1][2]= Gegner11;
                break;

            case ZAHL_2:
                Gegner Platzhalter20 = new Gegner("", "");
                Gegner Platzhalter21 = new Gegner("", "");
                Gegner Platzhalter22 = new Gegner("","");
                Gegner Platzhalter23 = new Gegner("", "");
                Gegner Platzhalter24 = new Gegner("", "");
                Gegner BossZweitesLevel = new Gegner("", "");

            case ZAHL_3:
                Gegner Platzhalter30 = new Gegner("", "");
                Gegner Platzhalter31 = new Gegner("", "");
                Gegner Platzhalter32 = new Gegner("","");
                Gegner Platzhalter33 = new Gegner("", "");
                Gegner Platzhalter34 = new Gegner("", "");
                Gegner BossDrittesLevel = new Gegner("", "");


            case ZAHL_4:
                Gegner Platzhalter40 = new Gegner("", "");
                Gegner Platzhalter41 = new Gegner("", "");
                Gegner Platzhalter42 = new Gegner("","");
                Gegner Platzhalter43 = new Gegner("", "");
                Gegner Platzhalter44 = new Gegner("", "");
                Gegner BossViertesLevel = new Gegner("", "");

            case ZAHL_5:
                Gegner Platzhalter50 = new Gegner("", "");
                Gegner Platzhalter51 = new Gegner("", "");
                Gegner Platzhalter52 = new Gegner("","");
                Gegner Platzhalter53 = new Gegner("", "");
                Gegner Platzhalter54 = new Gegner("", "");
                Gegner BossFuenftesLevel = new Gegner("", "");

            case 6 : ;

            case 7 : ;
        }
        return new Ebene(zeilen, spalten, meineRaeume);
    }
}
