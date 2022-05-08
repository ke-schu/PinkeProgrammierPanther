package control;

import model.*;

public class KartenEinheitController
{
    public static void beschwoeren (KarteEinheit karte, Spielfeld spielfeld, int x, int y)
    {
        //benachbarte einheit abfragen nicht vergessen
        if (spielfeld.getSpielfeld()[x][y] == null)
        {
            Einheit meineeinheit = new Einheit(karte);
            spielfeld.einheiteinsetzten(x,y,meineeinheit);
            meineeinheit.setPosition_x(x);
            meineeinheit.setPosition_y(y);
        }
    }

}

