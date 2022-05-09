package model.ereignisse;

public class Tempel extends Mensch
{

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Tempel. Tempel sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten aus dem Deck zu entfernen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param gratisInteraktion: Die Anzahl an kostenlosen Aufwertungen, die der Spieler zur Verfuegung hat
     */
    public Tempel (String name, String beschreibung, int gratisInteraktion)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.gratisInteraktion = gratisInteraktion;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte aus dem Deck entfernt oder
     * vorher die Zahlung durchgefuehrt.
     */
    public void ausfuehren ()
    {
        if(isAuswahl())
        {
            if(pruefeGratisInteraktion())
            {
                //Deck.entfern(Karte karte);
            }
            else
            {
                //Deck.entfernen(Schatz schatz);
                //Deck.entfernen(Karte karte);
            }
        }
    }
}
