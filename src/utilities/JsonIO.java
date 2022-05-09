package utilities;

import com.google.gson.JsonSyntaxException;
import control.KartenDeckController;
import model.KartenDeck;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonIO
{
    public static void schreibeDatei (KartenDeck deck) throws IOException
    {
        if (deck.getDatei().createNewFile())
        {
            System.out.println("Datei erstellt: " + deck.getDatei());
        }
        else
        {
            System.out.println("Datei existiert bereits: " + deck.getDatei());
        }
        FileWriter verfasser = new FileWriter(deck.getDatei());
        verfasser.write(KartenDeckController.serialisieren(deck));
        verfasser.close();
    }

    public static KartenDeck leseDatei (String pfad) throws IOException
    {
        Path path = Paths.get(pfad);
        String content = Files.readString(path);

        return KartenDeckController.deserialisieren(content);
    }

    public static boolean pruefeDatei (String pfad)
    {
        Path path = Paths.get(pfad);
        String content = null;
        try
        {
            content = Files.readString(path);
            KartenDeckController.deserialisieren(content);
        }
        catch (IOException | JsonSyntaxException ex)
        {
            return false;
        }
        return true;
    }

}
