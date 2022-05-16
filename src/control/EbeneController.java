package control;

import control.test.EbeneTest;
import model.*;
import static resources.Zahlen.*;

/**
 * Diese Klasse enthält Methoden, um mit Instanzen der Klasse Ebene zu interagieren.
 */
public class EbeneController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private EbeneController ()
    {
    }

    /**
     * Diese Methode initialisiert eine Instanz der Klasse SpielfigurEbene an den Indexen (4,4) in einer Ebene.
     * @param spielfigur Die Spielfigur, welche in der Ebene initialisiert werden soll.
     * @param ebene Die Ebene, in welcher die Instanz der Klasse SpielfigurEbene initialisiert werden soll.
     */
    public static void initSpielerInEbene (SpielfigurEbene spielfigur, Ebene ebene)
    {
        ebene.getRaumAnPosition(ZAHL_4,ZAHL_4).setSpielfigur(spielfigur);
        spielfigur.setPosition(ZAHL_4,ZAHL_4);
    }
}