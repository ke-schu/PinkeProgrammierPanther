package exceptions;

import static resources.Strings.*;

public class KartenDeckFehlerhaftException extends Exception
{
    public KartenDeckFehlerhaftException (String kartenDeck)
    {
        super(String.format(START_DECK_FEHLERHAFT_EXCEPTION_INFO, kartenDeck));
    }

    public KartenDeckFehlerhaftException ()
    {
        this(String.format(START_DECK_FEHLERHAFT_EXCEPTION_INFO, ""));
    }
}