package control.test;

import exceptions.JsonNichtLesbarException;
import model.*;
import resources.*;
import utility.KonsolenIO;

import java.io.IOException;
import java.util.Stack;

import static control.test.TestKonstanten.*;
import static control.test.TestZahlen.*;
import static resources.Artefakte.SCHUTZENGEL;
import static resources.Konstanten.charakterIO;
import static resources.Konstanten.spielStandIO;
import static resources.Talente.CHARME;

/**
 * Enthaelt Methoden zum Testen vom Spielstand
 */
public class SpielStandTest
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielStandTest()
    {
    }

    /**
     * Erstellt einen Beispielspieler mit beispielhaften Werten
     *
     * @return den Spieler
     */
    private static Spieler erstelleSpieler()
    {
        Stack<Talente> meinTalentStack = new Stack<>();
        meinTalentStack.push(CHARME);

        Artefakte[] meineArtefake = new Artefakte[ZAHL_2];
        meineArtefake[ZAHL_0] = SCHUTZENGEL;

        return new Spieler(TESTBEZEICHNUNG_SPIELER, ZAHL_1,
                           Einheiten.FERNKAEMPFER, ZAHL_1, ZAHL_1, ZAHL_1,
                           ZAHL_1, ZAHL_1, ZAHL_1, Effekte.LETZTEWORTE,
                           Effekte.ZURUECKWERFEN,
                           new Waffe(TESTBEZEICHNUNG_WAFFE, ZAHL_10),
                           meinTalentStack, meineArtefake, ZAHL_1);
    }

    /**
     * Schreibt einen neuen Charakter-Stack mit zwei Charakteren in die
     * Datei.
     */
    public static void schreibeCharacter()
    {
        try
        {
            Charakter charakter =
                    new Charakter(TESTBEZEICHNUNG_CHARAKTER, ZAHL_1,
                                  erstelleSpieler(), true);
            Stack<Charakter> meinCharakterStack = new Stack<>();
            meinCharakterStack.push(charakter);
            meinCharakterStack.push(charakter);
            charakterIO.schreibeDatei(meinCharakterStack);
        }
        catch (IOException ausnahme)
        {
            KonsolenIO.ausgeben(ausnahme.getMessage());
        }
    }

    /**
     * Liest den aktuell abgespeicherten Spielstand ein und gibt das Spieldeck
     * ueber die Konsole aus.
     */
    public static void leseSpielstand()
    {
        try
        {
            SpielStand meinSpielStand = spielStandIO.leseSpielstand();
            KonsolenIO.ausgeben(Strings.ZEILENUMBRUCH +
                                meinSpielStand.getSpieldeckSpieler() +
                                Strings.ZEILENUMBRUCH +
                                meinSpielStand.getAktuelleEbene().toString());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Speichert einen neuen Spielstand mit beispielhaften Werten in der Datei
     * ab.
     */
    public static void speichereSpielstand()
    {
        try
        {
            Stack<Charakter> meineCharaktere = charakterIO.leseCharaktere();
            SpielStand meinSpielStand =
                    new SpielStand(ZAHL_10, meineCharaktere.firstElement().getSpieler(), erstelleGegenSpieler());
            spielStandIO.schreibeDatei(meinSpielStand);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    private static Gegenspieler erstelleGegenSpieler()
    {
        return new Gegenspieler(
                "Dieter",
                1,
                Einheiten.FERNKAEMPFER,
                1,
                2,
                3,
                4,
                5,
                6,
                Effekte.ZURUECKWERFEN,
                Effekte.DURCHSCHNEIDEN,
                5);
    }
}
