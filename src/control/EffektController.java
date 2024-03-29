package control;

import model.KarteEinheit;
import model.Position;
import model.SpielFeld;
import resources.Effekte;

import static control.EinheitenController.positionInnerhalbVonFeld;

/**
 Loest die Effekte der Karten aus und kontrolliert diese. */
public class EffektController
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private EffektController ()
    {
    }
    
    /**
     Diese Methode erkennt, ob die Einheit einen Sterbeeffekt hat und falls
     ja,
     wird die zugehoerige Methode aufgerufen.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     @param meinEffekt Der auszuloesende Effekt.
     @param feld Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     */
    public static void sterbenEffektAusloesen (KarteEinheit ausloeser,
                                               Effekte meinEffekt,
                                               SpielFeld feld)
    {
        switch (meinEffekt)
        {
            case KOPIE:
                kopie(ausloeser, feld);
                break;
            case HELDENTAT:
                // heldentat();
                break;
            case ZURUECKWERFEN:
                //zurueckWerfen(ausloeser, feld);
                break;
        }
    }
    
    /**
     Diese Methode formuliert den Effekt "Kopie" aus. Dieser Effekt erstellt
     eine Kopie von der ausloesenden Einheit, wenn sie stirbt.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     @param feld Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     */
    private static void kopie (KarteEinheit ausloeser, SpielFeld feld)
    {
        feld.einheitEinsetzten(ausloeser.getPositionX(),
                               ausloeser.getPositionY(),
                               ausloeser.kopieErstelen(ausloeser));
    }
    
    /**
     Diese Methode erkennt, ob die Einheit einen Angriffseffekt hat und falls
     ja, wird die zugehoerige Methode aufgerufen.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     @param ziel Die Einheit, die angegriffen werden soll.
     @param meinEffekt Der auszuloesende Effekt.
     @param feld Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     */
    public static void angriffEffektAusloesen (
            KarteEinheit ausloeser, KarteEinheit ziel,
            Effekte meinEffekt, SpielFeld feld)
    {
        switch (meinEffekt)
        {
            case RAUBTIER:
                raubtier(ausloeser, ziel);
                break;
            case ZURUECKWERFEN:
                try
                {
                    zurueckWerfen(ausloeser, feld);
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    return;
                }
                break;
            default:
                return;
        }
    }
    
    /**
     Wirft die umliegenden, feindlichen Einheiten zurueck.
     @param ausloeser die Einheit, welche den Effekt ausloest.
     @param feld das Spielfeld, auf dem gespielt wird.
     */
    private static void zurueckWerfen (KarteEinheit ausloeser, SpielFeld feld)
    {
        final int UMKREIS_1 = 1;
        final int UMKREIS_2 = 2;
        KarteEinheit zielOben = null;
        KarteEinheit platzOben = null;
        KarteEinheit zielUnten = null;
        KarteEinheit platzUnten = null;
        KarteEinheit zielLinks = null;
        KarteEinheit platzLinks = null;
        KarteEinheit zielRechts = null;
        KarteEinheit platzRechts = null;
        
        zielOben = karteEinheitEinsetzenOben(
                ausloeser, zielOben, UMKREIS_1, feld);
        platzOben = karteEinheitEinsetzenOben(
                ausloeser, platzOben, UMKREIS_2, feld);
        zielUnten = karteEinheitEinsetzenUnten(
                ausloeser, zielUnten, UMKREIS_1, feld);
        platzUnten = karteEinheitEinsetzenUnten(
                ausloeser, platzUnten, UMKREIS_2, feld);
        zielLinks = karteEinheitEinsetzenLinks(
                ausloeser, zielLinks, UMKREIS_1, feld);
        platzLinks = karteEinheitEinsetzenLinks(
                ausloeser, platzLinks, UMKREIS_2, feld);
        zielRechts = karteEinheitEinsetzenRechts(
                ausloeser, zielRechts, UMKREIS_1, feld);
        platzRechts = karteEinheitEinsetzenRechts(
                ausloeser, platzRechts, UMKREIS_2, feld);
        
        if (zielOben != null && platzOben == null)
        {
            feld.einheitEinsetzten(ausloeser.getPositionX(),
                                   ausloeser.getPositionY() - UMKREIS_2,
                                   zielOben);
            feld.einheitLoeschen(ausloeser.getPositionX(),
                                 ausloeser.getPositionY() - UMKREIS_1);
        }
        if (zielUnten != null && platzUnten == null)
        {
            feld.einheitEinsetzten(ausloeser.getPositionX(),
                                   ausloeser.getPositionY() + UMKREIS_2,
                                   zielUnten);
            feld.einheitLoeschen(ausloeser.getPositionX(),
                                 ausloeser.getPositionY() + UMKREIS_1);
        }
        
        if (zielLinks != null && platzLinks == null)
        {
            feld.einheitEinsetzten(ausloeser.getPositionX() - UMKREIS_2,
                                   ausloeser.getPositionY(), zielLinks);
            feld.einheitLoeschen(ausloeser.getPositionX() - UMKREIS_1,
                                 ausloeser.getPositionY());
        }
        
        if (zielRechts != null && platzRechts == null)
        {
            feld.einheitEinsetzten(ausloeser.getPositionX() + UMKREIS_2,
                                   ausloeser.getPositionY(), zielRechts);
            feld.einheitLoeschen(ausloeser.getPositionX() + UMKREIS_1,
                                 ausloeser.getPositionY());
        }
    }
    
    /**
     Methode um festzustellen, ob der Effekt in die anvisierte Richtung
     ausgeloest werden kann.
     @param ausloeser Die ausloesende Einheit
     @param ziel Die Einheit die zurueckgeworfen wird
     @param umkreis Der Umkreis um den Ausloeser
     @param feld Das Spielfeld auf dem gespielt wird
     @return die KarteEinheit die initialisiert werden soll
     */
    private static KarteEinheit karteEinheitEinsetzenOben (
            KarteEinheit ausloeser, KarteEinheit ziel, int umkreis,
            SpielFeld feld)
    {
        Position pos = new Position(
                ausloeser.getPositionX(), ausloeser.getPositionY() - umkreis);
        
        if (positionInnerhalbVonFeld(pos, feld))
        {
            ziel = feld.getSpielfeldplatz(ausloeser.getPositionX(),
                                          ausloeser.getPositionY() -
                                          umkreis);
        }
        return ziel;
    }
    
    /**
     Methode um festzustellen, ob der Effekt in die anvisierte Richtung
     ausgeloest werden kann.
     @param ausloeser Die ausloesende Einheit
     @param ziel Die Einheit die zurueckgeworfen wird
     @param umkreis Der Umkreis um den Ausloeser
     @param feld Das Spielfeld auf dem gespielt wird
     @return die KarteEinheit die initialisiert werden soll
     */
    private static KarteEinheit karteEinheitEinsetzenUnten (
            KarteEinheit ausloeser, KarteEinheit ziel, int umkreis,
            SpielFeld feld)
    {
        Position position = new Position(ausloeser.getPositionX(),
                                         ausloeser.getPositionY() +
                                         umkreis);
        if (positionInnerhalbVonFeld(position, feld))
        {
            ziel = feld.getSpielfeldplatz(ausloeser.getPositionX(),
                                          ausloeser.getPositionY() +
                                          umkreis);
        }
        return ziel;
    }
    
    /**
     Methode um festzustellen, ob der Effekt in die anvisierte Richtung
     ausgeloest werden kann.
     @param ausloeser Die ausloesende Einheit
     @param ziel Die Einheit die zurueckgeworfen wird
     @param umkreis Der Umkreis um den Ausloeser
     @param feld Das Spielfeld auf dem gespielt wird
     @return die KarteEinheit die initialisiert werden soll
     */
    private static KarteEinheit karteEinheitEinsetzenLinks (
            KarteEinheit ausloeser, KarteEinheit ziel, int umkreis,
            SpielFeld feld)
    {
        Position position = new Position(ausloeser.getPositionX() - umkreis,
                                         ausloeser.getPositionY());
        if (positionInnerhalbVonFeld(position, feld))
        {
            ziel = feld.getSpielfeldplatz(ausloeser.getPositionX() - umkreis,
                                          ausloeser.getPositionY());
        }
        return ziel;
    }
    
    /**
     Methode um festzustellen, ob der Effekt in die anvisierte Richtung
     ausgeloest werden kann.
     @param ausloeser Die ausloesende Einheit
     @param ziel Die Einheit die zurueckgeworfen wird
     @param umkreis Der Umkreis um den Ausloeser
     @param feld Das Spielfeld auf dem gespielt wird
     @return die KarteEinheit die initialisiert werden soll
     */
    private static KarteEinheit karteEinheitEinsetzenRechts (
            KarteEinheit ausloeser, KarteEinheit ziel, int umkreis,
            SpielFeld feld)
    {
        Position position = new Position(ausloeser.getPositionX() + umkreis,
                                         ausloeser.getPositionY());
        if (positionInnerhalbVonFeld(position, feld))
        {
            ziel = feld.getSpielfeldplatz(ausloeser.getPositionX() + umkreis,
                                          ausloeser.getPositionY());
        }
        return ziel;
    }
    
    /**
     Diese Methode formuliert den Effekt "Raubtier" aus. Dieser Effekt laesst
     die Einheit zweimal angreifen sofern die Macht der Einheit hoeher ist,
     als
     die des Ziels und hoeher als 0.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     @param ziel Die Einheit, die angegriffen werden soll.
     */
    private static void raubtier (KarteEinheit ausloeser, KarteEinheit ziel)
    {
        
        if ((ausloeser.getMacht() > ziel.getMacht()) &&
            ausloeser.getZaehler() == 0)
        {
            ausloeser.setZaehler(1);
            ausloeser.setSchlafend(false);
        }
    }
    
    /**
     Diese Methode erkennt, ob die Einheit einen Starteffekt hat und falls ja,
     wird die zugehoerige Methode aufgerufen.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     @param meinEffekt Der auszuloesende Effekt.
     */
    public static void startEffektAusloesen (KarteEinheit ausloeser,
                                             Effekte meinEffekt)
    {
        switch (meinEffekt)
        {
            case SPRINT:
                sprint(ausloeser);
                break;
            case EILE:
                eile(ausloeser);
                break;
            default:
                return;
        }
    }
    
    /**
     Diese Methode formuliert den Effekt "Sprint" aus. Dieser Effekt erhoeht
     den Bewegungswert der Einheit um 1.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     */
    private static void sprint (KarteEinheit ausloeser)
    {
        ausloeser.setBeweglichkeit(ausloeser.getBeweglichkeit() + 1);
    }
    
    /**
     Diese Methode formuliert den Effekt "Eile" aus. Dieser Effekt sorgt,
     dafuer, dass die Einheit sich sofort nach dem ausspielen bewegen kann.
     @param ausloeser Die Einheit, die den Effekt ausloest.
     */
    private static void eile (KarteEinheit ausloeser)
    {
        ausloeser.setSchlafend(false);
    }
}
