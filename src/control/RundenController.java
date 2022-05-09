package control;
import model.*;

import static resources.Zahlen.*;

public class RundenController
{
    private static int zugzeahler = ZAHL_1;
    //boolean plaatzhalter = false;

    public static boolean isFreundlich()
    {
        return freundlich;
    }

    public static void setFreundlich(boolean freundlich)
    {
        RundenController.freundlich = freundlich;
    }

    static boolean freundlich = false;

    public void zugBeenden (Spielfeld feld )
    {
        this.zugzeahler = zugzeahler + 1;

        for (int i = 0; i < feld.getFeldZeile(); i++)
        {
            for (int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if (feld.getSpielfeldplatz(i,j).getLebenspunkte() == 0)
                {
                    feld.einheitloeschen(i,j);
                }
            }
        }
        bestimmenwerdranist();
    }
    public static void bestimmenwerdranist()
    {
        if(zugzeahler % 2 == 0)
        {
            freundlich = true;
        }
        else
        {
            freundlich = false;
        }
    }
    public static int getZugzeahler()
    {
        return zugzeahler;
    }

    public void setZugzeahler(int zugzeahler)
    {
        this.zugzeahler = zugzeahler;
    }
}
