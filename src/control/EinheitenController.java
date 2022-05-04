package control;

import model.Einheit;
import model.Spielfeld;

public class EinheitenController
{
    public static void bewegen (int start_x, int start_y , Spielfeld spielfeld, int ziel_x, int ziel_y)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;

        if ((spielfeld.getSpielfeldplatz(start_x, start_y) != null) && (spielfeld.getSpielfeldplatz(ziel_x, ziel_y)== null))
        {
            Einheit einheit = new Einheit();
            einheit = spielfeld.getSpielfeldplatz(start_x, start_y);
            int beweglichkeit = einheit.getBeweglichkeit();

            zielErreichbarInX = (ziel_x <= start_x + beweglichkeit) && (ziel_x >= start_x - ziel_x);
            zielErreichbarInY = (ziel_y <= start_y + beweglichkeit) && (ziel_y >= start_y - ziel_x);

            if (zielErreichbarInX && zielErreichbarInY)
            {
                 spielfeld.einheiteinsetzten(ziel_x, ziel_y, einheit);
                 spielfeld.einheiteinsetzten(start_x, start_y, null);
            }


        }

    }

}
