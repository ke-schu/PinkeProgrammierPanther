package control;

import model.*;

import static control.EinheitenController.verursacheSchaden;
import static resources.KonstantenGUI.*;
import static resources.StringsEreignisse.SCHADEN_WURFSPEER;

/**
 * Diese Klasse formuliert Methoden der Zauberkarten und Effekte aus.
 */
public class ZauberEffektController
{
    /**
     * Diese Methode ueberprueft, welcher Effekt der ZauberKarte vorliegt und
     * ausgefuehrt werden soll.
     *
     * @param ausloeser   Die ausloesende ZauberKarte.
     * @param ziel        Das Ziel, welches angegriffen wird.
     * @param feld        Das Spielfeld auf dem gespielt wird.
     * @param spielerDeck Das Deck des Players.
     * @param masterDeck  Das Deck des DungeonMaster.
     */
    public static int zauberKarteAusspielen(KarteZauber ausloeser,
                                             KarteEinheit ziel,
                                             KartenHand hand,int handposition,
                                             SpielFeld feld,
                                             KartenDeck spielerDeck,
                                             KartenDeck masterDeck)

    {
        hand.setElement(handposition, null);
        int rueckmeldung = RUECKMELDUNG_ERFOLGLOS;

        switch (ausloeser.getZeffekt())
        {
            case WURFSPEER:
                rueckmeldung = wurfspeer(ausloeser ,ziel, feld, spielerDeck, masterDeck);
                break;
            case HEILUNG:
                rueckmeldung = heilen(ausloeser, ziel, feld, spielerDeck, masterDeck);
                break;
            case VERSTAERKEN:
                rueckmeldung = verstaerken(ausloeser, ziel, feld, spielerDeck, masterDeck);
                break;
            default:

        }
        return rueckmeldung;
    }

    /**
     * Diese Methode wird zu einem späteren Zeitpunkt ausgefuehrt.
     */
    private static int wurfspeer(KarteZauber angreifer, KarteEinheit verteidiger, SpielFeld feld, KartenDeck spielerDeck, KartenDeck masterdeck)
    {
        if(angreifer.getFreundlich()!= verteidiger.getFreundlich())
        {
            verteidiger.schadenNehmen(angreifer.getMacht());
            boolean rückmeldung =RundenController.feldplatzAufraumen(feld,
                    spielerDeck,
                    masterdeck,
                    verteidiger.getPositionX(),
                    verteidiger.getPositionY());
            if (rückmeldung)
            {
                return RUECKMELDUNG_GESTORBEN ;
            }
            else return RUECKMELDUNG_SCHADEN;
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }

    private static int heilen(KarteZauber angreifer, KarteEinheit ziel, SpielFeld feld, KartenDeck spielerDeck, KartenDeck masterdeck)
    {
        if(angreifer.getFreundlich() == ziel.getFreundlich())
        {
            ziel.heilen(angreifer.getMacht());
            RundenController.feldplatzAufraumen(feld, spielerDeck, masterdeck,
                    ziel.getPositionX(), ziel.getPositionY());
            return RUECKMELDUNG_SCHADEN;
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }

    private static int verstaerken(KarteZauber angreifer, KarteEinheit ziel, SpielFeld feld, KartenDeck spielerDeck, KartenDeck masterdeck)
    {
        if(angreifer.getFreundlich() == ziel.getFreundlich())
        {
            ziel.angriffErhoehen(angreifer.getMacht());
            RundenController.feldplatzAufraumen(feld, spielerDeck, masterdeck,
                    ziel.getPositionX(), ziel.getPositionY());
            return RUECKMELDUNG_SCHADEN;
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }
}
