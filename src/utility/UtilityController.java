package utility;

import java.util.Random;

import static resources.Konstanten.SPIELFELD_GENERATOR_MAX;
import static resources.Konstanten.SPIELFELD_GENERATOR_MIN;

public class UtilityController
{
    /**
     * stellt eine random Zahl im angegebenen Bereich zur verfügung
     * @param bereichmin Minwert der erstellten Zahl
     * @param bereichmax Maxwert der erstellten Zahl
     * @return
     */
    public static int randomzahlmitbereich(int bereichmin, int bereichmax)
    {
        Random ran = new Random();
        int bereich = bereichmax- bereichmin;
        return bereichmin + ran.nextInt(bereich + 1);
    }
}
