package control;

import model.KarteEinheit;

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
              if (karte.getKartenLevel() == 3)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 2;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 2;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(4);
              }
              else if (karte.getKartenLevel() == 2)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 2;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 1;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(3);
              }
              else if (karte.getKartenLevel() == 1)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 1;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 1;
                  karte.setKartenMacht(atk);
                  karte.setKartenLevel(2);
              }
              break;

          case "Fernkaempfer":
              if (karte.getKartenLevel() == 3)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 1;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 1;
                  karte.setKartenMacht(atk);
                  reichweite = karte.getKartenReichweite();
                  reichweite++;
                  karte.setKartenReichweite(reichweite);
              }
              else if (karte.getKartenLevel() == 2)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 2;
                  karte.setKartenLebenspunkte(lp);
                  reichweite = karte.getKartenReichweite();
                  reichweite++;
                  karte.setKartenReichweite(reichweite);

              }
              else if (karte.getKartenLevel() == 1)
              {
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 2;
                  karte.setKartenMacht(atk);
              }
            break;
      }
    }
}
