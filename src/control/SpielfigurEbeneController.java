package control;

import model.Ebene;
import model.SpielStand;
import model.SpielfigurEbene;

import static resources.Zahlen.ZAHL_1;

public class SpielfigurEbeneController
{

    public static void bewegen (Ebene ebene, int ziel_x, int ziel_y, SpielfigurEbene spielfigur, SpielStand spielStand)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int start_x = spielfigur.getPosition_x();
        int start_y = spielfigur.getPosition_y();

        if ((ebene.getRaumAnPosition(ziel_x, ziel_y) != null))
        {
            zielErreichbarInX = (ziel_x == spielfigur.getPosition_x() + ZAHL_1) || (ziel_x == spielfigur.getPosition_x() - ZAHL_1);
            zielErreichbarInY = (ziel_y == spielfigur.getPosition_y() + ZAHL_1) || (ziel_y == spielfigur.getPosition_y() - ZAHL_1);

            if ((zielErreichbarInX || zielErreichbarInY) && !(zielErreichbarInX && zielErreichbarInY))
            {
                ebene.getRaumAnPosition(ziel_x, ziel_y).setSpielfigur(spielfigur);
                spielfigur.setPosition(ziel_x,ziel_y);
                ebene.getRaumAnPosition(start_x, start_y).setSpielfigur(null);

                ebene.getRaumAnPosition(ziel_x,ziel_y).getEreignis().ausfuehren(spielStand);
            }
        }
    }

}
