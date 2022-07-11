package utility;

import com.google.gson.JsonSyntaxException;
import exceptions.JsonNichtLesbarException;
import model.Charakter;
import model.KartenDeck;
import model.SpielStand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

import static resources.Strings.*;
import static resources.StringsGUI.SPIELSTAND_PFAD;

/**
 * Diese Klasse beinhaltet das Lesen und Schreiben von JSON Dateien.
 */
public class JsonIO<T>
{
    private Serialisierung<T> meineSerialisierung = new Serialisierung();
    private Type typ;

    public JsonIO(Type typ)
    {
        this.typ = typ;
    }

    public void schreibeDatei(T objekt, String dateiPfad) throws IOException
    {
        File datei = new File(dateiPfad);
        if (datei.createNewFile())
        {
            KonsolenIO.ausgeben("Datei erstellt.");
        }
        else
        {
            KonsolenIO.ausgeben("Datei überschrieben.");
        }
        FileWriter verfasser = new FileWriter(datei);
        verfasser.write(meineSerialisierung.serialisieren(objekt));
        verfasser.close();
    }

    public void schreibeDatei(SpielStand spielStand) throws IOException
    {
        schreibeDatei((T) spielStand, JSON_SPIELSTAND_PFAD);
    }

    public void schreibeDatei(Stack<Charakter> charaktere) throws IOException
    {
        schreibeDatei((T) charaktere, CHARAKTER_PFAD);
    }

    public void schreibeDatei(KartenDeck deck) throws IOException
    {
        deck.getDatei().createNewFile();
        schreibeDatei((T) deck, deck.getDatei().getAbsolutePath());
    }

    public T leseDatei(String dateiPfad) throws JsonNichtLesbarException
    {
        try
        {
            Path path = Paths.get(new File(dateiPfad).toURI());
            String content = Files.readString(path);
            return meineSerialisierung.deserialisieren(content, typ);
        }
        catch (JsonSyntaxException | IOException | IllegalArgumentException |
               FileSystemNotFoundException ex)
        {
            throw new JsonNichtLesbarException(ex.getMessage(), ex);
        }
    }

    public Stack<Charakter> leseCharaktere() throws JsonNichtLesbarException
    {
        return (Stack<Charakter>) leseDatei(CHARAKTER_PFAD);
    }

    public SpielStand leseSpielstand() throws JsonNichtLesbarException
    {
        return new SpielStand((SpielStand) leseDatei(JSON_SPIELSTAND_PFAD));
    }

    public KartenDeck leseKartenDeck(String pfad) throws JsonNichtLesbarException
    {
        KartenDeck gelesenesDeck = (KartenDeck) leseDatei(pfad);
        gelesenesDeck.setDatei(new File(pfad));
        return gelesenesDeck;
    }
}
