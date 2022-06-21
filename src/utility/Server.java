package utility;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server extends NetzwerkIO
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
                empfang = new ObjectInputStream(new BufferedInputStream(linkZumClient.getInputStream()));
                versand = new ObjectOutputStream(new BufferedOutputStream(linkZumClient.getOutputStream()));
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
}
