package utility;

import com.google.gson.stream.JsonReader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static resources.Strings.NETZWERK_GESENDET;

/**
 Diese Klasse ist die abstrakte Oberklasse fuer Server und Client und
 stellt dafuer Netzwerkfunktionalitaet mit JavaFX-threadfaehigen Services
 zur Verfuegung.
 * @param <T> Die Klasse, die ausgetauscht werden soll
 */
public abstract class NetzwerkIO<T>
{
    protected final Class<T> classType;
    private final Serialisierung serialisierung;
    protected BufferedReader netIn = null;
    protected PrintWriter netOut = null;
    protected boolean verbunden = false;
    protected Socket socket = null;
    private ObjectProperty objekt;
    private Service<T> inputService;
    
    /**
     Dies ist nur der Superkonstruktor,
     der nicht direkt instanziiert werden kann.
     * @param typ der Typ der auszutauschenden Klasse
     */
    public NetzwerkIO (Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.classType = typ;
        objekt = new SimpleObjectProperty(this, classType.getName());
        
        inputService = new Service()
        {
            @Override protected Task<T> createTask ()
            {
                return new Task()
                {
                    @Override protected T call ()
                    {
                        JsonReader reader = new JsonReader(netIn);
                        
                        while (verbunden)
                        {
                            T objekt =
                                    (T) serialisierung.deserialisieren(reader,
                                                                       classType);
                            if (objekt != null)
                            {
                                succeeded();
                                return objekt;
                            }
                            try
                            {
                                verbunden = netIn.ready();
                            }
                            catch (IOException e)
                            {
                                break;
                            }
                        }
                        beenden();
                        return null;
                    }
                };
            }
        };
    }
    
    /**
     Beendet den laufenden Server, bzw. laufenden Client und den Input-Task.
     */
    public abstract void beenden ();
    
    /**
     Sendet eine Nachricht an den Gespaechspartner.
     * @param nachricht die zu sendende Nachricht
     */
    public void senden (T nachricht)
    {
        String jsonNachricht = serialisierung.serialisieren(nachricht);
        
        if (nachricht != null)
        {
            netOut.println(jsonNachricht);
            netOut.flush();
        }
        KonsolenIO.ausgeben(NETZWERK_GESENDET);
    }
    
    /**
     Gibt den InputService zurueck, auf dem dauerhaft nach neuen Objekten
     geschaut wird.
     * @return den InputService der Klasse
     */
    public Service<T> getInputService ()
    {
        return inputService;
    }
    
    /**
     Gibt das Objekt aus dem Posteingang zurueck
     * @return ankommendes Objekt
     */
    public T getObjekt ()
    {
        return (T) objekt.get();
    }
    
    /**
     Die die Property von eingehenden Objekten zurueck
     * @return eingehende ObjekteProperty
     */
    public ObjectProperty objektProperty ()
    {
        return objekt;
    }
}