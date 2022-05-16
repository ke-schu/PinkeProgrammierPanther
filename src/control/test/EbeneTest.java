package control.test;

import control.EbeneController;
import control.SpielfigurEbeneController;
import io.EbeneIO;
import io.SpielStandIO;
import model.*;
import model.ereignisse.*;
import resources.Effekte;
import resources.Einheiten;
import java.io.File;
import java.io.IOException;

import static resources.Strings.EBENE_TEST_PFAD;
import static resources.Zahlen.*;

/**
 * Diese Klasse beinhaltet die für das Testen der Ebene notwendigen Methoden.
 */
public class EbeneTest
{
    /**
     * Methode um eine Instanz von Gegenspieler zu erstellen, welche für das erstellen von Gegnern notwendig ist.
     * @return gibt eine Instanz von Gegenspieler zurück.
     */
    private static Gegenspieler erstelleGegenspieler ()
    {
        return new Gegenspieler("Test_Gegner",
                ZAHL_10,
                Einheiten.FERNKAEMPFER,
                ZAHL_1,ZAHL_1,ZAHL_1,ZAHL_1,ZAHL_1,ZAHL_1,
                Effekte.LETZTEWORTE, Effekte.ZURUECKWERFEN,
                ZAHL_10);
    }

    /**
     * Diese Methode testet alle grundlegenden Funktionen einer Ebene.
     * Es wird eine Ebene und Raeume erstellt, welche mit Ereignissen gefüllt sind.
     * Anschließend wird die Instanz in die EbeneTest.json geschrieben und anschließend aus dieser
     * in eine neue Instanz von Ebene geschrieben.
     * Hiernach durchläuft eine Instanz der Klasse SpielfigurEbene diese Ebene und durchquert alle Ereignisse einmal.
     */
    public static void testeEbene()
    {
        Ebene testEbene1 = new Ebene(ZAHL_9, ZAHL_9);
        Ebene testEbene2 = new Ebene(ZAHL_0,ZAHL_0);
        SpielStand spielStand = null;

        LeererRaum leererRaum1 = new LeererRaum("Startraum", "Hier kann man starten.");
        LeererRaum leererRaum2 = new LeererRaum("Leerer Raum", "Hier kann man leeren.");
        Haendler haendler = new Haendler("Haendler", "Hier kann man handeln.");
        Schmied schmied = new Schmied("Schmied", "Hier kann man schmieden.");
        Tempel tempel = new Tempel("Tempel", "Hier kann tempeln.");
        Heiler heiler = new Heiler("Heiler", "Hier kann man heilern.");
        ZufallsEreignis zufallsEreignis = new ZufallsEreignis("Zufallsereignis", "Hier kann man zufalls ereignissen.",false);
        Truhe truhe = new Truhe("Truhe", "Hier kann man truhen.", false);
        Gegner gegner = new Gegner("Gegner", "Hier kann man gegnern.", erstelleGegenspieler(), new SpielFeld());
        Treppe treppe = new Treppe("Treppe", "Hier kann treppen.");

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

        testEbene1.raumEinsetzen(ZAHL_4,ZAHL_4,startRaum);
        testEbene1.raumEinsetzen(ZAHL_4,ZAHL_3,leererRaum);
        testEbene1.raumEinsetzen(ZAHL_4,ZAHL_2,haendlerRaum);
        testEbene1.raumEinsetzen(ZAHL_4,ZAHL_1,schmiedRaum);
        testEbene1.raumEinsetzen(ZAHL_4,ZAHL_0,tempelRaum);
        testEbene1.raumEinsetzen(ZAHL_5,ZAHL_0,heilerRaum);
        testEbene1.raumEinsetzen(ZAHL_6,ZAHL_0,zufallsRaum);
        testEbene1.raumEinsetzen(ZAHL_7,ZAHL_0,truhenRaum);
        testEbene1.raumEinsetzen(ZAHL_8,ZAHL_0,gegnerRaum);
        testEbene1.raumEinsetzen(ZAHL_8,ZAHL_1,treppenRaum);

        System.out.println(testEbene1.toString());


        try
        {
            EbeneIO.schreibeDatei(testEbene1, new File(EBENE_TEST_PFAD));
        }
        catch (IOException e)
        {
            e.getMessage();
        }

        try
        {
            testEbene2 = EbeneIO.leseDatei(new File(EBENE_TEST_PFAD));
        }
        catch (IOException e)
        {
            e.getMessage();
        }

        try
        {
            spielStand = SpielStandIO.leseDatei();
        }
        catch (IOException e)
        {
            e.getMessage();
        }

        SpielfigurEbene spielfigur = new SpielfigurEbene();
        EbeneController.initSpielerInEbene(spielfigur, testEbene2);

        System.out.println(testEbene2.toString());
        System.out.println(testEbene2.getRaumAnPosition(ZAHL_4,ZAHL_4).getSpielfigur());

        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_4,ZAHL_3,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_4,ZAHL_2,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_4,ZAHL_1,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_4,ZAHL_0,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_5,ZAHL_0,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_6,ZAHL_0,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_7,ZAHL_0,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_8,ZAHL_0,spielfigur,spielStand);
        SpielfigurEbeneController.bewegen(testEbene2,ZAHL_8,ZAHL_1,spielfigur,spielStand);
    }
}
