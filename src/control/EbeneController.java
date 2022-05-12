package control;

import model.Ebene;
import model.Raeume;
import model.ereignisse.Gegner;
import model.ereignisse.Treppe;

import static resources.StingsGegner.*;
import static resources.Zahlen.*;

public class EbeneController
{
    public static Ebene fuelleEbene(int EbenenStufe)
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
                Gegner platzhalter20 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter21 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter22 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter23 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter24 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner bossZweitesLevel = new Gegner(BOSS_ERSTES_LEVEL_NAME, BOSS_ERSTES_LEVEL_BESCHREIBUNG);
                Raeume gegner20 = new Raeume(platzhalter20);
                Raeume gegner21 = new Raeume(platzhalter21);
                Raeume gegner22 = new Raeume(platzhalter22);
                Raeume gegner23 = new Raeume(platzhalter23);
                Raeume gegner24 = new Raeume(platzhalter24);
                Raeume boss2 = new Raeume(bossZweitesLevel);

                meineRaeume[ZAHL_0][ZAHL_0] = gegner20;
                meineRaeume[ZAHL_0][ZAHL_1] = gegner21;
                meineRaeume[ZAHL_0][ZAHL_2] = gegner22;
                meineRaeume[ZAHL_0][ZAHL_3] = gegner23;
                meineRaeume[ZAHL_0][ZAHL_4] = gegner24;
                meineRaeume[ZAHL_0][ZAHL_5] = boss2;
                break;

            case ZAHL_3:
                Gegner platzhalter30 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter31 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter32 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter33 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter34 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner bossDrittesLevel = new Gegner(BOSS_DRITTES_LEVEL_NAME, BOSS_DRITTES_LEVEL_BESCHREIBUNG);
                Raeume gegner30 = new Raeume(platzhalter30);
                Raeume gegner31 = new Raeume(platzhalter31);
                Raeume gegner32 = new Raeume(platzhalter32);
                Raeume gegner33 = new Raeume(platzhalter33);
                Raeume gegner34 = new Raeume(platzhalter34);
                Raeume boss3 = new Raeume(bossDrittesLevel);

                meineRaeume[ZAHL_0][ZAHL_0] = gegner30;
                meineRaeume[ZAHL_0][ZAHL_1] = gegner31;
                meineRaeume[ZAHL_0][ZAHL_2] = gegner32;
                meineRaeume[ZAHL_0][ZAHL_3] = gegner33;
                meineRaeume[ZAHL_0][ZAHL_4] = gegner34;
                meineRaeume[ZAHL_0][ZAHL_5] = boss3;
                break;

            case ZAHL_4:
                Gegner platzhalter40 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter41 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter42 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter43 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter44 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner bossViertesLevel = new Gegner(BOSS_VIERTES_LEVEL_NAME, BOSS_VIERTES_LEVEL_BESCHREIBUNG);
                Raeume gegner40 = new Raeume(platzhalter40);
                Raeume gegner41 = new Raeume(platzhalter41);
                Raeume gegner42 = new Raeume(platzhalter42);
                Raeume gegner43 = new Raeume(platzhalter43);
                Raeume gegner44 = new Raeume(platzhalter44);
                Raeume boss4 = new Raeume(bossViertesLevel);

                meineRaeume[ZAHL_0][ZAHL_0] = gegner40;
                meineRaeume[ZAHL_0][ZAHL_1] = gegner41;
                meineRaeume[ZAHL_0][ZAHL_2] = gegner42;
                meineRaeume[ZAHL_0][ZAHL_3] = gegner43;
                meineRaeume[ZAHL_0][ZAHL_4] = gegner44;
                meineRaeume[ZAHL_0][ZAHL_5] = boss4;
                break;

            case ZAHL_5:
                Gegner platzhalter50 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter51 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter52 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter53 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter54 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner bossFuenftesLevel = new Gegner(BOSS_FUENFTES_LEVEL_NAME, BOSS_FUENFTES_LEVEL_BESCHREIBUNG);
                Raeume gegner50 = new Raeume(platzhalter50);
                Raeume gegner51 = new Raeume(platzhalter51);
                Raeume gegner52 = new Raeume(platzhalter52);
                Raeume gegner53 = new Raeume(platzhalter53);
                Raeume gegner54 = new Raeume(platzhalter54);
                Raeume boss5 = new Raeume(bossFuenftesLevel);

                meineRaeume[ZAHL_0][ZAHL_0] = gegner50;
                meineRaeume[ZAHL_0][ZAHL_1] = gegner51;
                meineRaeume[ZAHL_0][ZAHL_2] = gegner52;
                meineRaeume[ZAHL_0][ZAHL_3] = gegner53;
                meineRaeume[ZAHL_0][ZAHL_4] = gegner54;
                meineRaeume[ZAHL_0][ZAHL_5] = boss5;
                break;

            case ZAHL_6:
                Gegner platzhalter60 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter61 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter62 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter63 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter64 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner bossSechstesLevel = new Gegner(BOSS_SECHSTES_LEVEL_NAME, BOSS_SECHSTES_LEVEL_BESCHREIBUNG);
                Raeume gegner60 = new Raeume(platzhalter60);
                Raeume gegner61 = new Raeume(platzhalter61);
                Raeume gegner62 = new Raeume(platzhalter62);
                Raeume gegner63 = new Raeume(platzhalter63);
                Raeume gegner64 = new Raeume(platzhalter64);
                Raeume boss6 = new Raeume(bossSechstesLevel);

                meineRaeume[ZAHL_0][ZAHL_0] = gegner60;
                meineRaeume[ZAHL_0][ZAHL_1] = gegner61;
                meineRaeume[ZAHL_0][ZAHL_2] = gegner62;
                meineRaeume[ZAHL_0][ZAHL_3] = gegner63;
                meineRaeume[ZAHL_0][ZAHL_4] = gegner64;
                meineRaeume[ZAHL_0][ZAHL_5] = boss6;
                break;

            case ZAHL_7:
                Gegner platzhalter70 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter71 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter72 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter73 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner platzhalter74 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG);
                Gegner bossSiebtesLevel = new Gegner(BOSS_SIEBTES_LEVEL_NAME, BOSS_SIEBTES_LEVEL_BESCHREIBUNG);
                Raeume gegner70 = new Raeume(platzhalter70);
                Raeume gegner71 = new Raeume(platzhalter71);
                Raeume gegner72 = new Raeume(platzhalter72);
                Raeume gegner73 = new Raeume(platzhalter73);
                Raeume gegner74 = new Raeume(platzhalter74);
                Raeume boss7 = new Raeume(bossSiebtesLevel);

                meineRaeume[ZAHL_0][ZAHL_0] = gegner70;
                meineRaeume[ZAHL_0][ZAHL_1] = gegner71;
                meineRaeume[ZAHL_0][ZAHL_2] = gegner72;
                meineRaeume[ZAHL_0][ZAHL_3] = gegner73;
                meineRaeume[ZAHL_0][ZAHL_4] = gegner74;
                meineRaeume[ZAHL_0][ZAHL_5] = boss7;
                break;
        }

        return new Ebene(zeilen, spalten, meineRaeume);
    }
}
