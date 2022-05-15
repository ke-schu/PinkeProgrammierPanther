package model.ereignisse;

import model.KartenDeck;
import model.SpielStand;

/**
 *
 */
public class Haendler extends Mensch
{
    protected KartenDeck haendlerDeck = null;   //Das Deck des Haendlers, aus dem der Spieler Karten kaufen kann.

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler. Haendler sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten zu dem bestehenden Deck hinzuzufuegen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param haendlerDeck: Das Deck aus dem der Spieler neue Karten kaufen kann
     */
    public Haendler (String name, String beschreibung, KartenDeck haendlerDeck)
    {
        super(name, beschreibung);
        this.haendlerDeck = haendlerDeck;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte dem Deck hinzugefuegt oder
     * vorher die Zahlung durchgefuehrt.
     * @param spielStand
     * @param kartenPosition
     */
    public void ausfuehren (SpielStand spielStand, int kartenPosition)
    {
        auswaehlen();
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                spielStand.getSpieldeckSpieler().push(haendlerDeck.get(kartenPosition));
                haendlerDeck.remove(kartenPosition);
                gratisInteraktion--;
            }
            else
            {
                spielStand.setGold(spielStand.getGold() - kosten);
                spielStand.getSpieldeckSpieler().push(haendlerDeck.get(kartenPosition));
                haendlerDeck.remove(kartenPosition);
                interaktionsZaehler++;
                kostenErhoehen();
            }
        }

    }
}
