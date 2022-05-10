package model.ereignisse;

import control.KartenController;
import model.KarteEinheit;
import model.KartenDeck;

public class Schmied extends Mensch
{

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Schmied. Schmiede sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten aufzuwerten um sie zu verstaerken.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param gratisInteraktion: Die Anzahl an kostenlosen Aufwertungen, die der Spieler zur Verfuegung hat
     */
    public Schmied (String name, String beschreibung, int gratisInteraktion)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.gratisInteraktion = gratisInteraktion;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte aufgewertet oder
     * vorher die Zahlung durchgefuehrt.
     */
    public KartenDeck ausfuehren (KarteEinheit karte, KartenDeck deck, int indexSchatz)
    {
        if (isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                KartenController.kartenVerbessern(karte);
            }
            else
            {
                deck.remove(indexSchatz);
                KartenController.kartenVerbessern(karte);
            }
        }
        return null;
    }
}
