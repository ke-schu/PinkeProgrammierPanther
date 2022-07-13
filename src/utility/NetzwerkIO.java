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

public abstract class NetzwerkIO<T>
{
    protected BufferedReader netIn = null;
    protected PrintWriter netOut = null;
    protected boolean verbunden = false;
    protected final Class<T> classType;
    protected Socket socket = null;
    private final Serialisierung serialisierung;
    private ObjectProperty objekt;
    private Service<T> inputService;

    public NetzwerkIO(Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.classType = typ;
        objekt = new SimpleObjectProperty(this, classType.getName());

        inputService = new Service()
        {
            @Override protected Task<T> createTask()
            {
                return new Task()
                {
                    @Override protected T call()
                    {
                        JsonReader reader = new JsonReader(netIn);

                        while(verbunden)
                        {
                            T objekt = (T) serialisierung.deserialisieren(reader, classType);
                            if(objekt != null)
                            {
                                succeeded();
                                return objekt;
                            }
                            try
                            {
                                verbunden = netIn.ready();
                            } catch (IOException e)
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

    public abstract void beenden();

    public void senden(T nachricht)
    {
        String jsonNachricht = serialisierung.serialisieren(nachricht);

        if(nachricht != null)
        {
            netOut.println(jsonNachricht);
            netOut.flush();
        }

        KonsolenIO.ausgeben(NETZWERK_GESENDET);
    }

    public Service<T> getInputService()
    {
        return inputService;
    }

    public T getObjekt()
    {
        return (T) objekt.get();
    }

    public ObjectProperty objektProperty()
    {
        return objekt;
    }
}