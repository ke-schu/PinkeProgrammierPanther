package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import control.EbeneSerialisierung;
import exceptions.JsonNichtLesbarException;
import model.Charakter;
import model.Ebene;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import static resources.Strings.EBENE_DATEI_ERSTELLT;
import static resources.Strings.EBENE_DATEI_UEBERSCHRIEBEN;

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von Ebenen aus bzw. in
 * Dateien.
 */
public class EbeneIO
{
    private static Serialisierung<Ebene> meineSerialisierung = new Serialisierung();

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EbeneIO()
    {
    }

    /**
     * Schreibt eine Ebene serialisiert in eine Datei.
     *
     * @param ebene die Ebene
     * @param datei die Datei
     * @throws IOException wenn ein Fehler im Schreiben auftritt.
     */
    public static void schreibeDatei(Ebene ebene, File datei) throws IOException
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
        verfasser.write(meineSerialisierung.serialisieren(ebene));
        verfasser.close();
    }

    /**
     * Liest eine Ebenen-Datei ein und gibt eine Ebene deserialisiert
     * zurueck.
     *
     * @return die Ebene
     * @throws JsonNichtLesbarException wenn ein Fehler beim Einlesen auftritt.
     */
    public static Ebene leseDatei(File datei) throws JsonNichtLesbarException
    {
        try
        {
            Path path = Paths.get(datei.toURI());
            String content = Files.readString(path);
            return meineSerialisierung.deserialisieren(content, Ebene.class);
        }
        catch (JsonSyntaxException | IOException | IllegalArgumentException |
               FileSystemNotFoundException ex)
        {
            throw new JsonNichtLesbarException(ex.getMessage(), ex);
        }
    }
}
