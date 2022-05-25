package control.test;

import control.KartenDeckController;
import exceptions.JsonNichtLesbarException;
import io.KartenDeckIO;
import io.KonsolenIO;
import model.KarteEinheit;
import model.KartenDeck;
import resources.Effekte;
import resources.Einheiten;

import java.io.File;
import java.io.IOException;

import static resources.TestKonstanten.*;
import static resources.Zahlen.ZAHL_1;

/**
 * Enthaelt Methoden zum Testen von Kartendecks
 */
public class KartenDeckTest
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenDeckTest()
    {
    }

    /**
     * Erstellt eine neue KarteEinheit mit beispielhaften Werten
     * @return die KarteEinheit
     */
    private static KarteEinheit erstelleKarte()
    {
        return new KarteEinheit(KARTEN_NAME, ZAHL_1, Einheiten.NAHKAEMPFER,
                                ZAHL_1, ZAHL_1, ZAHL_1, ZAHL_1, ZAHL_1,
                                ZAHL_1, ZAHL_1,
                                Effekte.LETZTEWORTE, Effekte.ZURUECKWERFEN,
                                true, true);
    }

    /**
     * Erstellt ein Beispieldeck mit einer Anzahl an Beispielkarten und
     * schreibt es in eine Datei.
     */
    public static void erstelleDeck()
    {
        KartenDeck meinDeck = new KartenDeck(new File(TESTPFAD_KARTENDECK),
                                             TESTBEZEICHNUNG_KARTENDECK);

        for (int i = 0; i < ANZAHL_KARTEN; i++)
        {
            meinDeck.push(erstelleKarte());
        }

        try
        {
            KartenDeckIO.schreibeDatei(meinDeck);
        } catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Liest ein Deck in dem Testpfad ein, gibt es einmal im Original und
     * einmal gemischt aus.
     */
    public static void leseDeck()
    {
        try
        {
            KartenDeck meinDeck = KartenDeckIO.leseDatei(TESTPFAD_KARTENDECK);
            KonsolenIO.ausgeben(meinDeck);
            KartenDeckController.mischen(meinDeck);
            KonsolenIO.ausgeben(meinDeck);
        } catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
