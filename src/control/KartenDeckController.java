package control;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

import com.google.gson.*;
import model.KartenDeck;

public class KartenDeckController
{
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private static KartenDeckSerialisierung meineSerialisierung = new KartenDeckSerialisierung();
    private static Random meinRandom = new Random();
    private static Gson meinGson;

    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, meinRandom);
    }

    private static String serialisieren (KartenDeck deck)
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.setPrettyPrinting().create();

        return meinGson.toJson(deck);
    }

    public static void schreibeDatei (KartenDeck deck) throws IOException
    {
        if (deck.getDatei().createNewFile())
        {
            System.out.println("Datei erstellt: " + deck.getDatei());
        }
        else
        {
            System.out.println("Datei existiert bereits: " + deck.getDatei());
        }
        FileWriter verfasser = new FileWriter(deck.getDatei());
        verfasser.write(serialisieren(deck));
        verfasser.close();
    }

    private static KartenDeck deserialisieren (String jsonKartenDeck) throws JsonSyntaxException
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.create();

        return meinGson.fromJson(jsonKartenDeck, KartenDeck.class);
    }

    public static KartenDeck leseDatei (String pfad) throws IOException
    {
        Path path = Paths.get(pfad);
        String content = Files.readString(path);

        return deserialisieren(content);
    }

    public static boolean pruefeDatei (String pfad)
    {
        Path path = Paths.get(pfad);
        String content = null;
        try
        {
            content = Files.readString(path);
            deserialisieren(content);
        }
        catch (IOException | JsonSyntaxException ex)
        {
            return false;
        }
        return true;
    }
}
