package control;

import model.Ebene;
import model.Gegenspieler;
import model.Raum;
import model.SpielFeld;
import model.ereignisse.Gegner;
import resources.Effekte;
import resources.Einheiten;
import static resources.StringsGegner.*;
import static resources.Zahlen.*;

public class EbeneController
{
    private static Gegenspieler erstelleGegenspieler ()
    {
        return new Gegenspieler("Test_Gegner",
                10,
                Einheiten.FERNKAEMPFER,
                1,1,1,1,1,1,
                Effekte.LETZTEWORTE, Effekte.ZURUECKWERFEN,
                10);
    }
    
    /**
     * Methode die die Ebene mit vorbestimmten Raeumen fuellt.
     * @return liefert eine mit Raeumen gefuellte Ebene als 2D Array.
     */
    public static Ebene fuelleEbene (int EbenenStufe)
    {
        int spalten = ZAHL_9;
        int zeilen = ZAHL_9;
        Raum[][] meineRaum = new Raum[spalten][zeilen];
        switch (EbenenStufe)
        {
            case ZAHL_1:
                Gegner platzhalter10 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter11 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter12 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter13 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter14 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossErstesLevel = new Gegner(BOSS_ERSTES_LEVEL_NAME, BOSS_ERSTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner10 = new Raum(platzhalter10);
                Raum gegner11 = new Raum(platzhalter11);
                Raum gegner12 = new Raum(platzhalter12);
                Raum gegner13 = new Raum(platzhalter13);
                Raum gegner14 = new Raum(platzhalter14);
                Raum boss1 = new Raum(bossErstesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner10;
                meineRaum[ZAHL_0][ZAHL_1] = gegner11;
                meineRaum[ZAHL_0][ZAHL_2] = gegner12;
                meineRaum[ZAHL_0][ZAHL_3] = gegner13;
                meineRaum[ZAHL_0][ZAHL_4] = gegner14;
                meineRaum[ZAHL_0][ZAHL_5] = boss1;
                break;

            case ZAHL_2:
                Gegner platzhalter20 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter21 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter22 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter23 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter24 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossZweitesLevel = new Gegner(BOSS_ZWEITES_LEVEL_NAME, BOSS_ZWEITES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner20 = new Raum(platzhalter20);
                Raum gegner21 = new Raum(platzhalter21);
                Raum gegner22 = new Raum(platzhalter22);
                Raum gegner23 = new Raum(platzhalter23);
                Raum gegner24 = new Raum(platzhalter24);
                Raum boss2 = new Raum(bossZweitesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner20;
                meineRaum[ZAHL_0][ZAHL_1] = gegner21;
                meineRaum[ZAHL_0][ZAHL_2] = gegner22;
                meineRaum[ZAHL_0][ZAHL_3] = gegner23;
                meineRaum[ZAHL_0][ZAHL_4] = gegner24;
                meineRaum[ZAHL_0][ZAHL_5] = boss2;
                break;

            case ZAHL_3:
                Gegner platzhalter30 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter31 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter32 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter33 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter34 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossDrittesLevel = new Gegner(BOSS_DRITTES_LEVEL_NAME, BOSS_DRITTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner30 = new Raum(platzhalter30);
                Raum gegner31 = new Raum(platzhalter31);
                Raum gegner32 = new Raum(platzhalter32);
                Raum gegner33 = new Raum(platzhalter33);
                Raum gegner34 = new Raum(platzhalter34);
                Raum boss3 = new Raum(bossDrittesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner30;
                meineRaum[ZAHL_0][ZAHL_1] = gegner31;
                meineRaum[ZAHL_0][ZAHL_2] = gegner32;
                meineRaum[ZAHL_0][ZAHL_3] = gegner33;
                meineRaum[ZAHL_0][ZAHL_4] = gegner34;
                meineRaum[ZAHL_0][ZAHL_5] = boss3;
                break;

            case ZAHL_4:
                Gegner platzhalter40 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter41 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter42 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter43 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter44 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossViertesLevel = new Gegner(BOSS_VIERTES_LEVEL_NAME, BOSS_VIERTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner40 = new Raum(platzhalter40);
                Raum gegner41 = new Raum(platzhalter41);
                Raum gegner42 = new Raum(platzhalter42);
                Raum gegner43 = new Raum(platzhalter43);
                Raum gegner44 = new Raum(platzhalter44);
                Raum boss4 = new Raum(bossViertesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner40;
                meineRaum[ZAHL_0][ZAHL_1] = gegner41;
                meineRaum[ZAHL_0][ZAHL_2] = gegner42;
                meineRaum[ZAHL_0][ZAHL_3] = gegner43;
                meineRaum[ZAHL_0][ZAHL_4] = gegner44;
                meineRaum[ZAHL_0][ZAHL_5] = boss4;
                break;

            case ZAHL_5:
                Gegner platzhalter50 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter51 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter52 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter53 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter54 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossFuenftesLevel = new Gegner(BOSS_FUENFTES_LEVEL_NAME, BOSS_FUENFTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner50 = new Raum(platzhalter50);
                Raum gegner51 = new Raum(platzhalter51);
                Raum gegner52 = new Raum(platzhalter52);
                Raum gegner53 = new Raum(platzhalter53);
                Raum gegner54 = new Raum(platzhalter54);
                Raum boss5 = new Raum(bossFuenftesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner50;
                meineRaum[ZAHL_0][ZAHL_1] = gegner51;
                meineRaum[ZAHL_0][ZAHL_2] = gegner52;
                meineRaum[ZAHL_0][ZAHL_3] = gegner53;
                meineRaum[ZAHL_0][ZAHL_4] = gegner54;
                meineRaum[ZAHL_0][ZAHL_5] = boss5;
                break;

            case ZAHL_6:
                Gegner platzhalter60 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter61 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter62 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter63 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter64 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossSechstesLevel = new Gegner(BOSS_SECHSTES_LEVEL_NAME, BOSS_SECHSTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner60 = new Raum(platzhalter60);
                Raum gegner61 = new Raum(platzhalter61);
                Raum gegner62 = new Raum(platzhalter62);
                Raum gegner63 = new Raum(platzhalter63);
                Raum gegner64 = new Raum(platzhalter64);
                Raum boss6 = new Raum(bossSechstesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner60;
                meineRaum[ZAHL_0][ZAHL_1] = gegner61;
                meineRaum[ZAHL_0][ZAHL_2] = gegner62;
                meineRaum[ZAHL_0][ZAHL_3] = gegner63;
                meineRaum[ZAHL_0][ZAHL_4] = gegner64;
                meineRaum[ZAHL_0][ZAHL_5] = boss6;
                break;

            case ZAHL_7:
                Gegner platzhalter70 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter71 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter72 = new Gegner(GENERISCHER_GEGNER_NAME,GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter73 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter74 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossSiebtesLevel = new Gegner(BOSS_SIEBTES_LEVEL_NAME, BOSS_SIEBTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner70 = new Raum(platzhalter70);
                Raum gegner71 = new Raum(platzhalter71);
                Raum gegner72 = new Raum(platzhalter72);
                Raum gegner73 = new Raum(platzhalter73);
                Raum gegner74 = new Raum(platzhalter74);
                Raum boss7 = new Raum(bossSiebtesLevel);

                meineRaum[ZAHL_0][ZAHL_0] = gegner70;
                meineRaum[ZAHL_0][ZAHL_1] = gegner71;
                meineRaum[ZAHL_0][ZAHL_2] = gegner72;
                meineRaum[ZAHL_0][ZAHL_3] = gegner73;
                meineRaum[ZAHL_0][ZAHL_4] = gegner74;
                meineRaum[ZAHL_0][ZAHL_5] = boss7;
                break;
        }
        return new Ebene(zeilen, spalten, meineRaum);
    }
}
