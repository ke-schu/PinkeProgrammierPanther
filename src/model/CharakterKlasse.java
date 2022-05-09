package model;


import control.KartenDeckController;
//import exceptions.KartenDeckFehlerhaftException;

import java.io.File;
import java.io.IOException;

public class CharakterKlasse
{
    private String name;
    private int freischaltgebuehr;
    private Held held;
    private final int anzahlDecks = 3;
    private transient KartenDeck startDecks[] = new KartenDeck[anzahlDecks];

    public CharakterKlasse (String name, int freischaltgebuehr, Held held) //throws KartenDeckFehlerhaftException
    {
        this.name = name;
        this.freischaltgebuehr = freischaltgebuehr;
        this.held = held;
        sucheDecks();
    }

    private void sucheDecks () //hrows KartenDeckFehlerhaftException
    {
        for (int i = 1; i < (this.getAnzahlDecks() + 1); i++)
        {
            String pfad = "src/resources/carddecks/" + this.getName() + i + ".json";
            if (KartenDeckController.pruefeDatei(pfad))
            {
                try
                {
                    this.setDeck(KartenDeckController.leseDatei(pfad), i);
                } catch (IOException e)
                {
                    //throw new KartenDeckFehlerhaftException(i);
                }
            }
            else
            {
               // throw new KartenDeckFehlerhaftException(i);
            }
        }
    }

    /**
     * Überlagerung der toString Methode um die Attribute name, preis, held, und kartenblaetter als einen String zu returnen.
     * @return Gibt die name, preis, held, und kartenblaetter wieder.
     */
    @Override
    public String toString ()
    {
        return "Charakterklasse{" +
                "name='" + name + '\'' +
                ", preis=" + freischaltgebuehr +
                ", held='" + held + '\'' +
                ", kartenblaetter=" + startDecks +
                '}';
    }

    /**
     * Gibt den String des Attribut name wieder.
     * @return gibt das Attribut name wieder.
     */
    public String getName ()
    {
        return name;
    }

    public KartenDeck getDeck (int nummer)
    {
        return startDecks[nummer-1];
    }

    public boolean setDeck (KartenDeck deck, int nummer)
    {
        if (anzahlDecks >= nummer)
        {
            startDecks[nummer-1] = deck;
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getAnzahlDecks ()
    {
        return anzahlDecks;
    }
}
