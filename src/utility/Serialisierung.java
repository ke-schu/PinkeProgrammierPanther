package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import control.EbeneSerialisierung;
import control.KartenDeckSerialisierung;
import model.Ebene;
import model.KartenDeck;

import java.lang.reflect.Type;

public class Serialisierung<T>
{
    private Gson meinGson;
    private GsonBuilder meinGsonBuilder;
    
    public Serialisierung ()
    {
        meinGsonBuilder = new GsonBuilder();
        meinGsonBuilder.registerTypeAdapter(KartenDeck.class,
                                            new KartenDeckSerialisierung());
        meinGsonBuilder.registerTypeAdapter(Ebene.class,
                                            new EbeneSerialisierung());
    }
    
    public String serialisieren (T element)
    {
        meinGsonBuilder.setPrettyPrinting();
        meinGson = meinGsonBuilder.create();
        return meinGson.toJson(element);
    }
    
    public T deserialisieren (String jsonString, Type typ)
            throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return (T) meinGson.fromJson(jsonString, typ);
    }
    
    public T deserialisieren (JsonReader reader, Type typ)
            throws JsonSyntaxException
    {
        meinGson = meinGsonBuilder.create();
        return (T) meinGson.fromJson(reader, typ);
    }
}

