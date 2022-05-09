package model.ereignisse;

public class Gegner extends EreignisKlasse {

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Gegner. Gegner sind Ereignisse, die es dem
     * Spieler ermoeglichen, einen Kampf zu beginnen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param position: Die Position auf der Oberkarte
     */
    public Gegner (String name, String beschreibung, int position) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.position = position;
    }
    /**
     *
     */
    public void ausfuehren () {

    }
}
