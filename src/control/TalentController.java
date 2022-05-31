package control;

import model.Spieler;
import model.ereignisse.Mensch;

import static resources.Konstanten.*;
import static resources.Talente.*;

/**
 *
 */
public class TalentController
{
    /**
     *
     * @param spieler
     * @param mensch
     */
    public static void charme (Spieler spieler, Mensch mensch)
    {
        if(spieler.getTalente().contains(CHARME))
        {
            double i = mensch.getKosten() * CHARME_FAKTOR;
            mensch.setKosten((int) i);
        }
    }

    /**
     *
     * @param spieler
     * @return
     */
    public static boolean grosseHand (Spieler spieler)
    {
       return spieler.getTalente().contains(GROSSE_HAND);
    }
}
