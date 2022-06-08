package control;

import gui.xml.SpielebeneGuiController;
import model.Ebene;
import model.SpielStand;
import model.SpielfigurEbene;

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
     * @param spielStand Instanz der Klasse SpielStand, welcher den
     * Ereignissen uebergeben wird.
     * @return true, wenn die Bewegung erfolgreich war.
     */
    public static boolean bewegen(Ebene ebene, int ziel_x, int ziel_y,
                               SpielStand spielStand)
    {
        SpielfigurEbene spielfigur = ebene.getSpielfigur();
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        final int start_x = spielfigur.getPosition_x();
        final int start_y = spielfigur.getPosition_y();
        final int umkreis = 1;

        if ((ebene.getRaumAnPosition(ziel_x, ziel_y) != null))
        {
            zielErreichbarInX = (ziel_x == start_x + umkreis) ||
                                (ziel_x == start_x - umkreis);
            zielErreichbarInY = (ziel_y == start_y + umkreis) ||
                                (ziel_y == start_y - umkreis);

            if ((zielErreichbarInX || zielErreichbarInY) &&
                !(zielErreichbarInX && zielErreichbarInY))
            {
                spielfigur.setPosition(ziel_x, ziel_y);
                //SpielebeneGuiController.oeffneEreignis(ebene.getRaumAnPosition(ziel_x, ziel_y).getEreignis());
                //ebene.getRaumAnPosition(ziel_x, ziel_y).getEreignis()
                //    .ausfuehren(spielStand);
                return true;
            }
        }
        return false;
    }
}
