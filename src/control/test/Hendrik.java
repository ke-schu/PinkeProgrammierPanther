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

        try {
            Ebene ebene1 = new Ebene();
            ebene1 = EbeneController.fuelleEbene(1);
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene1.json"));
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene2.json"));
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene3.json"));
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene4.json"));
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene5.json"));
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene6.json"));
            EbeneIO.schreibeDatei(ebene1, new File("src/resources/ebenen/Ebene7.json"));

            Ebene meineEbene = EbeneIO.leseDatei(new File("src/resources/ebenen/Ebene1.json"));
            System.out.println(meineEbene.toString());
        }
        catch (IOException e)
        {
            e.getMessage();
        }
    }
}
