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

    /**
     *
     * @param name
     * @param level
     * @param typ
     * @param macht
     * @param lebenspunkte
     * @param beweglichkeit
     * @param reichweite
     * @param schild
     * @param verteidigung
     * @param effektEins
     * @param effektZwei
     * @param waffe
     * @param talente
     * @param artefakte
     * @param mana
     */
    public Spieler (String name, int level, Einheiten typ, int macht, int lebenspunkte, int beweglichkeit, int reichweite, int schild, int verteidigung, Effekte effektEins, Effekte effektZwei, Waffe waffe, Stack<Talente> talente, Artefakte[] artefakte, int mana)
    {
        super(name, level, typ, macht + waffe.getAngriffsPunkte(), lebenspunkte, 0, beweglichkeit, reichweite, schild, verteidigung, effektEins, effektZwei, true, true);
        this.waffe = waffe;
        this.talente = talente;
        this.artefakte = artefakte;
        this.mana = mana;
    }

    /**
     *
     * @return
     */
    public Waffe getWaffe ()
    {
        return waffe;
    }

    /**
     *
     * @param waffe
     */
    public void setWaffe (Waffe waffe)
    {
        this.waffe = waffe;
    }

    /**
     *
     * @return
     */
    public Stack<Talente> getTalente ()
    {
        return talente;
    }

    /**
     *
     * @param talente
     */
    public void setTalente (Stack<Talente> talente)
    {
        this.talente = talente;
    }

    /**
     *
     * @return
     */
    public Artefakte[] getArtefakte ()
    {
        return artefakte;
    }

    /**
     *
     * @param artefakte
     */
    public void setArtefakte (Artefakte[] artefakte)
    {
        this.artefakte = artefakte;
    }

    /**
     *
     * @return
     */
    public int getMana ()
    {
        return mana;
    }
}
