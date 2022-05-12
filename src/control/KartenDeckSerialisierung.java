package control;

import com.google.gson.*;
import model.Karte;
import model.KartenDeck;

import java.lang.reflect.Type;

/**
 * Diese Klasse ist ein benutzerdefinierter JsonSerializer und JsonDeserializer für ein KartenDeck.
 * Es wird hier festgelegt, wie genau ein Kartendeck im Controller serialisiert bzw. deserialisiert wird, indem
 * diese Klasse als Adapter im GsonBuilder eingebaut wird.
 */
public class KartenDeckSerialisierung implements JsonSerializer<KartenDeck>, JsonDeserializer<KartenDeck>
{
    private Gson meinGson = new Gson();

    /**
     * Serialisiert ein Kartendeck
     * @param src das Kartendeck
     * @param type Typ des zu serialisierenden Kartendecks
     * @param context Kontext der Serialisierung
     * @return ein Kartendeck als JsonElement
     */
    @Override
    public JsonElement serialize (KartenDeck src, Type type, JsonSerializationContext context)
    {
        JsonObject jsonKartenDeck = new JsonObject();

        jsonKartenDeck.addProperty("Bezeichnung", src.getDeckBezeichnung());
        jsonKartenDeck.add("Karten", meinGson.toJsonTree(src));

        return jsonKartenDeck;
    }

    /**
     * Deserialisiert ein Kartendeck
     * @param json das Kartendeck als JsonElement
     * @param type Typ des zu serialisierenden Kartendecks
     * @param context Kontext der Deserialisierung
     * @return das Kartendeck
     * @throws JsonParseException wenn das Element nicht gelesen werden kann
     */
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