package model.ereignisse;

import model.KarteEinheit;
import model.SpielStand;
import model.Spieler;

public class Heiler extends Mensch
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Heiler. Heiler sind Ereignisse, die es dem
     * Spieler ermöglichen seine Lebenspunkte zu regenerieren, wenn er vorher Schaden genommen hat.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Heiler (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Heiler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat werden die Lebenspunkte des Spielers kostenlos oder gegen
     * eine Bezahlung regeneriert.
     * @param spielStand:
     * @param spieler:
     */
    public Spieler ausfuehren (SpielStand spielStand, Spieler spieler)
    {
        auswaehlen();
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                //spieler.setLebenspunkte();
                gratisInteraktion--;
            }
            else
            {
                spielStand.setGold(spielStand.getGold() - kosten);
                //spieler.setLebenspunkte();
            }
        }
        interaktionsZaehler++;
        kostenErhoehen();
        return spieler;
    }
}
