package control;

import com.google.gson.*;
import model.Raum;

import java.lang.reflect.Type;

public class RaumSerialisierung implements JsonDeserializer<Raum>
{
    private Gson meinGson = new Gson();

    @Override
    public Raum deserialize (JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject meinJsonObject = json.getAsJsonObject();

        if (meinJsonObject != null)
        {
            JsonObject meinJsonEreignis = meinJsonObject.getAsJsonObject("ereignis");

            try
            {
                Type klasse = Class.forName(meinJsonEreignis.get("klasse").getAsString());
                Raum meinRaum = new Raum(meinGson.fromJson(meinJsonEreignis, klasse));
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
