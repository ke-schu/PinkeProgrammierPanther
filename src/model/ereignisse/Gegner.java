package model.ereignisse;

import io.KonsolenIO;
import model.Gegenspieler;
import model.SpielFeld;
import model.SpielStand;

/**
 * Diese Klasse ist eine Subklasse von Ereignis. Ein Gegner ist ein Ereignis, welches innerhalb einer Ebene angetroffen
 * werden kann und bei Begegnung zu einem Kampf fuehren kann.
 * Gegner enthaelt alle Methoden aus den Superklassen und eigene Getter und Setter fuer Attribute.
 */
public class Gegner extends Ereignis
{
    private Gegenspieler gegenspieler;
    private SpielFeld spielfeld;

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Gegner. Gegner sind Ereignisse, die es dem
     * Spieler ermoeglichen, einen Kampf zu beginnen.
     * @param name Der Name des Gegners
     * @param beschreibung Die Beschreibung des Gegners
     * @param gegenspieler Die Eigenschaften des Gegenspielers
     * @param spielfeld Das Spielfeld des Gegners
     */
    public Gegner(String name, String beschreibung, Gegenspieler gegenspieler, SpielFeld spielfeld)
    {
        super(name, beschreibung);
        this.gegenspieler = gegenspieler;
        this.spielfeld = spielfeld;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Sobald die Methode ausgefuert wird
     * wird der Name des Gegners ausgegeben und ein Spielfeld wird erzeugt.
     * @param spielStand der aktuelle Spielstand und seine Attribute
     */
    public void ausfuehren (SpielStand spielStand)
    {
        KonsolenIO.ausgeben(this.getName());
        auswaehlen();
        if (isAuswahl())
        {
            spielfeld = new SpielFeld();
        }
    }

    /**
     * Diese Methode dient als Getter um auf den Gegenspieler zuzugreifen
     * @return den aktuellen Gegenspieler
     */
    public Gegenspieler getGegenspieler()
    {
        return gegenspieler;
    }
}
