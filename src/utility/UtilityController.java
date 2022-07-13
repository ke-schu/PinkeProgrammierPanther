package utility;

import com.google.common.net.InetAddresses;
import static resources.Strings.LOCALHOST;

import java.util.Random;

/**
 Enthaelt universal einsetzbare Methoden fuer das ganze Programm. */
public class UtilityController
{
    /**
     Stellt eine zufaellige Zahl im angegebenen Bereich zur Verfuegung
     @param bereichMin Minwert der erstellten Zahl
     @param bereichMax Maxwert der erstellten Zahl
     @return die Zufallszahl
     */
    public static int randomZahlMitBereich (int bereichMin, int bereichMax)
    {
        Random ran = new Random();
        int bereich = bereichMax - bereichMin;
        return bereichMin + ran.nextInt(bereich + 1);
    }
    
    /**
     Prueft, ob eine IP-Adresse gueltig ist.
     * @param ip IP, die geprueft werden soll.
     * @return Gibt einen booleschen Wert zurueck, ob die gepruefte IP-Adresse gueltig ist.
     */
    public static boolean isGueltigeIP (String ip)
    {
        return InetAddresses.isInetAddress(ip) || ip.equals("localhost");
    }
}
