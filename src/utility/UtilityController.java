package utility;

import java.util.Random;

/**
 * Enthaelt universal einsetzbare Methoden fuer das ganze Programm.
 */
public class UtilityController
{
    /**
     * Stellt eine zufaellige Zahl im angegebenen Bereich zur Verfügung
     * @param bereichMin Minwert der erstellten Zahl
     * @param bereichMax Maxwert der erstellten Zahl
     * @return die Zufallszahl
     */
    public static int randomZahlMitBereich(int bereichMin, int bereichMax)
    {
        Random ran = new Random();
        int bereich = bereichMax- bereichMin;
        return bereichMin + ran.nextInt(bereich + 1);
    }

    /**
     * Ersetzt ASCII-Charakter-Sequenzen mit ihren Umlauten.
     * @param s der Eingabestring
     */
   private void ersetzeUmlaute(String s)
    {
        s.replaceAll("ae", "ä");
        s.replaceAll("ue", "ü");
        s.replaceAll("oe", "ö");
        s.replaceAll("ss", "ß");
    }
}
