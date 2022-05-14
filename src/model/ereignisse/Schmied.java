package model.ereignisse;

import control.KartenController;
import model.KarteEinheit;
import model.SpielStand;

public class Schmied extends Mensch
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Schmied. Schmiede sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten aufzuwerten um sie zu verstaerken.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Schmied (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte aufgewertet oder
     * vorher die Zahlung durchgefuehrt.
     * @param spielStand
     * @param karte
     * @return
     */
    public void ausfuehren (SpielStand spielStand, KarteEinheit karte)
    {
        if(isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                KartenController.kartenVerbessern(karte);
                gratisInteraktion--;
            }
            else
            {
                spielStand.setGold(spielStand.getGold() - kosten);
                KartenController.kartenVerbessern(karte);
                interaktionsZaehler++;
                kostenErhoehen();
            }
        }
    }
}
