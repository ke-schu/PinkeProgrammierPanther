package Alex;

import java.util.function.*;

public class KartenEinheitController
{
    public static void ausspielen(KarteEinheit karte)
    {

    }

    public static void ausspielen(KarteEinheit karte, Einheit ziel)
    {
        Effekt effekt = karte.getEffekt();

        switch (effekt.getTyp())
        {
            case SCHADEN -> EffektController.schadenVerursachen(ziel, effekt.getSchaden());
        }

    }
}

