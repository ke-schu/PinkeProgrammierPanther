package control.test;

import control.CharakterController;
import io.KartenDeckIO;
import io.SpielStandIO;
import exceptions.KartenDeckFehlerhaftException;
import model.*;
import resources.EffektTyp;
import resources.EinheitTyp;
import resources.Strings;

import java.io.File;
import java.io.IOException;

public class Keno
{
    public static void ausfuehren()
    {
        leseSpielstand();
    }

    private static void leseSpielstand()
    {
        try
        {
            SpielStand meinSpielStand = SpielStandIO.leseDatei();
            System.out.println(meinSpielStand.getSpieldeck_spieler().getDeckBezeichnung()
                    + Strings.LEERZEICHEN
                    + meinSpielStand.getSpieldeck_spieler().size());
        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }

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
                EinheitTyp.NAHKAEMPFER,
                3,
                10,
                1,
                1,
                1,
                1,
                EffektTyp.LETZTEWORTE,
                EffektTyp.ZURUECKWERFEN));
        meinDeck.push(new KarteEinheit(
                "RonWeasley",
                30,
                EinheitTyp.FERNKAEMPFER,
                2,
                9,
                1,
                1,
                1,
                1,
                EffektTyp.ZURUECKWERFEN,
                EffektTyp.LETZTEWORTE));
        KartenDeckIO.schreibeDatei(meinDeck);
    }
}
