package control;

import model.KarteEinheit;
import static resources.Zahlen.*;

/**
 * In dieser Klassen befinden sich Methoden um mit Instanzen von Karten zu interagieren.
 */
public class KartenController
{
    /**
     * Mit dieser Methode werden die Karten verbessert, dabei wird zwischen den Klassen unterschieden.
     * @param karte Die Karte die verbessert werden soll.
     */
    public static void kartenVerbessern (KarteEinheit karte)
    {
      switch (karte.getKlasse())
      {
          case "Nahkaempfer":
              if (karte.getKartenLevel() == ZAHL_3)
              {
                  karte.setKartenLebenspunkte(karte.getKartenLebenspunkte() + ZAHL_2);
                  karte.setKartenMacht(karte.getKartenMacht() + ZAHL_2);
                  karte.setKartenLevel(ZAHL_4);
              }
              else if (karte.getKartenLevel() == ZAHL_2)
              {

                  karte.setKartenLebenspunkte(karte.getKartenLebenspunkte() + ZAHL_2);
                  karte.setKartenMacht(karte.getKartenMacht() + ZAHL_1);
                  karte.setKartenLevel(ZAHL_3);
              }
              else if (karte.getKartenLevel() == ZAHL_1)
              {
                  karte.setKartenLebenspunkte(karte.getKartenLebenspunkte() + ZAHL_1);
                  karte.setKartenMacht(karte.getKartenMacht() + ZAHL_1);
                  karte.setKartenLevel(ZAHL_2);
              }
              break;

          case "Fernkaempfer":
              if (karte.getKartenLevel() == ZAHL_3)
              {
                  karte.setKartenLebenspunkte(karte.getKartenLebenspunkte() + ZAHL_1);
                  karte.setKartenMacht(karte.getKartenMacht() + ZAHL_1);
                  karte.setKartenReichweite(karte.getKartenReichweite()+ ZAHL_1);
                  karte.setKartenLevel(ZAHL_4);
              }
              else if (karte.getKartenLevel() == ZAHL_2)
              {
                  karte.setKartenLebenspunkte(karte.getKartenLebenspunkte() + ZAHL_2);
                  karte.setKartenReichweite(karte.getKartenReichweite() + ZAHL_1);
                  karte.setKartenLevel(ZAHL_3);

              }
              else if (karte.getKartenLevel() == ZAHL_1)
              {
                  karte.setKartenLebenspunkte(karte.getKartenLebenspunkte() + ZAHL_1);
                  karte.setKartenMacht(karte.getKartenMacht() + ZAHL_2);
                  karte.setKartenLevel(ZAHL_2);
              }
            break;
      }
    }
}
