package control;

import model.KarteEinheit;
import model.KarteZauber;
import model.KartenDeck;
import model.SpielFeld;
import resources.Effekte;

public class ZauberEffektController
{
    public static void zauberkarteausspielen (KarteZauber ausloeser, KarteEinheit ziel, SpielFeld feld,
                                              KartenDeck spielerDeck, KartenDeck masterDeck)
    {
        switch (ausloeser.getZeffekt())
        {
            case WURFSPEER:
                //opfern();
                break;
            default:
                return;
        }
    }

    //private static void wurfspee

}
