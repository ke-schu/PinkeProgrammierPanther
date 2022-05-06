package control;
import model.*;
public class RundenController
{
    private int zugzeahler = 1;

    public void zugbeenden(Spielfeld feld )
    {
        this.zugzeahler = zugzeahler + 1;
        for(int i = 0; i < feld.getFeldZeile(); i++)
        {
            for(int j = 0; j < feld.getFeldSpalte(); j++)
            {
                if (feld.getSpielfeldplatz(i,j).getLebenspunkte() == 0)
                {
                    feld.einheitloeschen(i,j);
                }
            }
        }

    }

}
