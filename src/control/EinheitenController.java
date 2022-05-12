package control;
import model.*;


/**
 * Klasse in der sich Methoden befinden die mit Instanzen von Einheiten interagieren.
 */
public class EinheitenController
{
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
        boolean selbeZeile = false;
        boolean selbeSpalte = false;

        if (spielfeld.getSpielfeldplatz(ziel_x, ziel_y)== null)
        {
            int beweglichkeit = einheit.getBeweglichkeit();

            selbeZeile = (einheit.getPosition_x() == ziel_x);
            selbeSpalte = (einheit.getPosition_y() == ziel_y);

            zielErreichbarInX = (ziel_x <= einheit.getPosition_x() + beweglichkeit) && (ziel_x >= einheit.getPosition_x() - ziel_x);
            zielErreichbarInY = (ziel_y <= einheit.getPosition_y() + beweglichkeit) && (ziel_y >= einheit.getPosition_y() - ziel_x);

            if ((zielErreichbarInX && zielErreichbarInY) && (selbeZeile || selbeSpalte))
            {
                 spielfeld.einheiteinsetzten(ziel_x, ziel_y, einheit);
                 spielfeld.einheiteinsetzten(start_x, start_y, null);
            }
        }
    }
}
