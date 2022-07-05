package utility;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.net.Socket;

public abstract class NetzwerkIO<T>
{
    protected BufferedReader netIn = null;
    protected PrintWriter netOut = null;
    protected boolean verbunden = false;
    protected final PrintStream infoOut = System.out;
    protected final Class<T> classType;
    protected Socket socket = null;
    private final Serialisierung serialisierung;
    private ObjectProperty<T> postEingang;
    private Thread inputThread;

    public NetzwerkIO(Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.classType = typ;
        postEingang = new SimpleObjectProperty();
        inputThread = new Thread(new Runnable() {
            public void run()
            {
                while(verbunden && socket.isConnected())
                {
                    empfangen();
                }
                beenden();
            }

            public void empfangen()
            {
                JsonReader reader = new JsonReader(netIn);
                try
                {
                    T objekt = (T) serialisierung.deserialisieren(reader, classType);
                    if(objekt != null)
                    {
                        postEingang.set(objekt);
                    }
                }
                catch(JsonSyntaxException e)
                {
                    beenden();
                }
            }
        });
    }

    public void beenden()
    {
        try
        {
            verbunden = false;
            netIn.close();
            netOut.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        infoOut.println("Verbindung getrennt.");
    }

    public void senden(T nachricht)
    {
        String jsonNachricht = serialisierung.serialisieren(nachricht);

        if(nachricht != null)
        {
            netOut.println(jsonNachricht);
            netOut.flush();
        }

        infoOut.println("Nachricht gesendet.");
    }

    public T getPostEingang()
    {
        return postEingang.get();
    }

    public ObjectProperty<T> postEingangProperty()
    {
        return postEingang;
    }

    public Thread getInputThread()
    {
        return inputThread;
    }
}