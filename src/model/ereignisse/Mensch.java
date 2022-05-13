package model.ereignisse;

import static resources.Zahlen.*;

public abstract class Mensch extends Ereignis
{
    protected int gratisInteraktion = 1;    //Kostenloses Nutzen der Dienste von Händlern, Tempeln, Heilern und Schmieden.
    protected int kosten = 20;               //Höhe der Kosten zum Nutzen der Dienste
    protected int interaktionsZaehler = 0;  //Zählt die Menge an Interaktionen mit dem jeweiligen Objekt
    protected final int KEINE_GRATIS_INTERAKTION = 0;   //Konstante für keine weiteren gratis Interaktionen.

    /**
     * Diese Methode prueft ob der Spieler gratis sein Deck verändern kann, wenn er die Dienste der
     * Einrichtungen in Anspruch nimmt.
     * @return Es wird zurückgegeben, ob der Spieler noch gratis Interaktionen zur Verfuegung hat.
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

    public void kostenErhoehen ()
    {
        if(interaktionsZaehler % ZAHL_3 == ZAHL_0)
        {
           setKosten(kosten + ZAHL_10);
        }
    }

    public int getGratisInteraktion ()
    {
        return gratisInteraktion;
    }

    public void setGratisInteraktion (int gratisInteraktion)
    {
        this.gratisInteraktion = gratisInteraktion;
    }

    public int getKosten()
    {
        return kosten;
    }

    public void setKosten(int kosten)
    {
        this.kosten = kosten;
    }

    public int getInteraktionsZaehler ()
    {
        return interaktionsZaehler;
    }
}
