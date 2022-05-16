package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import exceptions.KartenDeckFehlerhaftException;
import model.SpielStand;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static resources.Strings.*;

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von Spielständen aus bzw. in Dateien.
 */
public class SpielStandIO
{
    private static Gson meinGson;
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private final static File datei = new File(SPIELSTAND_PFAD);

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private SpielStandIO ()
    {
    }

    /**
     * Serialisiert den Spielstand ins Json-Format.
     * @param stand der Spielstand
     * @return einen String im Json-Format
     */
    private static String serialisieren (SpielStand stand)
    {
        meinGson = meinGsonBuilder.setPrettyPrinting().create();
        return meinGson.toJson(stand);
    }

    /**
     * Schreibt einen Spielstand serialisiert in die Spielstand-Datei.
     * @param stand der Spielstand
     * @throws IOException wenn ein Fehler im Schreiben auftritt.
     */
    public static void schreibeDatei (SpielStand stand) throws IOException
    {
        if (datei.createNewFile())
        {
            KonsolenIO.ausgeben(SPIELSTAND_DATEI_ERSTELLT);
        }
        else
        {
            KonsolenIO.ausgeben(SPIELSTAND_DATEI_UEBERSCHRIEBEN);
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(serialisieren(stand));
        verfasser.close();
    }

    /**
     * Deserialisiert einen im Json-Format vorliegenden String in einen Spielstand.
     * @param jsonStand die Zeichenkette
     * @return den Spielstand
     * @throws JsonSyntaxException wenn die Formatierung nicht mit der Json-Formatierung übereinstimmt.
     */
    private static SpielStand deserialisieren (String jsonStand) throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return meinGson.fromJson(jsonStand, SpielStand.class);
    }

    /**
     * Liest die Charaktere-Datei ein und gibt einen Stapel aus Charakteren deserialisiert zurück.
     * @return den Charakter-Stapel
     * @throws IOException wenn ein Fehler beim Einlesen auftritt.
     */
    public static SpielStand leseDatei () throws IOException
    {
        Path path = Paths.get(datei.toURI());
        String content = Files.readString(path);

        try
        {
            return new SpielStand(deserialisieren(content));
        }
        catch (JsonSyntaxException | KartenDeckFehlerhaftException e)
        {
            throw new IOException(JSON_FORMAT_FEHLERHAFT_INFO);
        }
    }
}
