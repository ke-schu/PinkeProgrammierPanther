package control;

import model.KarteEinheit;
import model.KartenDeck;
import model.SpielFeld;

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
     * Beendet eine Runde und zaehlt dabei den Zugzaehler hoch.
     *
     * @param feld        Feld aus dem die Runde gespielt wird.
     * @param spielerDeck Kartendeck des Spielers.
     * @param masterDeck  Kartendeck des Dungeonmasters.
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
     * haben und legt Sie zurueck in ihr zugehoeriges Kartendeck.
     *
     * @param feld        Feld aud dem die Runde gespielt wird.
     * @param spielerDeck Kartendeck des Player.
     * @param masterDeck  Kartendeck des Dungeonmasters.
     */
    public static void feldAufraeumen(SpielFeld feld, KartenDeck spielerDeck,
                                      KartenDeck masterDeck)
    {
        for (int j = 0; j < feld.getZeilen(); j++)
        {
            for (int i = 0; i < feld.getSpalten(); i++)
            {
                if (feld.getSpielfeldplatz(i, j) != null)
                {
                    feldplatzAufraumen(feld, spielerDeck, masterDeck, i, j);
                }
            }
        }
    }

    /**
     * Diese Methode leert den Spielfeldplatz, an dem eine Einheit war,
     * nachdem sie besiegt wurde.
     *
     * @param feld        Das Feld auf dem gespielt wird.
     * @param spielerDeck Das Deck des Players.
     * @param masterDeck  Das Deck des DungeonMaster.
     * @param feldspalte  Die Position an der X-Achse.
     * @param feldzeile   Die Position an der Y-Achse.
     */
    public static void feldplatzAufraumen(SpielFeld feld,
                                          KartenDeck spielerDeck,
                                          KartenDeck masterDeck, int feldspalte,
                                          int feldzeile)
    {
        KarteEinheit sterbendeEinheit =
                feld.getSpielfeldplatz(feldspalte, feldzeile);
        if (feld.getSpielfeldplatz(feldspalte, feldzeile).getLebenspunkte() <=
            0)

        {
            sterbendeEinheit.initialisieren();
            feld.einheitLoeschen(feldspalte, feldzeile);
            EffektController.sterbenEffektAusloesen(
                    sterbendeEinheit, sterbendeEinheit.getEffektEins(), feld);
            EffektController.sterbenEffektAusloesen(
                    sterbendeEinheit, sterbendeEinheit.getEffektZwei(), feld);
            KartenController.karteInDeckEinordnen(
                    sterbendeEinheit, spielerDeck, masterDeck);
        }
    }

    /**
     * Diese Methode gibt den Karten auf dem Feld nach jedem Zug ihre
     * Beweglichkeitspunkte
     * zurueck.
     *
     * @param feld Feld aud dem die Runde gespielt wird.
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
     * Diese Methode bestimmt, welcher Spieler am Zug ist.
     */
    public static void bestimmenWerDranIst()
    {
        if (zugZaehler % SPIELER_WECHSEL_NACH_ZUEGEN == 0)
        {
            freundlich = true;
        }
        else
        {
            freundlich = false;
        }
    }

    /**
     * Diese Methode sorgt dafuer, dass die Einheiten einsatzbereit werden.
     *
     * @param feld weckt die Karten nach jedem Zug auf sodas sie im naechsten
     *             Zug wieder agieren koennen.
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
     * Gibt den Zugzaehler als Int-Wert wieder.
     *
     * @return Wert des Zuges.
     */
    public static int getZugZaehler()
    {
        return zugZaehler;
    }

    /**
     * Setzt den Int-Wert Zugzaehler.
     *
     * @param zugZaehler Wert des Zuges.
     */
    public static void setZugZaehler(int zugZaehler)
    {
        RundenController.zugZaehler = zugZaehler;
    }

    /**
     * Gibt wieder, ob eine freundliche Einheit am Zug ist.
     *
     * @return true oder false.
     */
    public static boolean isFreundlich()
    {
        return freundlich;
    }

    /**
     * Setzt den Wahrheitswert freundlich.
     *
     * @param freundlich true oder false.
     */
    public static void setFreundlich(boolean freundlich)
    {
        RundenController.freundlich = freundlich;
    }
}

