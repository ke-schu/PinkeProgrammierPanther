package control;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

import com.google.gson.*;
import exceptions.KartenDeckFehlerhaftException;
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

    public static String serialisieren (KartenDeck deck)
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.setPrettyPrinting().create();

        return meinGson.toJson(deck);
    }

    public static KartenDeck deserialisieren (String jsonKartenDeck) throws JsonSyntaxException
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.create();

        return meinGson.fromJson(jsonKartenDeck, KartenDeck.class);
    }

    public static void schreibeDatei (KartenDeck deck) throws KartenDeckFehlerhaftException
    {
        try
        {
            deck.getDatei().createNewFile();
            FileWriter verfasser = new FileWriter(deck.getDatei());
            verfasser.write(KartenDeckController.serialisieren(deck));
            verfasser.close();
        }
        catch (IOException e)
        {
            throw new KartenDeckFehlerhaftException();
        }

    }

    public static KartenDeck leseDatei (String pfad) throws KartenDeckFehlerhaftException
    {
        Path path = Paths.get(pfad);
        String content = null;
        try
        {
            content = Files.readString(path);
        } catch (IOException e)
        {
            throw new KartenDeckFehlerhaftException();
        }

        return KartenDeckController.deserialisieren(content);
    }

    public static boolean pruefeDatei (String pfad)
    {
        Path path = Paths.get(pfad);
        String content = null;
        try
        {
            content = Files.readString(path);
            KartenDeckController.deserialisieren(content);
        }
        catch (IOException | JsonSyntaxException ex)
        {
            return false;
        }
        return true;
    }
}
