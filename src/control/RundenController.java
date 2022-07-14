package control;

import model.*;

import java.util.Objects;

import static resources.Konstanten.LEBENSPUNKTE_TOD;
import static resources.Konstanten.SPIELER_WECHSEL_NACH_ZUEGEN;
import static resources.Strings.GEGENSPIELER_KLASSE;
import static resources.Strings.SPIELER_KLASSE;

/**
 Kontrolliert eine Runde auf dem Kampffeld. */
public class RundenController
{
    private static int zugZaehler = 0;
    
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private RundenController ()
    {
    }
    
    /**
     Beendet eine Runde und zaehlt dabei den Zugzaehler hoch.
     @param feld Feld aus dem die Runde gespielt wird.
     */
    public static void zugBeenden (SpielFeld feld)
    {
        zugZaehler++;
        beweglichkeitAuffrischen(feld);
        aufwecken(feld);
    }
    
    /**
     Diese Methode gibt den Karten auf dem Feld nach jedem Zug ihre
     Beweglichkeitspunkte zurueck.
     @param feld Feld aud dem die Runde gespielt wird.
     */
    public static void beweglichkeitAuffrischen (SpielFeld feld)
    {
        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if (feld.getSpielfeldplatz(i, j) != null)
                {
                    feld.getSpielfeldplatz(i, j).setBeweglichkeit(
                            feld.getSpielfeldplatz(i, j).getInit()
                                .getBeweglichkeit());
                }
            }
        }
    }
    
    /**
     Diese Methode sorgt dafuer, dass die Einheiten einsatzbereit werden.
     @param feld weckt die Karten nach jedem Zug auf, sodass sie im naechsten
     Zug wieder agieren koennen.
     */
    public static void aufwecken (SpielFeld feld)
    {
        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if (feld.getSpielfeldplatz(j, i) != null)
                {
                    feld.getSpielfeldplatz(j, i).setSchlafend(false);
                }
            }
        }
    }
    
    /**
     Diese Methode leert den Spielfeldplatz, an dem eine Einheit war, nachdem
     sie besiegt wurde.
     @param feld Das Feld auf dem gespielt wird.
     @param spielerDeck Das Deck des Spielers.
     @param gegnerDeck Das Deck des Gegenspielers.
     @param feldspalte Die Position an der X-Achse.
     @param feldzeile Die Position an der Y-Achse.
     */
    public static boolean feldplatzAufraumen (SpielFeld feld,
                                              KartenDeck spielerDeck,
                                              KartenDeck gegnerDeck,
                                              int feldspalte,
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
                    sterbendeEinheit, spielerDeck, gegnerDeck);
            return true;
        }
        return false;
    }
    
    /**
     Diese Methode durchsucht ein Spielfeld nach dem Spieler und Gegenspieler
     und synchronisiert die Werte des Spielers/Gegenspielers, aus der
     Parameterliste mit dem Spieler/Gegenspieler im Spielfeld.
     @param feld das zu durchsuchende Spielfeld
     @param spieler der Spieler
     @param gegner der Gegenspieler
     */
    public static void synchronisiereFeldUndHelden (SpielFeld feld,
                                                    Spieler spieler,
                                                    Gegenspieler gegner)
    {
        if (!sucheEinheitNachKlasse(feld, SPIELER_KLASSE, spieler))
        {
            spieler.setLebenspunkte(LEBENSPUNKTE_TOD);
            return;
        }
        if (!sucheEinheitNachKlasse(feld, GEGENSPIELER_KLASSE, gegner))
        {
            gegner.setLebenspunkte(LEBENSPUNKTE_TOD);
            return;
        }
    }
    
    /**
     Sucht eine Einheit im Spielfeld mithilfe ihrer Klasse und aktualisiert
     ihre Attribute mit der nachfolgenden aktualisiereEinheit-Methode.
     @param feld das zu durchsuchende Spielfeld
     @param klasse die Klasse der Karte als String
     @param karte die zu bearbeitende Karte
     @return true, wenn die Einheit gefunden wurde
     */
    private static boolean sucheEinheitNachKlasse (SpielFeld feld,
                                                   String klasse,
                                                   KarteEinheit karte)
    {
        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if (feld.getSpielfeldplatz(j, i) != null &&
                    Objects.equals(feld.getSpielfeldplatz(j, i).getKlasse(),
                                   klasse))
                {
                    kopiereEinheit(feld.getSpielfeldplatz(j, i), karte);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     Kopiert die Werte eine KarteEinheit in eine andere bestehende
     KarteEinheit.
     @param quelle die Ausgangskarte
     @param ziel die Zielkarte
     */
    private static void kopiereEinheit (KarteEinheit quelle,
                                        KarteEinheit ziel)
    {
        ziel.setLebenspunkte(quelle.getLebenspunkte());
        ziel.setPosition(quelle.getPosition());
        ziel.setVerteidigung(quelle.getVerteidigung());
        ziel.setSchlafend(quelle.getSchlafend());
    }
    
    /**
     Gibt den Zugzaehler als Int-Wert wieder.
     @return Wert des Zuges.
     */
    public static int getZugZaehler ()
    {
        return zugZaehler;
    }
    
    /**
     Setzt den Int-Wert Zugzaehler.
     @param zugZaehler Wert des Zuges.
     */
    public static void setZugZaehler (int zugZaehler)
    {
        RundenController.zugZaehler = zugZaehler;
    }
    
    /**
     Gibt wieder, ob eine freundliche Einheit am Zug ist.
     @return true oder false.
     */
    public static boolean getDran ()
    {
        return zugZaehler % SPIELER_WECHSEL_NACH_ZUEGEN == 0;
    }
}

