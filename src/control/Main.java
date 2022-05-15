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
        switch(3)
        {
            case 1: Alex.ausfuehren();
            break;
            case 2: Hendrik.ausfuehren();
            break;
            case 3: EbeneTest.testeEbene();
            break;
            case 4: SpielStandTest.speichereSpielstand();
            case 5: SpielStandTest.leseSpielstand();
        }
    }
}