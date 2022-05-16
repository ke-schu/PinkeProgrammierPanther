package io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import control.RaumSerialisierung;
import model.Ebene;
import model.Raum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static resources.Strings.*;

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von Ebenen aus bzw. in Dateien.
 */
public class EbeneIO
{
    private static Gson meinGson;
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass keine Instanzen dieser Klasse
     * gebildet werden.
     */
    private EbeneIO ()
    {
    }

    /**
     * Serialisiert die Ebene ins Json-Format.
     * @param ebene die Ebene
     * @return einen String im Json-Format
     */
    private static String serialisieren (Ebene ebene)
    {
        meinGson = meinGsonBuilder.setPrettyPrinting().create();
        return meinGson.toJson(ebene);
    }

    /**
     * Schreibt eine Ebene serialisiert in eine Datei.
     * @param ebene die Ebene
     * @param datei die Datei
     * @throws IOException wenn ein Fehler im Schreiben auftritt.
     */
    public static void schreibeDatei (Ebene ebene, File datei) throws IOException
    {
        if (datei.createNewFile())
        {
            KonsolenIO.ausgeben(EBENE_DATEI_ERSTELLT);
        }
        else
        {
            KonsolenIO.ausgeben(EBENE_DATEI_UEBERSCHRIEBEN);
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(serialisieren(ebene));
        verfasser.close();
    }

    /**
     * Deserialisiert einen im Json-Format vorliegenden String in eine Ebene.
     * @param jsonEbene die Zeichenkette
     * @return die Ebene
     * @throws JsonSyntaxException wenn die Formatierung nicht mit der Json-Formatierung übereinstimmt.
     */
    private static Ebene deserialisieren (String jsonEbene) throws JsonSyntaxException
    {
        RaumSerialisierung meineSerialisierung = new RaumSerialisierung();
        meinGsonBuilder.registerTypeAdapter(Raum.class, meineSerialisierung);
        meinGson = meinGsonBuilder.create();

        return meinGson.fromJson(jsonEbene, Ebene.class);
    }

    /**
     * Liest eine Ebenen-Datei ein und gibt eine Ebene deserialisiert zurück.
     * @return die Ebene
     * @throws IOException wenn ein Fehler beim Einlesen auftritt.
     */
    public static Ebene leseDatei (File datei) throws IOException
    {
        Path path = Paths.get(datei.toURI());
        String content = Files.readString(path);

        try
        {
            return new Ebene(deserialisieren(content));
        }
        catch (JsonSyntaxException e)
        {
            throw new IOException(JSON_FORMAT_FEHLERHAFT_INFO);
        }
    }
}
