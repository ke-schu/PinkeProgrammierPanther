package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Diese Klasse ist fuer Eingabe- und Ausgabemethoden durch die Konsole.
 */
public class KonsolenIO
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KonsolenIO()
    {
    }

    /**
     * Diese Methode gibt, das uebergebene Objekt in der Konsole aus.
     * @param o auszugebendes Objekt
     */
    public static void ausgeben(Object o)
    {
        System.out.println(o);
    }

    /**
     * Diese Methode liest einen Integer ueber eine Benutzereingabe ein.
     * @return eingegebener Wert als Integer
     */
    public static int eingabeInt()
    {
        Scanner sc = new Scanner(System.in);
        int temp = sc.nextInt();
        return temp;
    }

    /**
     * Diese Methode liest einen Boolean ueber eine Benutzereingabe ein.
     * @return eingegebener Wert als Boolean
     */
    public static boolean eingabeBoolean()
    {
        Scanner sc = new Scanner(System.in);
        boolean temp = sc.nextBoolean();
        return temp;
    }
}
