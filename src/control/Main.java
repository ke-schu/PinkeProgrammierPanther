package control;
import control.test.*;

public class Main
{
    public static void main (String[] args)
    {
        testen();
    }

    private static void testen()
    {
        switch(4)
        {
            case 1: Alex.ausfuehren();
            break;
            case 2: Hendrik.ausfuehren();
            break;
            case 3: Henrik.testen();
            break;
            case 4: SpielStandTest.speichereSpielstand();
            case 5: SpielStandTest.leseSpielstand();
        }
    }
}