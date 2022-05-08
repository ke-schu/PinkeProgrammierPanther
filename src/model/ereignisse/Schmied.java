package model.ereignisse;

public class Schmied extends Mensch
{

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Schmied. Schmiede sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten aufzuwerten um sie zu verstaerken.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param position: Die Position auf der Oberkarte
     * @param gratisInteraktion: Die Anzahl an kostenlosen Aufwertungen, die der Spieler zur Verfuegung hat
     */
    public Schmied (String name, String beschreibung, int position, int gratisInteraktion)
    {
        this.name = name;
        this.beschreibung = beschreibung;
        this.position = position;
        this.gratisInteraktion = gratisInteraktion;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte aufgewertet oder
     * vorher die Zahlung durchgefuehrt.
     */
    @Override
    public void ausfuehren ()
    {
        if(isAuswahl()) {
            if (pruefeGratisInteraktion()) {

            }
        } else {
        }
    }
}
