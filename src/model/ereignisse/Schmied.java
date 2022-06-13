package model.ereignisse;

import control.KartenController;
import control.TalentController;
import exceptions.KarteNichtVerbessertException;
import utility.KonsolenIO;
import model.SpielStand;

import static utility.KonsolenIO.eingabeInt;

/**
 * Diese Klasse ist eine Subklasse von Mensch. Ein Schmied ist ein Ereignis,
 * welches innerhalb einer Ebene angetroffen werden kann. Schmied enthaelt
 * alle Methoden aus den Superklassen und eigene Getter und Setter fuer
 * Attribute.
 */
public class Schmied extends Mensch
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Schmied. Schmiede sind
     * Ereignisse, die es dem Spieler ermoeglichen Karten aufzuwerten um sie
     * zu verstaerken.
     * @param name: Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Schmied(String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     * Der Haendler prueft ob die Interaktion eine Bezahlung erfordert. Je
     * nach Resultat wird entweder kostenlos eine Karte aufgewertet oder
     * vorher die Zahlung durchgefuehrt.
     * @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren(SpielStand spielStand)
    {
        KonsolenIO.ausgeben(this.getName());
        //auswaehlen();
        if (isAuswahl())
        {
            int indexKarte = eingabeInt();
            try
            {
                if (pruefeGratisInteraktion())
                {
                    KartenController.karteVerbessern(
                            spielStand.getSpieldeckSpieler()
                                      .get(indexKarte));
                    gratisInteraktionen--;
                }
                else
                {
                    TalentController.charme(spielStand.getSpieler(), this);
                    spielStand.setGold(spielStand.getGold() - this.getKosten());
                    KartenController.karteVerbessern(
                            spielStand.getSpieldeckSpieler()
                                      .get(indexKarte));
                    interaktionsZaehler++;
                    kostenErhoehen();
                }
            }
            catch (KarteNichtVerbessertException ausnahme)
            {
                KonsolenIO.ausgeben(ausnahme.getMessage());
            }
        }
    }
}
