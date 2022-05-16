package model;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;

import static resources.Strings.START_DECK_PFAD;

public class Charakter
{
    private final String name;
    private final int freischaltgebuehr;
    private final Spieler spieler;
    private boolean freigeschaltet;
    private transient KartenDeck startDeck;

    public Charakter (String name, int freischaltgebuehr, Spieler spieler, boolean freigeschaltet) throws KartenDeckFehlerhaftException
    {
        this.name = name;
        this.freischaltgebuehr = freischaltgebuehr;
        this.spieler = spieler;
        this.freigeschaltet = freigeschaltet;
        this.startDeck = KartenDeckIO.leseDatei(String.format(START_DECK_PFAD, name));
    }

    public Charakter (Charakter charakter) throws KartenDeckFehlerhaftException
    {
        this.name = charakter.getName();
        this.freischaltgebuehr = charakter.getFreischaltgebuehr();
        this.spieler = charakter.getSpieler();
        this.freigeschaltet = charakter.getFreigeschaltet();
        this.startDeck = KartenDeckIO.leseDatei(String.format(START_DECK_PFAD, charakter.getName()));
    }

    /**
     * Überlagerung der toString Methode um die Attribute name und freischaltgebuehr als einen String zurückzugeben.
     * @return Gibt Name und Freischaltgebühr der Klasse zurück.
     */
    @Override
    public String toString ()
    {
        return "Charakterklasse " + name + ", Gebühr " + freischaltgebuehr;
    }

    /**
     * Gibt den String des Attribut name wieder.
     * @return gibt das Attribut name wieder.
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Gibt den Int-Wert des Attributes freischaltgebuehr wieder.
     * @return gibt den Int-Wert des Attributes wieder.
     */
    public int getFreischaltgebuehr ()
    {
        return freischaltgebuehr;
    }

    /**
     * Gibt das Attribut spieler mit der Klasse Spieler wieder.
     * @return gibt die Instanz der Klasse Spieler die im Attribut spieler liegt.
     */
    public Spieler getSpieler ()
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
     * Methode um einen booleschen Wert in das Attribut freigeschaltet zu setzen.
     * @param freigeschaltet boolescher Wert, welcher in das Attribut geschrieben werden soll.
     */
    public void setFreigeschaltet(boolean freigeschaltet)
    {
        this.freigeschaltet = freigeschaltet;
    }

    /**
     * Gibt das Attribut startDeck mit der Klasse KartenDeck wieder.
     * @return liefert eine Instanz der Klasse KartenDeck aus dem Attribut startDeck.
     */
    public KartenDeck getStartDeck ()
    {
        return startDeck;
    }
}
