package control;

import exceptions.JsonNichtLesbarException;
import io.KonsolenIO;
import io.SpielStandIO;
import model.SpielStand;
import model.Spieler;
import resources.Artefakte;

public class ArtefaktController
{
    public static boolean hatArtefakt(Artefakte artefakt, Spieler spieler)
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

    public static boolean entferneArtefakt(Artefakte artefakt, Spieler spieler)
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
