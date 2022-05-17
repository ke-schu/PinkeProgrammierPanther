package control;

import model.Ebene;
import model.SpielStand;
import model.SpielfigurEbene;

import static resources.Zahlen.ZAHL_1;

/**
 * Klasse, in der sich Methoden befinden, die mit Instanzen der Klasse
 * SpielfigurEbene interagieren.
 */
public class SpielfigurEbeneController
{

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielfigurEbeneController()
    {
    }

    /**
     * Methode, um eine Instanz von SpielfigurEbene in einen Raum der Ebene zu
     * bewegen und das Ereignis des Raums nach Betreten auszufuehren.
     * @param ebene Instanz der Klasse Ebene in der sich die Instanz von
     * SpielfigurEbene befindet.
     * @param ziel_x Zielkoordinate des Raums auf der X-Achse.
     * @param ziel_y Zielkoordinate des Raums auf der Y-Achse.
     * @param spielfigur Instanz der Klasse SpielfigurEbene, welche in der
     * Instanz von Ebene bewegt werden soll.
     * @param spielStand Instanz der Klasse SpielStand, welcher den
     * Ereignissen uebergeben wird.
     */
    public static void bewegen(Ebene ebene, int ziel_x, int ziel_y,
                               SpielfigurEbene spielfigur,
                               SpielStand spielStand)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int start_x = spielfigur.getPosition_x();
        int start_y = spielfigur.getPosition_y();

        if ((ebene.getRaumAnPosition(ziel_x, ziel_y) != null))
        {
            zielErreichbarInX = (ziel_x == start_x + ZAHL_1) ||
                                (ziel_x == start_x - ZAHL_1);
            zielErreichbarInY = (ziel_y == start_y + ZAHL_1) ||
                                (ziel_y == start_y - ZAHL_1);

            if ((zielErreichbarInX || zielErreichbarInY) &&
                !(zielErreichbarInX && zielErreichbarInY))
            {
                ebene.getRaumAnPosition(ziel_x, ziel_y)
                     .setSpielfigur(spielfigur);
                spielfigur.setPosition(ziel_x, ziel_y);
                ebene.getRaumAnPosition(start_x, start_y).setSpielfigur(null);
                ebene.getRaumAnPosition(ziel_x, ziel_y).getEreignis()
                     .ausfuehren(spielStand);
            }
        }
    }
}
