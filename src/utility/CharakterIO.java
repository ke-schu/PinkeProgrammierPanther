package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import exceptions.JsonNichtLesbarException;
import model.Charakter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
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
    private static Serialisierung<Stack<Charakter>> meineSerialisierung = new Serialisierung();
    private final static File datei = new File(CHARAKTER_PFAD);

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private CharakterIO()
    {
    }

    /**
     * Schreibt einen Charakter-Stapel serialisiert in die Charaktere-Datei.
     *
     * @param charaktere der Charakter-Stapel
     * @throws IOException wenn ein Fehler im Schreiben auftritt.
     */
    public static void schreibeDatei(Stack<Charakter> charaktere)
            throws IOException
    {
        if (datei.createNewFile())
        {
            KonsolenIO.ausgeben(CHARAKTER_DATEI_ERSTELLT);
        }
        else
        {
            KonsolenIO.ausgeben(CHARAKTER_DATEI_UEBERSCHRIEBEN);
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(meineSerialisierung.serialisieren(charaktere));
        verfasser.close();
    }

    /**
     * Liest die Charaktere-Datei ein und gibt einen Stapel aus Charakteren
     * deserialisiert zurueck.
     *
     * @return den Charakter-Stapel
     * @throws JsonNichtLesbarException, wenn ein Fehler beim Einlesen auftritt.
     */
    public static Stack<Charakter> leseDatei() throws JsonNichtLesbarException
    {
        try
        {
            Path path = Paths.get(datei.toURI());
            String content = Files.readString(path);
            return meineSerialisierung.deserialisieren(content, new TypeToken<Stack<Charakter>>()
            {
            }.getType());
        }
        catch (JsonSyntaxException | IOException | IllegalArgumentException |
               FileSystemNotFoundException ex)
        {
            throw new JsonNichtLesbarException(ex.getMessage(), ex);
        }
    }

    /**
     *          VERALTET
     * Kann mit dem Test-Paket gelöscht werden!
     */
    public static Charakter leseCharakter(int position)
            throws JsonNichtLesbarException
    {
        Stack meineCharaktere = leseDatei();
        Charakter meinCharakter =
                new Charakter((Charakter) meineCharaktere.get(position));
        return meinCharakter;
    }
}
