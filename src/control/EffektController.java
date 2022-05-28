package control;

import io.KonsolenIO;
import model.KarteEinheit;
import model.SpielFeld;

/**
 * Loest die Effekte der Karten aus und kontrolliert diese.
 */
public class EffektController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EffektController()
    {
    }

    /**
     * Loest einen neuen Effekt aus.
     * @param ausloeser die Einheit, welche den Effekt ausloest.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    public void effektAusloesen(KarteEinheit ausloeser, SpielFeld feld)
    {
        switch (ausloeser.getEffektEins())
        {
            case LETZTEWORTE:
                letzteWorte(ausloeser, feld);
                break;
            default:
                return;
        }
    }

    /**
     * Loest den Effekt "LetzteWorte" aus.
     * @param ausloeser die Einheit, welche den Effekt ausloest.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    private static void letzteWorte(KarteEinheit ausloeser, SpielFeld feld)
    {
        if (ausloeser.getLebenspunkte() == 0) ;
        {
            zurueckWerfen(ausloeser, feld);
        }
    }

    /**
     * Wirft die umliegenden, feindlichen Einheiten zurueck.
     * @param ausloeser die Einheit, welche den Effekt ausloest.
     * @param feld das Spielfeld, auf dem gespielt wird.
     */
    private static void zurueckWerfen(KarteEinheit ausloeser, SpielFeld feld)
    {
        final int umkreis1 = 1;
        final int umkreis2 = 2;

        KarteEinheit zielOben = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() - umkreis1);
        KarteEinheit platzOben = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() - umkreis2);
        KarteEinheit zielUnten = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() + umkreis1);
        KarteEinheit platzUnten = feld.getSpielfeldplatz(
                ausloeser.getPositionX(), ausloeser.getPositionY() + umkreis2);
        KarteEinheit zielLinks = feld.getSpielfeldplatz(
                ausloeser.getPositionX() - umkreis1, ausloeser.getPositionY());
        KarteEinheit platzLinks = feld.getSpielfeldplatz(
                ausloeser.getPositionX() - umkreis2, ausloeser.getPositionY());
        KarteEinheit zielRechts = feld.getSpielfeldplatz(
                ausloeser.getPositionX() + umkreis1, ausloeser.getPositionY());
        KarteEinheit platzRechts = feld.getSpielfeldplatz(
                ausloeser.getPositionX() + umkreis2, ausloeser.getPositionY());

        try
        {
            if (zielOben != null && platzOben == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX(),
                                       ausloeser.getPositionY() - umkreis2,
                                       zielOben);
                feld.einheitloeschen(ausloeser.getPositionX(),
                                     ausloeser.getPositionY() - umkreis1);
            }

            if (zielUnten != null && platzUnten == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX(),
                                       ausloeser.getPositionY() + umkreis2,
                                       zielUnten);
                feld.einheitloeschen(ausloeser.getPositionX(),
                                     ausloeser.getPositionY() + umkreis1);
            }

            if (zielLinks != null && platzLinks == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX() - umkreis2,
                                       ausloeser.getPositionY(), zielLinks);
                feld.einheitloeschen(ausloeser.getPositionX() - umkreis1,
                                     ausloeser.getPositionY());
            }

            if (zielRechts != null && platzRechts == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX() + umkreis2,
                                       ausloeser.getPositionY(), zielRechts);
                feld.einheitloeschen(ausloeser.getPositionX() + umkreis1,
                                     ausloeser.getPositionY());
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
