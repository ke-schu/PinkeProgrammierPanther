package control;

import model.KartenDeck;

import java.io.File;
import java.util.Collections;
import java.util.Random;

/**
 * Beinhaltet verschiedene Methoden, die an Kartendeck-Instanzen arbeiten
 */
public class KartenDeckController
{
    private static Random meinRandom = new Random();

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenDeckController ()
    {
    }

    /**
     * Methode um eine Instanz von der Klasse KartenDeck zu mischen.
     * @param deck Der Stack der gemischt werden soll.
     */
    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, meinRandom);
    }

    /**
     *
     * @param quelle Das Kartendeck, welches kopiert werden soll.
     * @param pfad
     * @return
     */
    public static KartenDeck kopiereDeck (KartenDeck quelle, String pfad)
    {
        File ziel = new File(pfad);
        KartenDeck kopie = (KartenDeck) quelle.clone();
        kopie.setDatei(ziel);
        return kopie;
    }
}