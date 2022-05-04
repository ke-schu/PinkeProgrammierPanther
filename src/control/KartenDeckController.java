package control;

import java.io.*;
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

    private static String serialisieren (KartenDeck deck)
    {
        return meinGson.toJson(deck);
    }

    public static void schreibeInDatei (KartenDeck deck) throws IOException
    {
        if (deck.getDatei().createNewFile())
        {
            System.out.println("Datei erstellt");
        }
        else
        {
            System.out.println("Datei existiert bereits");
        }
        System.out.println(deck.getDatei());

        FileWriter verfasser = new FileWriter(deck.getDatei());
        verfasser.write(KartenDeckController.serialisieren(deck));
        verfasser.close();
    }
}
