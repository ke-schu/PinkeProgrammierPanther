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

    /**
     * Methode um eine Instanz von der Klasse KartenDeck in zu mischen.
     * @param deck Der Stack der gemischt werden soll.
     */
    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, meinRandom);
    }

    /**
     * Methode zum serialisieren einer Instanz der Klasse KartenDeck.
     * @param deck Instanz der Klasse KartenDeck die als .json String zurückgeben werden soll.
     * @return liefert den Inhalt der Instanz als String in .json formatierung.
     */
    private static String serialisieren (KartenDeck deck)
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.setPrettyPrinting().create();

        return meinGson.toJson(deck);
    }

    /**
     * Methode zum deserialisieren eines Strings in .json Format um eine Instanz der Klasse KartenDeck zu bilden.
     * @param jsonKartenDeck String in .json Format der zu der Instanzbildung genutzt wird.
     * @return liefert die erstellte Instanz der Klasse KartenDeck
     * @throws JsonSyntaxException wird zunächst weiter geworfen, um in der nächsten Ebene gegebenenfalls eine genauere Exception zu werfen.
     */
    private static KartenDeck deserialisieren (String jsonKartenDeck) throws JsonSyntaxException
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
