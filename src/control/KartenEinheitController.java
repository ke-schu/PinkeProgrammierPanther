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
                positiongeben((KarteEinheit) meinekarte,x,y);
                spielfeld.einheiteinsetzten(x,y, (KarteEinheit) meinekarte);
                kartenhand.setElement(positionhand, null);
                tank.manabezahlen(((KarteEinheit) meinekarte).getManaKosten());
            }
        }
    }

    public static void beschwoerenHeld (KarteEinheit held, SpielFeld spielfeld)
    {
        if(held.getFreundlich() == true)
        {
            (held).startwertespeichern();
            positiongeben((KarteEinheit) held,ZAHL_0,ZAHL_0);
            spielfeld.einheiteinsetzten(ZAHL_0,ZAHL_0, held);
        }
        else
        {
            (held).startwertespeichern();
            positiongeben(held,spielfeld.getFeldSpalte()-ZAHL_1,spielfeld.getFeldZeile()-ZAHL_1);
            spielfeld.einheiteinsetzten(spielfeld.getFeldSpalte()-ZAHL_1,spielfeld.getFeldZeile()-ZAHL_1, held);
        }
    }

    public static void positiongeben(KarteEinheit einheit, int x, int y)
    {
        Position position = new Position(x,y);
        einheit.setPosition(position);
    }

    public static boolean freundbenachbart(int x, int y, SpielFeld spielfeld)
    {
        boolean freundlich = false;

        KarteEinheit oben = spielfeld.getSpielfeldplatz(x, y- ZAHL_1);
        KarteEinheit unten  = spielfeld.getSpielfeldplatz(x, y + ZAHL_1);
        KarteEinheit links  = spielfeld.getSpielfeldplatz(x - ZAHL_1, y);
        KarteEinheit rechts = spielfeld.getSpielfeldplatz(x + ZAHL_1, y);

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

