package control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import model.KartenDeck;
import model.SpielStand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpielStandController
{
    private static Gson meinGson;
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private final static File datei = new File("src/resources/spielstand.json");
    private static KartenDeckSerialisierung meineSerialisierung = new KartenDeckSerialisierung();

    private static String serialisieren (SpielStand stand)
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.setPrettyPrinting().create();

        return meinGson.toJson(stand);
    }

    public static void schreibeDatei (SpielStand stand) throws IOException
    {
        if (datei.createNewFile())
        {
            System.out.println("Datei erstellt: " + datei.getCanonicalPath());
        }
        else
        {
            System.out.println("Datei existiert bereits: " + datei.getCanonicalPath());
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(serialisieren(stand));
        verfasser.close();
    }

    private static SpielStand deserialisieren (String jsonStand) throws JsonSyntaxException
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, meineSerialisierung);
        meinGson = meinGsonBuilder.create();

        return meinGson.fromJson(jsonStand, SpielStand.class);
    }

    public static SpielStand leseDatei () throws IOException
    {
        Path path = Paths.get(datei.toURI());
        String content = Files.readString(path);

        return deserialisieren(content);
    }
}
