package Adrian.exceptions;

import Adrian.resources.strings;

public class SpielfeldDimensionGleichNullException extends Exception
{
    public SpielfeldDimensionGleichNullException()
    {
        super(strings.SPIELFELD_DIMENSION_GLEICH_NULL_EXCEPTION_INFO);
    }
}
