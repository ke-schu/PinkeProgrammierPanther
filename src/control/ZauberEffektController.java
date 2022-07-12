package control;

import model.KarteEinheit;
import model.KarteZauber;
import model.KartenDeck;
import model.SpielFeld;

import static control.EinheitenController.verursacheSchaden;
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
    public static void zauberKarteAusspielen(KarteZauber ausloeser,
                                             KarteEinheit ziel, SpielFeld feld,
                                             KartenDeck spielerDeck,
                                             KartenDeck masterDeck)
    {
        switch (ausloeser.getZeffekt())
        {
            case WURFSPEER:
                wurfspeer(ziel, feld, spielerDeck, masterDeck);
                break;
            default:
                return;
        }
    }

    /**
     * Diese Methode wird zu einem späteren Zeitpunkt ausgefuehrt.
     */
    private static void wurfspeer(KarteEinheit verteidiger,SpielFeld feld,KartenDeck spielerDeck, KartenDeck masterdeck  )
    {
    verursacheSchaden(verteidiger, SCHADEN_WURFSPEER);
    RundenController.feldplatzAufraumen(feld,
                                        spielerDeck,
                                        masterdeck,
                                        verteidiger.getPositionX(),
                                        verteidiger.getPositionY());
    }
}
