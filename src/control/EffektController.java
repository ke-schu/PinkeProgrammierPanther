package control;

import io.KonsolenIO;
import model.KarteEinheit;
import model.SpielFeld;
import static resources.Zahlen.*;

/**
 * Löst die Effekte der Karten aus und kontrolliert diese.
 */
public class EffektController
{
    /**
     * Löst einen neuen Effekt aus.
     * @param ausloeser die Einheit, welche den Effekt auslöst.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    public void effektAusloesen (KarteEinheit ausloeser, SpielFeld feld)
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
    private static void letzteWorte (KarteEinheit ausloeser, SpielFeld feld)
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
    private static void zurueckWerfen (KarteEinheit ausloeser, SpielFeld feld)
    {
        KarteEinheit zielOben = feld.getSpielfeldplatz(
                ausloeser.getPosition_x(), ausloeser.getPosition_y() - ZAHL_1);
        KarteEinheit platzOben = feld.getSpielfeldplatz(
                ausloeser.getPosition_x(), ausloeser.getPosition_y() - ZAHL_2);
        KarteEinheit zielUnten = feld.getSpielfeldplatz(
                ausloeser.getPosition_x(), ausloeser.getPosition_y() + ZAHL_1);
        KarteEinheit platzUnten = feld.getSpielfeldplatz(
                ausloeser.getPosition_x(), ausloeser.getPosition_y() + ZAHL_2);
        KarteEinheit zielLinks  = feld.getSpielfeldplatz(
                ausloeser.getPosition_x() - ZAHL_1, ausloeser.getPosition_y());
        KarteEinheit platzLinks = feld.getSpielfeldplatz(
                ausloeser.getPosition_x() - ZAHL_2, ausloeser.getPosition_y());
        KarteEinheit zielRechts  = feld.getSpielfeldplatz(
                ausloeser.getPosition_x() + ZAHL_1, ausloeser.getPosition_y());
        KarteEinheit platzRechts = feld.getSpielfeldplatz(
                ausloeser.getPosition_x() + ZAHL_2, ausloeser.getPosition_y());

        try
        {
            if (zielOben != null && platzOben == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2, zielOben);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_1);
            }

            if (zielUnten != null && platzUnten == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2, zielUnten);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
            }

            if (zielLinks != null && platzLinks == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x() - ZAHL_2, ausloeser.getPosition_y(), zielLinks);
                feld.einheitloeschen(ausloeser.getPosition_x() - ZAHL_1, ausloeser.getPosition_y());
            }

            if (zielRechts != null && platzRechts == null)
            {
                feld.einheitEinsetzten(ausloeser.getPosition_x() + ZAHL_2, ausloeser.getPosition_y(), zielRechts);
                feld.einheitloeschen(ausloeser.getPosition_x() + ZAHL_1, ausloeser.getPosition_y());
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
