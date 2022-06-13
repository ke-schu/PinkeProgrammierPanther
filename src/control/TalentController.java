package control;

import model.Spieler;
import model.ereignisse.Mensch;

import static resources.Konstanten.CHARME_FAKTOR;
import static resources.Talente.CHARME;
import static resources.Talente.GROSSE_HAND;

/**
 * Diese Klasse dient als Controller um Methoden fuer Talente auszuformulieren.
 */
public class TalentController
{
    /**
     * Diese Methode ueberprueft, ob der Held das Talent "CHARME" besitzt,
     * falls ja werden die Kosten
     * bei allen Ereignissen, welche Gold Kosten gesenkt.
     *
     * @param spieler Der Held, welcher das Talent besitzt.
     * @param mensch  Das Ereignis, bei dem die Kosten gesenkt werden sollen.
     */
    public static void charme(Spieler spieler, Mensch mensch)
    {
        if (spieler.getTalente().contains(CHARME))
        {
            double i = mensch.getKosten() * CHARME_FAKTOR;
            mensch.setKosten((int) i);
        }
    }

    /**
     * Diese Methode ueberprueft, ob der Held das Talent GROSSE_HAND besitzt.
     *
     * @param spieler Der Held, der das Talent besitzt.
     * @return Ob der Held das Talent besitzt.
     */
    public static boolean grosseHand(Spieler spieler)
    {
        return spieler.getTalente().contains(GROSSE_HAND);
    }
}
