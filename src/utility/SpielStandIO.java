package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import exceptions.JsonNichtLesbarException;
import model.KartenDeck;
import model.SpielStand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static resources.Strings.*;

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von Spielstaenden aus bzw.
 * in Dateien.
 */
public class SpielStandIO
{
    private static Serialisierung<SpielStand> meineSerialisierung = new Serialisierung();
    private final static File datei = new File(SPIELSTAND_PFAD);

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielStandIO()
    {
    }

    /**
     * Schreibt einen Spielstand serialisiert in die Spielstand-Datei.
     *
     * @param stand der Spielstand
     * @throws IOException wenn ein Fehler im Schreiben auftritt.
     */
    public static void schreibeDatei(SpielStand stand) throws IOException
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
        verfasser.write(meineSerialisierung.serialisieren(stand));
        verfasser.close();
    }

    /**
     * Liest die Charaktere-Datei ein und gibt einen Stapel aus Charakteren
     * deserialisiert zurueck.
     *
     * @return den Charakter-Stapel
     * @throws JsonNichtLesbarException wenn ein Fehler beim Einlesen auftritt.
     */
    public static SpielStand leseDatei() throws JsonNichtLesbarException
    {
        try
        {
            Path path = Paths.get(datei.toURI());
            String content = Files.readString(path);
            return new SpielStand(meineSerialisierung.deserialisieren(content, SpielStand.class));
        }
        catch (JsonSyntaxException | IOException | IllegalArgumentException |
               FileSystemNotFoundException ex)
        {
            throw new JsonNichtLesbarException(ex.getMessage(), ex);
        }
    }
}
