package control;

import model.KartenDeck;
import model.SpielFeld;

import static resources.Effekte.LETZTEWORTE;
import static resources.Konstanten.SPIELER_WECHSEL_NACH_ZUEGEN;

/**
 * Kontrolliert eine Runde auf dem Kampffeld.
 */
public class RundenController
{
    private static int zugZaehler = 1;
    private static boolean freundlich = true;

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private RundenController()
    {
    }

    /**
     * Beendet eine Runde und zaehlt dabei den Zugzaehler hoch
     * @param feld Feld aus dem die Runde gespielt wird
     * @param spielerDeck Kartendeck des Spielers
     * @param masterDeck Kartendeck des Dungeonmasters
     */
    public static void zugBeenden(SpielFeld feld, KartenDeck spielerDeck,
                                  KartenDeck masterDeck)
    {
        zugZaehler = zugZaehler++;

        feldAufraeumen(feld, spielerDeck, masterDeck);
        beweglichkeitAuffrischen(feld);
        aufwecken(feld);
        bestimmenWerDranIst();
    }

    /**
     * Entfernt alle Karten vom Spielfeld, welche keine Lebenspunkte mehr
     * haben und legt Sie zurueck in ihr zugehoeriges Kartendeck
     * @param feld Feld aud dem die Runde gespielt wird
     * @param spielerDeck Kartendeck des spielers
     * @param masterDeck Kartendeck Dungeonmasters
     */
    public static void feldAufraeumen(SpielFeld feld, KartenDeck spielerDeck,
                                      KartenDeck masterDeck)
    {
        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if (feld.getSpielfeldplatz(i, j) != null)
                {
                    feldplatzAufraumen(feld, spielerDeck, masterDeck, i, j);
                }
            }
        }
    }

    public static void feldplatzAufraumen(SpielFeld feld, KartenDeck spielerDeck,
                                      KartenDeck masterDeck, int feldzeile, int feldspalte)
    {
        if (feld.getSpielfeldplatz(feldzeile, feldspalte).getLebenspunkte() <= 0)
        {
            if(feld.getSpielfeldplatz(feldzeile, feldspalte).getEffektEins() == LETZTEWORTE
               || feld.getSpielfeldplatz(feldzeile, feldspalte).getEffektZwei() == LETZTEWORTE)
            {
                EffektController.effektAusloesen(feld.getSpielfeldplatz(feldzeile, feldspalte), feld);
            }
            feld.getSpielfeldplatz(feldzeile, feldspalte).initialisieren();
            if (feld.getSpielfeldplatz(feldzeile, feldspalte).getFreundlich())
            {
                spielerDeck.push(feld.getSpielfeldplatz(feldzeile, feldspalte));
                KartenDeckController.mischen(spielerDeck);
                feld.einheitloeschen(feldzeile, feldspalte);
            } else
            {
                masterDeck.push(feld.getSpielfeldplatz(feldzeile, feldspalte));
                KartenDeckController.mischen(masterDeck);
                feld.einheitloeschen(feldzeile, feldspalte);
            }
        }
    }

    /**
     * gibt den Karten auf dem Feld nach jedem Zug ihre Beweglichkeitspunkte
     * zurueck
     * @param feld Feld aud dem die Runde gespielt wird
     */
    public static void beweglichkeitAuffrischen(SpielFeld feld)
    {

        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if (feld.getSpielfeldplatz(i, j) != null)
                {
                    if (feld.getSpielfeldplatz(i, j).getLebenspunkte() == 0)
                    {
                        feld.getSpielfeldplatz(i, j).setBeweglichkeit(
                                feld.getSpielfeldplatz(i, j).getInit()
                                    .getBeweglichkeit());
                    }
                }

            }
        }
    }

    /**
     * bestimmt, welcher Spieler dran ist
     */
    public static void bestimmenWerDranIst()
    {
        if (zugZaehler % SPIELER_WECHSEL_NACH_ZUEGEN == 0)
        {
            freundlich = true;
        } else
        {
            freundlich = false;
        }
    }

    /**
     * @param feld weckt die Karten nach jedem Zug auf sodas sie im naechsten
     * Zug wieder agieren koennen
     */
    public static void aufwecken(SpielFeld feld)
    {
        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if (feld.getSpielfeldplatz(i, j) != null)
                {
                    feld.getSpielfeldplatz(i, j).setSchlafend(false);
                }
            }
        }

    }

    /**
     * Gibt den Zugzaehler als Int-Wert wieder
     * @return Wert des Zuges
     */
    public static int getZugZaehler()
    {
        return zugZaehler;
    }

    /**
     * Setzt den Int-Wert Zugzaehler
     * @param zugZaehler Wert des Zuges
     */
    public static void setZugZaehler(int zugZaehler)
    {
        RundenController.zugZaehler = zugZaehler;
    }

    /**
     * Gibt wieder, ob eine freundliche Einheit am Zug ist.
     * @return true oder false
     */
    public static boolean isFreundlich()
    {
        return freundlich;
    }

    /**
     * Setzt den Wahrheitswert freundlich
     * @param freundlich true oder false
     */
    public static void setFreundlich(boolean freundlich)
    {
        RundenController.freundlich = freundlich;
    }
}

