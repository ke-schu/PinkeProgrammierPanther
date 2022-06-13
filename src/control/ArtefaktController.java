package control;

import model.Spieler;
import resources.Artefakte;

/**
 * Diese Klasse enthaelt Methoden um mit Instanzen von Artefakten zu interagieren.
 */
public class ArtefaktController
{
    /**
     * Diese Methode ueberprueft, ob der Spieler ein bestimmtes Artefakt besitzt.
     * @param artefakt Das gesuchte Artefakt
     * @param spieler Der Spieler der ueberprueft werden soll
     * @return Es wird zurueckgegeben, ob der Spieler dieses Artefakt besitzt
     */
    public static boolean hatArtefakt (Artefakte artefakt, Spieler spieler)
    {
        Artefakte[] meineArtefake = spieler.getArtefakte();
        for(Artefakte a : meineArtefake)
        {
            if(a == artefakt)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Diese Methode dient dazu ein Artefakt, aus dem Besitz des Spielers zu entfernen.
     * @param artefakt Das zu entfernende Artefakt
     * @param spieler Der Spieler, bei dem das Artefakt entfernt werden soll
     * @return Es wird zurueckgegeben, ob das Artefakt entfernt werden konnte
     */
    public static boolean entferneArtefakt (Artefakte artefakt, Spieler spieler)
    {
        Artefakte[] meineArtefake = spieler.getArtefakte();
        for(int i = 0; i < meineArtefake.length - 1; i++)
        {
            if(meineArtefake[i] == artefakt)
            {
                meineArtefake[i] = null;
                return true;
            }
        }
        return false;
    }
}
