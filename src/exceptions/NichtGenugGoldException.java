package exceptions;

import static resources.Strings.NICHT_GENUG_GOLD_INFO;

/**
 Die NichtGenugGoldException kann geworfen werden, wenn nicht genug Gold fuer
 eine Aktion vorhanden ist */
public class NichtGenugGoldException extends Exception
{
    /**
     Konstruiert eine NichtGenugGoldException.
     */
    public NichtGenugGoldException ()
    {
        super(NICHT_GENUG_GOLD_INFO);
    }
}
