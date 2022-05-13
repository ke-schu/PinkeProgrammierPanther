package control.test;

import control.CharakterController;
import io.KartenDeckIO;
import io.SpielStandIO;
import exceptions.KartenDeckFehlerhaftException;
import model.*;
import resources.*;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import static resources.Artefakte.*;
import static resources.Talente.*;

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

    private static Spieler erstelleSpieler ()
    {
        Stack<Talente> meinTalentStack = new Stack<>();
        meinTalentStack.push(ABKLINGEN);
        meinTalentStack.push(MANA_GOTT);

        Artefakte[] meineArtefake = new Artefakte[2];
        meineArtefake[0] = DER_GRABSTEIN;

        return new Spieler("meinSpieler",
                1,
                Einheiten.FERNKAEMPFER,
                10,
                20,
                1,
                1,
                0,
                Effekte.LETZTEWORTE,
                Effekte.ZURUECKWERFEN,
                new Waffe("meineWaffe", 10),
                meinTalentStack,
                meineArtefake,
                10);
    }

    private static void leseSpielstand()
    {
        try
        {
            SpielStand meinSpielStand = SpielStandIO.leseDatei();
            System.out.println(meinSpielStand.getSpieldeckSpieler().getDeckBezeichnung()
                    + Strings.LEERZEICHEN
                    + meinSpielStand.getSpieldeckSpieler().size());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    private static void speichereSpielstand()
    {
        try
        {
            SpielStand meinSpielStand = new SpielStand(100, 32, erstelleSpieler());
            SpielStandIO.schreibeDatei(meinSpielStand);
        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
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
                "src/resources/kartendecks/Spieldeck_Spieler.json"),
                "IchBinDasSpieldeck");
        meinDeck.push(new KarteEinheit(
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
                Effekte.ZURUECKWERFEN));
        meinDeck.push(new KarteEinheit(
                "RonWeasley",
                30,
                Einheiten.FERNKAEMPFER,
                2,
                9,
                1,
                1,
                1,
                1,
                Effekte.ZURUECKWERFEN,
                Effekte.LETZTEWORTE));
        KartenDeckIO.schreibeDatei(meinDeck);
    }
}
