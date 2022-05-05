package control;

import com.google.gson.*;
import model.KartenDeck;

import java.lang.reflect.Type;

public class KartenDeckSerialisierer implements JsonSerializer<KartenDeck>
{
    Gson gson = new Gson();
    GsonBuilder gsonBuilder = new GsonBuilder();

    @Override
    public JsonElement serialize(KartenDeck src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonKartenDeck = new JsonObject();

        jsonKartenDeck.addProperty("Bezeichnung", src.getDeckBezeichnung());
        jsonKartenDeck.add("Karten", gson.toJsonTree(src));

        return jsonKartenDeck;
    }
}