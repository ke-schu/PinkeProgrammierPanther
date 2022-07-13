package exceptions;

import static resources.Strings.KARTEN_DECK_ZU_KLEIN_INFO;

public class KartenDeckZuKleinException extends IndexOutOfBoundsException
{
    /**
     Konstruiert eine KartenDeckZuKleinException.
     */
    public KartenDeckZuKleinException ()
    {
        super(KARTEN_DECK_ZU_KLEIN_INFO);
    }
}
