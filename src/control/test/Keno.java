package control.test;

import control.SpielStandController;
import exceptions.KartenDeckFehlerhaftException;
import model.*;
import utilities.JsonIO;

import java.io.File;
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

    private static void speichereSpielstand() throws IOException
    {
        SpielStand meinSpielStand = new SpielStand(
                10,
                new KartenDeck("testdeck"),
                new Level(),
                47);
        SpielStandController.schreibeDatei(meinSpielStand);
    }

    private static void waehleKlasse()
    {
        Held meinHeld = new Held(10, new Waffe(10));
        try
        {
            CharakterKlasse meineKlasse = new CharakterKlasse("Magier",150, meinHeld);
        } catch (KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck(new File("src/resources/carddecks/MagierHaendler.json"), "irgendeinName");;

        System.out.println(meinDeck);

        try
        {
            JsonIO.schreibeDatei(meinDeck);
            KartenDeck meinDeck2 = JsonIO.leseDatei("src/resources/carddecks/MagierHaendler.json");
            System.out.println(meinDeck2.toString());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
