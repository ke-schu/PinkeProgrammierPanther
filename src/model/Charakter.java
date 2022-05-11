package model;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;

import static resources.Strings.*;

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
        this.startDeck = KartenDeckIO.leseDatei(String.format(START_DECK_PFAD, name));
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

    public int getFreischaltgebuehr ()
    {
        return freischaltgebuehr;
    }

    public Spieler getSpieler ()
    {
        return spieler;
    }

    public boolean getFreigeschaltet()
    {
        return freigeschaltet;
    }

    public void setFreigeschaltet(boolean freigeschaltet)
    {
        this.freigeschaltet = freigeschaltet;
    }

    public KartenDeck getStartDeck ()
    {
        return startDeck;
    }
}
