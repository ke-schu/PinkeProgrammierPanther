package model.ereignisse;

import static resources.Zahlen.*;

/**
 * Diese Klasse ist eine Subklasse von Ereignis. Sie ist ebenfalls eine Superklasse fuer weitere Ereignisse und
 * formuliert Methoden und Attribute, die alle Subklassen gemein haben.
 */
public abstract class Mensch extends Ereignis
{
    //Kostenloses Nutzen der Dienste von Händlern, Tempeln, Heilern und Schmieden.
    protected int gratisInteraktion = ZAHL_1;

    //Höhe der Kosten zum Nutzen der Dienste
    protected int kosten = ZAHL_20;

    //Zählt die Menge an Interaktionen mit dem jeweiligen Objekt
    protected int interaktionsZaehler = ZAHL_0;

    //Konstante für keine weiteren gratis Interaktionen.
    protected final int KEINE_GRATIS_INTERAKTION = ZAHL_0;

    /**
     * Der Konstruktor von Mensch
     * @param name Der Name des Ereignisses
     * @param beschreibung Die Beschreibung fuer den Spieler
     */
    public Mensch (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode prueft ob der Spieler gratis sein Deck verändern kann, wenn er die Dienste der
     * Einrichtungen in Anspruch nimmt.
     */
    public boolean pruefeGratisInteraktion ()
    {
        if(gratisInteraktion > KEINE_GRATIS_INTERAKTION)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Diese Methode erhoeht automatisch die Kosten der Dienste, wenn der Spieler sie haeufig nutzt.
     */
    public void kostenErhoehen ()
    {
        if(interaktionsZaehler % ZAHL_3 == ZAHL_0)
        {
           setKosten(kosten + ZAHL_10);
        }
    }

    /**
     * Getter für die Menge an kostenlosen Interaktionen mit dem jeweiligen Ereignis
     * @return Menge an konstenlosen Interaktionen.
     */
    public int getGratisInteraktion ()
    {
        return gratisInteraktion;
    }

    /**
     * Setter fuer die Menge an kostenlosen Interaktionen mit dem jeweiligen Ereignis
     * @param gratisInteraktion Anzahl an kostenlosen Interaktionen
     */
    public void setGratisInteraktion (int gratisInteraktion)
    {
        this.gratisInteraktion = gratisInteraktion;
    }

    /**
     * Getter fuer die Hoehe der Kosten, die das jeweilige Ereignis verlangt
     * @return Hoehe der Kosten
     */
    public int getKosten ()
    {
        return kosten;
    }

    /**
     * Setter fuer die Hoehe der Kosten, die das jeweilige Ereignis verlangt
     * @param kosten Hoehe der Kosten
     */
    public void setKosten (int kosten)
    {
        this.kosten = kosten;
    }

    /**
     * Getter fuer die Menge an Interaktionen mit dem jeweiligen Ereignis
     * @return Menge an Interaktionen
     */
    public int getInteraktionsZaehler ()
    {
        return interaktionsZaehler;
    }
}
