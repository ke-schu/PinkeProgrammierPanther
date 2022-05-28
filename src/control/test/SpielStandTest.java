package control.test;

import control.CharakterController;
import exceptions.JsonNichtLesbarException;
import io.CharakterIO;
import io.KonsolenIO;
import io.SpielStandIO;
import model.Charakter;
import model.SpielStand;
import model.Spieler;
import model.Waffe;
import resources.*;

import java.io.IOException;
import java.util.Stack;

import static resources.Artefakte.DER_GRABSTEIN;
import static resources.Talente.ABKLINGEN;
import static resources.Talente.MANA_GOTT;
import static control.test.TestKonstanten.*;
import static control.test.TestZahlen.*;

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
     * @return den Spieler
     */
    private static Spieler erstelleSpieler()
    {
        Stack<Talente> meinTalentStack = new Stack<>();
        meinTalentStack.push(ABKLINGEN);
        meinTalentStack.push(MANA_GOTT);

        Artefakte[] meineArtefake = new Artefakte[ZAHL_2];
        meineArtefake[ZAHL_0] = DER_GRABSTEIN;

        return new Spieler(TESTBEZEICHNUNG_SPIELER, ZAHL_1,
                           Einheiten.FERNKAEMPFER, ZAHL_1,
                           ZAHL_1, ZAHL_1, ZAHL_1, ZAHL_1, ZAHL_1,
                           Effekte.LETZTEWORTE,
                           Effekte.ZURUECKWERFEN,
                           new Waffe(TESTBEZEICHNUNG_WAFFE, ZAHL_10),
                           meinTalentStack,
                           meineArtefake, ZAHL_1);
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
            CharakterIO.schreibeDatei(meinCharakterStack);
        } catch (IOException ausnahme)
        {
            KonsolenIO.ausgeben(ausnahme.getMessage());
        }
    }

    /**
     * Liest den ersten Charakter aus der Datei ein und gibt den zugehoerigen
     * Spieler zurueck.
     * @return den Spieler
     * @throws JsonNichtLesbarException wenn das Kartendeck des
     * Charakters Fehler aufwirft
     * @throws IOException wenn die Datei nicht oder nur falsch geladen werden
     * kann
     */
    private static Spieler leseCharakter()
            throws JsonNichtLesbarException, IOException
    {
        Charakter meineKlasse = CharakterController.leseCharakter(1);
        return meineKlasse.getSpieler();
    }

    /**
     * Liest den aktuell abgespeicherten Spielstand ein und gibt das Spieldeck
     * ueber die Konsole aus.
     */
    public static void leseSpielstand()
    {
        try
        {
            SpielStand meinSpielStand = SpielStandIO.leseDatei();
            KonsolenIO.ausgeben(
                    Strings.ZEILENUMBRUCH
                    + meinSpielStand.getSpieldeckSpieler()
                    + Strings.ZEILENUMBRUCH
                    + meinSpielStand.getAktuelleEbene().toString());
        } catch (JsonNichtLesbarException e)
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
            SpielStand meinSpielStand =
                    new SpielStand(ZAHL_10, ZAHL_9, leseCharakter());
            SpielStandIO.schreibeDatei(meinSpielStand);
        } catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
