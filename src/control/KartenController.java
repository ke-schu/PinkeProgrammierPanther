package control;

import exceptions.KarteNichtVerbessertException;
import model.Karte;
import model.KarteEinheit;
import model.KarteZauber;
import model.KartenDeck;

/**
 * In dieser Klassen befinden sich Methoden, um mit Instanzen von Karten zu
 * interagieren.
 */
public class KartenController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenController()
    {
    }

    /**
     * Mit dieser Methode werden die Karten verbessert, dabei wird zwischen
     * den Klassen unterschieden.
     * @param karte Die Karte die verbessert werden soll.
     */
    public static void karteVerbessern(Karte karte)
            throws KarteNichtVerbessertException
    {
        if (karte instanceof KarteZauber)
        {
            karteVerbessern((KarteZauber) karte);
        }
        else if (karte instanceof KarteEinheit)
        {
            karteVerbessern((KarteEinheit) karte);
        }
        else
        {
            throw new KarteNichtVerbessertException();
        }
    }

    private static void karteVerbessern(KarteZauber karteZauber)
    {
        //  TODO
        throw new KarteNichtVerbessertException();
    }

    private static void karteVerbessern(KarteEinheit einheit)
    {
        switch (einheit.getTyp())
        {
            case NAHKAEMPFER:
                nahkaempferVerbessern(einheit);
                break;
            case FERNKAEMPFER:
                fernkaempferVerbessern(einheit);
                break;
            case BLOCKADE:
                blockadeVerbessern(einheit);
                break;
            default:
                throw new KarteNichtVerbessertException();
        }
    }

    private static void nahkaempferVerbessern(KarteEinheit einheit)
    {
        if (einheit.getLevel() == 3)
        {
            einheitVerbessern(einheit, 2, 2,0);
        }
        else if (einheit.getLevel() == 2)
        {
            einheitVerbessern(einheit, 2, 1,0);
        }
        else if (einheit.getLevel() == 1)
        {
            einheitVerbessern(einheit, 1, 1,0);
        }
        else
        {
            throw new KarteNichtVerbessertException();
        }
    }

    private static void fernkaempferVerbessern(KarteEinheit einheit)
    {
        if (einheit.getLevel() == 3)
        {
            einheitVerbessern(einheit, 1, 1,1);
        }
        else if (einheit.getLevel() == 2)
        {
            einheitVerbessern(einheit, 2, 0,1);

        }
        else if (einheit.getLevel() == 1)
        {
            einheitVerbessern(einheit, 1, 2,0);
        }
        else
        {
            throw new KarteNichtVerbessertException();
        }
    }

    private static void blockadeVerbessern(KarteEinheit einheit)
    {
        //  TODO
        throw new KarteNichtVerbessertException();
    }

    private static void einheitVerbessern(KarteEinheit einheit, int lp, int macht, int reichweite)
    {
        einheit.setLebenspunkte(einheit.getLebenspunkte() + lp);
        einheit.setMacht(einheit.getMacht() + macht);
        einheit.setReichweite(einheit.getReichweite() + reichweite);
        einheit.setLevel(einheit.getLevel() + 1);
    }

    public static void karteindeckeinordnen(Karte karte, KartenDeck spielerDeck, KartenDeck masterDeck)
    {
        if (karte instanceof KarteEinheit)
        {
            KarteEinheit meinekarte = (KarteEinheit) karte;

            if (meinekarte.getKopie())
            {
                ;
            }
            else if (karte.getFreundlich())
            {
                spielerDeck.push(karte);
                KartenDeckController.mischen(spielerDeck);
            } else
            {
                masterDeck.push(karte);
                KartenDeckController.mischen(masterDeck);
            }
        }
        else
        {
            if (karte.getFreundlich())
            {
                spielerDeck.push(karte);
                KartenDeckController.mischen(spielerDeck);
            } else
            {
                masterDeck.push(karte);
                KartenDeckController.mischen(masterDeck);
            }
        }

    }
}
