package exceptions;

import resources.Strings;

public class SpielfeldNichtQuadratischException extends Exception
{
    public SpielfeldNichtQuadratischException()
    {
        super(Strings.SPIELFELD_NICHT_QUADRATISCH_EXCEPTION_INFO);
    }
}
