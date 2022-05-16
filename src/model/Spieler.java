package model;

import resources.Artefakte;
import resources.Effekte;
import resources.Einheiten;
import resources.Talente;
import java.util.Stack;

/**
 * Spieler repraesentiert die Karte, welche den Spieler auf dem Spielfeld darstellt.
 * Sie erbt von Klasse KarteEinheit.
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
     * Methode die eine Instanz der Klasse Waffe aus dem Attribut waffe wiedergibt.
     * @return gibt eine Instanz der Klasse Waffe aus dem Attribut waffe wieder.
     */
    public Waffe getWaffe ()
    {
        return waffe;
    }

    /**
     * Setzt den uebergebenen Parameter der Klasse Waffe in das Attribut waffe.
     * @param waffe Instanz der Klasse Waffe, die in das Attribut waffe gesetzt werden soll.
     */
    public void setWaffe (Waffe waffe)
    {
        this.waffe = waffe;
    }

    /**
     * Methode die einen Stack mit Elementen des Enums Talente wiedergibt.
     * @return gibt einen Stack mit Elementen des Enums Talente wieder.
     */
    public Stack<Talente> getTalente ()
    {
        return talente;
    }

    /**
     * Setzt den uebergebenen Parameter in das Attribut talente.
     * @param talente Stack mit Elementen des Enums Talente, welches in das Attribut talente gesetzt werden soll.
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
