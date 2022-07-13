package model.ereignisse;

import control.EbeneController;
import model.SpielStand;

import java.io.IOException;

import static resources.Konstanten.spielStandIO;

/**
 Diese Klasse ist eine Subklasse von Ereignis. Eine Treppe ist nur als
 Uebergang in die naechste Ebene fuer den Spieler gedacht. Treppe enthaelt
 alle
 Methoden aus den Superklassen und eigene Getter und Setter fuer Attribute. */
public class Treppe extends Ereignis
{
    /**
     Der Konstruktor erstellt ein Ereignis vom Typ Treppe. Treppen sind
     Ereignisse, die es dem Spieler ermoeglichen seine aktuelle Ebene zu
     verlassen und die naechst hoehere zu betreten.
     @param name: Der Name des Ereignisses.
     @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Treppe (String name, String beschreibung)
    {
        super(name, beschreibung);
    }
    
    /**
     Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     Wird
     diese Methode ausgefuehrt, dann wird der Name der Treppe ausgegeben.
     @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren (SpielStand spielStand)
    {
        try
        {
            spielStand.setAktuelleEbeneNummer(
                    spielStand.getAktuelleEbeneNummer() + 1);
            spielStandIO.schreibeDatei(spielStand);
            EbeneController.ueberschreibeAktuelleEbene(
                    spielStand.getAktuelleEbeneNummer());
        }
        catch (IOException e)
        {
            e.getMessage();
        }
    }
}
