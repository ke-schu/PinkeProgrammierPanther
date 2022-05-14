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
        switch(5)
        {
            case 1: Alex.ausfuehren();
            break;
            case 2: Keno.ausfuehren();
            break;
            case 3: Hendrik.ausfuehren();
            break;
            case 4: Henrik.testen();
            break;
            case 5: SpielStandTest.speichereSpielstand();
        }
    }
}