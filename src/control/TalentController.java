package control;

import model.Spieler;
import model.ereignisse.Mensch;

import static resources.Konstanten.*;
import static resources.Talente.*;

/**
 * Diese Klasse dient als Controller um Methoden fuer Talente auszuformulieren
 */
public class TalentController
{
    /**
     * Diese Methode ueberprueft, ob der Held das Talent "charme" enthaelt, falls ja werden die Kosten
     * bei allen Ereignissen, welche Gold Kosten gesenkt
     * @param spieler Der Held, welcher das Talent besitzt
     * @param mensch Das Ereignis, bei dem die Kosten gesenkt werden sollen
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
