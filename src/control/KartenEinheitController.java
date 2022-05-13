package control;

import model.*;

import static resources.Zahlen.*;

public class KartenEinheitController
{
    public static void beschwoeren (KartenHand kartenhand, int positionhand, SpielFeld spielfeld, int x, int y, ManaTank tank)
    {//Karteeinheit aus Hand rausholen und auf spielfeld legten
        if ((spielfeld.getSpielfeld()[x][y] == null) && (freundbenachbart(x,y,spielfeld)))
        {
            Karte meinekarte = kartenhand.getelement(positionhand);
            if(meinekarte instanceof KarteEinheit && (tank.getMana()>=((KarteEinheit) meinekarte).getManaKosten()) )
            {
                spielfeld.einheiteinsetzten(x,y, (KarteEinheit) meinekarte);
                kartenhand.setElement(positionhand, null);
                tank.manabezahlen(((KarteEinheit) meinekarte).getManaKosten());
            }
        }
    }

    public static boolean freundbenachbart(int x, int y, SpielFeld spielfeld)
    {
        boolean freundlich = false;

        KarteEinheit oben = spielfeld.getSpielfeld()[x][y - ZAHL_1];
        KarteEinheit unten  = spielfeld.getSpielfeld()[x][y + ZAHL_1];
        KarteEinheit links  = spielfeld.getSpielfeld()[x - ZAHL_1][y];
        KarteEinheit rechts = spielfeld.getSpielfeld()[x+ ZAHL_1][y];

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

