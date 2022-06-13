package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import control.KartenDeckSerialisierung;
import exceptions.JsonNichtLesbarException;
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
    private static GsonBuilder meinGsonBuilder = new GsonBuilder();
    private static KartenDeckSerialisierung meineSerialisierung =
            new KartenDeckSerialisierung();
    private static Gson meinGson;

    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private KartenDeckIO()
    {
    }

    /**
     * Serialisiert ein KartenDeck ins Json-Format.
     * @param deck Instanz der Klasse KartenDeck die als .json String
     * zurueckgeben werden soll.
     * @return liefert den Inhalt der Instanz als String in .json
     * formatierung.
     */
    private static String serialisieren(KartenDeck deck)
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class,
                                            meineSerialisierung);
        meinGson = meinGsonBuilder.setPrettyPrinting().create();

        return meinGson.toJson(deck);
    }

    /**
     * Schreibt ein Kartendeck serialisiert ins Kartendeck-Paket. Jedes
     * Kartendeck hat seine eigene Datei, festgehalten in der entsprechenden
     * Instanz.
     * @param deck das Kartendeck
     * @throws JsonNichtLesbarException wenn die Formatierung oder
     * Attribute des Kartendecks falsch sind.
     */
    public static void schreibeDatei(KartenDeck deck)
            throws IOException
    {
        deck.getDatei().createNewFile();
        FileWriter verfasser = new FileWriter(deck.getDatei());
        verfasser.write(KartenDeckIO.serialisieren(deck));
        verfasser.close();
    }

    /**
     * Methode zum deserialisieren eines Strings in .json Format um eine
     * Instanz der Klasse KartenDeck zu bilden.
     * @param jsonKartenDeck String in .json Format der zu der Instanzbildung
     * genutzt wird.
     * @return liefert die erstellte Instanz der Klasse KartenDeck
     * @throws JsonSyntaxException wenn die Formatierung nicht mit der
     * Json-Formatierung uebereinstimmt.
     */
    private static KartenDeck deserialisieren(String jsonKartenDeck)
            throws JsonSyntaxException
    {
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class,
                                            meineSerialisierung);
        meinGson = meinGsonBuilder.create();

        return meinGson.fromJson(jsonKartenDeck, KartenDeck.class);
    }

    /**
     * Liest eine Kartendeck-Datei ein und gibt das entsprechende Deck
     * deserialisiert zurueck.
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
            KartenDeck deck = KartenDeckIO.deserialisieren(content);
            deck.setDatei(new File(pfad));
            return deck;
        }
        catch (JsonSyntaxException | IOException | InvalidPathException ex)
        {
            throw new JsonNichtLesbarException(ex.getMessage(), ex);
        }
    }
}
