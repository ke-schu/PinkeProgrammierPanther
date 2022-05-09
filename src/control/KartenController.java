package control;

import model.KarteEinheit;
import static resources.zahlen.*;

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
        int lp = 0;
        int atk = 0;
        int reichweite = 0;

      switch (karte.getKlasse())
      {
          case "Nahkaempfer":
              if (karte.getKartenLevel() == ZAHL_3)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + ZAHL_2;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + ZAHL_2;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(ZAHL_4);
              }
              else if (karte.getKartenLevel() == ZAHL_2)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + ZAHL_2;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + ZAHL_1;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(ZAHL_3);
              }
              else if (karte.getKartenLevel() == ZAHL_1)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + ZAHL_1;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + ZAHL_1;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(ZAHL_2);
              }
              break;

          case "Fernkaempfer":
              if (karte.getKartenLevel() == ZAHL_3)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + ZAHL_1;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + ZAHL_1;
                  karte.setKartenMacht(atk);
                  reichweite = karte.getKartenReichweite();
                  reichweite++;
                  karte.setKartenReichweite(reichweite);
                  karte.setKartenLevel(ZAHL_4);
              }
              else if (karte.getKartenLevel() == ZAHL_2)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + ZAHL_2;
                  karte.setKartenLebenspunkte(lp);
                  reichweite = karte.getKartenReichweite();
                  reichweite++;
                  karte.setKartenReichweite(reichweite);
                  karte.setKartenLevel(ZAHL_3);

              }
              else if (karte.getKartenLevel() == ZAHL_1)
              {
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + ZAHL_2;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(ZAHL_2);
              }
            break;
      }
    }
}
