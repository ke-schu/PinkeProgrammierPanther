package control;

import io.KonsolenIO;
import model.KarteEinheit;
import model.KartenDeck;
import model.Position;
import model.SpielFeld;
import resources.Effekte;

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
    public static void aktioneffektausloesen(KarteEinheit ausloeser,KarteEinheit ziel,Effekte meineffekt, SpielFeld feld)
    {
        switch (meineffekt)
        {
            case OPFERN:
                //opfern();
                break;
            default:
                return;
        }
    }
    public static void sterbeneffektausloesen(KarteEinheit ausloeser,Effekte meineffekt, SpielFeld feld)
    {
        switch (meineffekt)
        {
            case KOPIE:
                kopie(ausloeser, feld);
                break;
            case HELDENTAT:
               // heldentat();
                break;
            case ZURUECKWERFEN:
                zurueckWerfen(ausloeser, feld);
                break;
            default:
                return;
        }
    }
    public static void angriffeffektAusloesen(KarteEinheit ausloeser,KarteEinheit ziel,
                                              Effekte meineffekt, SpielFeld feld,
                                              KartenDeck spielerDeck,KartenDeck masterDeck)
    {
            switch (meineffekt)
            {
                case RAUBTIER:
                    raubtier(ausloeser,ziel);
                    break;
                case DURCHSCHNEIDEN:
                    durchschneiden(ausloeser, ziel, feld, spielerDeck, masterDeck);
                    break;
                case VERSCHLINGEN:
                   // verschlingen();
                    break;
                default:
                    return;
            }
    }

    public static void starteffektausloesen(KarteEinheit ausloeser, Effekte meineffekt)
    {
        switch (meineffekt)
        {
            case SPRINT:
                sprint(ausloeser);
                break;
            case EILE:
                eile(ausloeser);
                break;
            default:
                return;
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

    private static void sprint(KarteEinheit ausloeser)
    {
        ausloeser.setBeweglichkeit(ausloeser.getBeweglichkeit()+1);
    }

    private static void raubtier(KarteEinheit ausloeser, KarteEinheit ziel)
    {

        if((ausloeser.getMacht() > ziel.getMacht()) && ausloeser.getZaehler() == 0)
        {
            ausloeser.setZaehler(1);
            ausloeser.setSchlafend(false);
        }
    }

    private static void eile(KarteEinheit ausloeser)
    {
        ausloeser.setSchlafend(false);
    }

    private static void kopie(KarteEinheit ausloeser, SpielFeld feld)
    {
        feld.einheitEinsetzten(ausloeser.getPositionX(), ausloeser.getPositionY(),ausloeser.kopieerstelen(ausloeser));
    }
    private static void durchschneiden(KarteEinheit ausloeser,KarteEinheit ziel,
                                       SpielFeld feld, KartenDeck spielerDeck,
                                       KartenDeck masterDeck)
    {
        Position positonhinterziel = EinheitenController.positionhinterkarteberechnen(ausloeser, ziel, feld);
        boolean imfeld =  EinheitenController.positioninnerhalbvonfeld(positonhinterziel,feld);
        if(imfeld)
        {
            EinheitenController.einheitenAngreifenMitEinheiten(feld, spielerDeck,masterDeck,ausloeser,
                    feld.getSpielfeldplatz(positonhinterziel.getX(),positonhinterziel.getY()));
        }
    }

}
