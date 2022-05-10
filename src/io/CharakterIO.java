package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import model.Charakter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import static resources.Strings.*;

public class CharakterIO
{
    private static Gson meinGson;
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private final static File datei = new File(CHARAKTER_PFAD);

    private static String serialisieren (Stack<Charakter> charaktere)
    {
        meinGson = meinGsonBuilder.setPrettyPrinting().create();
        return meinGson.toJson(charaktere);
    }

    public static void schreibeDatei (Stack<Charakter> charaktere) throws IOException
    {
        if (datei.createNewFile())
        {
            System.out.println(CHARAKTER_DATEI_ERSTELLT);
        }
        else
        {
            System.out.println(CHARAKTER_DATEI_UEBERSCHRIEBEN);
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(serialisieren(charaktere));
        verfasser.close();
    }

    private static Stack<Charakter> deserialisieren (String jsonCharaktere) throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return meinGson.fromJson(jsonCharaktere, new TypeToken<Stack<Charakter>>(){}.getType());
    }

    public static Stack<Charakter> leseDatei () throws IOException
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
