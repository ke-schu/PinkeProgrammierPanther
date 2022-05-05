package control;

import model.*;

/**
 * Klasse in der Methoden stehen um mit Instanzen von KartenEinheit zu interagieren.
 */
public class KartenEinheitController
{
    /**
     * Methode um eine Instanz von Einheit an gezielten Koordinaten zu platzieren.
     * @param karte Aus dieser Karteninstanz wird die Einheiteninstanz erstellt.
     * @param spielfeld Spielfeld in das die Einheit beschworen werden soll.
     * @param x Zielzeile der Beschwörung.
     * @param y Zielspalte der Beschwörung.
     */
    public static void beschwören (KarteEinheit karte, Spielfeld spielfeld, int x, int y)
    {
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

