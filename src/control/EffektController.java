package control;

import model.*;
import static resources.zahlen.*;

public class EffektController
{
    public void effektAusloesen(Einheit ausloeser, Einheit ziel,Spielfeld feld)
    {
        switch (ausloeser.getEffekteins())
        {
            case LETZTEWORTE: letzteworte(ausloeser, ziel, feld); break;
        }

    }
    public static void schadenVerursachen (Einheit ziel, int schaden)
    {
        ziel.schadenNehmen(schaden);
    }

    public static void letzteworte (Einheit ausloeser, Einheit ziel, Spielfeld feld)
    {
        if (ausloeser.getLebenspunkte() == 0);
        {
            zurueckwerfen(ausloeser, feld);
        }
    }
    public static void zurueckwerfen (Einheit ausloeser, Spielfeld feld)
    {
        Einheit zieloben = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()- ZAHL_1);
        Einheit platzoben = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2);

        Einheit zielunten = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
        Einheit platzunten = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2);

        Einheit ziellinks  = feld.getSpielfeldplatz(ausloeser.getPosition_x()-ZAHL_1, ausloeser.getPosition_y());
        Einheit platzlinks = feld.getSpielfeldplatz(ausloeser.getPosition_x()-ZAHL_2, ausloeser.getPosition_y());

        Einheit zielrechts  = feld.getSpielfeldplatz(ausloeser.getPosition_x()+ZAHL_1, ausloeser.getPosition_y());
        Einheit platzrechts = feld.getSpielfeldplatz(ausloeser.getPosition_x()+ZAHL_2, ausloeser.getPosition_y());

            if (zieloben != null && platzoben == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2, zieloben);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_1);
            }

            if (zielunten != null && platzunten == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2, zielunten);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
            }

            if (ziellinks != null && platzlinks == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x() - ZAHL_2, ausloeser.getPosition_y(), ziellinks);
                feld.einheitloeschen(ausloeser.getPosition_x() - ZAHL_1, ausloeser.getPosition_y());
            }

            if (zielrechts != null && platzrechts == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x() + ZAHL_2, ausloeser.getPosition_y(), zielrechts);
                feld.einheitloeschen(ausloeser.getPosition_x() + ZAHL_1, ausloeser.getPosition_y());
            }


    }
}
