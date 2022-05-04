package control;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.gson.*;
import model.KartenDeck;

public class KartenDeckController
{
    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Random zufallsGenerator = new Random();
    private static Gson meinGson = new GsonBuilder().setPrettyPrinting().create();

    public static void mischen (KartenDeck deck)
    {
        Collections.shuffle(deck, zufallsGenerator);

    }

    private static String serialisieren (KartenDeck deck)
    {
        KartenDeckSerialisierer meinSerialisierer = new KartenDeckSerialisierer();

        gsonBuilder.registerTypeAdapter(KartenDeck.class, meinSerialisierer);
        meinGson = gsonBuilder.setPrettyPrinting().create();

        return meinGson.toJson(deck);
    }

    public static void schreibeDatei(KartenDeck deck) throws IOException
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

    private static KartenDeck deserialisieren (String jsonKartenDeck)
    {
        KartenDeckDeserialisierer meinDeserialisierer = new KartenDeckDeserialisierer();

        gsonBuilder.registerTypeAdapter(KartenDeck.class, meinDeserialisierer);
        meinGson = gsonBuilder.create();

        return meinGson.fromJson(jsonKartenDeck, KartenDeck.class);
    }

    public static KartenDeck leseDatei (String pfad) throws IOException
    {
        /*BufferedReader br = new BufferedReader(new FileReader(pfad));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            line = br.readLine();
        }

        String fileAsString = sb.toString();*/

        Path path = Paths.get(pfad);
        String content = Files.readString(path);

        return deserialisieren(content);
    }
}
