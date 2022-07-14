package model.ereignisse;

import control.TalentController;
import model.SpielStand;

import static resources.Konstanten.HEILER_AUFWERTUNG_LEBENSPUNKTE;

/**
 Diese Klasse ist eine Subklasse von Mensch. Ein Heiler ist ein Ereignis,
 welches innerhalb einer Ebene angetroffen werden kann. Heiler enthaelt alle
 Methoden aus den Superklassen und eigene Getter und Setter fuer Attribute. */
public class Heiler extends Mensch
{
    /**
     Der Konstruktor erstellt ein Ereignis vom Typ Heiler. Heiler sind
     Ereignisse, die es dem Spieler ermoeglichen seine Lebenspunkte zu
     regenerieren, wenn er vorher Schaden genommen hat.
     @param name: Der Name des Ereignisses.
     @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Heiler (String name, String beschreibung)
    {
        super(name, beschreibung);
    }
    
    /**
     Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis". Der
     Heiler prueft ob die Interaktion eine Bezahlung erfordert. Je nach
     Resultat werden die Lebenspunkte des Spielers kostenlos oder gegen eine
     Bezahlung regeneriert.
     @param spiel der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren (SpielStand spiel)
    {
        if (isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                spiel.getSpieler().getMaxLeben();
                spiel.getSpieler().setLebenspunkte(
                        spiel.getSpieler().getLebenspunkte() +
                        HEILER_AUFWERTUNG_LEBENSPUNKTE);
                gratisInteraktionen--;
            }
            else if (spiel.getGold() >= this.getKosten())
            {
                TalentController.charme(spiel.getSpieler(), this);
                spiel.setGold(spiel.getGold() - this.getKosten());
                if (spiel.getSpieler().getLebenspunkte() +
                    HEILER_AUFWERTUNG_LEBENSPUNKTE <
                    spiel.getSpieler().getMaxLeben())
                {
                    spiel.getSpieler().setLebenspunkte(
                            spiel.getSpieler().getLebenspunkte() +
                            HEILER_AUFWERTUNG_LEBENSPUNKTE);
                }
                interaktionsZaehler++;
                kostenErhoehen();
            }
        }
    }
}
