package control;

import model.*;

public class KartenEinheitController
{
    public static void beschwören (KarteEinheit karte, Spielfeld spielfeld, int x, int y)
    {
        if(spielfeld.getSpielfeld()[x][y] == null)
        {
            spielfeld.setSpielfeld
        }

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

