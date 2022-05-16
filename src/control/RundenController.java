package control;

import model.KartenDeck;
import model.SpielFeld;

import static resources.Zahlen.*;

/**
 * Kontrolliert eine Runde auf dem Kampffeld.
 */
public class RundenController
{
    private static int zugZaehler = ZAHL_1;
    private static boolean freundlich = true;

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private RundenController ()
    {
    }

    /**
     * Beendet eine Runde und zaehlt dabei den Zugzaehler hoch
     * @param feld Feld aus dem die Runde gespielt wird
     * @param spielerDeck Kartendeck des Spielers
     * @param masterDeck Kartendeck des Dungeonmasters
     */
    public static void  zugBeenden (SpielFeld feld,KartenDeck spielerDeck, KartenDeck masterDeck)
    {
        zugZaehler = zugZaehler + ZAHL_1;

        feldAufraeumen(feld, spielerDeck, masterDeck);
        beweglichkeitAuffrischen(feld);
        aufwecken(feld);
        bestimmenWerDranIst();
    }

    /**
     * Entfernt alle Karten vom Spielfeld, welche keine Lebenspunkte mehr haben
     * und legt Sie zurueck in ihr zugehoeriges Kartendeck
     * @param feld Feld aud dem die Runde gespielt wird
     * @param spielerDeck Kartendeck des spielers
     * @param masterDeck Kartendeck Dungeonmasters
     */
    public static void feldAufraeumen (SpielFeld feld, KartenDeck spielerDeck, KartenDeck masterDeck)
    {
        for (int i = 0; i < feld.getFeldZeile(); i++)
        {
            for (int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if (feld.getSpielfeldplatz(i,j) != null)
                {
                    if (feld.getSpielfeldplatz(i,j).getLebenspunkte() <= ZAHL_0)
                    {
                        feld.getSpielfeldplatz(i,j).initialisieren();
                        if (feld.getSpielfeldplatz(i, j).getFreundlich())
                        {
                            spielerDeck.push( feld.getSpielfeldplatz(i,j));
                            KartenDeckController.mischen(spielerDeck);
                            feld.einheitloeschen(i,j);
                        }
                        else
                        {
                            masterDeck.push( feld.getSpielfeldplatz(i,j));
                            KartenDeckController.mischen(masterDeck);
                            feld.einheitloeschen(i,j);
                        }
                    }
                }
            }
        }
    }

    /**
     * gibt den Karten auf dem Feld nach jedem Zug ihre Beweglichkeitspunkte zurueck
     * @param feld Feld aud dem die Runde gespielt wird
     */
    public static void beweglichkeitAuffrischen (SpielFeld feld)
    {

        for (int i = 0; i < feld.getFeldZeile(); i++)
        {
            for (int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if(feld.getSpielfeldplatz(i,j) != null)
                {
                    if (feld.getSpielfeldplatz(i,j).getLebenspunkte() == ZAHL_0)
                    {
                        feld.getSpielfeldplatz(i,j).setBeweglichkeit(feld.getSpielfeldplatz(i,j).getInit().getBeweglichkeit());
                    }
                }

            }
        }
    }

    /**
     * bestimmt, welcher Spieler dran ist
     */
    public static void bestimmenWerDranIst ()
    {
        if(zugZaehler % ZAHL_2 == ZAHL_0)
        {
            freundlich = true;
        }
        else
        {
            freundlich = false;
        }
    }

    /**
     * @param feld weckt die Karten nach jedem Zug auf sodas sie im naechsten Zug wieder agieren koennen
     */
    public static void aufwecken (SpielFeld feld)
    {
        for(int i = 0; i < feld.getFeldZeile(); i++)
        {
            for(int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if (feld.getSpielfeldplatz(i,j) != null)
                {
                    feld.getSpielfeldplatz(i,j).setSchlafend(false);
                }
            }
        }

    }

    /**
     * Gibt den Zugzaehler als Int-Wert wieder
     * @return Wert des Zuges
     */
    public static int getZugZaehler ()
    {
        return zugZaehler;
    }

    /**
     * Setzt den Int-Wert Zugzaehler
     * @param zugZaehler Wert des Zuges
     */
    public static void setZugZaehler (int zugZaehler)
    {
        RundenController.zugZaehler = zugZaehler;
    }

    /**
     * Gibt wieder, ob eine freundliche Einheit am Zug ist.
     * @return true oder false
     */
    public static boolean isFreundlich ()
    {
        return freundlich;
    }

    /**
     * Setzt den Wahrheitswert freundlich
     * @param freundlich true oder false
     */
    public static void setFreundlich (boolean freundlich)
    {
        RundenController.freundlich = freundlich;
    }
}

