package control;

import model.*;

import static resources.Konstanten.*;

/**
 * Kontrolliert KartenEinheiten und enthaelt Methoden zum Beschwoeren der
 * Einheiten.
 */
public class KartenEinheitController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenEinheitController()
    {
    }

    /**
     * Legt Karte auf Spielfeld und ueberprueft ob die dafuer noetigen
     * Voraussetzungen gegeben sind
     * @param kartenhand Hand aus welcher die Karte auf das Spielfeld gelegt
     * wird
     * @param positionhand Stelle, auf der hand an welcher sich die
     * ausgewaehlte Karte befindet
     * @param spielfeld Spielfeld, auf welches die Karte gelegt wird
     * @param x Zeile im spielfeld
     * @param y Spalte im spielfeld
     * @param tank zu Verfuegung stehende Mana-Reserve
     */
    public static void beschwoeren(KartenHand kartenhand, int positionhand,
                                   SpielFeld spielfeld, int x, int y,
                                   ManaTank tank)
    {
        if ((spielfeld.getSpielfeld()[x][y] == null) &&
            (freundBenachbart(x, y, spielfeld)))
        {
            Karte meineKarte = kartenhand.getElement(positionhand);
            if (meineKarte instanceof KarteEinheit && (tank.getMana() >=
                                                       ((KarteEinheit) meineKarte).getManaKosten()))
            {
                ((KarteEinheit) meinekarte).startwertespeichern();
                positionGeben((KarteEinheit) meinekarte, x, y);
                spielfeld.einheitEinsetzten(x, y, (KarteEinheit) meinekarte);
                kartenhand.setElement(positionhand, null);
                tank.manaBezahlen(
                        ((KarteEinheit) meineKarte).getManaKosten());
                EffektController.startEffektAusloesen((KarteEinheit)meineKarte, ((KarteEinheit) meineKarte).getEffektEins());
                EffektController.startEffektAusloesen((KarteEinheit)meineKarte, ((KarteEinheit) meineKarte).getEffektZwei());
            }
        }
    }



    /**
     * Legt Held- und Dungeonmasterkarte zu Beginn einer runde an ihre
     * vorgesehenen Plaetze
     * @param held HeldKarte welche gelegt werden soll
     * @param spielfeld auf dem die Karten gelegt werden
     */
    public static void beschwoerenHelden(KarteEinheit held, SpielFeld spielfeld)
    {
        if (held.getFreundlich())
        {
            held.startwertespeichern();
            positionGeben(held, SPIELER_KAMPFFELD_STARTPOSITION_X,
                          SPIELER_KAMPFFELD_STARTPOSITION_Y);
            spielfeld.einheitEinsetzten(SPIELER_KAMPFFELD_STARTPOSITION_X,
                                        SPIELER_KAMPFFELD_STARTPOSITION_Y,
                                        held);
        } else
        {
            held.startwertespeichern();
            positionGeben(held, spielfeld.getSpalten() -
                                GEGNER_KAMPFFELD_STARTPOSITION_X,
                          spielfeld.getZeilen() -
                          GEGNER_KAMPFFELD_STARTPOSITION_Y);
            spielfeld.einheitEinsetzten(spielfeld.getSpalten() -
                                        GEGNER_KAMPFFELD_STARTPOSITION_X,
                                        spielfeld.getZeilen() -
                                        GEGNER_KAMPFFELD_STARTPOSITION_Y,
                                        held);
        }
    }

    /**
     * weist einer Karte eine Position zu
     * @param einheit Karte welcher eine Position gegeben wird
     * @param x x Position im Spielfeld
     * @param y y Position im Spielfeld
     */
    private static void positionGeben(KarteEinheit einheit, int x, int y)
    {
        Position position = new Position(x, y);
        einheit.setPosition(position);
    }

    /**
     * Gibt an, ob befreundete Karten an eine Position grenzen
     * @param x Position in welcher Umgebung nach befreundeten Karten gesucht
     * wird
     * @param y Position in welcher Umgebung nach befreundeten Karten gesucht
     * wird
     * @param spielfeld Spielfeld in dem gesucht wird
     * @return boolean ob befreundetet karten in der umgebung ist
     */
    public static boolean freundBenachbart(int x, int y, SpielFeld spielfeld)
    {
        boolean freundlich = false;
        final int umkreis = 1;

        KarteEinheit oben = spielfeld.getSpielfeldplatz(x, y - umkreis);
        KarteEinheit unten = spielfeld.getSpielfeldplatz(x, y + umkreis);
        KarteEinheit links = spielfeld.getSpielfeldplatz(x - umkreis, y);
        KarteEinheit rechts = spielfeld.getSpielfeldplatz(x + umkreis, y);

        if (oben != null)
        {
            if (oben.getFreundlich())
            {
                freundlich = true;
            }
        }
        if (unten != null)
        {
            if (unten.getFreundlich())
            {
                freundlich = true;
            }
        }
        if (links != null)
        {
            if (links.getFreundlich())
            {
                freundlich = true;
            }
        }
        if (rechts != null)
        {
            if (rechts.getFreundlich())
            {
                freundlich = true;
            }
        }
        return freundlich;
    }
}

