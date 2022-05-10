package control.test;
import exceptions.KartenDeckFehlerhaftException;
import model.*;

import static control.KartenDeckController.leseDatei;

/**
 * In dieser Klasse liegen Methoden, die Code testen sollen.
 */
public class Hendrik
{
    public static void ausfuehren ()
    {
        KartenDeck haendler1 = new KartenDeck("Haendler1Deck");
        KartenDeck haendler2 = new KartenDeck("Haendler2Deck");


    }

    /*

    haendler1 = leseDatei("src/resources/kartendecks/Haendler1.json");
            System.out.println(haendler1);
            haendler2.push(haendler1.get(2));
            haendler1.remove(2);
            System.out.println(haendler1);
            System.out.println(haendler2);

     */
}
