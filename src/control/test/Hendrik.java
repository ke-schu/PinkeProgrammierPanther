package control.test;
import control.EbeneController;
import control.EinheitenController;
import io.EbeneIO;
import model.*;
import resources.Strings;

import java.io.File;
import java.io.IOException;

/**
 * In dieser Klasse liegen Methoden, die Code testen sollen.
 */
public class Hendrik
{
    public static void ausfuehren ()
    {
        testeBewegen();
    }

    private static void testeBewegen()
    {
        KarteEinheit einheit = new KarteEinheit("Peter",1,null,1,1,1,3,2,2,null,null);
        SpielFeld feld = new SpielFeld();
        System.out.println(feld.toString());
        feld.einheiteinsetzten(0,0,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());
        System.out.println(Strings.ZEILENUMBRUCH);
        EinheitenController.bewegen(feld,1,0,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());
        System.out.println(Strings.ZEILENUMBRUCH);
        EinheitenController.bewegen(feld,2,0,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());
        System.out.println(Strings.ZEILENUMBRUCH);
        EinheitenController.bewegen(feld,2,1,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());
        EinheitenController.bewegen(feld,2,2,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());

    }
    private static void testeEbenenErstellung()
    {
        try
        {
            Ebene meineEbene = EbeneController.fuelleEbene(2);
            System.out.println(meineEbene.toString());
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
