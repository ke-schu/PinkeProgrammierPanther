package model;

import exceptions.JsonNichtLesbarException;
import utility.KartenDeckIO;

import static resources.Strings.START_DECK_PFAD;
import static resources.Strings.TRENNUNG;

/**
 * Diese Klasse definiert den Charakter, den man sich zu Beginn des Spieles
 * aussuchen kann.
 */
public class Charakter
{
    private final String name;
    private final int freischaltgebuehr;
    private final Spieler spieler;
    private boolean freigeschaltet;
    private transient KartenDeck startDeck;

    /**
     * Konstruktor fuer den jeweiligen Charakter.
     * @param name Name des Charakters.
     * @param freischaltgebuehr Hoehe der Freischaltgebuehr, falls vorhanden.
     * @param spieler Der Spieler des Charakters, welcher verwendet wird.
     * @param freigeschaltet Zustand, ob dieser Charakter verwendbar ist.
     * @throws JsonNichtLesbarException Exception, welche sich im Fall
     * eines fehlerhaften Decks um eine Loesung kuemmert.
     */
    public Charakter(String name, int freischaltgebuehr, Spieler spieler,
                     boolean freigeschaltet)
            throws JsonNichtLesbarException
    {
        this.name = name;
        this.freischaltgebuehr = freischaltgebuehr;
        this.spieler = spieler;
        this.freigeschaltet = freigeschaltet;
        this.startDeck =
                KartenDeckIO.leseDatei(String.format(START_DECK_PFAD, name));
    }

    /**
     * Konstruktor fuer den jeweiligen Charakter nach der Deserialisierung.
     * @param charakter deserialisierter Charakter.
     * @throws JsonNichtLesbarException Exception, welche sich im Fall
     * eines fehlerhaften Decks um eine Loesung kuemmert.
     */
    public Charakter(Charakter charakter) throws JsonNichtLesbarException
    {
        this.name = charakter.getName();
        this.freischaltgebuehr = charakter.getFreischaltgebuehr();
        this.spieler = charakter.getSpieler();
        this.freigeschaltet = charakter.getFreigeschaltet();
        this.startDeck = KartenDeckIO.leseDatei(
                String.format(START_DECK_PFAD, charakter.getName()));
    }

    /**
     * Ueberlagerung der toString Methode um die Attribute name und
     * freischaltgebuehr als einen String zurueckzugeben.
     * @return Gibt Name und Freischaltgebuehr der Klasse zurueck.
     */
    @Override public String toString()
    {
        return name + TRENNUNG + freischaltgebuehr;
    }

    /**
     * Gibt den String des Attribut name wieder.
     * @return gibt das Attribut name wieder.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gibt den Int-Wert des Attributes freischaltgebuehr wieder.
     * @return gibt den Int-Wert des Attributes wieder.
     */
    public int getFreischaltgebuehr()
    {
        return freischaltgebuehr;
    }

    /**
     * Gibt das Attribut spieler mit der Klasse Spieler wieder.
     * @return gibt die Instanz der Klasse Spieler die im Attribut spieler
     * liegt.
     */
    public Spieler getSpieler()
    {
        return spieler;
    }

    /**
     * Gibt den booleschen Wert des Attributes freigeschaltet wieder.
     * @return gibt den Zustand des Attributes freigeschaltet wieder.
     */
    public boolean getFreigeschaltet()
    {
        return freigeschaltet;
    }

    /**
     * Methode um einen booleschen Wert in das Attribut freigeschaltet zu
     * setzen.
     * @param freigeschaltet boolescher Wert, welcher in das Attribut
     * geschrieben werden soll.
     */
    public void setFreigeschaltet(boolean freigeschaltet)
    {
        this.freigeschaltet = freigeschaltet;
    }

    /**
     * Gibt das Attribut startDeck mit der Klasse KartenDeck wieder.
     * @return liefert eine Instanz der Klasse KartenDeck aus dem Attribut
     * startDeck.
     */
    public KartenDeck getStartDeck()
    {
        return startDeck;
    }
}
