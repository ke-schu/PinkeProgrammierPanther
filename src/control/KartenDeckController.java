package control;

import model.KartenDeck;

import java.util.Collections;
import java.util.Random;

/**
 * Beinhaltet verschiedene Methoden, die an Kartendeck-Instanzen arbeiten
 */
public class KartenDeckController
{
    private static Random meinRandom = new Random();

    /**
     * Methode um eine Instanz von der Klasse KartenDeck zu mischen.
     * @param deck Der Stack der gemischt werden soll.
     */
    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, meinRandom);
    }
}
