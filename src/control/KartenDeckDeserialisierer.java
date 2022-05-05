package control;

import com.google.gson.*;
import model.Charakterklasse;
import model.Karte;
import model.KarteEinheit;
import model.KartenDeck;

import java.io.File;
import java.lang.reflect.Type;

public class KartenDeckDeserialisierer implements JsonDeserializer<KartenDeck>
{
    Gson gson = new Gson();

    @Override
    public KartenDeck deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        JsonObject meinJsonObject = jsonElement.getAsJsonObject();
        JsonArray meinJsonArray = meinJsonObject.get("Karten").getAsJsonArray();

        KartenDeck meinKartenDeck = new KartenDeck(meinJsonObject.get("Bezeichnung").getAsString());

        for (int i = 0; i < meinJsonArray.size(); i++)
        {
            JsonObject JsonKarte = meinJsonArray.get(i).getAsJsonObject();
            try
            {
                Type klasse = Class.forName(JsonKarte.get("klasse").getAsString());
                Karte karte = gson.fromJson(JsonKarte, klasse);
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