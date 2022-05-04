package control;

import java.io.File;
import java.util.Collections;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.KartenDeck;

public class KartenDeckController
{
    private static Random zufallsGenerator = new Random();
    private static Gson meinGson = new GsonBuilder().setPrettyPrinting().create();

    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, zufallsGenerator);
    }
    public static String serialisieren (KartenDeck deck)
    {
        return meinGson.toJson(deck);
    }

    public static void schreibeInDatei (KartenDeck deck)
    {
        File datei = new File(deck.getDateiPfad());
    }
}
