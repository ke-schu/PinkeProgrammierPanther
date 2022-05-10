package model.ereignisse;

import model.KartenDeck;
import model.SpielStand;

public class Haendler extends Mensch
{
    protected KartenDeck haendlerDeck = null;

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler. Haendler sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten zu dem bestehenden Deck hinzuzufuegen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param gratisInteraktion: Die Anzahl an kostenlosen Transaktionen, die der Spieler zur Verfuegung hat
     */
    public Haendler (String name, String beschreibung, int gratisInteraktion, KartenDeck haendlerDeck)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.gratisInteraktion = gratisInteraktion;
        this.haendlerDeck = haendlerDeck;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte dem Deck hinzugefuegt oder
     * vorher die Zahlung durchgefuehrt.
     */
    public KartenDeck ausfuehren (KartenDeck spielDeck, int kartenPosition)
    {
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                spielDeck.push(haendlerDeck.get(kartenPosition));
                haendlerDeck.remove(kartenPosition);
            }
            else
            {
                spielDeck.push(haendlerDeck.get(kartenPosition));
                haendlerDeck.remove(kartenPosition);
                //Deck.entfernen(Schatz schatz);
            }
        }
        return spielDeck;
    }
}
