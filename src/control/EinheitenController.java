package control;

import model.KarteEinheit;
import model.KartenDeck;
import model.Position;
import model.SpielFeld;

import static resources.Konstanten.WERT_SCHILD;
import static resources.KonstantenGUI.*;


/**
 Klasse, in der sich Methoden befinden die, mit Instanzen der Klasse
 KarteEinheit interagieren. */
public class EinheitenController
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private EinheitenController ()
    {
    }
    
    /**
     Methode zum Bewegen von Instanzen von Einheiten im Spielfeldarray.
     @param spielfeld Instanz des Spielfeldes, auf der die Einheit bewegt
     werden soll.
     @param ziel_x Integer mit der Zielzeile der Bewegung.
     @param ziel_y Integer mit der Zielspalte der Bewegung.
     @param einheit Einheit die bewegt werden soll.
     */
    public static boolean bewegen (boolean istSpieler, SpielFeld spielfeld,
                                   int ziel_x, int ziel_y,
                                   KarteEinheit einheit)
    {
        if (einheit != null)
        {
            boolean zielErreichbarInX = false;
            boolean zielErreichbarInY = false;
            final int startX = einheit.getPositionX();
            final int startY = einheit.getPositionY();
            final int umkreis = 1;
            
            if ((spielfeld.getSpielfeldplatz(ziel_x, ziel_y) == null) &&
                (einheit.getBeweglichkeit() > 0) &&
                ((RundenController.getDran() == istSpieler)
                 && (einheit.getFreundlich() == istSpieler)) &&
                (!einheit.getSchlafend()))
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
            return KartenEinheitController.bewegenErfolgreich(
                    spielfeld, einheit, ziel_x, ziel_y);
        }
        return false;
    }
    
    /**
     Mit dieser Methode kann mit einer auf dem Spielfeld platzierten Einheit
     eine weitere angegriffen werden.
     @param angreifer Einheit, welche angreift.
     @param verteidiger Einheit, welche angegriffen wird.
     */
    public static int einheitenAngreifenMitEinheiten (
            boolean istSpieler, SpielFeld feld,
            KartenDeck spielerDeck, KartenDeck gegnerDeck,
            KarteEinheit angreifer, KarteEinheit verteidiger)
    {
        if ((RundenController.getDran() == istSpieler)
            && (angreifer.getFreundlich() == istSpieler))
        {
            boolean schlafend = angreifer.getSchlafend();
            if ((einheitInReichweite(angreifer, verteidiger) &&
                 pruefeObFeindlich(angreifer, verteidiger)) && !schlafend)
            {
                if (verteidiger.getSchild() >= WERT_SCHILD)
                {
                    brecheSchild(verteidiger);
                    angreifer.setSchlafend(true);
                    return RUECKMELDUNG_SCHILDBREAK;
                }
                else
                {
                    verursacheSchaden(verteidiger, angreifer.getMacht());
                    boolean gestorben = RundenController.feldplatzAufraumen(
                            feld, spielerDeck, gegnerDeck,
                            verteidiger.getPositionX(),
                            verteidiger.getPositionY());
                    EffektController.angriffEffektAusloesen(
                            angreifer, verteidiger, angreifer.getEffektEins(),
                            feld);
                    EffektController.angriffEffektAusloesen(
                            angreifer, verteidiger, angreifer.getEffektZwei(),
                            feld);
                    angreifer.setSchlafend(true);
                    if (gestorben)
                    {
                        return RUECKMELDUNG_GESTORBEN;
                    }
                    return RUECKMELDUNG_SCHADEN;
                }
            }
        }
        return RUECKMELDUNG_ERFOLGLOS;
    }
    
    /**
     Ueberprueft, ob ein Verteidiger in Reichweite des Angreifers ist.
     @param angreifer die angreifende Einheit.
     @param verteidiger die verteidigende Einheit.
     @return true, wenn sie in Reichweite ist.
     */
    private static boolean einheitInReichweite (KarteEinheit angreifer,
                                                KarteEinheit verteidiger)
    {
        boolean zielErreichbarInX = false;
        boolean zielErreichbarInY = false;
        boolean selbeZeile = false;
        boolean selbeSpalte = false;
        final int angreiferX = angreifer.getPositionX();
        final int angreiferY = angreifer.getPositionY();
        final int verteidigerX = verteidiger.getPositionX();
        final int verteidigerY = verteidiger.getPositionY();
        final int reichweite = angreifer.getReichweite();
        
        selbeZeile = angreiferX == verteidigerX;
        selbeSpalte = angreiferY == verteidigerY;
        
        zielErreichbarInX = (verteidigerX <= angreiferX + reichweite) ||
                            (verteidigerX >= angreiferX - reichweite);
        zielErreichbarInY = (verteidigerY <= angreiferY + reichweite) ||
                            (verteidigerY >= angreiferY - reichweite);
        
        return (zielErreichbarInX && selbeSpalte) ||
               (zielErreichbarInY && selbeZeile);
    }
    
    /**
     Diese Methode ueberprueft, ob die beiden uebergebenen Einheiten sich
     angreifen koennen.
     @param angreifer Die Einheit, die angreifen soll.
     @param verteidiger Die Einheit, die angegriffen werden soll.
     @return Der Rueckgabewert ist, ob die Einheiten sich angreifen koennen.
     */
    private static boolean pruefeObFeindlich (KarteEinheit angreifer,
                                              KarteEinheit verteidiger)
    {
        return angreifer.getFreundlich() != verteidiger.getFreundlich();
    }
    
    /**
     Diese Methode setzt den Schildwert der Einheit um eine Konstante tiefer.
     @param verteidiger Die Einheit, dessen Schild gesenkt wird.
     */
    private static void brecheSchild (KarteEinheit verteidiger)
    {
        verteidiger.setSchild(verteidiger.getSchild() - WERT_SCHILD);
    }
    
    /**
     Methode um Schaden mit den Lebens- und Verteidigungswerten einer Instanz
     von KarteEinheit zu verrechnen.
     @param verteidiger Die KarteEinheit mit dessen Verteidigungswerten ein
     Schadenswert verrechnet werden soll.
     @param schadensWert der Schadenwert, welcher mit dem Ziel verrechnet
     werden soll.
     */
    private static void verursacheSchaden (KarteEinheit verteidiger,
                                           int schadensWert)
    {
        int schaden = schadensWert - verteidiger.getVerteidigung();
        
        if (schaden > 0)
        {
            verteidiger.schadenNehmen(schaden);
        }
    }
    
    /**
     Diese Methode ueberprueft, ob eine Position innerhalb des vorhanden
     Spielfeldes liegt.
     @param position Die zu ueberpruefende Position.
     @param feld Das Spielfeld auf dem gespielt wird.
     @return Das Ergebnis, ob die Position innerhalb liegt oder nicht.
     */
    public static boolean positionInnerhalbVonFeld (Position position,
                                                    SpielFeld feld)
    {
        return (position.getX() < feld.getSpalten()) &&
               (position.getY() < feld.getZeilen());
    }
}
