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

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von Charakter-Stapeln aus
 * bzw. in Dateien.
 */
public class CharakterIO
{
    private static Gson meinGson;
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private final static File datei = new File(CHARAKTER_PFAD);

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private CharakterIO()
    {
    }

    /**
     * Serialisiert den Charakter-Stapel ins Json-Format.
     * @param charaktere der Charakter-Stapel
     * @return einen String im Json-Format
     */
    private static String serialisieren(Stack<Charakter> charaktere)
    {
        meinGson = meinGsonBuilder.setPrettyPrinting().create();
        return meinGson.toJson(charaktere);
    }

    /**
     * Schreibt einen Charakter-Stapel serialisiert in die Charaktere-Datei.
     * @param charaktere der Charakter-Stapel
     * @throws IOException wenn ein Fehler im Schreiben auftritt.
     */
    public static void schreibeDatei(Stack<Charakter> charaktere)
            throws IOException
    {
        if (datei.createNewFile())
        {
            KonsolenIO.ausgeben(CHARAKTER_DATEI_ERSTELLT);
        } else
        {
            KonsolenIO.ausgeben(CHARAKTER_DATEI_UEBERSCHRIEBEN);
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(serialisieren(charaktere));
        verfasser.close();
    }

    /**
     * Deserialisiert einen im Json-Format vorliegenden String in einen
     * Charakter-Stapel.
     * @param jsonCharaktere die Zeichenkette
     * @return den Charakter-Stapel
     * @throws JsonSyntaxException wenn die Formatierung nicht mit der
     * Json-Formatierung uebereinstimmt.
     */
    private static Stack<Charakter> deserialisieren(String jsonCharaktere)
            throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return meinGson.fromJson(jsonCharaktere,
                                 new TypeToken<Stack<Charakter>>()
                                 {
                                 }.getType());
    }

    /**
     * Liest die Charaktere-Datei ein und gibt einen Stapel aus Charakteren
     * deserialisiert zurueck.
     * @return den Charakter-Stapel
     * @throws IOException wenn ein Fehler beim Einlesen auftritt.
     */
    public static Stack<Charakter> leseDatei() throws IOException
    {
        Path path = Paths.get(datei.toURI());
        String content = Files.readString(path);

        try
        {
            return deserialisieren(content);
        } catch (JsonSyntaxException e)
        {
            throw new IOException(JSON_FORMAT_FEHLERHAFT_INFO);
        }
    }
}
