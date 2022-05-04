package control;

import com.google.gson.*;
import model.Charakterklasse;
import model.Karte;
import model.KarteEinheit;
import model.KartenDeck;

import java.lang.reflect.Type;

public class KartenDeckDeserialisierer implements JsonDeserializer<KartenDeck>
{
    Gson gson = new Gson();

    @Override
    public KartenDeck deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException
    {
        JsonObject meinJsonObject = jsonElement.getAsJsonObject();

        KartenDeck meinKartenDeck = new KartenDeck(
                meinJsonObject.get("Bezeichnung").getAsString(),
                new Charakterklasse(meinJsonObject.get("Charakterklasse").getAsString(), 10)
        );

        //meinKartenDeck.addAll(gson.fromJson(meinJsonObject.get("Karten"), KartenDeck.class));
        //Karte x = new KarteEinheit();

        // TODO: Karten auslesen und deserialisieren! -> JsonArray durchiterieren

        return meinKartenDeck;
    }
}