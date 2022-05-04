package control;

import model.*;

public class KartenEinheitController
{
    public static void ausspielen(KarteEinheit karte)
    {

    }

    public static void ausspielen(KarteEinheit karte, Einheit ziel)
    {
        Effekt effekt = karte.getKartenEffekt();

        switch (effekt.getTyp())
        {
            //case SCHADEN -> EffektController.schadenVerursachen(ziel, effekt.getSchaden());
        }

    }
}

