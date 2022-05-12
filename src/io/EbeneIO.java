package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import exceptions.KartenDeckFehlerhaftException;
import model.Ebene;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static resources.Strings.*;
import static resources.Strings.JSON_FORMAT_FEHLERHAFT_INFO;

public class EbeneIO
{
    private static Gson meinGson;
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();

    private static String serialisieren (Ebene ebene)
    {
        meinGson = meinGsonBuilder.setPrettyPrinting().create();
        return meinGson.toJson(ebene);
    }

    public static void schreibeDatei (Ebene ebene, File datei) throws IOException
    {
        if (datei.createNewFile())
        {
            System.out.println(EBENE_DATEI_ERSTELLT);
        }
        else
        {
            System.out.println(EBENE_DATEI_UEBERSCHRIEBEN);
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(serialisieren(ebene));
        verfasser.close();
    }

    private static Ebene deserialisieren (String jsonStand) throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return meinGson.fromJson(jsonStand, Ebene.class);
    }

    public static Ebene leseDatei (File datei) throws IOException
    {
        Path path = Paths.get(datei.toURI());
        String content = Files.readString(path);

        try
        {
            return deserialisieren(content);
        }
        catch (JsonSyntaxException e)
        {
            throw new IOException(JSON_FORMAT_FEHLERHAFT_INFO);
        }
    }
}
