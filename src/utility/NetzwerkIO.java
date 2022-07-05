package utility;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

public abstract class NetzwerkIO<T>
{
    protected BufferedReader netIn = null;
    protected PrintWriter netOut = null;
    protected boolean verbunden = false;
    protected final PrintStream infoOut = System.out;
    protected final Class<T> classType;
    protected Socket socket = null;
    private final Serialisierung serialisierung;
    private Thread inputThread;
    private T empfangenesPaket;

    public NetzwerkIO(Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.classType = typ;
        inputThread = new Thread(new Runnable() {
            public void run()
            {
                empfangen();
                beenden();
            }

            public void empfangen()
            {
                JsonReader reader = new JsonReader(netIn);
                try
                {
                    T objekt = (T) serialisierung.deserialisieren(reader, classType);
                    while(objekt == null)
                    {
                        sleep(100);
                    }
                    empfangenesPaket = objekt;
                }
                catch(JsonSyntaxException | InterruptedException e)
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

    public Thread getInputThread()
    {
        return inputThread;
    }

    public boolean isVerbunden()
    {
        return verbunden;
    }

    public T getEmpfangenesPaket()
    {
        return empfangenesPaket;
    }
}