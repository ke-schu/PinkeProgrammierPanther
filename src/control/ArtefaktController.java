package control;

import model.Spieler;
import resources.Artefakte;

/**
 Diese Klasse enthaelt Methoden um mit Instanzen von Artefakten zu
 interagieren. */
public class ArtefaktController
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private ArtefaktController ()
    {
    }
    
    /**
     Diese Methode dient dazu ein Artefakt, aus dem Besitz des Spielers zu
     entfernen.
     @param artefakt Das zu entfernende Artefakt.
     @param spieler Der Spieler, bei dem das Artefakt entfernt werden soll.
     @return Es wird zurueckgegeben, ob das Artefakt entfernt werden konnte.
     */
    public static boolean entferneArtefakt (Artefakte artefakt,
                                            Spieler spieler)
    {
        Artefakte[] meineArtefakte = spieler.getArtefakte();
        for (int i = 0; i < meineArtefakte.length - 1; i++)
        {
            if (meineArtefakte[i] == artefakt)
            {
                meineArtefakte[i] = null;
                return true;
            }
        }
        return false;
    }
}
