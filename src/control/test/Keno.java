package control.test;

import control.CharakterController;
import io.KartenDeckIO;
import io.SpielStandIO;
import exceptions.KartenDeckFehlerhaftException;
import model.*;

import java.io.File;
import java.io.IOException;

public class Keno
{
    public static void ausfuehren()
    {
        try
        {
            erstelleDeck();
        } catch (KartenDeckFehlerhaftException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void leseSpielstand() throws IOException
    {
        SpielStand meinSpielStand = SpielStandIO.leseDatei();
        System.out.println(meinSpielStand.getSpieldeck().getDeckBezeichnung());
    }

    private static void speichereSpielstand() throws KartenDeckFehlerhaftException, IOException
    {
        SpielStand meinSpielStand = new SpielStand(10, new Level(), 47);
        SpielStandIO.schreibeDatei(meinSpielStand);
    }

    private static void waehleCharakter()
    {
        try
        {
            Charakter meineKlasse = CharakterController.leseCharakter(0);
            System.out.println(meineKlasse.getStartDeck());
        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void erstelleDeck () throws KartenDeckFehlerhaftException
    {
        KartenDeck meinDeck = new KartenDeck(new File(
                "src/resources/kartendecks/Spieldeck.json"),
                "IchBinDasSpieldeck");
        meinDeck.push(new KarteEinheit(
                "HarryPotter",
                40,
                KarteEinheit.Typ.NAHKAEMPFER,
                3,
                10,
                1,
                1,
                1,
                EffektTyp.LETZTEWORTE,
                EffektTyp.ZURUECKWERFEN));
        meinDeck.push(new KarteEinheit(
                "RonWeasley",
                30,
                KarteEinheit.Typ.FERNKAEMPFER,
                2,
                9,
                1,
                1,
                1,
                EffektTyp.ZURUECKWERFEN,
                EffektTyp.LETZTEWORTE));
        KartenDeckIO.schreibeDatei(meinDeck);
    }
}
