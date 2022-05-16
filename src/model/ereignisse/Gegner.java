package model.ereignisse;

import io.KonsolenIO;
import model.Gegenspieler;
import model.SpielFeld;
import model.SpielStand;


public class Gegner extends Ereignis
{
    private Gegenspieler gegenspieler;
    private SpielFeld spielfeld;

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Gegner. Gegner sind Ereignisse, die es dem
     * Spieler ermoeglichen, einen Kampf zu beginnen.
     * @param name
     * @param beschreibung
     * @param gegenspieler
     * @param spielfeld
     */
    public Gegner(String name, String beschreibung, Gegenspieler gegenspieler, SpielFeld spielfeld)
    {
        super(name, beschreibung);
        this.gegenspieler = gegenspieler;
        this.spielfeld = spielfeld;
    }

    /**
     *
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

    public Gegenspieler getGegenspieler()
    {
        return gegenspieler;
    }
}
