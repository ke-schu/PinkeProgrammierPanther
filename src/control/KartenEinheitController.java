package control;

import model.*;

import static resources.Zahlen.ZAHL_0;
import static resources.Zahlen.ZAHL_1;

/**
 * Kontrolliert KartenEinheiten und enthaelt Methoden zum Beschwoeren der Einheiten.
 */
public class KartenEinheitController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private KartenEinheitController ()
    {
    }

    /**
     * @param kartenhand Hand aus welcher die Karte auf das Spielfeld gelegt wird
     * @param positionhand Stelle, auf der hand an welcher sich die ausgewaehlte Karte befindet
     * @param spielfeld Spielfeld, auf welches die Karte gelegt wird
     * @param x Zeile im spielfeld
     * @param y Spalte im spielfeld
     * @param tank zu Verfügung stehende Mana-Reserve
     */
    public static void beschwoeren (KartenHand kartenhand, int positionhand,
                                    SpielFeld spielfeld, int x, int y, ManaTank tank)
    {
        if ((spielfeld.getSpielfeld()[x][y] == null) && (freundBenachbart(x,y,spielfeld)))
        {
            Karte meinekarte = kartenhand.getelement(positionhand);
            if(meinekarte instanceof KarteEinheit && (tank.getMana()>=((KarteEinheit) meinekarte).getManaKosten()) )
            {
                ((KarteEinheit) meinekarte).startwertespeichern();
                positionGeben((KarteEinheit) meinekarte,x,y);
                spielfeld.einheitEinsetzten(x,y, (KarteEinheit) meinekarte);
                kartenhand.setElement(positionhand, null);
                tank.manaBezahlen(((KarteEinheit) meinekarte).getManaKosten());
            }
        }
    }

    public static void beschwoerenHeld (KarteEinheit held, SpielFeld spielfeld)
    {
        if(held.getFreundlich())
        {
            (held).startwertespeichern();
            positionGeben((KarteEinheit) held,ZAHL_0,ZAHL_0);
            spielfeld.einheitEinsetzten(ZAHL_0,ZAHL_0, held);
        }
        else
        {
            (held).startwertespeichern();
            positionGeben(held,spielfeld.getFeldSpalte()-ZAHL_1,spielfeld.getFeldZeile()-ZAHL_1);
            spielfeld.einheitEinsetzten(spielfeld.getFeldSpalte()-ZAHL_1,spielfeld.getFeldZeile()-ZAHL_1, held);
        }
    }

    public static void positionGeben (KarteEinheit einheit, int x, int y)
    {
        Position position = new Position(x,y);
        einheit.setPosition(position);
    }

    public static boolean freundBenachbart (int x, int y, SpielFeld spielfeld)
    {
        boolean freundlich = false;

        KarteEinheit oben = spielfeld.getSpielfeldplatz(x, y- ZAHL_1);
        KarteEinheit unten  = spielfeld.getSpielfeldplatz(x, y + ZAHL_1);
        KarteEinheit links  = spielfeld.getSpielfeldplatz(x - ZAHL_1, y);
        KarteEinheit rechts = spielfeld.getSpielfeldplatz(x + ZAHL_1, y);

        if(oben != null)
        {
            if(oben.getFreundlich())
            {
                freundlich = true;
            }
        }
        if(unten != null)
        {
            if(unten.getFreundlich())
            {
                freundlich = true;
            }
        }
        if(links != null)
        {
            if(links.getFreundlich())
            {
                freundlich = true;
            }
        }
        if(rechts != null)
        {
            if(rechts.getFreundlich())
            {
                freundlich = true;
            }
        }

        return freundlich;
    }
}

