package control;
import control.test.Alex;
import control.test.Hendrik;
import control.test.Keno;
//import control.test.Keno;

public class Main
{
    public static void main (String[] args)
    {
        testen();
    }

    private static void testen()
    {
        switch(2)
        {
            case 1: Alex.ausfuehren();
            break;
            case 2: Keno.ausfuehren();
            break;
            case 3: Hendrik.ausfuehren();
        }
    }
}