package control;

import model.Ebene;
import model.Position;
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
     *
     * @param ebene      Instanz der Klasse Ebene in der sich die Instanz von
     *                   SpielfigurEbene befindet.
     * @param zielX      Zielkoordinate des Raums auf der X-Achse.
     * @param zielY      Zielkoordinate des Raums auf der Y-Achse.
     * @param spielStand Instanz der Klasse SpielStand, welcher den
     *                   Ereignissen uebergeben wird.
     * @return true, wenn die Bewegung erfolgreich war.
     */
    public static boolean bewegen(Ebene ebene, int zielX, int zielY,
                                  SpielStand spielStand)
    {
        SpielfigurEbene spielfigur = ebene.getSpielfigur();

        final Position zielPosition = new Position(zielX, zielY);
        final Position startPosition = spielfigur.getPosition();
        final int startX = startPosition.getX();
        final int startY = startPosition.getY();
        final int umkreis = 1;

        if ((ebene.getRaumAnPosition(zielX, zielY) != null))
        {
            boolean zielErreichbarInX = (zielX == startX + umkreis) ||
                                        (zielX == startX - umkreis) ||
                                        (zielX == startX);
            boolean zielErreichbarInY = (zielY == startY + umkreis) ||
                                        (zielY == startY - umkreis) ||
                                        (zielY == startY);

            if (zielErreichbarInX && zielErreichbarInY &&
                !zielPosition.equals(startPosition))
            {
                spielfigur.setPosition(zielPosition);
                return true;
            }
        }
        return false;
    }
}
