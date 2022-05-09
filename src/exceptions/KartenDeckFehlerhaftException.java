package exceptions;

import static resources.Strings.*;

public class KartenDeckFehlerhaftException extends Exception
{
    public KartenDeckFehlerhaftException (int kartenDeckNummer)
    {
        super(String.format(START_DECK_FEHLERHAFT_EXCEPTION_INFO, kartenDeckNummer));
    }

    public KartenDeckFehlerhaftException ()
    {
        super(REST_DECK_FEHLERHAFT_EXCEPTION_INFO);
    }
}