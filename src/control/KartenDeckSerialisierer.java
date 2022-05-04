package control;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.KartenDeck;

import java.lang.reflect.Type;

public class KartenDeckSerialisierer implements JsonSerializer<KartenDeck>
{
    @Override
    public JsonElement serialize(KartenDeck deck, Type type, JsonSerializationContext jsonSerializationContext)
    {
        return null;
    }
}
