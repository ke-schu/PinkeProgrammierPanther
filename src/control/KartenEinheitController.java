package control;

import model.*;

public class KartenEinheitController
{
    public static void beschwoeren (KarteEinheit karte, Spielfeld spielfeld, int x, int y)
    {
        //benachbarte einheit abfragen nicht vergessen
        if(spielfeld.getSpielfeld()[x][y] == null)
        {
            Einheit meineeinheit = new Einheit(karte);
            spielfeld.einheiteinsetzten(x,y,meineeinheit);
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

