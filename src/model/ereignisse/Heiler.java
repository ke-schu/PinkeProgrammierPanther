package model.ereignisse;

import io.KonsolenIO;
import model.SpielStand;

import static resources.Zahlen.ZAHL_2;

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
     */
    public void ausfuehren (SpielStand spielStand)
    {
        KonsolenIO.ausgeben(this.getName());
        auswaehlen();
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                spielStand.getSpieler().setLebenspunkte(spielStand.getSpieler().getLebenspunkte() + ZAHL_2);
                gratisInteraktion--;
            }
            else
            {
                spielStand.setGold(spielStand.getGold() - kosten);
                spielStand.getSpieler().setLebenspunkte(spielStand.getSpieler().getLebenspunkte() + ZAHL_2);
            }
        }
        interaktionsZaehler++;
        kostenErhoehen();
    }
}
