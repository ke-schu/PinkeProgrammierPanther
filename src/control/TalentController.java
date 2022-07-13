package control;

import model.Spieler;
import model.ereignisse.Mensch;

import static resources.Konstanten.CHARME_FAKTOR;
import static resources.Talente.CHARME;

/**
 Diese Klasse dient als Controller um Methoden fuer Talente
 auszuformulieren. */
public class TalentController
{
    /**
     Diese Methode ueberprueft, ob der Held das Talent "CHARME" besitzt. Wenn
     ja, werden die Kosten bei allen Ereignissen, welche Gold Kosten gesenkt.
     @param spieler Der Held, welcher das Talent besitzt.
     @param mensch Das Ereignis, bei dem die Kosten gesenkt werden sollen.
     */
    public static void charme (Spieler spieler, Mensch mensch)
    {
        if (spieler.getTalente().contains(CHARME))
        {
            double i = mensch.getKosten() * CHARME_FAKTOR;
            mensch.setKosten((int) i);
        }
    }
}