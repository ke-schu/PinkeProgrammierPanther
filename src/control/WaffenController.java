package control;

import model.SpielStand;
import resources.Waffen;

import static resources.Konstanten.*;
import static resources.Waffen.*;

/**
 * Diese Klasse dient als Controller um Methoden fuer Waffen auszuformulieren
 */
public class WaffenController
{
    /**
     * Diese Methode formuliert aus, wie eine Waffe ausgeruestet wird
     * @param spiel Der Spielstand, der die Waffe erhaelt
     * @param waffe Die Waffe, die ausgeruestet wird
     */
    public static void waffeAusruesten (SpielStand spiel, Waffen waffe)
    {
           spiel.getSpieler().setWaffen(waffe);
           spiel.getSpieler().setMacht(spiel.getSpieler().getMacht() + waffe.getMACHT());
           spiel.getSpieler().setReichweite(waffe.getREICHWEITE());
    }

    public static Waffen generiereZufaelligeWaffe()
    {
        double waffenNummer;
        waffenNummer = Math.random() * GRENZE;
        if(waffenNummer <=  BOGEN_GRENZE)
        {
            return BOGEN;
        }
        else if (waffenNummer <= SCHWERT_GRENZE)
        {
            return SCHWERT;
        }
        else
        {
            return AXT;
        }
    }
}
