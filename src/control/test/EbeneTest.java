package control.test;

import control.SpielfigurEbeneController;
import model.*;
import model.ereignisse.*;
import resources.Effekte;
import resources.Einheiten;
import utility.EbeneIO;
import utility.KonsolenIO;
import utility.SpielStandIO;

import java.io.File;
import java.io.IOException;

import static control.test.TestZahlen.*;
import static resources.Strings.EBENE_TEST_PFAD;
import static resources.StringsEreignisse.*;
import static resources.StringsGegner.GENERISCHER_GEGNER_NAME;

/**
 * Diese Klasse beinhaltet die fuer das Testen der Ebene notwendigen
 * Methoden.
 */
public class EbeneTest
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EbeneTest()
    {
    }

    /**
     * Methode um eine Instanz von Gegenspieler zu erstellen, welche fuer das
     * Erstellen von Gegnern notwendig ist.
     *
     * @return gibt eine Instanz von Gegenspieler zurueck.
     */
    private static Gegenspieler erstelleGegenspieler()
    {
        return new Gegenspieler(GENERISCHER_GEGNER_NAME, ZAHL_10,
                                Einheiten.FERNKAEMPFER, ZAHL_1, ZAHL_1, ZAHL_1,
                                ZAHL_1, ZAHL_1, ZAHL_1, Effekte.LETZTEWORTE,
                                Effekte.ZURUECKWERFEN, ZAHL_10);
    }

    /**
     * Erstellt eine neue Ebene mit beispielhaften Werten.
     *
     * @return die erstellte Ebene
     */
    private static Ebene erstelleEbene()
    {
        Ebene meineEbene = new Ebene(new SpielfigurEbene(), ZAHL_9, ZAHL_9);

        LeererRaum leererRaum1 =
                new LeererRaum(START_RAUM_NAME, START_RAUM_BESCHREIBUNG);
        LeererRaum leererRaum2 =
                new LeererRaum(LEERER_RAUM_NAME, LEERER_RAUM_BESCHREIBUNG);
        Haendler haendler = new Haendler(HAENDLER_NAME, HAENDLER_BESCHREIBUNG);
        Schmied schmied = new Schmied(SCHMIED_NAME, SCHMIED_BESCHREIBUNG);
        Tempel tempel = new Tempel(TEMPEL_NAME, TEMPEL_BESCHREIBUNG);
        Heiler heiler = new Heiler(HEILER_NAME, HEILER_BESCHREIBUNG);
        ZufallsEreignis zufallsEreignis =
                new ZufallsEreignis(ZUFALLS_EREIGNIS_NAME,
                                    ZUFALLS_EREIGNIS_BESCHREIBUNG, false);
        Truhe truhe = new Truhe(TRUHE_NAME, TRUHE_BESCHREIBUNG, false);
        Gegner gegner = new Gegner(GEGNER_NAME, GEGNER_BESCHREIBUNG,
                                   erstelleGegenspieler(), new SpielFeld());
        Treppe treppe = new Treppe(TREPPE_NAME, TREPPE_BESCHREIBUNG);

        meineEbene.raumEinsetzen(ZAHL_4, ZAHL_4, new Raum(leererRaum1));
        meineEbene.raumEinsetzen(ZAHL_4, ZAHL_3, new Raum(leererRaum2));
        meineEbene.raumEinsetzen(ZAHL_4, ZAHL_2, new Raum(haendler));
        meineEbene.raumEinsetzen(ZAHL_4, ZAHL_1, new Raum(schmied));
        meineEbene.raumEinsetzen(ZAHL_4, ZAHL_0, new Raum(tempel));
        meineEbene.raumEinsetzen(ZAHL_5, ZAHL_0, new Raum(heiler));
        meineEbene.raumEinsetzen(ZAHL_6, ZAHL_0, new Raum(zufallsEreignis));
        meineEbene.raumEinsetzen(ZAHL_7, ZAHL_0, new Raum(truhe));
        meineEbene.raumEinsetzen(ZAHL_8, ZAHL_0, new Raum(gegner));
        meineEbene.raumEinsetzen(ZAHL_8, ZAHL_1, new Raum(treppe));

        return meineEbene;
    }

    /**
     * Diese Methode testet alle grundlegenden Funktionen einer Ebene. Dafuer
     * wird die erstellte Testebene in die EbeneTest.json geschrieben und
     * anschliessend aus dieser in eine neue Instanz von Ebene erstellt.
     * Hiernach durchlaeuft eine Instanz der Klasse SpielfigurEbene diese
     * Ebene und durchquert alle Ereignisse einmal.
     */
    public static void testeEbene()
    {
        Ebene testEbene1 = erstelleEbene();
        KonsolenIO.ausgeben(testEbene1.toString());

        try
        {
            EbeneIO.schreibeDatei(testEbene1, new File(EBENE_TEST_PFAD));
            Ebene testEbene2 = EbeneIO.leseDatei(new File(EBENE_TEST_PFAD));
            SpielStand spielStand = SpielStandIO.leseDatei();

            KonsolenIO.ausgeben(testEbene2.toString());
            KonsolenIO.ausgeben(testEbene2.getSpielfigur());

            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_3,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_2,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_1,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_0,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_5, ZAHL_0,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_6, ZAHL_0,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_7, ZAHL_0,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_8, ZAHL_0,
                                              spielStand);
            SpielfigurEbeneController.bewegen(testEbene2, ZAHL_8, ZAHL_1,
                                              spielStand);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
