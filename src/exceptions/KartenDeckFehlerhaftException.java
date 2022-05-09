package exceptions;

import resources.strings;

public class KartenDeckFehlerhaftException extends Exception
{
    public KartenDeckFehlerhaftException (int kartenDeckNummer)
    {
        super(String.format(strings.KARTEN_DECK_FEHLERHAFT_EXCEPTION_INFO, kartenDeckNummer));
    }
}
