package control;

import model.KarteEinheit;
import model.KarteZauber;
import model.SpielFeld;

import static resources.Zahlen.ZAHL_0;
import static resources.Zahlen.ZAHL_1;


/**
 * Klasse, in der sich Methoden befinden die, mit Instanzen der Klasse KarteEinheit interagieren.
 */
public class EinheitenController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private EinheitenController ()
    {
    }

    /**
     * Methode zum Springen von Instanzen von Einheiten im Spielfeldarray.
     * @param spielfeld Instanz des Spielfeldes, auf der die Einheit bewegt werden soll.
     * @param zielX Integer, mit der Zielzeile der Bewegung.
     * @param zielY Integer, mit der Zielspalte der Bewegung.
     * @param einheit Einheit, die springen soll.
     */
    public static void springen (SpielFeld spielfeld, int zielX, int zielY, KarteEinheit einheit)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int startX = einheit.getPositionX();
        int startY = einheit.getPositionY();
        boolean selbeZeile = false;
        boolean selbeSpalte = false;

        if (spielfeld.getSpielfeldplatz(zielX, zielY) == null)
        {
            int flex = einheit.getBeweglichkeit();
            int distanz = Math.abs((zielX - startX) - (zielY - startY));

            selbeZeile = (startX == zielX);
            selbeSpalte = (startY == zielY);

            zielErreichbarInX = (zielX <= startX + flex) || (zielX >= startX - flex);
            zielErreichbarInY = (zielY <= startY + flex) || (zielY >= startY - flex);

            if ((zielErreichbarInX || zielErreichbarInY) && (selbeZeile || selbeSpalte) && (flex - distanz) >= ZAHL_0)
            {
                spielfeld.einheitEinsetzten(zielX, zielY, einheit);
                einheit.setPosition(zielX,zielY);
                spielfeld.einheitEinsetzten(startX, startY, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - distanz);
            }
        }
    }

     /**
     * Methode zum Bewegen von Instanzen von Einheiten im Spielfeldarray.
     * @param spielfeld Instanz des Spielfeldes, auf der die Einheit bewegt werden soll.
     * @param ziel_x Integer mit der Zielzeile der Bewegung.
     * @param ziel_y Integer mit der Zielspalte der Bewegung.
     * @param einheit Einheit die bewegt werden soll.
      */
    public static void bewegen (SpielFeld spielfeld, int ziel_x, int ziel_y, KarteEinheit einheit)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        int startX = einheit.getPositionX();
        int startY = einheit.getPositionY();

        if ((spielfeld.getSpielfeldplatz(ziel_x, ziel_y) == null) && (einheit.getBeweglichkeit() > ZAHL_0))
        {
            zielErreichbarInX = (ziel_x == startX + ZAHL_1) || (ziel_x == startX - ZAHL_1);
            zielErreichbarInY = (ziel_y == startY + ZAHL_1) || (ziel_y == startY - ZAHL_1);

            if ((zielErreichbarInX || zielErreichbarInY) && !(zielErreichbarInX && zielErreichbarInY))
            {
                spielfeld.einheitEinsetzten(ziel_x, ziel_y, einheit);
                einheit.setPosition(ziel_x,ziel_y);
                spielfeld.einheitEinsetzten(startX, startY, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - ZAHL_1);
            }
        }
    }

    /**
     * Ueberprueft, ob ein Verteidiger in Reichweite des Angreifers ist.
     * @param angreifer die angreifende Einheit
     * @param verteidiger die verteidigende Einheit
     * @return true, wenn sie in Reichweite ist.
     */
    private static boolean einheitInReichweite (KarteEinheit angreifer, KarteEinheit verteidiger)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        boolean selbeZeile = false;
        boolean selbeSpalte = false;
        int angreiferX = angreifer.getPositionX();
        int angreiferY = angreifer.getPositionY();
        int verteidigerX = verteidiger.getPositionX();
        int verteidigerY = verteidiger.getPositionX();
        int reichweite = angreifer.getReichweite();

        selbeZeile = angreiferX == verteidigerX;
        selbeSpalte = angreiferY == verteidigerY;

        zielErreichbarInX = (verteidigerX <= angreiferX + reichweite) || (verteidigerX >= angreiferX - reichweite);
        zielErreichbarInY = (verteidigerY <= angreiferY + reichweite) || (verteidigerY >= angreiferY - reichweite);

        return (zielErreichbarInX && selbeSpalte) || (zielErreichbarInY && selbeZeile);
    }

    /**
     * Mit dieser Methode kann mit einer auf dem Spielfeld platzierten Einheit eine weitere angegriffen werden.
     * @param angreifer Einheit, welche angreift.
     * @param verteidiger Einheit, welche angegriffen wird.
     */
    public static void einheitenAngreifenMitEinheiten (KarteEinheit angreifer, KarteEinheit verteidiger)
    {
        if (einheitInReichweite(angreifer, verteidiger))
        {
            berechneSchaden(verteidiger, angreifer.getMacht());
        }
    }

    /**
     * Mit dieser Methode kann eine Instanz der Klasse KarteZauber auf eine Instanz der Klasse KarteEinheit
     * innerhalb des Spielfeldes angewendet werden.
     * Die Macht von KarteZauber wird mit den Lebenspunkten von KarteEinheit verrechnet.
     * @param zauber Instanz von KarteZauber, welche benutzt werden soll.
     * @param verteidiger Instanz von KarteEinheit, welche angegriffen wird.
     */
    public static void einheitenAngreifenMitKarteZauber (KarteZauber zauber, KarteEinheit verteidiger)
    {
        berechneSchaden(verteidiger, zauber.getMacht());
    }

    /**
     * Methode um Schaden mit den Lebens- und Verteidigungswerten einer Instanz von KarteEinheit zu verrechnen.
     * @param verteidiger Die KarteEinheit mit dessen Verteidigungswerten ein Schadenswert verrechnet werden soll.
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
            verteidiger.setLebenspunkte(verteidiger.getLebenspunkte() + differenzDefAtk);
        }
    }
}
