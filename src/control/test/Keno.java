package control.test;

import control.CharakterController;
import control.KartenDeckController;
import control.SpielStandController;
import exceptions.KartenDeckFehlerhaftException;
import model.*;

import java.io.IOException;

public class Keno
{
    public static void ausfuehren()
    {
        waehleKlasse();
    }

    private static void leseSpielstand() throws IOException
    {
        SpielStand meinSpielStand = SpielStandController.leseDatei();
        System.out.println(meinSpielStand.getSpieldeck().getDeckBezeichnung());
    }

    private static void speichereSpielstand() throws KartenDeckFehlerhaftException, IOException
    {
        SpielStand meinSpielStand = new SpielStand(10, new Level(), 47);
        SpielStandController.schreibeDatei(meinSpielStand);
    }

    private static void waehleKlasse()
    {
        Held meinHeld = new Held(10, new Waffe(10));
        try
        {
            Charakter meineKlasse = CharakterController.leseCharakter(1);
            System.out.println(meineKlasse.getName());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void erstelleDeck () throws KartenDeckFehlerhaftException
    {
        KartenDeck meinDeck = KartenDeckController.leseDatei("src/resources/kartendecks/Magier2.json");
        meinDeck.get(0).setLevel(2);
        KartenDeckController.schreibeDatei(meinDeck);
    }
}
