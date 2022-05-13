package control;

import model.*;

import static resources.Zahlen.*;

public class KartenEinheitController
{
    /**
     * @param kartenhand Hand aus welcher die Karte auf das Spielfeld gelegt wird
     * @param positionhand Stelle auf der hand an welcher sich die ausgewehlte karte befindet
     * @param spielfeld spielfeld auf welches die karte gelegt wird
     * @param x zeile im spielfeld
     * @param y spalte im spielfeld
     * @param tank zu verfügung stehende manareserve
     */
    public static void beschwoeren (KartenHand kartenhand, int positionhand, SpielFeld spielfeld, int x, int y, ManaTank tank)
    {
        if ((spielfeld.getSpielfeld()[x][y] == null) && (freundbenachbart(x,y,spielfeld)))
        {
            Karte meinekarte = kartenhand.getelement(positionhand);
            if(meinekarte instanceof KarteEinheit && (tank.getMana()>=((KarteEinheit) meinekarte).getManaKosten()) )
            {
                ((KarteEinheit) meinekarte).startwertespeichern();
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

