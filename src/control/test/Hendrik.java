package control.test;
import control.EbeneController;
import control.EinheitenController;
import exceptions.KartenDeckFehlerhaftException;
import io.EbeneIO;
import io.SpielStandIO;
import model.*;
import model.ereignisse.Haendler;
import resources.Strings;

import java.io.File;
import java.io.IOException;

import static resources.Strings.SPIELSTAND_PFAD;

/**
 * In dieser Klasse liegen Methoden, die Code testen sollen.
 */
public class Hendrik
{
    public static void ausfuehren ()
    {
        testeEbenenErstellung();
    }

    private static void testeBewegen()
    {
        KarteEinheit einheit = new KarteEinheit("Peter",1,null,1,1,1,3,2,2,null,null);
        SpielFeld feld = new SpielFeld();
        System.out.println(feld.toString());
        feld.einheiteinsetzten(0,1,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());
        System.out.println(Strings.ZEILENUMBRUCH);
        EinheitenController.bewegen(feld,0,0,einheit);
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
        EinheitenController.bewegen(feld,2,1,einheit);
        System.out.println(feld.toString());
        System.out.println(einheit.getBeweglichkeit());
        System.out.println(einheit.getPosition_x());
        System.out.println(einheit.getPosition_y());

    }
    private static void testeEbenenErstellung()
    {

        //Schreibe Ebene 1

        try
        {
            Ebene eben1 = EbeneController.fuelleEbene(1);
            EbeneIO.schreibeDatei(eben1, new File("src/resources/ebenen/Ebene1.json"));
            System.out.println(eben1.toString());
        }
        catch (IOException e)
        {
            e.getMessage();
        }




        // Lese Ebene 1


        try
        {
            Ebene ebene1 = EbeneIO.leseDatei(new File("src/resources/ebenen/Ebene1.json"));
            System.out.println(ebene1.toString());
            Haendler h = (Haendler)ebene1.getRaumAnPosition(6,4).getEreignis();
            System.out.println(h.getName());
            h.ausfuehren(new SpielStand(SpielStandIO.leseDatei()));
            System.out.println(h.getHaendlerDeck().peek());



        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            e.getMessage();
        }






    }
}
