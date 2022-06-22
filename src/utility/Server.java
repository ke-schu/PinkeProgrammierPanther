package utility;

import model.Ebene;

import java.io.*;
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
                empfang = new ObjectInputStream(linkZumClient.getInputStream());
                versand = new ObjectOutputStream(linkZumClient.getOutputStream());
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
        Server<Ebene> s = new Server(8000);
        s.verbinde();
        try
        {
            KonsolenIO.ausgeben(s.leseDatei());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        s.trenne();
    }
}
