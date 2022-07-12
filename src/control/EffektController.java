package control;

import model.*;
import resources.Effekte;
import utility.KonsolenIO;

import static control.EinheitenController.positionInnerhalbVonFeld;

/**
 * Loest die Effekte der Karten aus und kontrolliert diese.
 */
public class EffektController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EffektController()
    {
    }

    /**
     * Loest einen neuen Effekt aus.
     *
     * @param ausloeser die Einheit, welche den Effekt ausloest.
     * @param feld      das Spielfeld, auf dem gespielt wird.
     */
    public static void aktionEffektAusloesen(KarteEinheit ausloeser,
                                             KarteEinheit ziel,
                                             Effekte meineffekt, SpielFeld feld)
    {
        switch (meineffekt)
        {
            case OPFERN:
                //opfern();
                break;
            default:
                return;
        }
    }

    /**
     * Diese Methode erkennt, ob die Einheit einen Sterbeeffekt hat und falls
     * ja, wird die zugehoerige
     * Methode aufgerufen.
     *
     * @param ausloeser  Die Einheit, die den Effekt ausloest.
     * @param meineffekt Der auszuloesende Effekt.
     * @param feld       Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     */
    public static void sterbenEffektAusloesen(KarteEinheit ausloeser,
                                              Effekte meineffekt,
                                              SpielFeld feld)
    {
        switch (meineffekt)
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
     * Diese Methode erkennt, ob die Einheit einen Angriffseffekt hat und
     * falls ja, wird die zugehoerige
     * Methode aufgerufen.
     *
     * @param ausloeser   Die Einheit, die den Effekt ausloest.
     * @param ziel        Die Einheit, die angegriffen werden soll.
     * @param meineffekt  Der auszuloesende Effekt.
     * @param feld        Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     * @param spielerDeck Das Deck des Players.
     * @param masterDeck  Das Deck des DungeonMaster.
     */
    public static void angriffEffektAusloesen(KarteEinheit ausloeser,
                                              KarteEinheit ziel,
                                              Effekte meineffekt,
                                              SpielFeld feld,
                                              KartenDeck spielerDeck,
                                              KartenDeck masterDeck)
    {
        switch (meineffekt)
        {
            case RAUBTIER:
                raubtier(ausloeser, ziel);
                break;
            case DURCHSCHNEIDEN:
                durchschneiden(ausloeser, ziel, feld, spielerDeck, masterDeck);
                break;
            case VERSCHLINGEN:
                // verschlingen();
                break;
            default:
                return;
        }
    }

    /**
     * Diese Methode erkennt, ob die Einheit einen Starteffekt hat und falls
     * ja, wird die zugehoerige
     * Methode aufgerufen.
     *
     * @param ausloeser  Die Einheit, die den Effekt ausloest.
     * @param meineffekt Der auszuloesende Effekt.
     */
    public static void startEffektAusloesen(KarteEinheit ausloeser,
                                            Effekte meineffekt)
    {
        switch (meineffekt)
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
     * Wirft die umliegenden, feindlichen Einheiten zurueck.
     *
     * @param ausloeser die Einheit, welche den Effekt ausloest.
     * @param feld      das Spielfeld, auf dem gespielt wird.
     */
    private static void zurueckWerfen(KarteEinheit ausloeser, SpielFeld feld)
    {
       final int umkreis1 = 1;
        final int umkreis2 = 2;
        KarteEinheit zielOben = null;
        KarteEinheit platzOben = null;
        Position positionzieloben =new Position( ausloeser.getPositionX(),
                ausloeser.getPositionY() -
                umkreis1);
        if(positionInnerhalbVonFeld(positionzieloben, feld))
        {
            zielOben = feld.getSpielfeldplatz(ausloeser.getPositionX(),
                    ausloeser.getPositionY() -
                    umkreis1);
        }

        Position positionplatzoben = new Position(ausloeser.getPositionX(),
                ausloeser.getPositionY() - umkreis2);
      if(positionInnerhalbVonFeld(positionplatzoben, feld))
      {
          platzOben =
                  feld.getSpielfeldplatz(ausloeser.getPositionX(),
                          ausloeser.getPositionY() - umkreis2);
      }

        KarteEinheit zielUnten =
                feld.getSpielfeldplatz(ausloeser.getPositionX(),
                                       ausloeser.getPositionY() + umkreis1);
        KarteEinheit platzUnten =
                feld.getSpielfeldplatz(ausloeser.getPositionX(),
                                       ausloeser.getPositionY() + umkreis2);
        KarteEinheit zielLinks =
                feld.getSpielfeldplatz(ausloeser.getPositionX() - umkreis1,
                                       ausloeser.getPositionY());
        KarteEinheit platzLinks =
                feld.getSpielfeldplatz(ausloeser.getPositionX() - umkreis2,
                                       ausloeser.getPositionY());
        KarteEinheit zielRechts =
                feld.getSpielfeldplatz(ausloeser.getPositionX() + umkreis1,
                                       ausloeser.getPositionY());
        KarteEinheit platzRechts =
                feld.getSpielfeldplatz(ausloeser.getPositionX() + umkreis2,
                                       ausloeser.getPositionY());

        try
        {
            if (zielOben != null && platzOben == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX(),
                                       ausloeser.getPositionY() - umkreis2,
                                       zielOben);
                feld.einheitLoeschen(ausloeser.getPositionX(),
                                     ausloeser.getPositionY() - umkreis1);
            }

            if (zielUnten != null && platzUnten == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX(),
                                       ausloeser.getPositionY() + umkreis2,
                                       zielUnten);
                feld.einheitLoeschen(ausloeser.getPositionX(),
                                     ausloeser.getPositionY() + umkreis1);
            }

            if (zielLinks != null && platzLinks == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX() - umkreis2,
                                       ausloeser.getPositionY(), zielLinks);
                feld.einheitLoeschen(ausloeser.getPositionX() - umkreis1,
                                     ausloeser.getPositionY());
            }

            if (zielRechts != null && platzRechts == null)
            {
                feld.einheitEinsetzten(ausloeser.getPositionX() + umkreis2,
                                       ausloeser.getPositionY(), zielRechts);
                feld.einheitLoeschen(ausloeser.getPositionX() + umkreis1,
                                     ausloeser.getPositionY());
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Diese Methode formuliert den Effekt "Sprint" aus. Dieser Effekt
     * erhoeht den Bewegungswert der Einheit
     * um 1.
     *
     * @param ausloeser Die Einheit, die den Effekt ausloest.
     */
    private static void sprint(KarteEinheit ausloeser)
    {
        ausloeser.setBeweglichkeit(ausloeser.getBeweglichkeit() + 1);
    }

    /**
     * Diese Methode formuliert den Effekt "Raubtier" aus. Dieser Effekt
     * laesst die Einheit zweimal angreifen
     * sofern die Macht der Einheit hoeher ist, als die des Ziels und hoeher
     * als 0.
     *
     * @param ausloeser Die Einheit, die den Effekt ausloest.
     * @param ziel      Die Einheit, die angegriffen werden soll.
     */
    private static void raubtier(KarteEinheit ausloeser, KarteEinheit ziel)
    {

        if ((ausloeser.getMacht() > ziel.getMacht()) &&
            ausloeser.getZaehler() == 0)
        {
            ausloeser.setZaehler(1);
            ausloeser.setSchlafend(false);
        }
    }

    /**
     * Diese Methode formuliert den Effekt "Eile" aus. Dieser Effekt sorgt,
     * dafuer, dass die Einheit sich
     * sofort nach dem ausspielen bewegen kann.
     *
     * @param ausloeser Die Einheit, die den Effekt ausloest.
     */
    private static void eile(KarteEinheit ausloeser)
    {
        ausloeser.setSchlafend(false);
    }

    /**
     * Diese Methode formuliert den Effekt "Kopie" aus. Dieser Effekt
     * erstellt eine Kopie von der ausloesenden
     * Einheit, wenn sie stirbt.
     *
     * @param ausloeser Die Einheit, die den Effekt ausloest.
     * @param feld      Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     */
    private static void kopie(KarteEinheit ausloeser, SpielFeld feld)
    {
        feld.einheitEinsetzten(ausloeser.getPositionX(),
                               ausloeser.getPositionY(),
                               ausloeser.kopieErstelen(ausloeser));
    }

    /**
     * Diese Methode formuliert den Effekt "Durchschneiden" aus. Dieser
     * Effekt ermoeglicht es ein Ziel hinter
     * einer anderen Einheit anzugreifen.
     *
     * @param ausloeser   Die Einheit, die den Effekt auslöst.
     * @param ziel        Die Einheit, die angegriffen werden soll.
     * @param feld        Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     * @param spielerDeck Das Deck des Players.
     * @param masterDeck  Das Deck des DungeonMaster.
     */
    private static void durchschneiden(
           KarteEinheit ausloeser, KarteEinheit ziel, SpielFeld feld,
            KartenDeck spielerDeck, KartenDeck masterDeck)
    {/*
        Position positonhinterziel =
                EinheitenController.positionHinterKarteBerechnen(ausloeser,
                                                                 ziel, feld);
        boolean imfeld =
                positionInnerhalbVonFeld(positonhinterziel,
                                                             feld);
        if (imfeld)
        {
            EinheitenController.einheitenAngreifenMitEinheiten(
                    feld, spielerDeck, masterDeck, ausloeser,
                    feld.getSpielfeldplatz(positonhinterziel.getX(),
                                           positonhinterziel.getY()));
        }*/
    }

    /**
     * Diese Methode formuliert den Effekt "Verschlingen" aus. Dieser Effekt
     * heilt den Ausloeser um den Wert,
     * das Ziel als Macht besitzt.
     *
     * @param ausloeser Die Einheit, die den Effekt ausloest.
     * @param ziel      Die Einheit, die angegriffen werden soll.
     */
    private static void verschlingen(KarteEinheit ausloeser, KarteEinheit ziel)
    {
        ausloeser.heilen(ziel.getMacht());
    }

    /**
     * Diese Methode formuliert den Effekt "Opfern" aus. Die ausloesende
     * Karte opfert sich selbst um einer
     * anderen Einheit +1 Leben und Macht zu geben.
     *
     * @param ausloeser   Die Einheit, die den Effekt ausloest.
     * @param ziel        Die Einheit, die angegriffen werden soll.
     * @param feld        Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     * @param spielerDeck Das Deck des Player.
     * @param masterDeck  Das Deck des DungeonMaster.
     */
    private static void opfern(KarteEinheit ausloeser, KarteEinheit ziel,
                               SpielFeld feld, KartenDeck spielerDeck,
                               KartenDeck masterDeck)
    {
        if (ausloeser.getFreundlich() == ziel.getFreundlich())
            ziel.heilen(1);
        ziel.angriffErhoehen(1);
        RundenController.feldplatzAufraumen(feld, spielerDeck, masterDeck,
                                            ausloeser.getPositionX(),
                                            ausloeser.getPositionY());
    }

    /**
     * Diese Methode formuliert den Effekt "Heldentat" aus. Dieser Effekt
     * erhoeht die Macht des Helden wenn
     * die ausloesende Einheit stirbt.
     *
     * @param ausloeser Die Einheit, die den Effekt ausloest.
     * @param feld      Das Spielfeld auf dem der Effekt ausgefuehrt wird.
     */
    private static void heldentat(KarteEinheit ausloeser, SpielFeld feld)
    {
        for (int i = 0; i < feld.getZeilen(); i++)
        {
            for (int j = 0; j < feld.getSpalten(); j++)
            {
                if ((feld.getSpielfeldplatz(i, j) != null &&
                     feld.getSpielfeldplatz(i, j) instanceof Spieler) ||
                    (feld.getSpielfeldplatz(i, j) != null &&
                     feld.getSpielfeldplatz(i, j) instanceof Gegenspieler))
                {
                    if (feld.getSpielfeldplatz(i, j).getFreundlich() ==
                        ausloeser.getFreundlich())
                    {
                        feld.getSpielfeldplatz(i, j).angriffErhoehen(1);
                    }

                }

            }
        }
    }
}
