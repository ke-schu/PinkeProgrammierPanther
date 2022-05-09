package control.test;

import control.KartenDeckController;
import control.SpielStandController;
import exceptions.KartenDeckFehlerhaftException;
import model.*;

import java.io.File;
import java.io.IOException;

import static model.KartenEinheitType.*;

public class Keno
{
    public static void ausfuehren()
    {
        erstelleDeck();
        /*try
        {
            speichereSpielstand();
        } catch (KartenDeckFehlerhaftException | IOException e)
        {
            System.out.println(e.getMessage());
        }*/
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
            CharakterKlasse meineKlasse = new CharakterKlasse("Magier",150, meinHeld);
        } catch (KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void erstelleDeck ()
    {
        KartenDeck meinDeck = new KartenDeck(new File("src/resources/kartendecks/Spieldeck.json"), "IchBinDasStartDeck");;

        meinDeck.add(new KarteEinheit(EricKarte));
        meinDeck.add(new KarteEinheit(KennyKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(KyleKarte));
        meinDeck.add(new KarteEinheit(StanKarte));

        KartenDeckController.mischen(meinDeck);
        System.out.println(meinDeck);

        try
        {
            KartenDeckController.schreibeDatei(meinDeck);
            KartenDeck meinDeck2 = KartenDeckController.leseDatei("src/resources/kartendecks/Spieldeck.json");
            System.out.println(meinDeck2.toString());
        }
        catch (KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
