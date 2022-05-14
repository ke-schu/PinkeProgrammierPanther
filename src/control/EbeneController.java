package control;

import model.*;
import model.ereignisse.*;
import resources.Effekte;
import resources.Einheiten;

import java.io.File;

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
        Raum[][] meinRaum = new Raum[spalten][zeilen];
        switch (EbenenStufe)
        {
            case ZAHL_1:
                Gegner platzhalter10 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner platzhalter11 = new Gegner(GENERISCHER_GEGNER_NAME, GENERISCHER_GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Gegner bossErstesLevel = new Gegner(BOSS_ERSTES_LEVEL_NAME, BOSS_ERSTES_LEVEL_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
                Raum gegner10 = new Raum(platzhalter10);
                Raum gegner11 = new Raum(platzhalter11);
                Raum boss1 = new Raum(bossErstesLevel);
                meinRaum[ZAHL_2][ZAHL_4] = gegner10;
                meinRaum[ZAHL_3][ZAHL_5] = gegner11;
                meinRaum[ZAHL_2][ZAHL_6] = boss1;

                ZufallsEreignis ze = new ZufallsEreignis("Zufaelliger Zufall", "Achtung, das ist ein Zufall!",false);
                Raum RaumMitZufallsEreignis = new Raum(ze);
                meinRaum[ZAHL_3][ZAHL_3] = RaumMitZufallsEreignis;

                Schmied schmied1 = new Schmied("Bernhard","Hoere mal wer da haemmert!");
                Raum raumSchmied = new Raum(schmied1);
                meinRaum[ZAHL_6][ZAHL_4] = raumSchmied;

                Haendler haendler1 = new Haendler("Josef", "Mehr als nur Staubsauger.", new KartenDeck("Deckbezeichnung einfuegen"));
                Raum raumHaendler1 = new Raum(haendler1);
                meinRaum[ZAHL_4][ZAHL_6] = raumHaendler1;

                Treppe treppe1 = new Treppe("Treppe 1", "Ne Menge Stufen");
                Raum raumTreppe1 = new Raum(treppe1);
                meinRaum[ZAHL_2][ZAHL_7] = raumTreppe1;

                Raum leererRaum10 = new Raum();
                Raum leererRaum11 = new Raum();
                Raum leererRaum12 = new Raum();
                Raum leererRaum13 = new Raum();
                meinRaum[ZAHL_5][ZAHL_4] = leererRaum10;
                meinRaum[ZAHL_3][ZAHL_4] = leererRaum11;
                meinRaum[ZAHL_3][ZAHL_6] = leererRaum12;
                meinRaum[ZAHL_4][ZAHL_4] = leererRaum13;
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

                meinRaum[ZAHL_0][ZAHL_0] = gegner20;
                meinRaum[ZAHL_0][ZAHL_1] = gegner21;
                meinRaum[ZAHL_0][ZAHL_2] = gegner22;
                meinRaum[ZAHL_0][ZAHL_3] = gegner23;
                meinRaum[ZAHL_0][ZAHL_4] = gegner24;
                meinRaum[ZAHL_0][ZAHL_5] = boss2;
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

                meinRaum[ZAHL_0][ZAHL_0] = gegner30;
                meinRaum[ZAHL_0][ZAHL_1] = gegner31;
                meinRaum[ZAHL_0][ZAHL_2] = gegner32;
                meinRaum[ZAHL_0][ZAHL_3] = gegner33;
                meinRaum[ZAHL_0][ZAHL_4] = gegner34;
                meinRaum[ZAHL_0][ZAHL_5] = boss3;
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

                meinRaum[ZAHL_0][ZAHL_0] = gegner40;
                meinRaum[ZAHL_0][ZAHL_1] = gegner41;
                meinRaum[ZAHL_0][ZAHL_2] = gegner42;
                meinRaum[ZAHL_0][ZAHL_3] = gegner43;
                meinRaum[ZAHL_0][ZAHL_4] = gegner44;
                meinRaum[ZAHL_0][ZAHL_5] = boss4;
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

                meinRaum[ZAHL_0][ZAHL_0] = gegner50;
                meinRaum[ZAHL_0][ZAHL_1] = gegner51;
                meinRaum[ZAHL_0][ZAHL_2] = gegner52;
                meinRaum[ZAHL_0][ZAHL_3] = gegner53;
                meinRaum[ZAHL_0][ZAHL_4] = gegner54;
                meinRaum[ZAHL_0][ZAHL_5] = boss5;
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

                meinRaum[ZAHL_0][ZAHL_0] = gegner60;
                meinRaum[ZAHL_0][ZAHL_1] = gegner61;
                meinRaum[ZAHL_0][ZAHL_2] = gegner62;
                meinRaum[ZAHL_0][ZAHL_3] = gegner63;
                meinRaum[ZAHL_0][ZAHL_4] = gegner64;
                meinRaum[ZAHL_0][ZAHL_5] = boss6;
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

                meinRaum[ZAHL_0][ZAHL_0] = gegner70;
                meinRaum[ZAHL_0][ZAHL_1] = gegner71;
                meinRaum[ZAHL_0][ZAHL_2] = gegner72;
                meinRaum[ZAHL_0][ZAHL_3] = gegner73;
                meinRaum[ZAHL_0][ZAHL_4] = gegner74;
                meinRaum[ZAHL_0][ZAHL_5] = boss7;
                break;
        }
        return new Ebene(zeilen, spalten, meinRaum);
    }
}
