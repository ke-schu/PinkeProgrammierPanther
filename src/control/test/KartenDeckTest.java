package control.test;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import model.KarteEinheit;
import model.KartenDeck;
import resources.Effekte;
import resources.Einheiten;
import static resources.Strings.*;
import static resources.Zahlen.ZAHL_1;

import java.io.File;

public class KartenDeckTest
{
    private final static String TESTPFAD = KARTENDECK_PAKET_PFAD + "Spieldeck_Gegner.json";
    private final static String TESTBEZEICHNUNG = "IchBinDasSpieldeck";
    private final static String KARTEN_NAME = "HarryPotter";

    private static KarteEinheit erstelleKarte ()
    {
        return new KarteEinheit(
                KARTEN_NAME,
                ZAHL_1,
                Einheiten.NAHKAEMPFER,
                ZAHL_1,
                ZAHL_1,
                ZAHL_1,
                ZAHL_1,
                ZAHL_1,
                ZAHL_1,
                ZAHL_1,
                Effekte.LETZTEWORTE,
                Effekte.ZURUECKWERFEN,
                true,
                true);
    }

    public static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck(new File(TESTPFAD), TESTBEZEICHNUNG);

        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());

        try
        {
            KartenDeckIO.schreibeDatei(meinDeck);
        }
        catch (KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void leseDeck ()
    {
        try
        {
            KartenDeck meinDeck = KartenDeckIO.leseDatei(TESTPFAD);
            System.out.println(meinDeck);
        }
        catch (KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
