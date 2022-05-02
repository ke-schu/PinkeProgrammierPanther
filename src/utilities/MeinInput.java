package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MeinInput
{
    public <E>Object leseJSON(String pfad, E Element) throws IOException
    {
        File datei = new File(pfad);
        String inhalt = Files.readString(Paths.get(datei.toURI()));

        Gson gson = new Gson();
        JsonObject objekt = gson.fromJson(inhalt, (Type) Element);

        return objekt;
    }
}
