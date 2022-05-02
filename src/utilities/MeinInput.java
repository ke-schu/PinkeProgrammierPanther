package utilities;

import com.google.gson.Gson;
import model.Charakterklasse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MeinInput
{
    public static Charakterklasse leseJson(String pfad) throws IOException
    {
        Path path = Paths.get(pfad);
        String inhalt = Files.readString(path);

        Gson gson = new Gson();
        Charakterklasse klasse = gson.fromJson(inhalt, Charakterklasse.class);

        return klasse;
    }
}
