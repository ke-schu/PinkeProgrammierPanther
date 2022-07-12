package control;

import model.*;

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
                                             KarteEinheit ziel,
                                             KartenHand hand,int handposition,
                                             SpielFeld feld,
                                             KartenDeck spielerDeck,
                                             KartenDeck masterDeck)

    {
        hand.setElement(handposition, null);

        switch (ausloeser.getZeffekt())
        {
            case WURFSPEER:
                wurfspeer(ausloeser ,ziel, feld, spielerDeck, masterDeck);
                break;
            case HEILUNG:
                heilen(ausloeser, ziel, feld, spielerDeck, masterDeck);
            default:
                return;
        }
    }

    /**
     * Diese Methode wird zu einem späteren Zeitpunkt ausgefuehrt.
     */
    private static void wurfspeer(KarteZauber angreifer, KarteEinheit verteidiger, SpielFeld feld, KartenDeck spielerDeck, KartenDeck masterdeck)
    {
        verteidiger.schadenNehmen(angreifer.getMacht());
        RundenController.feldplatzAufraumen(feld,
                                            spielerDeck,
                                            masterdeck,
                                            verteidiger.getPositionX(),
                                            verteidiger.getPositionY());
    }

    private static void heilen(KarteZauber angreifer, KarteEinheit ziel, SpielFeld feld, KartenDeck spielerDeck, KartenDeck masterdeck)
    {
        ziel.heilen(angreifer.getMacht());
        RundenController.feldplatzAufraumen(feld,
                                            spielerDeck,
                                            masterdeck,
                                            ziel.getPositionX(),
                                            ziel.getPositionY());
    }
}
