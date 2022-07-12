package control;

import model.*;

import static resources.Konstanten.WERT_SCHILD;


/**
 * Klasse, in der sich Methoden befinden die, mit Instanzen der Klasse
 * KarteEinheit interagieren.
 */
public class EinheitenController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EinheitenController()
    {
    }

    /**
     * Methode zum Springen von Instanzen von Einheiten im Spielfeldarray.
     *
     * @param spielfeld Instanz des Spielfeldes, auf der die Einheit bewegt
     *                  werden soll.
     * @param zielX     Integer, mit der Zielzeile der Bewegung.
     * @param zielY     Integer, mit der Zielspalte der Bewegung.
     * @param einheit   Einheit, die springen soll.
     */
    public static void springen(SpielFeld spielfeld, int zielX, int zielY,
                                KarteEinheit einheit)
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

            selbeZeile  = (startX == zielX);
            selbeSpalte = (startY == zielY);

            zielErreichbarInX =
                    (zielX <= startX + flex) || (zielX >= startX - flex);
            zielErreichbarInY =
                    (zielY <= startY + flex) || (zielY >= startY - flex);

            if ((zielErreichbarInX || zielErreichbarInY) &&
                (selbeZeile || selbeSpalte) && (flex - distanz) >= 0)
            {
                spielfeld.einheitEinsetzten(zielX, zielY, einheit);
                einheit.setPosition(zielX, zielY);
                spielfeld.einheitEinsetzten(startX, startY, null);
                einheit.setBeweglichkeit(einheit.getBeweglichkeit() - distanz);
            }
        }
    }

    /**
     * Methode zum Bewegen von Instanzen von Einheiten im Spielfeldarray.
     *
     * @param spielfeld Instanz des Spielfeldes, auf der die Einheit bewegt
     *                  werden soll.
     * @param ziel_x    Integer mit der Zielzeile der Bewegung.
     * @param ziel_y    Integer mit der Zielspalte der Bewegung.
     * @param einheit   Einheit die bewegt werden soll.
     */
    public static boolean bewegen(boolean istspieler,SpielFeld spielfeld, int ziel_x, int ziel_y,
                               KarteEinheit einheit)
    {
        if(einheit != null)
        {
            boolean zielErreichbarInX = false;
            boolean zielErreichbarInY = false;
            final int startX = einheit.getPositionX();
            final int startY = einheit.getPositionY();
            final int umkreis = 1;

            if ((spielfeld.getSpielfeldplatz(ziel_x, ziel_y) == null) &&
                (einheit.getBeweglichkeit() > 0) && ((RundenController.getDran() == istspieler)
                                                     &&(einheit.getFreundlich()==istspieler)))
            {
                zielErreichbarInX = (ziel_x == startX + umkreis) ||
                                    (ziel_x == startX - umkreis);
                zielErreichbarInY = (ziel_y == startY + umkreis) ||
                                    (ziel_y == startY - umkreis);

                if ((zielErreichbarInX || zielErreichbarInY) &&
                    !(zielErreichbarInX && zielErreichbarInY))
                {
                    spielfeld.einheitEinsetzten(ziel_x, ziel_y, einheit);
                    einheit.setPosition(ziel_x, ziel_y);
                    spielfeld.einheitEinsetzten(startX, startY, null);
                    einheit.setBeweglichkeit(einheit.getBeweglichkeit() - 1);
                }
            }

            return KartenEinheitController.bewegenErfolgreich(spielfeld, einheit, ziel_x , ziel_y);
        }
        System.out.println("Bewegen war nicht erfolgreich");
        return false;
    }

    /**
     * Ueberprueft, ob ein Verteidiger in Reichweite des Angreifers ist.
     *
     * @param angreifer   die angreifende Einheit.
     * @param verteidiger die verteidigende Einheit.
     * @return true, wenn sie in Reichweite ist.
     */
    private static boolean einheitInReichweite(KarteEinheit angreifer,
                                               KarteEinheit verteidiger)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        boolean selbeZeile = false;
        boolean selbeSpalte = false;
        final int angreiferX = angreifer.getPositionX();
        final int angreiferY = angreifer.getPositionY();
        final int verteidigerX = verteidiger.getPositionX();
        final int verteidigerY = verteidiger.getPositionX();
        final int reichweite = angreifer.getReichweite();

        selbeZeile  = angreiferX == verteidigerX;
        selbeSpalte = angreiferY == verteidigerY;

        zielErreichbarInX = (verteidigerX <= angreiferX + reichweite) ||
                            (verteidigerX >= angreiferX - reichweite);
        zielErreichbarInY = (verteidigerY <= angreiferY + reichweite) ||
                            (verteidigerY >= angreiferY - reichweite);

        return (zielErreichbarInX && selbeSpalte) ||
               (zielErreichbarInY && selbeZeile);
    }

    /**
     * Mit dieser Methode kann mit einer auf dem Spielfeld platzierten Einheit
     * eine weitere angegriffen werden.
     *
     * @param angreifer   Einheit, welche angreift.
     * @param verteidiger Einheit, welche angegriffen wird.
     */
    public static void einheitenAngreifenMitEinheiten(SpielFeld feld,
                                                      KartenDeck spielerDeck,
                                                      KartenDeck masterDeck,
                                                      KarteEinheit angreifer,
                                                      KarteEinheit verteidiger)
    {
        boolean schlafend = angreifer.getSchlafend();
        if ((einheitInReichweite(angreifer, verteidiger) &&
             pruefeObFeindlich(angreifer, verteidiger)) && !schlafend)
        {
            if (verteidiger.getSchild() >= WERT_SCHILD)
            {
                brecheSchild(verteidiger);
                System.out.println("schildgebrochen");
                angreifer.setSchlafend(true);
            }
            else
            {
                verursacheSchaden(verteidiger, angreifer.getMacht());
                RundenController.feldplatzAufraumen(feld, spielerDeck,
                                                    masterDeck,
                                                    verteidiger.getPositionX(),
                                                    verteidiger.getPositionY());
                EffektController.angriffEffektAusloesen(angreifer, verteidiger,
                                                        angreifer.getEffektEins(),
                                                        feld, spielerDeck,
                                                        masterDeck);
                EffektController.angriffEffektAusloesen(angreifer, verteidiger,
                                                        angreifer.getEffektZwei(),
                                                        feld, spielerDeck,
                                                        masterDeck);
                System.out.println("angriffausgeführt");
                angreifer.setSchlafend(true);
            }
        }
    }

    /**
     * Diese Methode ueberprueft, ob die beiden uebergebenen Einheiten sich
     * angreifen koennen.
     *
     * @param angreifer   Die Einheit, die angreifen soll.
     * @param verteidiger Die Einheit, die angegriffen werden soll.
     * @return Der Rueckgabewert ist, ob die Einheiten sich angreifen koennen.
     */
    public static boolean pruefeObFeindlich(KarteEinheit angreifer,
                                            KarteEinheit verteidiger)
    {
        if (angreifer.getFreundlich() != verteidiger.getFreundlich())
        {
            return true;
        }
        return false;
    }

    /**
     * Diese Methode setzt den Schildwert der Einheit um eine Konstante tiefer.
     *
     * @param verteidiger Die Einheit, dessen Schild gesenkt wird.
     */
    public static void brecheSchild(KarteEinheit verteidiger)
    {
        verteidiger.setSchild(verteidiger.getSchild() - WERT_SCHILD);
    }

    /**
     * Mit dieser Methode kann eine Instanz der Klasse KarteZauber auf eine
     * Instanz der Klasse KarteEinheit innerhalb des Spielfeldes angewendet
     * werden. Die Macht von KarteZauber wird mit den Lebenspunkten von
     * KarteEinheit verrechnet.
     *
     * @param zauber      Instanz von KarteZauber, welche benutzt werden soll.
     * @param verteidiger Instanz von KarteEinheit, welche angegriffen wird.
     */
    public static void einheitenAngreifenMitKarteZauber(KarteZauber zauber,
                                                        KarteEinheit verteidiger)
    {
        verursacheSchaden(verteidiger, zauber.getMacht());
    }

    /**
     * Methode um Schaden mit den Lebens- und Verteidigungswerten einer
     * Instanz von KarteEinheit zu verrechnen.
     *
     * @param verteidiger  Die KarteEinheit mit dessen Verteidigungswerten ein
     *                     Schadenswert verrechnet werden soll.
     * @param schadensWert der Schadenwert, welcher mit dem Ziel verrechnet
     *                     werden soll.
     */
    protected static void verursacheSchaden(KarteEinheit verteidiger,
                                          int schadensWert)
    {
        int schaden = schadensWert - verteidiger.getVerteidigung();

        if (schaden > 0)
        {
            verteidiger.schadenNehmen(schaden);
        }
    }

    /**
     * Diese Methode berechnet die Position hinter einer anvisierten Karte.
     *
     * @param ausloeser Die anvisierende Karte.
     * @param ziel      Die anvisierte Karte.
     * @param feld      Das Spielfeld auf dem gespielt wird.
     * @return Die Position hinter der anvisierten Karte.
     */
    public static Position positionHinterKarteBerechnen(KarteEinheit ausloeser,
                                                        KarteEinheit ziel,
                                                        SpielFeld feld)
    {
        Position positionHinterKarte = new Position();
        int xAusloeser = ausloeser.getPositionX();
        int yAusloeser = ausloeser.getPositionY();

        int xZiel = ziel.getPositionX();
        int yZiel = ziel.getPositionY();

        if (xAusloeser == xZiel)
        {
            positionHinterKarte.setX(xAusloeser);
            if (yAusloeser > yZiel)
                positionHinterKarte.setY(yZiel - 1);
            else if (yZiel > yAusloeser)
            {
                positionHinterKarte.setY(yZiel + 1);
            }
        }
        if (yAusloeser == yZiel)
        {
            positionHinterKarte.setY(yAusloeser);
            if (xAusloeser > xZiel)
                positionHinterKarte.setX(xZiel - 1);
            else if (xZiel > xAusloeser)
            {
                positionHinterKarte.setX(xZiel + 1);
            }
        }
        return positionHinterKarte;
    }

    /**
     * Diese Methode ueberprueft, ob eine Position innerhalb des vorhanden
     * Spielfeldes liegt.
     *
     * @param position Die zu ueberpruefende Position.
     * @param feld     Das Spielfeld auf dem gespielt wird.
     * @return Das Ergebnis, ob die Position innerhalb liegt oder nicht.
     */
    public static boolean positionInnerhalbVonFeld(Position position,
                                                   SpielFeld feld)
    {
        if ((position.getX() < feld.getSpalten()) &&
            (position.getY() < feld.getZeilen()))
        {
            return true;
        }
        return false;
    }
}
