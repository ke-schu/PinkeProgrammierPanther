package model.ereignisse;

import control.TalentController;
import model.Karte;
import model.SpielStand;
import utility.KonsolenIO;

import java.io.IOException;

import static resources.Konstanten.kartenDeckIO;
import static utility.KonsolenIO.eingabeInt;

/**
 * Diese Klasse ist eine Subklasse von Mensch. Ein Tempel ist ein Ereignis,
 * welches innerhalb einer Ebene angetroffen werden kann. Tempel enthaelt alle
 * Methoden aus den Superklassen und eigene Getter und Setter fuer Attribute.
 */
public class Tempel extends Mensch
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Tempel. Tempel sind
     * Ereignisse, die es dem Spieler ermoeglichen Karten aus dem Deck zu
     * entfernen.
     *
     * @param name:         Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Tempel(String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     * Der Haendler prueft ob die Interaktion eine Bezahlung erfordert. Je
     * nach Resultat wird entweder kostenlos eine Karte aus dem Deck entfernt
     * oder vorher die Zahlung durchgefuehrt.
     *
     * @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren (SpielStand spielStand, Karte karte)
    {
        if (isAuswahl())
        {
            if (pruefeGratisInteraktion())
            {
                spielStand.getSpieldeckSpieler().remove(karte);
                gratisInteraktionen--;
            }
            else
            {
                TalentController.charme(spielStand.getSpieler(), this);
                spielStand.setGold(spielStand.getGold() - this.getKosten());
                spielStand.getSpieldeckSpieler().remove(karte);
                interaktionsZaehler++;
                kostenErhoehen();
            }
            try
            {
                kartenDeckIO.schreibeDatei(spielStand.getSpieldeckSpieler());
            }
            catch (IOException e)
            {
                e.getMessage();
            }
        }
    }
}
