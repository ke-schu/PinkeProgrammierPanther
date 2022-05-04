package control;

import model.KarteEinheit;

public class KartenController
{
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
              }
              else if (karte.getKartenLevel() == 2)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 2;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 1;
                  karte.setKartenMacht(atk);
              }
              else if (karte.getKartenLevel() == 1)
              {
                  lp = karte.getKartenLebenspunkte();
                  lp = lp + 1;
                  karte.setKartenLebenspunkte(lp);
                  atk = karte.getKartenMacht();
                  atk = atk + 1;
                  karte.setKartenMacht(atk);
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
