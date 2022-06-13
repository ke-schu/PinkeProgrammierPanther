package model.ereignisse;

import utility.KonsolenIO;
import model.SpielStand;

import static resources.StringsEreignisse.LEERER_RAUM_BESCHREIBUNG;


/**
 * Diese Klasse ist eine Subklasse von Ereignis. Ein Leerer Raum ist nur als
 * Durchgangsraum fuer den Spieler gedacht. LeererRaum enthaelt alle Methoden
 * aus den Superklassen und eigene Getter und Setter fuer Attribute.
 */
public class LeererRaum extends Ereignis
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Leerer Raum. Leere Raeume
     * sind fuer den Spieler nur zum durchqueren gedacht, ansonsten geschieht
     * in ihnen nichts.
     * @param name: Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public LeererRaum(String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     * Sie zeigt die Eigenschaft des leeren Raumes an.
     * @param spielStand: Spielstand Datei.
     */
    public void ausfuehren(SpielStand spielStand)
    {
        KonsolenIO.ausgeben(LEERER_RAUM_BESCHREIBUNG);
    }
}
