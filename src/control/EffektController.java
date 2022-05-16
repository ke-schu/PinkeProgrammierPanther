package control;

import model.*;
import static resources.Zahlen.*;

public class EffektController
{
    /**
     * Löst einen neuen Effekt aus.
     * @param ausloeser die Einheit, welche den Effekt auslöst.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    public void effektAusloesen(KarteEinheit ausloeser, SpielFeld feld)
    {
        switch (ausloeser.getEffektEins())
        {
            case LETZTEWORTE: letzteWorte(ausloeser, feld); break;
        }
    }

    /**
     * Löst den Effekt "LetzteWorte" aus.
     * @param ausloeser die Einheit, welche den Effekt auslöst.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    private static void letzteWorte(KarteEinheit ausloeser, SpielFeld feld)
    {
        if (ausloeser.getLebenspunkte() == 0);
        {
            zurueckWerfen(ausloeser, feld);
        }
    }

    /**
     * Wirft die umliegenden, feindlichen Einheiten zurück.
     * @param ausloeser die Einheit, welche den Effekt auslöst.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    private static void zurueckWerfen(KarteEinheit ausloeser, SpielFeld feld)
    {
        try
        {
            KarteEinheit zieloben = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()- ZAHL_1);
            KarteEinheit platzoben = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2);

            if (zieloben != null && platzoben == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2, zieloben);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_1);
            }

            KarteEinheit zielunten = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
            KarteEinheit platzunten = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2);

            if (zielunten != null && platzunten == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2, zielunten);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
            }

            KarteEinheit ziellinks  = feld.getSpielfeldplatz(ausloeser.getPosition_x()-ZAHL_1, ausloeser.getPosition_y());
            KarteEinheit platzlinks = feld.getSpielfeldplatz(ausloeser.getPosition_x()-ZAHL_2, ausloeser.getPosition_y());

            if (ziellinks != null && platzlinks == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x() - ZAHL_2, ausloeser.getPosition_y(), ziellinks);
                feld.einheitloeschen(ausloeser.getPosition_x() - ZAHL_1, ausloeser.getPosition_y());
            }

            KarteEinheit zielrechts  = feld.getSpielfeldplatz(ausloeser.getPosition_x()+ZAHL_1, ausloeser.getPosition_y());
            KarteEinheit platzrechts = feld.getSpielfeldplatz(ausloeser.getPosition_x()+ZAHL_2, ausloeser.getPosition_y());

            if (zielrechts != null && platzrechts == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x() + ZAHL_2, ausloeser.getPosition_y(), zielrechts);
                feld.einheitloeschen(ausloeser.getPosition_x() + ZAHL_1, ausloeser.getPosition_y());
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
