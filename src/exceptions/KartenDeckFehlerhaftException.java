package exceptions;

import static resources.Strings.START_DECK_FEHLERHAFT_EXCEPTION_INFO;

/**
 * Die KartenDeckFehlerhaftException kann beim Einlesen von Kartendecks geworfen werden.
 */
public class KartenDeckFehlerhaftException extends Exception
{
    /**
     * Konstruiert eine KartenDeckFehlerhaftException.
     * @param kartenDeck das Kartendeck, welches die Exception ausloest
     */
    public KartenDeckFehlerhaftException (String kartenDeck)
    {
        super(String.format(START_DECK_FEHLERHAFT_EXCEPTION_INFO, kartenDeck));
    }

    /**
     * Konstruiert eine KartenDeckFehlerhaftException.
     */
    public KartenDeckFehlerhaftException ()
    {
        this(String.format(START_DECK_FEHLERHAFT_EXCEPTION_INFO, ""));
    }
}