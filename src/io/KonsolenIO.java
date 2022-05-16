package io;

import java.util.Scanner;

/**
 * Diese Klasse ist für Eingabe- und Ausgabemethoden durch die Konsole.
 */
public class KonsolenIO
{
    /**
     * Diese Methode gibt, das uebergebene Objekt in der Konsole aus.
     * @param o auszugebenes Objekt
     */
    public static void ausgeben (Object o)
    {
        System.out.println(o);
    }

    /**
     * Diese Methode liest einen Integer ueber eine Benutzereingabe ein.
     * @return eingegebener Wert als Integer
     */
    public static int eingabeInt ()
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
