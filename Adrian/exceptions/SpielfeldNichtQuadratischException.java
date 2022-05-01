package Adrian.exceptions;

import Adrian.resources.strings;

public class SpielfeldNichtQuadratischException extends Exception
{
    public SpielfeldNichtQuadratischException()
    {
        super(strings.SPIELFELD_NICHT_QUADRATISCH_EXCEPTION_INFO);
    }
}
