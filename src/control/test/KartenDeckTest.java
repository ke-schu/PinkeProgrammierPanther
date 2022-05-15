package control.test;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import model.KarteEinheit;
import model.KartenDeck;
import resources.Effekte;
import resources.Einheiten;
import static resources.Strings.*;
import java.io.File;

public class KartenDeckTest
{
    private final static String TESTPFAD = KARTENDECK_PAKET_PFAD + "Spieldeck_Gegner.json";
    private final static String TESTBEZEICHNUNG = "IchBinDasSpieldeck";

    private static KarteEinheit erstelleKarte ()
    {
        return new KarteEinheit(
                "HarryPotter",
                40,
                Einheiten.NAHKAEMPFER,
                3,
                10,
                1,
                1,
                1,
                1,
                Effekte.LETZTEWORTE,
                Effekte.ZURUECKWERFEN);
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
