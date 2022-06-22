package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import control.EbeneSerialisierung;
import control.KartenDeckSerialisierung;
import model.Ebene;
import model.KartenDeck;

public class JsonIO<T>
{
    private Gson meinGson;
    private GsonBuilder meinGsonBuilder;
    private KartenDeckSerialisierung kartenDeckSerialisierung;
    private EbeneSerialisierung ebeneSerialisierung;

    public JsonIO()
    {
        meinGsonBuilder = new GsonBuilder();
        kartenDeckSerialisierung = new KartenDeckSerialisierung();
        ebeneSerialisierung = new EbeneSerialisierung();
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class, kartenDeckSerialisierung);
        meinGsonBuilder.registerTypeAdapter(Ebene.class, ebeneSerialisierung);
    }

    public String serialisieren(T element)
    {
        meinGsonBuilder.setPrettyPrinting();
        meinGson = meinGsonBuilder.create();
        return meinGson.toJson(element);
    }

    public T deserialisieren(String jsonString) throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return meinGson.fromJson(jsonString, new TypeToken<T>(){}.getType());
    }
}
