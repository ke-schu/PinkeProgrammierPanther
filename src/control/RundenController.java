package control;
import model.*;

import java.util.Collections;

import static resources.Zahlen.*;

public class RundenController
{
    private static int zugzeahler = ZAHL_1;
   private static boolean freundlich =true;
    //boolean plaatzhalter = false;

    public static boolean getFreundlich()
    {
        return freundlich;
    }

    public static void setFreundlich(boolean freundlich)
    {
        RundenController.freundlich = freundlich;
    }

    public void zugBeenden (SpielFeld feld,KartenDeck deck )
    {
        this.zugzeahler = zugzeahler + ZAHL_1;

        feldaufraeumen(feld, deck);
        aufwecken(feld);
        bestimmenwerdranist();
    }

    public void feldaufraeumen(SpielFeld feld,KartenDeck deck)
    {
        for (int i = 0; i < feld.getFeldZeile(); i++)
        {
            for (int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if (feld.getSpielfeldplatz(i,j).getLebenspunkte() == 0)
                {
                    feld.getSpielfeldplatz(i,j).initialisieren();
                    deck.push( feld.getSpielfeldplatz(i,j));
                    Collections.shuffle(deck);
                    feld.einheitloeschen(i,j);
                }
            }
        }
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

    public void aufwecken(SpielFeld feld)
    {
        for(int i = 0; i < feld.getFeldZeile(); i++)
        {
            for(int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if (feld.getSpielfeldplatz(i,j) != null)
                {
                    feld.getSpielfeldplatz(i,j).setSchlafend(false);
                }
            }
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

