package control.test;
import control.EbeneController;
import io.EbeneIO;
import model.*;

import java.io.File;
import java.io.IOException;

/**
 * In dieser Klasse liegen Methoden, die Code testen sollen.
 */
public class Hendrik
{
    public static void ausfuehren ()
    {
        testeEbenenErstellung();
    }

    private static void testeEbenenErstellung()
    {
        try
        {
            Ebene meineEbene = EbeneController.fuelleEbene(1);
            EbeneIO.schreibeDatei(meineEbene, new File("src/resources/ebenen/Ebene1.json"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }



    /*
        KartenDeck haendler1 = new KartenDeck("Haendler1Deck");
        KartenDeck haendler2 = new KartenDeck("Haendler2Deck");
    haendler1 = leseDatei("src/resources/kartendecks/Haendler1.json");
            System.out.println(haendler1);
            haendler2.push(haendler1.get(2));
            haendler1.remove(2);
            System.out.println(haendler1);
            System.out.println(haendler2);
     */
}
