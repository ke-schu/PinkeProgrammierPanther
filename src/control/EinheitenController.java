package control;

import model.*;
import static resources.Zahlen.*;


/**
 * Klasse in der sich Methoden befinden die mit Instanzen von Einheiten interagieren.
 */
public class EinheitenController
{
    /**
     * Methode zum Springen von Instanzen von Einheiten im Spielfeldarray.
     * @param spielfeld Instanz des Spielfeldes auf der die Einheit bewegt werden soll.
     * @param ziel_x Integer mit der Zielzeile der Bewegung.
     * @param ziel_y Integer mit der Zielspalte der Bewegung.
     * @param einheit Einheit die springen soll.
     */
    public static void springen (SpielFeld spielfeld, int ziel_x, int ziel_y, KarteEinheit einheit)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int start_x = einheit.getPosition_x();
        int start_y = einheit.getPosition_y();
        boolean selbeZeile = false;
        boolean selbeSpalte = false;

        if (spielfeld.getSpielfeldplatz(ziel_x, ziel_y) == null)
        {
            int beweglichkeit = einheit.getBeweglichkeit();
            int distanz = Math.abs((ziel_x - start_x) - (ziel_y - start_y));

            selbeZeile = (einheit.getPosition_x() == ziel_x);
            selbeSpalte = (einheit.getPosition_y() == ziel_y);
            zielErreichbarInX = (ziel_x <= einheit.getPosition_x() + beweglichkeit) || (ziel_x >= einheit.getPosition_x() - beweglichkeit);
            zielErreichbarInY = (ziel_y <= einheit.getPosition_y() + beweglichkeit) || (ziel_y >= einheit.getPosition_y() - beweglichkeit);

            if ((zielErreichbarInX || zielErreichbarInY) && (selbeZeile || selbeSpalte) && (beweglichkeit - distanz) >= ZAHL_0)
            {
                spielfeld.einheiteinsetzten(ziel_x, ziel_y, einheit);
                einheit.setPosition(ziel_x,ziel_y);
                spielfeld.einheiteinsetzten(start_x, start_y, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - distanz);
            }
        }
    }

     /**
     * Methode zum bewegen von Instanzen von Einheiten im Spielfeldarray.
     * @param spielfeld Instanz des Spielfeldes auf der die Einheit bewegt werden soll.
     * @param ziel_x Integer mit der Zielzeile der Bewegung.
     * @param ziel_y Integer mit der Zielspalte der Bewegung.
     * @param einheit Einheit die bewegt werden soll.
      */
    public static void bewegen (SpielFeld spielfeld, int ziel_x, int ziel_y, KarteEinheit einheit)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int start_x = einheit.getPosition_x();
        int start_y = einheit.getPosition_y();

        if ((spielfeld.getSpielfeldplatz(ziel_x, ziel_y) == null) && (einheit.getBeweglichkeit() > ZAHL_0))
        {
            zielErreichbarInX = (ziel_x == einheit.getPosition_x() + ZAHL_1) || (ziel_x == einheit.getPosition_x() - ZAHL_1);
            zielErreichbarInY = (ziel_y == einheit.getPosition_y() + ZAHL_1) || (ziel_y == einheit.getPosition_y() - ZAHL_1);

            if ((zielErreichbarInX || zielErreichbarInY) && !(zielErreichbarInX && zielErreichbarInY))
            {
                spielfeld.einheiteinsetzten(ziel_x, ziel_y, einheit);
                einheit.setPosition(ziel_x,ziel_y);
                spielfeld.einheiteinsetzten(start_x, start_y, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - ZAHL_1);
            }
        }
    }

    /**
     * Mit dieser Methode kann mit einer auf dem Spielfeld platzierten Einheit eine weitere angegriffen werden.
     * @param spielfeld Das Spielfeld auf dem sich beide Einheiten befinden.
     * @param angreifer Einheit, welche angreift.
     * @param verteidiger Einheit, welche angegriffen wird.
     */
    public static void einheitenAngreifenMitEinheiten (SpielFeld spielfeld, KarteEinheit angreifer, KarteEinheit verteidiger)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        boolean selbeZeile = false;
        boolean selbeSpalte = false;

        selbeZeile = (angreifer.getPosition_x() == verteidiger.getPosition_x());
        selbeSpalte = (angreifer.getPosition_y() == verteidiger.getPosition_y());

        zielErreichbarInX = ((verteidiger.getPosition_x() <= angreifer.getPosition_x() + angreifer.getReichweite()) || (verteidiger.getPosition_x() >= angreifer.getPosition_x() - angreifer.getReichweite()));
        zielErreichbarInY = ((verteidiger.getPosition_y() <= angreifer.getPosition_y() + angreifer.getReichweite()) || (verteidiger.getPosition_y() >= angreifer.getPosition_y() - angreifer.getReichweite()));



        if ((zielErreichbarInX && selbeSpalte) || (zielErreichbarInY && selbeZeile))
        {
            berechneSchaden(verteidiger, angreifer.getMacht());
        }
    }

    /**
     * Mit diese Methode kann eine Instanz der Klasse KarteZauber auf eine Instanz der Klasse KarteEinheit innerhalb des Spielfeldes angewendet werden.
     * Die Macht von KarteZauber wird mit den Lebenspunkten von KarteEinheit verechnet.
     * @param spielfeld Spiefeld auf dem sich die Instanz von KarteEinheit befindet.
     * @param zauber Instanz von KarteZauber, welche benutzt werden soll.
     * @param verteidiger Instanz von KarteEinheit, welche angegriffen wird.
     */
    public static void einheitenAngreifenMitKarteZauber (SpielFeld spielfeld, KarteZauber zauber, KarteEinheit verteidiger)
    {
        {
            berechneSchaden(verteidiger, zauber.getMacht());
        }
    }

    /**
     * Methode um Schaden mit den Lebens- und Verteidigungswerten einer Instanz von KarteEinheit zu verrechnen.
     * @param verteidiger Instanz von KarteEinheit mit dessen Verteidigungswerten ein Schadenswert verrechnet werden soll.
     * @param schadensWert der Schadenwert, welcher mit dem Ziel verrechnet werden soll.
     */
    private static void berechneSchaden (KarteEinheit verteidiger, int schadensWert)
    {
        int differenzDefAtk = verteidiger.getVerteidigung() - schadensWert;

        if (verteidiger.getSchild() > ZAHL_0)
        {
            verteidiger.setSchild(verteidiger.getSchild() - ZAHL_1);

        }
        if (differenzDefAtk < ZAHL_0)
        {
            verteidiger.setLebenspunkte(verteidiger.getLebenspunkte() - differenzDefAtk);
        }
    }
}
