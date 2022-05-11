package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

import com.google.gson.*;
import control.KartenDeckSerialisierung;
import exceptions.KartenDeckFehlerhaftException;
import model.KartenDeck;

public class KartenDeckIO
{
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private static KartenDeckSerialisierung meineSerialisierung = new KartenDeckSerialisierung();
    private static Gson meinGson;

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
            verfasser.write(KartenDeckIO.serialisieren(deck));
            verfasser.close();
        }
        catch (IOException e)
        {
            throw new KartenDeckFehlerhaftException(deck.getDeckBezeichnung());
        }

    }

    public static KartenDeck leseDatei (String pfad) throws KartenDeckFehlerhaftException
    {
        try
        {
            Path klassenPfad = Paths.get(pfad);
            String content = Files.readString(klassenPfad);
            KartenDeck deck = KartenDeckIO.deserialisieren(content);
            deck.setDatei(new File(pfad));
            return deck;
        }
        catch (IOException | InvalidPathException e)
        {
            throw new KartenDeckFehlerhaftException();
        }
    }

    public static boolean pruefeDatei (String pfad)
    {
        Path path = Paths.get(pfad);
        String content = null;
        try
        {
            content = Files.readString(path);
            KartenDeckIO.deserialisieren(content);
        }
        catch (IOException | JsonSyntaxException ex)
        {
            return false;
        }
        return true;
    }
}
