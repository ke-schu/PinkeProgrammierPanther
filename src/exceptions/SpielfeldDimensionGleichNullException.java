package exceptions;

import resources.Strings;

public class SpielfeldDimensionGleichNullException extends Exception
{
    public SpielfeldDimensionGleichNullException()
    {
        super(Strings.SPIELFELD_DIMENSION_GLEICH_NULL_EXCEPTION_INFO);
    }
}
