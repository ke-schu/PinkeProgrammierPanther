package model;

import control.SpielStandController;
import resources.*;

import java.util.Stack;

import static resources.Konstanten.*;

/**
 Spieler repraesentiert die Karte, welche den Spieler auf dem Spielfeld
 darstellt. Sie erbt von Klasse KarteEinheit. */
public class Spieler extends KarteEinheit implements Spielbar
{
    private Waffen waffe;
    private Stack<Talente> talente;
    private Artefakte[] artefakte;
    private int mana;
    private int erfahrungspunkte = 0;
    private int levelGrenze = 50;
    private int maxleben;
    
    /**
     Konstruiert einen Spieler mit allen notwendigen Attributen.
     @param waffe die Waffe des Spielers.
     @param talente der Stack an Talenten.
     @param artefakte die Artefakte des Spielers als Array.
     @param mana das Mana-Guthaben.
     */
    public Spieler (String name, int level, Einheiten typ, int macht,
                    int lebenspunkte, int beweglichkeit, int reichweite,
                    int schild, int verteidigung, Effekte effektEins,
                    Effekte effektZwei, Stack<Talente> talente,
                    Artefakte[] artefakte, int mana, Waffen waffe)
    {
        super(name, level, typ, macht, lebenspunkte,
              0, beweglichkeit, reichweite, schild, verteidigung, effektEins,
              effektZwei, true, true);
        this.talente = talente;
        this.artefakte = artefakte;
        this.mana = mana;
        this.waffe = waffe;
    }
    
    /**
     Methode die eine Instanz des Enums Waffen aus dem Attribut waffe
     wiedergibt.
     @return gibt eine Instanz des Enums Waffen aus dem Attribut waffe wieder.
     */
    public Waffen getWaffen ()
    {
        return waffe;
    }
    
    /**
     Setzt den uebergebenen Parameter des Enums Waffen in das Attribut waffe.
     @param waffe Instanz des Enums Waffen, die in das Attribut waffe gesetzt
     werden soll.
     */
    public void setWaffen (Waffen waffe)
    {
        this.waffe = waffe;
    }
    
    /**
     Methode die einen Stack mit Elementen des Enums Talente wiedergibt.
     @return gibt einen Stack mit Elementen des Enums Talente wieder.
     */
    public Stack<Talente> getTalente ()
    {
        return talente;
    }
    
    /**
     Setzt den uebergebenen Parameter in das Attribut talente.
     @param talente Stack mit Elementen des Enums Talente, welches in das
     Attribut talente gesetzt werden soll.
     */
    public void setTalente (Stack<Talente> talente)
    {
        this.talente = talente;
    }
    
    /**
     Methode die ein Array mit Elementen des Enums Artefakte wiedergibt
     @return gibt ein Array mit Elementen des Enums Artefakte wieder.
     */
    public Artefakte[] getArtefakte ()
    {
        return artefakte;
    }
    
    /**
     Setzt den uebergebenen Parameter des Enum Artefakte in das Attribut
     artefakt.
     @param artefakte Array mit Elementen des Enums Artefakte, welches in das
     Attribut artefakte gesetzt werden soll.
     */
    public void setArtefakte (Artefakte[] artefakte)
    {
        this.artefakte = artefakte;
    }
    
    /**
     Getter Methode um den INT-Wert vom Mana des Spielers zurueckzugeben.
     @return Den Manawert des Spielers.
     */
    public int getMana ()
    {
        return mana;
    }
    
    /**
     Setter Methode um den INT-Wert vom Mana des Spielers zu setzen.
     @param mana Der neue Manawert des Spielers.
     */
    public void setMana (int mana)
    {
        this.mana = mana;
    }
    
    /**
     Getter Methode um den Wert des Attributes maxleben zu erhalten.
     @return gibt den Wert des Attributes maxleben wieder.
     */
    public int getMaxleben ()
    {
        return maxleben;
    }
    
    /**
     Setter Methode um das Attribut maxleben zu setzen.
     @param maxleben auf welchen das Attribut gesetzt werden soll.
     */
    public void setMaxleben (int maxleben)
    {
        this.maxleben = maxleben;
    }
    
    /**
     Methode um zu pruefen, ob die Levelgrenze ueberschritten wurde. Ist dies
     der Fall, wird das Attribut level erhoeht und die Erfahrungspunkte
     zurueck
     gesetzt.
     */
    public void berechneLevelUp ()
    {
        if (this.getErfahrungspunkte() >= this.getLevelGrenze())
        {
            this.levelUp();
            this.setErfahrungspunkte(AUSGANGSWERT_ERFAHRUNGSPUNKTE_SPIELER);
            this.setLevelGrenze(this.getLevelGrenze() * LEVEL_GRENZE_FAKTOR);
        }
    }
    
    /**
     Methode um den Int-Wert des Attributes erfahrungspunkte zu bekommen.
     @return gibt den Int-Wert des Attributes erfahrungspunkte wieder.
     */
    public int getErfahrungspunkte ()
    {
        return erfahrungspunkte;
    }
    
    /**
     Methode um einen Int-Wert in das Attribut erfahrungspunkte zu setzen.
     @param erfahrungspunkte Int-Wert, welcher in das Attribut gesetzt werden
     soll.
     */
    public void setErfahrungspunkte (int erfahrungspunkte)
    {
        this.erfahrungspunkte = erfahrungspunkte;
    }
    
    /**
     Methode um den Int-Wert des Attributes levelGrenze zu bekommen.
     @return gibt den Int-Wert des Attributes levelGrenze wieder.
     */
    public int getLevelGrenze ()
    {
        return levelGrenze;
    }
    
    /**
     Methode um einen Int-Wert in das Attribut levelGrenze zu setzen.
     @param levelGrenze Int-Wert, welcher in das Attribut gesetzt werden soll.
     */
    public void setLevelGrenze (int levelGrenze)
    {
        this.levelGrenze = levelGrenze;
    }
    
    /**
     Private Methode um das Attribut level um eins zu erhöhen.
     */
    private void levelUp ()
    {
        this.setLevel(this.getLevel() + LEVEL_SUMMAND_SPIELER);
        this.setMaxleben(this.maxleben + 2);
    }
    
    /**
     Methode welche Spieler Schaden nehmen laesst.
     @param schaden welcher von den Lebenspunkten abgezogen werden soll.
     */
    @Override public void schadenNehmen (int schaden)
    {
        this.setLebenspunkte(this.getLebenspunkte() - schaden);
        if (getLebenspunkte() <= 0)
        {
            SpielStandController.sterben(this);
        }
    }
    
    /**
     gibt einer Karte ihre Initialwerte zurueck.
     */
    @Override public void initialisieren ()
    {
        this.setMacht(getInit().getMacht());
        this.setBeweglichkeit(getInit().getBeweglichkeit());
        this.setReichweite(getInit().getReichweite());
        this.setSchild(getInit().getSchild());
        this.setVerteidigung(getInit().getVerteidigung());
        this.setPosition(getInit().getPosition());
        this.setSchlafend(getInit().getSchlafend());
    }
}
