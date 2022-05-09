package model.ereignisse;

public class Haendler extends Mensch
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler. Haendler sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten zu dem bestehenden Deck hinzuzufuegen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param gratisInteraktion: Die Anzahl an kostenlosen Transaktionen, die der Spieler zur Verfuegung hat
     */
    public Haendler (String name, String beschreibung, int gratisInteraktion)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.gratisInteraktion = gratisInteraktion;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte dem Deck hinzugefuegt oder
     * vorher die Zahlung durchgefuehrt.
     */

    public void ausfuehren ()
    {
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                //Deck.erweitern(Karte karte);
            }
            else
            {
                //Deck.entfernen(Schatz schatz);
                //Deck.erweitern(Karte karte);
            }
        }
    }
}
