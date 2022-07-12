package utility;

import java.util.Random;
import com.google.common.net.InetAddresses;

/**
 * Enthaelt universal einsetzbare Methoden fuer das ganze Programm.
 */
public class UtilityController
{
    /**
     * Stellt eine zufaellige Zahl im angegebenen Bereich zur Verfügung
     *
     * @param bereichMin Minwert der erstellten Zahl
     * @param bereichMax Maxwert der erstellten Zahl
     * @return die Zufallszahl
     */
    public static int randomZahlMitBereich(int bereichMin, int bereichMax)
    {
        Random ran = new Random();
        int bereich = bereichMax - bereichMin;
        return bereichMin + ran.nextInt(bereich + 1);
    }

    public static boolean isGueltigeIP(String ip)
    {
        return InetAddresses.isInetAddress(ip) || ip.equals("localhost");
    }
}
