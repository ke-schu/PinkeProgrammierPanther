package exceptions;

import static resources.Strings.KARTE_NICHT_VERBESSERT_INFO;

public class KarteNichtVerbessertException extends RuntimeException
{
    public KarteNichtVerbessertException()
    {
        super(KARTE_NICHT_VERBESSERT_INFO);
    }
}
