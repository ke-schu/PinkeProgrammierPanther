package model.ereignisse;

import control.KartenController;
import control.TalentController;
import exceptions.KarteNichtVerbessertException;
import model.Karte;
import model.SpielStand;
import utility.KonsolenIO;

import java.io.IOException;

import static resources.Konstanten.kartenDeckIO;

/**
 Diese Klasse ist eine Subklasse von Mensch. Ein Schmied ist ein Ereignis,
 welches innerhalb einer Ebene angetroffen werden kann. Schmied enthaelt alle
 Methoden aus den Superklassen und eigene Getter und Setter fuer Attribute. */
public class Schmied extends Mensch
{
    /**
     Der Konstruktor erstellt ein Ereignis vom Typ Schmied. Schmiede sind
     Ereignisse, die es dem Spieler ermoeglichen Karten aufzuwerten um sie zu
     verstaerken.
     @param name: Der Name des Ereignisses.
     @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Schmied (String name, String beschreibung)
    {
        super(name, beschreibung);
    }
    
    /**
     Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis". Der
     Haendler prueft ob die Interaktion eine Bezahlung erfordert. Je nach
     Resultat wird entweder kostenlos eine Karte aufgewertet oder vorher die
     Zahlung durchgefuehrt.
     @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren (SpielStand spielStand)
    {
        if (isAuswahl())
        {
            try
            {
                if (pruefeGratisInteraktion())
                {
                    gratisInteraktionen--;
                }
                else
                {
                    TalentController.charme(spielStand.getSpieler(), this);
                    spielStand.setGold(
                            spielStand.getGold() - this.getKosten());
                    interaktionsZaehler++;
                    kostenErhoehen();
                }
            }
            catch (KarteNichtVerbessertException ausnahme)
            {
                KonsolenIO.ausgeben(ausnahme.getMessage());
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
    
    /**
     Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis". Der
     Haendler prueft ob die Interaktion eine Bezahlung erfordert. Je nach
     Resultat wird entweder kostenlos eine Karte aufgewertet oder vorher die
     Zahlung durchgefuehrt.
     @param spielStand der aktuelle Spielstand und seine Attribute.
     @param karte Karte, die verbessert werden soll.
     */
    public void ausfuehren (SpielStand spielStand, Karte karte)
    {
        if (isAuswahl())
        {
            try
            {
                if (pruefeGratisInteraktion())
                {
                    KartenController.karteVerbessern(karte);
                    gratisInteraktionen--;
                }
                else
                {
                    TalentController.charme(spielStand.getSpieler(), this);
                    spielStand.setGold(
                            spielStand.getGold() - this.getKosten());
                    KartenController.karteVerbessern(karte);
                    interaktionsZaehler++;
                    kostenErhoehen();
                }
            }
            catch (KarteNichtVerbessertException ausnahme)
            {
                KonsolenIO.ausgeben(ausnahme.getMessage());
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
