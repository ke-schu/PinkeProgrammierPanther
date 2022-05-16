package control;

import com.google.gson.*;
import model.Karte;
import model.KartenDeck;
import java.lang.reflect.Type;

import static resources.Strings.*;

/**
 * Diese Klasse ist ein benutzerdefinierter JsonSerializer und JsonDeserializer fuer ein KartenDeck.
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

        jsonKartenDeck.addProperty(KARTEN_DECK_BEZEICHNUNG, src.getDeckBezeichnung());
        jsonKartenDeck.add(KARTEN_DECK_STACK, meinGson.toJsonTree(src));

        return jsonKartenDeck;
    }

    /**
     * Deserialisiert ein Kartendeck
     * @param json das Kartendeck als JsonElement
     * @param type Typ des zu serialisierenden Kartendecks
     * @param context Kontext der Deserialisierung
     * @return das Kartendeck
     * @throws JsonParseException wird geworfen, wenn das Element nicht gelesen werden kann
     */
    @Override
    public KartenDeck deserialize (JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject meinJsonObject = json.getAsJsonObject();
        JsonArray meinJsonArray = meinJsonObject.get(KARTEN_DECK_STACK).getAsJsonArray();

        KartenDeck meinKartenDeck = new KartenDeck(meinJsonObject.get(KARTEN_DECK_BEZEICHNUNG).getAsString());

        for (int i = 0; i < meinJsonArray.size(); i++)
        {
            JsonObject JsonKarte = meinJsonArray.get(i).getAsJsonObject();
            try
            {
                Type klasse = Class.forName(JsonKarte.get(JSON_KLASSE).getAsString());
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