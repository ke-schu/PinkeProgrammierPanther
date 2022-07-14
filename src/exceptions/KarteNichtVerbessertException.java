package exceptions;

import static resources.Strings.KARTE_NICHT_VERBESSERT_INFO;

/**
 Die KarteNichtVerbessertException kann geworfen werden, wenn eine Karte im
 Schmied nicht verbessert werden kann */
public class KarteNichtVerbessertException extends RuntimeException
{
    /**
     Konstruiert eine KarteNichtVerbessertException.
     */
    public KarteNichtVerbessertException ()
    {
        super(KARTE_NICHT_VERBESSERT_INFO);
    }
}
