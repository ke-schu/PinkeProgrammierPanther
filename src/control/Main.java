package control;
import control.test.Alex;
import control.test.Hendrik;
import control.test.Henrik;
import control.test.Keno;

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
            case 2: Keno.ausfuehren();
            break;
            case 3: Hendrik.ausfuehren();
            break;
            case 4: Henrik.testen();
        }
    }
}