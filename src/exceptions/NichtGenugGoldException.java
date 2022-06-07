package exceptions;

import static resources.Strings.NICHT_GENUG_GOLD_INFO;

public class NichtGenugGoldException extends Exception
{
    public NichtGenugGoldException()
    {
        super(NICHT_GENUG_GOLD_INFO);
    }
}