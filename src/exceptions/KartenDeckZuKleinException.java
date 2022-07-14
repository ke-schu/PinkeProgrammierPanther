package exceptions;

import static resources.Strings.KARTEN_DECK_ZU_KLEIN_INFO;

/**
 Die KartenDeckZuKleinException kann beim Einlesen von Kartendecks geworfen
 werden, wenn das uebrige Kartendeck zu klein ist um noch in die Hand
 aufgenommen zu werden */
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
