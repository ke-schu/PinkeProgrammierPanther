package control;

import model.*;
import static resources.Zahlen.*;

public class EffektController
{
    public void effektAusloesen(KarteEinheit ausloeser, KarteEinheit ziel, SpielFeld feld)
    {
        switch (ausloeser.getEffektEins())
        {
            case LETZTEWORTE: letzteworte(ausloeser, ziel, feld); break;
        }

    }
    public static void schadenVerursachen (KarteEinheit ziel, int schaden)
    {
        ziel.schadenNehmen(schaden);
    }

    public static void letzteworte (KarteEinheit ausloeser, KarteEinheit ziel, SpielFeld feld)
    {
        if (ausloeser.getLebenspunkte() == 0);
        {
            zurueckwerfen(ausloeser, feld);
        }
    }
    public static void zurueckwerfen (KarteEinheit ausloeser, SpielFeld feld)
    {
        try
        {
            KarteEinheit zieloben = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()- ZAHL_1);
            KarteEinheit platzoben = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2);

            if (zieloben != null && platzoben == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_2, zieloben);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()-ZAHL_1);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }

        try
        {
            KarteEinheit zielunten = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
            KarteEinheit platzunten = feld.getSpielfeldplatz(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2);

            if (zielunten != null && platzunten == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_2, zielunten);
                feld.einheitloeschen(ausloeser.getPosition_x(), ausloeser.getPosition_y()+ZAHL_1);
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }

        try
        {
            KarteEinheit ziellinks  = feld.getSpielfeldplatz(ausloeser.getPosition_x()-ZAHL_1, ausloeser.getPosition_y());
            KarteEinheit platzlinks = feld.getSpielfeldplatz(ausloeser.getPosition_x()-ZAHL_2, ausloeser.getPosition_y());

            if (ziellinks != null && platzlinks == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x() - ZAHL_2, ausloeser.getPosition_y(), ziellinks);
                feld.einheitloeschen(ausloeser.getPosition_x() - ZAHL_1, ausloeser.getPosition_y());
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }

        try
        {
            KarteEinheit zielrechts  = feld.getSpielfeldplatz(ausloeser.getPosition_x()+ZAHL_1, ausloeser.getPosition_y());
            KarteEinheit platzrechts = feld.getSpielfeldplatz(ausloeser.getPosition_x()+ZAHL_2, ausloeser.getPosition_y());

            if (zielrechts != null && platzrechts == null)
            {
                feld.einheiteinsetzten(ausloeser.getPosition_x() + ZAHL_2, ausloeser.getPosition_y(), zielrechts);
                feld.einheitloeschen(ausloeser.getPosition_x() + ZAHL_1, ausloeser.getPosition_y());
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {

        }
    }
}
