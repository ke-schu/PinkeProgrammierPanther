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
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private EffektController ()
    {
    }

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
                ausloeser.getPositionX(), ausloeser.getPositionY() - ZAHL_1);
        KarteEinheit platzOben = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() - ZAHL_2);
        KarteEinheit zielUnten = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() + ZAHL_1);
        KarteEinheit platzUnten = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() + ZAHL_2);
        KarteEinheit zielLinks  = feld.getSpielfeldplatz(
                ausloeser.getPositionX() - ZAHL_1, ausloeser.getPositionY());
        KarteEinheit platzLinks = feld.getSpielfeldplatz(
                ausloeser.getPositionX() - ZAHL_2, ausloeser.getPositionY());
        KarteEinheit zielRechts  = feld.getSpielfeldplatz(
                ausloeser.getPositionX() + ZAHL_1, ausloeser.getPositionY());
        KarteEinheit platzRechts = feld.getSpielfeldplatz(
                ausloeser.getPositionX() + ZAHL_2, ausloeser.getPositionY());

        try
        {
            if (zielOben != null && platzOben == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX(), ausloeser.getPositionY()-ZAHL_2, zielOben);
                feld.einheitloeschen(ausloeser.getPositionX(), ausloeser.getPositionY()-ZAHL_1);
            }

            if (zielUnten != null && platzUnten == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX(), ausloeser.getPositionY()+ZAHL_2, zielUnten);
                feld.einheitloeschen(ausloeser.getPositionX(), ausloeser.getPositionY()+ZAHL_1);
            }

            if (zielLinks != null && platzLinks == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX() - ZAHL_2, ausloeser.getPositionY(), zielLinks);
                feld.einheitloeschen(ausloeser.getPositionX() - ZAHL_1, ausloeser.getPositionY());
            }

            if (zielRechts != null && platzRechts == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX() + ZAHL_2, ausloeser.getPositionY(), zielRechts);
                feld.einheitloeschen(ausloeser.getPositionX() + ZAHL_1, ausloeser.getPositionY());
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
