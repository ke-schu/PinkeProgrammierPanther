package model;

import resources.Artefakte;
import resources.Effekte;
import resources.Einheiten;
import resources.Talente;
import java.util.Stack;

import static resources.Zahlen.*;

/**
 * Spieler repraesentiert die Karte, welche den Spieler auf dem Spielfeld
 * darstellt. Sie erbt von Klasse KarteEinheit.
 */
public class Spieler extends KarteEinheit implements Spielbar
{
    private Waffe waffe;
    private Stack<Talente> talente;
    private Artefakte[] artefakte;
    private final int mana;
    private int erfahrungspunkte = 0;
    private int levelGrenze = 50;

    /**
     * Konstruiert einen Spieler mit allen notwendigen Attributen.
     * @param waffe die Waffe des Spielers
     * @param talente der Stack an Talenten
     * @param artefakte die Artefakte des Spielers als Array
     * @param mana das Mana-Guthaben
     */
    public Spieler(String name, int level, Einheiten typ, int macht,
                   int lebenspunkte, int beweglichkeit, int reichweite,
                   int schild, int verteidigung, Effekte effektEins,
                   Effekte effektZwei, Waffe waffe, Stack<Talente> talente,
                   Artefakte[] artefakte, int mana)
    {
        super(name, level, typ, macht + waffe.getAngriffsPunkte(),
              lebenspunkte, 0, beweglichkeit, reichweite, schild,
              verteidigung, effektEins, effektZwei, true, true);
        this.waffe = waffe;
        this.talente = talente;
        this.artefakte = artefakte;
        this.mana = mana;
    }

    /**
     * Methode die eine Instanz der Klasse Waffe aus dem Attribut waffe
     * wiedergibt.
     * @return gibt eine Instanz der Klasse Waffe aus dem Attribut waffe
     * wieder.
     */
    public Waffe getWaffe()
    {
        return waffe;
    }

    /**
     * Setzt den uebergebenen Parameter der Klasse Waffe in das Attribut
     * waffe.
     * @param waffe Instanz der Klasse Waffe, die in das Attribut waffe
     * gesetzt werden soll.
     */
    public void setWaffe(Waffe waffe)
    {
        this.waffe = waffe;
    }

    /**
     * Methode die einen Stack mit Elementen des Enums Talente wiedergibt.
     * @return gibt einen Stack mit Elementen des Enums Talente wieder.
     */
    public Stack<Talente> getTalente()
    {
        return talente;
    }

    /**
     * Setzt den uebergebenen Parameter in das Attribut talente.
     * @param talente Stack mit Elementen des Enums Talente, welches in das
     * Attribut talente gesetzt werden soll.
     */
    public void setTalente(Stack<Talente> talente)
    {
        this.talente = talente;
    }

    /**
     * Methode die ein Array mit Elementen des Enums Artefakte wiedergibt
     * @return gibt ein Array mit Elementen des Enums Artefakte wieder.
     */
    public Artefakte[] getArtefakte()
    {
        return artefakte;
    }

    /**
     * Setzt den uebergebenen Parameter des Enum Artefakte in das Attribut
     * artefakt.
     * @param artefakte Array mit Elementen des Enums Artefakte, welches in
     * das Attribut artefakte gesetzt werden soll.
     */
    public void setArtefakte(Artefakte[] artefakte)
    {
        this.artefakte = artefakte;
    }

    /**
     * Getter Methode um den INT-Wert vom Mana des Spielers zurueckzugeben
     * @return Den Manawert des Spielers
     */
    public int getMana()
    {
        return mana;
    }

    /**
     * Methode um den Int-Wert des Attributes erfahrungspunkte zu bekommen.
     * @return gibt den Int-Wert des Attributes erfahrungspunkte wieder.
     */
    public int getErfahrungspunkte()
    {
        return erfahrungspunkte;
    }

    /**
     * Methode um einen Int-Wert in das Attribut erfahrungspunkte zu setzen.
     * @param erfahrungspunkte Int-Wert, welcher in das Attribut gesetzt werden soll.
     */
    public void setErfahrungspunkte(int erfahrungspunkte)
    {
        this.erfahrungspunkte = erfahrungspunkte;
    }

    /**
     * Methode um den Int-Wert des Attributes levelGrenze zu bekommen.
     * @return gibt den Int-Wert des Attributes levelGrenze wieder.
     */
    public int getLevelGrenze()
    {
        return levelGrenze;
    }
    /**
     * Methode um einen Int-Wert in das Attribut levelGrenze zu setzen.
     * @param levelGrenze Int-Wert, welcher in das Attribut gesetzt werden soll.
     */
    public void setLevelGrenze(int levelGrenze)
    {
        this.levelGrenze = levelGrenze;
    }

    /**
     * Private Methode um das Attribut level um eins zu erhöhen.
     */
    private void levelUp ()
    {
        this.setLevel(this.getLevel() + ZAHL_1);
    }

    /**
     * Methode um zu pruefen, ob die Levelgrenze ueberschritten wurde.
     * Ist dies der Fall, wird das Attribut level erhoeht und die Erfahrungspunkte zurueck gesetzt.
     */
    public void berechneLevelUp ()
    {
        if (this.getErfahrungspunkte() >= this.getLevelGrenze())
        {
            this.levelUp();
            this.setErfahrungspunkte(ZAHL_0);
            this.setLevelGrenze(this.getLevelGrenze() * ZAHL_2);
        }
    }

}
