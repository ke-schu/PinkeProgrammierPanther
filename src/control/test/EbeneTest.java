package control.test;

import control.EbeneController;
import control.SpielfigurEbeneController;
import io.EbeneIO;
import io.KonsolenIO;
import io.SpielStandIO;
import model.*;
import model.ereignisse.*;
import resources.Effekte;
import resources.Einheiten;

import java.io.File;
import java.io.IOException;

import static resources.Strings.EBENE_TEST_PFAD;
import static resources.StringsEreignisse.*;
import static resources.StringsGegner.GENERISCHER_GEGNER_NAME;
import static resources.Zahlen.*;

/**
 * Diese Klasse beinhaltet die für das Testen der Ebene notwendigen Methoden.
 */
public class EbeneTest
{
    /**
     * Methode um eine Instanz von Gegenspieler zu erstellen, welche für das erstellen von Gegnern notwendig ist.
     *
     * @return gibt eine Instanz von Gegenspieler zurück.
     */
    private static Gegenspieler erstelleGegenspieler ()
    {
        return new Gegenspieler(GENERISCHER_GEGNER_NAME, ZAHL_10, Einheiten.FERNKAEMPFER, ZAHL_1, ZAHL_1, ZAHL_1, ZAHL_1, ZAHL_1,
                                ZAHL_1, Effekte.LETZTEWORTE, Effekte.ZURUECKWERFEN, ZAHL_10);
    }

    /**
     * Diese Methode testet alle grundlegenden Funktionen einer Ebene. Es wird eine Ebene und Raeume erstellt, welche
     * mit Ereignissen gefüllt sind. Anschließend wird die Instanz in die EbeneTest.json geschrieben und anschließend
     * aus dieser in eine neue Instanz von Ebene geschrieben. Hiernach durchläuft eine Instanz der Klasse
     * SpielfigurEbene diese Ebene und durchquert alle Ereignisse einmal.
     */
    public static void testeEbene ()
    {
        Ebene testEbene1 = new Ebene(ZAHL_9, ZAHL_9);
        Ebene testEbene2 = new Ebene(ZAHL_0, ZAHL_0);
        SpielStand spielStand = null;

        LeererRaum leererRaum1 = new LeererRaum(START_RAUM_NAME, START_RAUM_BESCHREIBUNG);
        LeererRaum leererRaum2 = new LeererRaum(LEERER_RAUM_NAME, LEERER_RAUM_BESCHREIBUNG);
        Haendler haendler = new Haendler(HAENDLER_NAME, HAENDLER_BESCHREIBUNG);
        Schmied schmied = new Schmied(SCHMIED_NAME, SCHMIED_BESCHREIBUNG);
        Tempel tempel = new Tempel(TEMPEL_NAME, TEMPEL_BESCHREIBUNG);
        Heiler heiler = new Heiler(HEILER_NAME, HEILER_BESCHREIBUNG);
        ZufallsEreignis zufallsEreignis =
                new ZufallsEreignis(ZUFALLS_EREIGNIS_NAME, ZUFALLS_EREIGNIS_BESCHREIBUNG, false);
        Truhe truhe = new Truhe(TRUHE_NAME, TRUHE_BESCHREIBUNG, false);
        Gegner gegner = new Gegner(GEGNER_NAME, GEGNER_BESCHREIBUNG, erstelleGegenspieler(), new SpielFeld());
        Treppe treppe = new Treppe(TREPPE_NAME, TREPPE_BESCHREIBUNG);

        Raum startRaum = new Raum(leererRaum1);
        Raum leererRaum = new Raum(leererRaum2);
        Raum haendlerRaum = new Raum(haendler);
        Raum schmiedRaum = new Raum(schmied);
        Raum tempelRaum = new Raum(tempel);
        Raum heilerRaum = new Raum(heiler);
        Raum zufallsRaum = new Raum(zufallsEreignis);
        Raum truhenRaum = new Raum(truhe);
        Raum gegnerRaum = new Raum(gegner);
        Raum treppenRaum = new Raum(treppe);

        testEbene1.raumEinsetzen(ZAHL_4, ZAHL_4, startRaum);
        testEbene1.raumEinsetzen(ZAHL_4, ZAHL_3, leererRaum);
        testEbene1.raumEinsetzen(ZAHL_4, ZAHL_2, haendlerRaum);
        testEbene1.raumEinsetzen(ZAHL_4, ZAHL_1, schmiedRaum);
        testEbene1.raumEinsetzen(ZAHL_4, ZAHL_0, tempelRaum);
        testEbene1.raumEinsetzen(ZAHL_5, ZAHL_0, heilerRaum);
        testEbene1.raumEinsetzen(ZAHL_6, ZAHL_0, zufallsRaum);
        testEbene1.raumEinsetzen(ZAHL_7, ZAHL_0, truhenRaum);
        testEbene1.raumEinsetzen(ZAHL_8, ZAHL_0, gegnerRaum);
        testEbene1.raumEinsetzen(ZAHL_8, ZAHL_1, treppenRaum);

        KonsolenIO.ausgeben(testEbene1.toString());

        try
        {
            EbeneIO.schreibeDatei(testEbene1, new File(EBENE_TEST_PFAD));
            testEbene2 = EbeneIO.leseDatei(new File(EBENE_TEST_PFAD));
            spielStand = SpielStandIO.leseDatei();
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

        SpielfigurEbene spielfigur = new SpielfigurEbene();
        EbeneController.initSpielerInEbene(spielfigur, testEbene2);

        KonsolenIO.ausgeben(testEbene2.toString());
        KonsolenIO.ausgeben(testEbene2.getRaumAnPosition(ZAHL_4, ZAHL_4).getSpielfigur());

        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_3, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_2, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_1, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_4, ZAHL_0, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_5, ZAHL_0, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_6, ZAHL_0, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_7, ZAHL_0, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_8, ZAHL_0, spielfigur, spielStand);
        SpielfigurEbeneController.bewegen(testEbene2, ZAHL_8, ZAHL_1, spielfigur, spielStand);
    }
}
