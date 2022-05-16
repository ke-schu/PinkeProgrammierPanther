package exceptions;

import static resources.Strings.START_DECK_FEHLERHAFT_EXCEPTION_INFO;

/**
 * Die KartenDeckFehlerhaftException kann beim Einlesen von Kartendecks geworfen werden.
 */
public class KartenDeckFehlerhaftException extends Exception
{
    /**
     * Konstruiert eine KartenDeckFehlerhaftException.
     */
    public KartenDeckFehlerhaftException ()
    {
        super(START_DECK_FEHLERHAFT_EXCEPTION_INFO);
    }
}