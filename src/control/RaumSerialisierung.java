package control;

import com.google.gson.*;
import model.Raum;

import java.lang.reflect.Type;

import static resources.Strings.*;

/**
 * Klasse in der sich Methoden befinden um Raeume aus .json Dateien korrekt zu
 * deserialisieren.
 */
public class RaumSerialisierung implements JsonDeserializer<Raum>
{
    private Gson meinGson = new Gson();

    /**
     * Methode um aus einem Json-Element eine Instanz von Raum zu erhalten.
     * @param json der Raum als JsonElement.
     * @param type Typ des zu serialisierenden Raums.
     * @param context Kontext der Deserialisierung.
     * @return gibt eine Instanz von Raum aus der .json wieder.
     * @throws JsonParseException wird geworfen, wenn das Element nicht
     * gelesen werden kann..
     */
    @Override
    public Raum deserialize(JsonElement json, Type type,
                            JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject meinJsonObject = json.getAsJsonObject();

        if ((meinJsonObject != null) &&
            (meinJsonObject.getAsJsonObject(JSON_EREIGNIS) != null))
        {
            JsonObject meinJsonEreignis =
                    meinJsonObject.getAsJsonObject(JSON_EREIGNIS);

            try
            {
                Type klasse = Class.forName(
                        meinJsonEreignis.get(JSON_KLASSE).getAsString());
                Raum meinRaum =
                        new Raum(meinGson.fromJson(meinJsonEreignis, klasse));
                return meinRaum;
            } catch (ClassNotFoundException e)
            {
                throw new JsonParseException(e);
            }
        } else
        {
            return null;
        }
    }
}
