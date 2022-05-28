package control;

import model.Ebene;
import model.SpielfigurEbene;

import static resources.Konstanten.SPIELFIGUR_EBENE_STARTPOSITION_X;
import static resources.Konstanten.SPIELFIGUR_EBENE_STARTPOSITION_Y;

/**
 * Diese Klasse enthaelt Methoden, um mit Instanzen der Klasse Ebene zu
 * interagieren.
 */
public class EbeneController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EbeneController()
    {
    }

    /**
     * Diese Methode initialisiert eine Instanz der Klasse SpielfigurEbene an
     * den Indexen (4,4) in einer Ebene.
     * @param spielfigur Die Spielfigur, welche in der Ebene initialisiert
     * werden soll.
     * @param ebene Die Ebene, in welcher die Instanz der Klasse
     * SpielfigurEbene initialisiert werden soll.
     */
    public static void initSpielerInEbene(SpielfigurEbene spielfigur,
                                          Ebene ebene)
    {
        ebene.getRaumAnPosition(SPIELFIGUR_EBENE_STARTPOSITION_X,
                                SPIELFIGUR_EBENE_STARTPOSITION_Y)
             .setSpielfigur(spielfigur);
        spielfigur.setPosition(SPIELFIGUR_EBENE_STARTPOSITION_X,
                               SPIELFIGUR_EBENE_STARTPOSITION_Y);
    }
}