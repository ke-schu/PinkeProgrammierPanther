package model;

import resources.Artefakte;
import resources.Effekte;
import resources.Einheiten;
import resources.Talente;

import java.util.Stack;

/**
 * Spieler repraesentiert die Karte, welche den Spieler auf dem Spielfeld darstellt
 * Sie erbt von Klasse KarteEinheit
 */
public class Spieler extends KarteEinheit implements Spielbar
{
    private Waffe waffe;
    private Stack<Talente> talente;
    private Artefakte[] artefakte;
    private final int mana;

    public Spieler(String name, int level, Einheiten typ, int macht, int lebenspunkte,
                   int beweglichkeit, int reichweite, int verteidigung, Effekte effektEins, Effekte effektZwei,
                   Waffe waffe, Stack<Talente> talente, Artefakte[] artefakte, int mana)
    {
        super(name, level, typ, waffe.getAngriffsPunkte() + macht, lebenspunkte,
                0, beweglichkeit, reichweite, verteidigung, effektEins, effektZwei);
        this.waffe = waffe;
        this.talente = talente;
        this.artefakte = artefakte;
        this.mana = mana;
    }

    public Waffe getWaffe()
    {
        return waffe;
    }

    public void setWaffe(Waffe waffe)
    {
        this.waffe = waffe;
    }

    public Stack<Talente> getTalente()
    {
        return talente;
    }

    public void setTalente(Stack<Talente> talente)
    {
        this.talente = talente;
    }

    public Artefakte[] getArtefakte()
    {
        return artefakte;
    }

    public void setArtefakte(Artefakte[] artefakte)
    {
        this.artefakte = artefakte;
    }

    public int getMana()
    {
        return mana;
    }
}
