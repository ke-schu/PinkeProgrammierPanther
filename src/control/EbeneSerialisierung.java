package control;

import com.google.gson.*;
import model.Ebene;
import model.Raum;
import model.SpielfigurEbene;

import java.lang.reflect.Type;

import static resources.Strings.*;

/**
 Diese Klasse dient zum Serialisieren und Deserialisieren der Ebenen. */
public class EbeneSerialisierung implements JsonDeserializer<Ebene>
{
    private static final GsonBuilder meinGsonBuilder = new GsonBuilder();
    
    /**
     Methode um aus einem Json-Element eine Instanz von Raum zu erhalten.
     @param json der Raum als JsonElement.
     @param type Typ des zu serialisierenden Raums.
     @param context Kontext der Deserialisierung.
     @return gibt eine Instanz von Raum aus der .json wieder.
     @throws JsonParseException wird geworfen, wenn das Element nicht gelesen
     werden kann.
     */
    @Override public Ebene deserialize (JsonElement json, Type type,
                                        JsonDeserializationContext context)
            throws JsonParseException
    {
        JsonObject meinJsonObject = json.getAsJsonObject();
        
        RaumSerialisierung meineSerialisierung = new RaumSerialisierung();
        meinGsonBuilder.registerTypeAdapter(Raum.class, meineSerialisierung);
        Gson meinGson = meinGsonBuilder.create();
        
        Ebene meineEbene = meinGson.fromJson(meinJsonObject, Ebene.class);
        
        int ebenenZeile = meinJsonObject.get(JSON_EBENEN_ZEILE).getAsInt();
        int ebenenSpalte = meinJsonObject.get(JSON_EBENEN_SPALTE).getAsInt();
        JsonObject jsonSpieler =
                meinJsonObject.get(JSON_SPIELFIGUR_EBENE).getAsJsonObject();
        SpielfigurEbene spieler =
                meinGson.fromJson(jsonSpieler, SpielfigurEbene.class);
        
        meineEbene.setEbenenZeile(ebenenZeile);
        meineEbene.setEbenenSpalte(ebenenSpalte);
        meineEbene.setSpielfigur(spieler);
        
        return meineEbene;
    }
}
