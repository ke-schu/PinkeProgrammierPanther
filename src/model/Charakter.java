package model;

import exceptions.KartenDeckFehlerhaftException;
import control.KartenDeckController;

import static resources.Strings.*;

public class Charakter
{
    private final String name;
    private final int freischaltgebuehr;
    private final Held held;
    private transient KartenDeck startDeck;
    private transient KartenDeck haendlerDeck;
    private transient KartenDeck tempelDeck;

    public Charakter (String name, int freischaltgebuehr, Held held) throws KartenDeckFehlerhaftException
    {
        this.name = name;
        this.freischaltgebuehr = freischaltgebuehr;
        this.held = held;
        this.startDeck = KartenDeckController.leseDatei(String.format(START_DECK_PFAD, name));
        this.haendlerDeck = KartenDeckController.leseDatei(String.format(HAENDLER_DECK_PFAD, name));
        this.tempelDeck = KartenDeckController.leseDatei(String.format(TEMPEL_DECK_PFAD, name));
    }

    public Charakter (Charakter charakter) throws KartenDeckFehlerhaftException
    {
        this.name = charakter.getName();
        this.freischaltgebuehr = charakter.getFreischaltgebuehr();
        this.held = charakter.getHeld();
        this.startDeck = KartenDeckController.leseDatei(String.format(START_DECK_PFAD, name));
        this.haendlerDeck = KartenDeckController.leseDatei(String.format(HAENDLER_DECK_PFAD, name));
        this.tempelDeck = KartenDeckController.leseDatei(String.format(TEMPEL_DECK_PFAD, name));
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

    public Held getHeld ()
    {
        return held;
    }

    public KartenDeck getStartDeck ()
    {
        return startDeck;
    }

    public KartenDeck getHaendlerDeck ()
    {
        return haendlerDeck;
    }

    public KartenDeck getTempelDeck ()
    {
        return tempelDeck;
    }
}
