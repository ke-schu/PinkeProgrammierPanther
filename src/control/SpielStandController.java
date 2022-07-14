package control;

import exceptions.NichtGenugGoldException;
import model.Charakter;
import model.KartenDeck;
import model.SpielStand;
import model.Spieler;
import utility.KonsolenIO;

import java.io.IOException;
import java.util.Stack;

import static resources.Artefakte.SCHUTZENGEL;
import static resources.Konstanten.*;
import static resources.Strings.SPIEL_DECK_SPIELER_PFAD;

/**
 Beinhaltet verschiedene Methoden, die an und mit Charakteren arbeiten. */
public class SpielStandController
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielStandController ()
    {
    }
    
    /**
     Diese Methode dient zum erstellen eines neuen Spielstandes.
     @param charakter Der ausgewaehlte Charakter des Player.
     @param spiel Der aktuelle Spielstand.
     */
    public static void spielErstellen (Charakter charakter, SpielStand spiel)
    {
        try
        {
            SpielStand neuerSpielStand =
                    new SpielStand(spiel.getGold(), charakter.getSpieler(),
                                   spiel.getGegenSpieler());
            spielStandIO.schreibeDatei(neuerSpielStand);
            KartenDeck spielDeck =
                    KartenDeckController.kopiereDeck(charakter.getStartDeck(),
                                                     SPIEL_DECK_SPIELER_PFAD);
            kartenDeckIO.schreibeDatei(spielDeck);
            EbeneController.ueberschreibeAktuelleEbene(START_EBENE);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     Diese Methode bietet die Moeglichkeit einen neuen Charakter
     freizuschalten.
     @param charakterStack Der Charakterstack des Players.
     @param pos Die Position an der sich der Charakter befindet.
     @param spiel Der aktuelle Spielstand.
     @throws NichtGenugGoldException Exception, falls der Player zu wenig Gold
     zum freischalten besitzt.
     */
    public static void charakterKaufen (Stack<Charakter> charakterStack,
                                        int pos,
                                        SpielStand spiel)
            throws NichtGenugGoldException
    {
        Charakter charakter = charakterStack.get(pos);
        if (spiel.getGold() >= charakter.getFreischaltgebuehr())
        {
            spiel.setGold(spiel.getGold() - charakter.getFreischaltgebuehr());
            charakter.setFreigeschaltet(true);
            try
            {
                charakterIO.schreibeDatei(charakterStack);
                spielStandIO.schreibeDatei(spiel);
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
    
    /**
     Diese Methode formuliert aus, was passiert, wenn der Player stirbt.
     @param spieler Der Player.
     */
    public static void sterben (Spieler spieler)
    {
        if (ArtefaktController.entferneArtefakt(SCHUTZENGEL, spieler))
        {
            spieler.setLebenspunkte(
                    spieler.getMaxLeben() / SCHUTZENGEL_ANTEIL_MAXLEBEN);
        }
    }
}