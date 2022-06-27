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

    public NetzwerkIO(Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.classType = typ;
    }

    class InputThread extends Thread
    {
        public void run()
        {
            while (verbunden)
            {
                try
                {
                    this.sleep(10);
                    empfangen();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                netIn.close();;
                netOut.close();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
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

    private void empfangen()
    {
        JsonReader reader = new JsonReader(netIn);
        infoOut.println(serialisierung.deserialisieren(reader, classType));
    }
}