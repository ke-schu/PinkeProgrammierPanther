package exceptions;

import resources.Strings;

public class KartenDeckFehlerhaftException extends Exception
{
    public KartenDeckFehlerhaftException (int kartenDeckNummer)
    {
        super(String.format(Strings.KARTEN_DECK_FEHLERHAFT_EXCEPTION_INFO, kartenDeckNummer));
    }
}