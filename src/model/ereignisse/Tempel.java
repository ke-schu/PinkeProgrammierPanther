package model.ereignisse;

import model.KartenDeck;
import model.SpielStand;

public class Tempel extends Mensch
{

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Tempel. Tempel sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten aus dem Deck zu entfernen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Tempel (String name, String beschreibung)
    {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte aus dem Deck entfernt oder
     * vorher die Zahlung durchgefuehrt.
     */
    public void ausfuehren (SpielStand spielStand, int indexKarte)
    {
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                spielStand.getSpieldeckSpieler().remove(indexKarte);
                gratisInteraktion--;
            }
            else
            {
                spielStand.setGold(spielStand.getGold() - kosten);
                spielStand.getSpieldeckSpieler().remove(indexKarte);
                interaktionsZaehler++;
                kostenErhoehen();
            }
        }
    }
}
