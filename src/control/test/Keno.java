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
        waehleCharakter();
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
        KartenDeck meinDeck = KartenDeckController.leseDatei("src/resources/kartendecks/Magier2.json");
        meinDeck.get(0).setLevel(2);
        KartenDeckController.schreibeDatei(meinDeck);
    }
}
