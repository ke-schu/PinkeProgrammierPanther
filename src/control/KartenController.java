package control;

import model.KarteEinheit;

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
    public static void kartenVerbessern(KarteEinheit karte)
    {
        //  Muss noch fuer andere Karten anwendbar gemacht werden
        switch (karte.getTyp())
        {
            case NAHKAEMPFER:
                if (karte.getLevel() == ZAHL_3)
                {
                    karte.setLebenspunkte(karte.getLebenspunkte() + ZAHL_2);
                    karte.setMacht(karte.getMacht() + ZAHL_2);
                    karte.setLevel(ZAHL_4);
                } else if (karte.getLevel() == ZAHL_2)
                {
                    karte.setLebenspunkte(karte.getLebenspunkte() + ZAHL_2);
                    karte.setMacht(karte.getMacht() + ZAHL_1);
                    karte.setLevel(ZAHL_3);
                } else if (karte.getLevel() == ZAHL_1)
                {
                    karte.setLebenspunkte(karte.getLebenspunkte() + ZAHL_1);
                    karte.setMacht(karte.getMacht() + ZAHL_1);
                    karte.setLevel(ZAHL_2);
                }
                break;

            case FERNKAEMPFER:
                if (karte.getLevel() == ZAHL_3)
                {
                    karte.setLebenspunkte(karte.getLebenspunkte() + ZAHL_1);
                    karte.setMacht(karte.getMacht() + ZAHL_1);
                    karte.setReichweite(karte.getReichweite() + ZAHL_1);
                    karte.setLevel(ZAHL_4);
                } else if (karte.getLevel() == ZAHL_2)
                {
                    karte.setLebenspunkte(karte.getLebenspunkte() + ZAHL_2);
                    karte.setReichweite(karte.getReichweite() + ZAHL_1);
                    karte.setLevel(ZAHL_3);

                } else if (karte.getLevel() == ZAHL_1)
                {
                    karte.setLebenspunkte(karte.getLebenspunkte() + ZAHL_1);
                    karte.setMacht(karte.getMacht() + ZAHL_2);
                    karte.setLevel(ZAHL_2);
                }
                break;
            default:
                return;
        }
    }
}
