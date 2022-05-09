package control.test;
import model.*;

/**
 * In dieser Klasse liegen Methoden, die Code testen sollen.
 */
public class Hendrik
{
    public static void ausfuehren ()
    {
        Ebene level = new Ebene(5,5);
        level.erstelleEbene(1);
        System.out.println(level.toString());

    }

}
