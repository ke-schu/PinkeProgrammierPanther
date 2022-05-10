package control;

import model.*;

import static resources.Zahlen.*;

public class KartenEinheitController
{
    public static void beschwoeren (KarteEinheit karte, SpielFeld spielfeld, int x, int y)
    {
        if ((spielfeld.getSpielfeld()[x][y] == null) && (freundbenachbart(x,y,spielfeld)))
        {
            Einheit meineeinheit = new Einheit(karte,RundenController.getFreundlich());
            spielfeld.einheiteinsetzten(x,y,meineeinheit);
            meineeinheit.setPosition_x(x);
            meineeinheit.setPosition_y(y);
        }
    }

    public static boolean freundbenachbart(int x, int y, SpielFeld spielfeld)
    {
        boolean freundlich = false;

        Einheit oben = spielfeld.getSpielfeld()[x][y - ZAHL_1];
        Einheit unten  = spielfeld.getSpielfeld()[x][y + ZAHL_1];
        Einheit links  = spielfeld.getSpielfeld()[x - ZAHL_1][y];
        Einheit rechts = spielfeld.getSpielfeld()[x+ ZAHL_1][y];

        if(oben != null)
        {
            if(oben.getFreundlich() == true)
            {
                freundlich = true;
            }
        }
        if(unten != null)
        {
            if(unten.getFreundlich() == true)
            {
                freundlich = true;
            }
        }
        if(links != null)
        {
            if(links.getFreundlich() == true)
            {
                freundlich = true;
            }
        }
        if(rechts != null)
        {
            if(rechts.getFreundlich() == true)
            {
                freundlich = true;
            }
        }
        return freundlich;
    }
}

