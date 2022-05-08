package control;

import com.google.gson.*;
import model.Karte;
import model.KartenDeck;

import java.lang.reflect.Type;

public class KartenDeckSerialisierung implements JsonSerializer<KartenDeck>, JsonDeserializer<KartenDeck>
{
    Gson meinGson = new Gson();

    @Override
    public JsonElement serialize (KartenDeck src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonKartenDeck = new JsonObject();

        jsonKartenDeck.addProperty("Bezeichnung", src.getDeckBezeichnung());
        jsonKartenDeck.add("Karten", meinGson.toJsonTree(src));

        return jsonKartenDeck;
    }

    @Override
    public KartenDeck deserialize (JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject meinJsonObject = json.getAsJsonObject();
        JsonArray meinJsonArray = meinJsonObject.get("Karten").getAsJsonArray();

        KartenDeck meinKartenDeck = new KartenDeck(meinJsonObject.get("Bezeichnung").getAsString());

        for (int i = 0; i < meinJsonArray.size(); i++)
        {
            JsonObject JsonKarte = meinJsonArray.get(i).getAsJsonObject();
            try
            {
                Type klasse = Class.forName(JsonKarte.get("klasse").getAsString());
                Karte karte = meinGson.fromJson(JsonKarte, klasse);
                meinKartenDeck.add(i, karte);
            }
            catch (ClassNotFoundException e)
            {
                throw new JsonParseException(e);
            }
        }
        return meinKartenDeck;
    }
}