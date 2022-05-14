package control;

import com.google.gson.*;
import model.Karte;
import model.Raeume;

import java.lang.reflect.Type;

public class RaeumeSerialisierung implements JsonDeserializer<Raeume>
{
    private Gson meinGson = new Gson();

    @Override
    public Raeume deserialize (JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject meinJsonObject = json.getAsJsonObject();
        JsonElement meinJsonEreignisElement = meinJsonObject.get("ereignis");

        if (meinJsonEreignisElement != null)
        {
            JsonObject meinJsonEreignis = meinJsonEreignisElement.getAsJsonObject();

            try
            {
                Type klasse = Class.forName(meinJsonEreignis.get("klasse").getAsString());
                Raeume meinRaum = meinGson.fromJson(meinJsonEreignis, klasse);
                return meinRaum;
            }
            catch (ClassNotFoundException e)
            {
                throw new JsonParseException(e);
            }
        }
        else
        {
            return null;
        }
    }
}
