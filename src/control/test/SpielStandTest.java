package control.test;

import control.CharakterController;
import exceptions.KartenDeckFehlerhaftException;
import io.CharakterIO;
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

public class SpielStandTest
{
    public static void schreibeCharacter()
    {
        try
        {
            Charakter charakter = new Charakter("Magier", 321, erstelleSpieler(), true);
            Stack<Charakter> meinCharakterStack = new Stack<>();
            meinCharakterStack.push(charakter);
            meinCharakterStack.push(charakter);
            CharakterIO.schreibeDatei(meinCharakterStack);
        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static Spieler leseCharakter() throws KartenDeckFehlerhaftException, IOException
    {
        Charakter meineKlasse = CharakterController.leseCharakter(1);
        return meineKlasse.getSpieler();
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

    public static void leseSpielstand()
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

    public static void speichereSpielstand()
    {
        try
        {
            SpielStand meinSpielStand = new SpielStand(100, 32, leseCharakter());
            SpielStandIO.schreibeDatei(meinSpielStand);
        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
