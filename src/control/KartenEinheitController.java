package control;

import model.*;

/**
 * Klasse in der Methoden stehen um mit Instanzen von KartenEinheit zu interagieren.
 */
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

    /**
     * Methode um nicht plazierbare Karten mit Effekten zu benutzen.
     * @param karte Karte deren Effekt benutzt werden soll.
     * @param ziel Einheit die Ziel der Aktion ist.
     */
    public static void ausspielen(KarteEinheit karte, Einheit ziel)
    {
        Effekt effekt = karte.getKartenEffekt();

        switch (effekt.getTyp())
        {
            //case SCHADEN -> EffektController.schadenVerursachen(ziel, effekt.getSchaden());
        }

    }
}

