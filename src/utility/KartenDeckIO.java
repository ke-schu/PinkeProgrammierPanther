package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import control.KartenDeckSerialisierung;
import exceptions.JsonNichtLesbarException;
import model.Ebene;
import model.KartenDeck;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von Kartenstapeln aus bzw.
 * in Dateien.
 */
public class KartenDeckIO
{
    private static Serialisierung<KartenDeck> meineSerialisierung = new Serialisierung();

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenDeckIO()
    {
    }

    /**
     * Schreibt ein Kartendeck serialisiert ins Kartendeck-Paket. Jedes
     * Kartendeck hat seine eigene Datei, festgehalten in der entsprechenden
     * Instanz.
     *
     * @param deck das Kartendeck
     * @throws JsonNichtLesbarException wenn die Formatierung oder
     *                                  Attribute des Kartendecks falsch sind.
     */
    public static void schreibeDatei(KartenDeck deck) throws IOException
    {
        deck.getDatei().createNewFile();
        FileWriter verfasser = new FileWriter(deck.getDatei());
        verfasser.write(meineSerialisierung.serialisieren(deck));
        verfasser.close();
    }

    /**
     * Liest eine Kartendeck-Datei ein und gibt das entsprechende Deck
     * deserialisiert zurueck.
     *
     * @param pfad der Dateipfad
     * @return das Kartendeck
     * @throws JsonNichtLesbarException wenn ein Fehler beim Einlesen auftritt.
     */
    public static KartenDeck leseDatei(String pfad)
            throws JsonNichtLesbarException
    {
        try
        {
            Path klassenPfad = Paths.get(pfad);
            String content = Files.readString(klassenPfad);
            KartenDeck deck = meineSerialisierung.deserialisieren(content, KartenDeck.class);
            deck.setDatei(new File(pfad));
            return deck;
        }
        catch (JsonSyntaxException | IOException | InvalidPathException ex)
        {
            throw new JsonNichtLesbarException(ex.getMessage(), ex);
        }
    }
}
