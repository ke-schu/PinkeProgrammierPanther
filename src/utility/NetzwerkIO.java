package utility;

import com.google.gson.stream.JsonReader;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.net.Socket;

public abstract class NetzwerkIO<T>
{
    protected BufferedReader netIn = null;
    protected PrintWriter netOut = null;
    boolean verbunden = false;
    PrintStream infoOut = System.out;
    Serialisierung serialisierung;
    final Class<T> classType;
    Socket socket = null;
    private ObjectProperty<T> obj;

    public NetzwerkIO(Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.classType = typ;
        obj = new SimpleObjectProperty();
    }

    class InputThread extends Thread
    {
        public void run()
        {
            while(verbunden)
            {
                empfangen();
            }

            try
            {
                netIn.close();;
                netOut.close();
                socket.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        public void empfangen()
        {
            JsonReader reader = new JsonReader(netIn);
            T objekt = (T) serialisierung.deserialisieren(reader, classType);

            if(objekt != null)
            {
                obj.set(objekt);
            }
        }
    }

    public void starteInputThread()
    {
        new InputThread().start();
    }

    public void beenden()
    {
        try
        {
            netIn.close();
            netOut.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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

    public Object getObj()
    {
        return obj.get();
    }

    public ObjectProperty<T> objProperty()
    {
        return obj;
    }
}