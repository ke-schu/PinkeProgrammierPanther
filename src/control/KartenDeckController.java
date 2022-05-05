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
    private static Random meinRandom = new Random();
    private static Gson meinGson;

    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, meinRandom);

    }

    private static String serialisieren (KartenDeck deck)
    {
        KartenDeckSerialisierer meinSerialisierer = new KartenDeckSerialisierer();

        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meinSerialisierer);
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
        verfasser.write(KartenDeckController.serialisieren(deck));
        verfasser.close();
    }

    private static KartenDeck deserialisieren (String jsonKartenDeck)
    {
        KartenDeckDeserialisierer meinDeserialisierer = new KartenDeckDeserialisierer();

        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meinDeserialisierer);
        meinGson = meinGsonBuilder.create();

        return meinGson.fromJson(jsonKartenDeck, KartenDeck.class);
    }

    public static KartenDeck leseDatei (String pfad) throws IOException
    {
        Path path = Paths.get(pfad);
        String content = Files.readString(path);

        return deserialisieren(content);
    }
}
