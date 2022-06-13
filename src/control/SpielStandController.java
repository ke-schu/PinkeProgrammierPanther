package control;

import exceptions.NichtGenugGoldException;
import utility.CharakterIO;
import utility.KartenDeckIO;
import utility.KonsolenIO;
import utility.SpielStandIO;
import model.Charakter;
import model.KartenDeck;
import model.SpielStand;
import model.Spieler;

import java.io.IOException;
import java.util.Stack;

import static resources.Artefakte.SCHUTZENGEL;
import static resources.Konstanten.SCHUTZENGEL_ANTEIL_MAXLEBEN;
import static resources.Strings.SPIEL_DECK_SPIELER_PFAD;

/**
 * Beinhaltet verschiedene Methoden, die an und mit Charakteren arbeiten.
 */
public class SpielStandController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielStandController()
    {
        /*
        Hier muss man zum Hauptmenü weitergeleitet werden.
        Dort darf man nicht das Spiel fortsetzten können, sondern nur
        ein neues Spiel starten können.
         */
    }

    public static void spielErstellen(Charakter charakter, SpielStand spiel)
    {
        try
        {
            SpielStand neuerSpielStand = new SpielStand(spiel.getGold(), charakter.getSpieler());
            SpielStandIO.schreibeDatei(neuerSpielStand);
            KartenDeck spielDeck = KartenDeckController.kopiereDeck(charakter.getStartDeck(), SPIEL_DECK_SPIELER_PFAD);
            KartenDeckIO.schreibeDatei(spielDeck);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    public static void charakterKaufen(Stack<Charakter> charakterStack, int pos, SpielStand spiel)
            throws NichtGenugGoldException
    {
        Charakter charakter = charakterStack.get(pos);
        if(spiel.getGold() >= charakter.getFreischaltgebuehr())
        {
            spiel.setGold(spiel.getGold() - charakter.getFreischaltgebuehr());
            charakter.setFreigeschaltet(true);
            try
            {
                CharakterIO.schreibeDatei(charakterStack);
                SpielStandIO.schreibeDatei(spiel);
            }
            catch (IOException e)
            {
                KonsolenIO.ausgeben(e.getMessage());
            }
        }
        else
        {
            throw new NichtGenugGoldException();
        }
    }

    public static void sterben(Spieler spieler)
    {
        if(ArtefaktController.entferneArtefakt(SCHUTZENGEL, spieler))
        {
            spieler.setLebenspunkte(spieler.getMaxleben() / SCHUTZENGEL_ANTEIL_MAXLEBEN);
        }
        else
        {
            spielZurueckSetzen();
        }
    }

    private static void spielZurueckSetzen()
    {

    }
}