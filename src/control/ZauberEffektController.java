package control;

import model.*;

import static resources.KonstantenGUI.*;

/**
 Diese Klasse formuliert Methoden der Zauberkarten und Effekte aus. */
public class ZauberEffektController
{
    /**
     Diese Methode ueberprueft, welcher Effekt der ZauberKarte vorliegt und
     ausgefuehrt werden soll.
     @param ausloeser Die ausloesende ZauberKarte.
     @param ziel Das Ziel, welches angegriffen wird.
     @param feld Das Spielfeld auf dem gespielt wird.
     @param spielerDeck Das Deck des Spielers.
     @param gegnerDeck Das Deck des Gegenspielers.
     */
    public static int zauberKarteAusspielen (
            KarteZauber ausloeser, KarteEinheit ziel, KartenHand hand,
            int handposition, SpielFeld feld,
            KartenDeck spielerDeck, KartenDeck gegnerDeck)
    {
        
        int rueckmeldung = RUECKMELDUNG_ERFOLGLOS;
        
        switch (ausloeser.getZeffekt())
        {
            case WURFSPEER:
                rueckmeldung = wurfspeer(ausloeser, ziel, feld, spielerDeck,
                                         gegnerDeck);
                break;
            case HEILUNG:
                rueckmeldung = heilen(ausloeser, ziel, feld, spielerDeck,
                                      gegnerDeck);
                break;
            case VERSTAERKEN:
                rueckmeldung = verstaerken(ausloeser, ziel, feld, spielerDeck,
                                           gegnerDeck);
                break;
            default:
            
        }
        if (rueckmeldung != RUECKMELDUNG_ERFOLGLOS)
        {
            hand.setElement(handposition, null);
        }
        return rueckmeldung;
    }
    
    /**
     Diese Methode wird durch die Karte Wurfspeer ausgeloest und fuegt einer gegnerischen Karte Schaden zu
     * @param angreifer Die Karte welche angreift
     * @param verteidiger Die Karte welche angegriffen wird
     * @param feld Das spielfeld auf welchem sich die Karten befinden
     * @param spielerDeck Das Kartendeck des Spielers
     * @param gegnerDeck Das Kartendeck des Gegenspielers
     * @return
     */
    private static int wurfspeer (
            KarteZauber angreifer, KarteEinheit verteidiger, SpielFeld feld,
            KartenDeck spielerDeck, KartenDeck gegnerDeck)
    {
        if (angreifer.getFreundlich() != verteidiger.getFreundlich())
        {
            verteidiger.schadenNehmen(angreifer.getMacht());
            boolean rueckmeldung = RundenController.feldplatzAufraumen(
                    feld, spielerDeck, gegnerDeck,
                    verteidiger.getPositionX(), verteidiger.getPositionY());
            if (rueckmeldung)
            {
                return RUECKMELDUNG_GESTORBEN;
            }
            else
            {
                return RUECKMELDUNG_SCHADEN;
            }
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }
    
    /**
     Diese Methode wird durch die Karte Heilen ausgeloest und erhoeht
     die Lebenspunkte einer befreundeten Einheit.
     * @param angreifer die KarteZauber, welche den Effekt auslöst
     * @param ziel die zu heilende Karte
     * @param feld das Spielfeld
     * @param spielerDeck das Deck des Spielers
     * @param gegnerDeck das Deck des Gegenspielers
     * @return eine Rueckmeldung, ob der Effekt erfolgreich war
     */
    private static int heilen (
            KarteZauber angreifer, KarteEinheit ziel,
            SpielFeld feld, KartenDeck spielerDeck, KartenDeck gegnerDeck)
    {
        if (angreifer.getFreundlich() == ziel.getFreundlich())
        {
            ziel.heilen(angreifer.getMacht());
            RundenController.feldplatzAufraumen(feld, spielerDeck, gegnerDeck,
                                                ziel.getPositionX(),
                                                ziel.getPositionY());
            return RUECKMELDUNG_SCHADEN;
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }
    
    /**
     Diese Methode wird durch die Karte Verstaerken ausgeloest und
     erhoeht die Macht einer befreundeten Einheit
     * @param angreifer die KarteZauber, welche den Effekt auslöst
     * @param ziel die zu verstaerkende Karte
     * @param feld das Spielfeld
     * @param spielerDeck das Deck des Spielers
     * @param gegnerDeck das Deck des Gegenspielers
     * @return eine Rueckmeldung, ob der Effekt erfolgreich war
     */
    private static int verstaerken (KarteZauber angreifer, KarteEinheit ziel,
                                    SpielFeld feld, KartenDeck spielerDeck,
                                    KartenDeck gegnerDeck)
    {
        if (angreifer.getFreundlich() == ziel.getFreundlich())
        {
            ziel.angriffErhoehen(angreifer.getMacht());
            RundenController.feldplatzAufraumen(feld, spielerDeck, gegnerDeck,
                                                ziel.getPositionX(),
                                                ziel.getPositionY());
            return RUECKMELDUNG_SCHADEN;
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }
}
