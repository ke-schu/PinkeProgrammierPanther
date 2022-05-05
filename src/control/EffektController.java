package control;

import model.*;

public class EffektController
{
    public static void schadenVerursachen(Einheit ziel, int schaden)
    {
        ziel.schadenNehmen(schaden);
    }

    public static void letzteworte  (Einheit ausfuehrer, Einheit ziel)
    {
        if (ausfuehrer.getLebenspunkte() == 0);
        {
            zurueckwerfen(ziel);
        }
    }
    public static void zurueckwerfen(Einheit ziel)
    {

    }
}
