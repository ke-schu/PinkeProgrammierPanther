package control;

import model.*;
import utility.KonsolenIO;

/**
 Kontrolliert KartenEinheiten und enthaelt Methoden zum Beschwoeren der
 Einheiten. */
public class KartenEinheitController
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenEinheitController ()
    {
    }
    
    /**
     Legt Karte auf Spielfeld und ueberprueft ob die dafuer noetigen
     Voraussetzungen gegeben sind
     @param kartenhand Hand aus welcher die Karte auf das Spielfeld gelegt
     wird
     @param positionhand Stelle, auf der hand an welcher sich die ausgewaehlte
     Karte befindet
     @param spielfeld Spielfeld, auf welches die Karte gelegt wird
     @param x Zeile im spielfeld
     @param y Spalte im spielfeld
     @param tank zu Verfuegung stehende Mana-Reserve
     */
    public static ManaTank beschwoeren (KartenHand kartenhand,
                                        int positionhand,
                                        SpielFeld spielfeld, int x, int y,
                                        ManaTank tank)
    {
        if ((spielfeld.getSpielfeldplatz(x, y) == null) &&
            (freundBenachbart(x, y, spielfeld,
                              kartenhand.getElement(positionhand))))
        {
            Karte meineKarte = kartenhand.getElement(positionhand);
            if (meineKarte instanceof KarteEinheit &&
                (tank.getMana() >=
                 ((KarteEinheit) meineKarte).getManaKosten()))
            {
                ((KarteEinheit) meineKarte).startWerteSpeichern();
                positionGeben((KarteEinheit) meineKarte, x, y);
                spielfeld.einheitEinsetzten(x, y, (KarteEinheit) meineKarte);
                kartenhand.setElement(positionhand, null);
                tank.manaBezahlen(
                        ((KarteEinheit) meineKarte).getManaKosten());
                EffektController.startEffektAusloesen(
                        (KarteEinheit) meineKarte,
                        ((KarteEinheit) meineKarte).getEffektEins());
                EffektController.startEffektAusloesen(
                        (KarteEinheit) meineKarte,
                        ((KarteEinheit) meineKarte).getEffektZwei());
                
                return tank;
            }
        }
        return tank;
    }
    
    /**
     weist einer Karte eine Position zu
     @param einheit Karte welcher eine Position gegeben wird
     @param x x Position im Spielfeld
     @param y y Position im Spielfeld
     */
    private static void positionGeben (KarteEinheit einheit, int x, int y)
    {
        Position position = new Position(x, y);
        einheit.setPosition(position);
    }
    
    /**
     Gibt an, ob befreundete Karten an eine Position grenzen
     @param x Position in welcher Umgebung nach
     befreundeten Karten gesucht wird
     @param y Position in welcher Umgebung nach
     befreundeten Karten gesucht wird
     @param spielfeld Spielfeld, in dem gesucht wird
     @return true, wenn eine befreundete Karte in der Umgebung ist
     */
    public static boolean freundBenachbart (int x, int y, SpielFeld spielfeld,
                                            Karte zubeschwoeren)
    {
        boolean befreundet = false;
        final int umkreis = 1;
        
        KarteEinheit oben = spielfeld.getSpielfeldplatz(x, y - umkreis);
        KarteEinheit unten = spielfeld.getSpielfeldplatz(x, y + umkreis);
        KarteEinheit links = spielfeld.getSpielfeldplatz(x - umkreis, y);
        KarteEinheit rechts = spielfeld.getSpielfeldplatz(x + umkreis, y);
        
        if (oben != null)
        {
            if (oben.getFreundlich() == zubeschwoeren.getFreundlich())
            {
                befreundet = true;
            }
        }
        if (unten != null)
        {
            if (unten.getFreundlich() == zubeschwoeren.getFreundlich())
            {
                befreundet = true;
            }
        }
        if (links != null)
        {
            if (links.getFreundlich() == zubeschwoeren.getFreundlich())
            {
                befreundet = true;
            }
        }
        if (rechts != null)
        {
            if (rechts.getFreundlich() == zubeschwoeren.getFreundlich())
            {
                befreundet = true;
            }
        }
        return befreundet;
    }
    
    public static boolean bewegenErfolgreich (SpielFeld spielfeld,
                                              Karte aktuellekarte,
                                              int feldspaltenindex,
                                              int feldzeilenindex)
    {
        Karte karteAufSpielfeld =
                spielfeld.getSpielfeldplatz(feldspaltenindex,
                                            feldzeilenindex);
        if (karteAufSpielfeld == aktuellekarte)
        {
            return true;
        }
        return false;
    }
    
    /**
     Legt Spieler- und Gegenspieler-Karte zu Beginn einer Runde an ihre
     vorgesehenen Plaetze
     @param held Karte, welche gelegt werden soll
     @param spielfeld auf dem die Karten gelegt werden
     */
    public static void beschwoerenHelden (KarteEinheit held,
                                          SpielFeld spielfeld)
    {
        if (held.getFreundlich())
        {
            held.startWerteSpeichern();
            
            int spaltenindex = spielfeld.getSpalten() - 1;
            int zeilenindex = spielfeld.getZeilen() - 1;
            
            positionGeben(held, spaltenindex,
                          zeilenindex);
            spielfeld.einheitEinsetzten(spaltenindex,
                                        zeilenindex, held);
        }
        else
        {
            held.startWerteSpeichern();
            positionGeben(held, 0, 0);
            spielfeld.einheitEinsetzten(0, 0, held);
        }
    }
}

