package control;

import exceptions.KarteNichtVerbessertException;
import model.Karte;
import model.KarteEinheit;
import model.KarteZauber;

import static resources.Zahlen.*;

/**
 * In dieser Klassen befinden sich Methoden, um mit Instanzen von Karten zu
 * interagieren.
 */
public class KartenController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenController()
    {
    }

    /**
     * Mit dieser Methode werden die Karten verbessert, dabei wird zwischen
     * den Klassen unterschieden.
     * @param karte Die Karte die verbessert werden soll.
     */
    public static void kartenVerbessern(Karte karte)
            throws KarteNichtVerbessertException
    {
        if (karte instanceof KarteEinheit)
        {
            kartenVerbessern((KarteEinheit) karte);
        }
        else if (karte instanceof KarteZauber)
        {
            //  TODO
        }
        else
        {
            throw new KarteNichtVerbessertException();
        }
    }

    private static void kartenVerbessern(KarteEinheit karteEinheit)
    {
        switch (karteEinheit.getTyp())
        {
            case NAHKAEMPFER:
                if (karteEinheit.getLevel() == ZAHL_3)
                {
                    karteEinheit.setLebenspunkte(karteEinheit.getLebenspunkte() + ZAHL_2);
                    karteEinheit.setMacht(karteEinheit.getMacht() + ZAHL_2);
                    karteEinheit.setLevel(ZAHL_4);
                } else if (karteEinheit.getLevel() == ZAHL_2)
                {
                    karteEinheit.setLebenspunkte(karteEinheit.getLebenspunkte() + ZAHL_2);
                    karteEinheit.setMacht(karteEinheit.getMacht() + ZAHL_1);
                    karteEinheit.setLevel(ZAHL_3);
                } else if (karteEinheit.getLevel() == ZAHL_1)
                {
                    karteEinheit.setLebenspunkte(karteEinheit.getLebenspunkte() + ZAHL_1);
                    karteEinheit.setMacht(karteEinheit.getMacht() + ZAHL_1);
                    karteEinheit.setLevel(ZAHL_2);
                }
                break;

            case FERNKAEMPFER:
                if (karteEinheit.getLevel() == ZAHL_3)
                {
                    karteEinheit.setLebenspunkte(karteEinheit.getLebenspunkte() + ZAHL_1);
                    karteEinheit.setMacht(karteEinheit.getMacht() + ZAHL_1);
                    karteEinheit.setReichweite(karteEinheit.getReichweite() + ZAHL_1);
                    karteEinheit.setLevel(ZAHL_4);
                } else if (karteEinheit.getLevel() == ZAHL_2)
                {
                    karteEinheit.setLebenspunkte(karteEinheit.getLebenspunkte() + ZAHL_2);
                    karteEinheit.setReichweite(karteEinheit.getReichweite() + ZAHL_1);
                    karteEinheit.setLevel(ZAHL_3);

                } else if (karteEinheit.getLevel() == ZAHL_1)
                {
                    karteEinheit.setLebenspunkte(karteEinheit.getLebenspunkte() + ZAHL_1);
                    karteEinheit.setMacht(karteEinheit.getMacht() + ZAHL_2);
                    karteEinheit.setLevel(ZAHL_2);
                }
                break;
            default:
                return;
        }
    }
}
