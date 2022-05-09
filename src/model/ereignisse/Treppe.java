package model.ereignisse;

public class Treppe extends EreignisKlasse {

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Treppe. Treppen sind Ereignisse, die es dem
     * Spieler ermoeglichen seine aktuelle Ebene zu verlassen und die nächsthöhere zu betreten.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param position: Die Position auf der Oberkarte
     */
    public Treppe (String name, String beschreibung, int position)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.position = position;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Wird diese Methode ausgefuehrt,
     * dann wechselt der Spieler die Ebene, auf der er sich befindet, zur nächst höheren.
     */
    public void ausfuehren ()
    {

    }
}
