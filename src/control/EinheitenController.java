package control;

import model.*;
import static resources.Zahlen.*;


/**
 * Klasse in der sich Methoden befinden die mit Instanzen von Einheiten interagieren.
 */
public class EinheitenController
{
    /**
     * Methode zum Springen von Instanzen von Einheiten im Spielfeldarray.
     * @param spielfeld Instanz des Spielfeldes auf der die Einheit bewegt werden soll.
     * @param ziel_x Integer mit der Zielzeile der Bewegung.
     * @param ziel_y Integer mit der Zielspalte der Bewegung.
     * @param einheit Einheit die springen soll.
     */
    public static void springen (SpielFeld spielfeld, int ziel_x, int ziel_y, KarteEinheit einheit)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int start_x = einheit.getPosition_x();
        int start_y = einheit.getPosition_y();
        boolean selbeZeile = false;
        boolean selbeSpalte = false;

        if (spielfeld.getSpielfeldplatz(ziel_x, ziel_y)== null)
        {
            int beweglichkeit = einheit.getBeweglichkeit();
            int distanz = Math.abs((ziel_x - start_x) - (ziel_y - start_y));

            selbeZeile = (einheit.getPosition_x() == ziel_x);
            selbeSpalte = (einheit.getPosition_y() == ziel_y);
            zielErreichbarInX = (ziel_x <= einheit.getPosition_x() + beweglichkeit) || (ziel_x >= einheit.getPosition_x() - beweglichkeit);
            zielErreichbarInY = (ziel_y <= einheit.getPosition_y() + beweglichkeit) || (ziel_y >= einheit.getPosition_y() - beweglichkeit);

            if ((zielErreichbarInX || zielErreichbarInY) && (selbeZeile || selbeSpalte) && (beweglichkeit - distanz) >= ZAHL_0)
            {
                spielfeld.einheiteinsetzten(ziel_x, ziel_y, einheit);
                einheit.setPosition(ziel_x,ziel_y);
                spielfeld.einheiteinsetzten(start_x, start_y, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - distanz);
            }
        }
    }

     /**
     * Methode zum bewegen von Instanzen von Einheiten im Spielfeldarray.
     * @param spielfeld Instanz des Spielfeldes auf der die Einheit bewegt werden soll.
     * @param ziel_x Integer mit der Zielzeile der Bewegung.
     * @param ziel_y Integer mit der Zielspalte der Bewegung.
     * @param einheit Einheit die bewegt werden soll.
      */
    public static void bewegen (SpielFeld spielfeld, int ziel_x, int ziel_y, KarteEinheit einheit)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int start_x = einheit.getPosition_x();
        int start_y = einheit.getPosition_y();

        if ((spielfeld.getSpielfeldplatz(ziel_x, ziel_y) == null) && (einheit.getBeweglichkeit() > ZAHL_0))
        {
            zielErreichbarInX = (ziel_x == einheit.getPosition_x() + ZAHL_1) || (ziel_x == einheit.getPosition_x() - ZAHL_1);
            zielErreichbarInY = (ziel_y == einheit.getPosition_y() + ZAHL_1) || (ziel_y == einheit.getPosition_y() - ZAHL_1);

            if ((zielErreichbarInX || zielErreichbarInY) && !(zielErreichbarInX && zielErreichbarInY))
            {
                spielfeld.einheiteinsetzten(ziel_x, ziel_y, einheit);
                einheit.setPosition(ziel_x,ziel_y);
                spielfeld.einheiteinsetzten(start_x, start_y, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - ZAHL_1);
            }
        }
    }
}
