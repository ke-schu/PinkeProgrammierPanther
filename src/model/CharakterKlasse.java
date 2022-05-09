package model;

import control.KartenDeckController;
import exceptions.KartenDeckFehlerhaftException;
import java.io.IOException;
import static resources.Strings.*;

public class CharakterKlasse
{
    private final String name;
    private final int freischaltgebuehr;
    private final Held held;
    private final int anzahlDecks = 3;
    private transient KartenDeck startDecks[] = new KartenDeck[anzahlDecks];
    private KartenDeck restDeck;

    public CharakterKlasse (String name, int freischaltgebuehr, Held held) throws KartenDeckFehlerhaftException
    {
        this.name = name;
        this.freischaltgebuehr = freischaltgebuehr;
        this.held = held;
        sucheStartDecks();
        sucheRestDeck();
    }

    private void sucheRestDeck() throws KartenDeckFehlerhaftException
    {
        String pfad = KARTENDECK_PFAD + this.getName() + REST_DECK_DATEI;
        if (KartenDeckController.pruefeDatei(pfad))
        {
            try
            {
                this.restDeck = KartenDeckController.leseDatei(pfad);
            } catch (IOException e)
            {
                throw new KartenDeckFehlerhaftException();
            }
        }
        else
        {
            throw new KartenDeckFehlerhaftException();
        }
    }

    private void sucheStartDecks() throws KartenDeckFehlerhaftException
    {
        for (int i = 1; i < (this.getAnzahlDecks() + 1); i++)
        {
            String pfad = KARTENDECK_PFAD + this.getName() + i + JSON_DATEIENDUNG;
            if (KartenDeckController.pruefeDatei(pfad))
            {
                try
                {
                    this.setDeck(KartenDeckController.leseDatei(pfad), i);
                } catch (IOException e)
                {
                    throw new KartenDeckFehlerhaftException(i);
                }
            }
            else
            {
                throw new KartenDeckFehlerhaftException(i);
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
