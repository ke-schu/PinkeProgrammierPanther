package utility;

import com.google.gson.reflect.TypeToken;
import model.Ebene;
import model.KartenDeck;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server<E> extends NetzwerkIO<E>
{
    ServerSocket serverSocket;
    Socket linkZumClient;

    public Server(int serverPort)
    {
        super(serverPort);
    }

    @Override public void verbinde()
    {
        while(!verbunden)
        {
            try
            {
                serverSocket = new ServerSocket(port);
                KonsolenIO.ausgeben("[Server] Warte auf Verbindung...");
                linkZumClient = serverSocket.accept();
                KonsolenIO.ausgeben("[Server] Verbunden mit "
                                    + linkZumClient.getInetAddress()
                                    + ":"
                                    + linkZumClient.getPort());
                empfang = new DataInputStream(linkZumClient.getInputStream());
                versand = new DataOutputStream(linkZumClient.getOutputStream());
                verbunden = true;
                break;
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            trenne();
            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override public void trenne()
    {
        try
        {
            linkZumClient.close();
            versand.close();
            empfang.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        verbunden = false;
    }

    public static void main(String[] args)
    {
        Server<KartenDeck> s = new Server(8000);
        s.verbinde();
        Type klassenTyp = new TypeToken<KartenDeck>() {}.getType();
        try
        {
            KonsolenIO.ausgeben(s.leseDatei(klassenTyp).getDeckBezeichnung());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        s.trenne();
    }
}
