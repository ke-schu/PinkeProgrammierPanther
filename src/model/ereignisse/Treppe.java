package model.ereignisse;

import model.SpielStand;

public class Treppe extends Ereignis
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Treppe. Treppen sind Ereignisse, die es dem
     * Spieler ermoeglichen seine aktuelle Ebene zu verlassen und die nächsthöhere zu betreten.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Treppe (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Wird diese Methode ausgefuehrt,
     * dann wechselt der Spieler die Ebene, auf der er sich befindet, zur nächst höheren.
     */
    public void ausfuehren (SpielStand spielStand)
    {
        //Ebene ebene = new Ebene();
        //try
        {
          //  ebene = EbeneIO.leseDatei();
        }
        //catch (IOException e)
        {
         //e.getMessage();
        }
        //return ebene;
    }
}
