package control;

import model.*;

public class EffektController
{
    public static void schadenVerursachen(Einheit ziel, int schaden){
        ziel.schadenNehmen(schaden);
    }
}
