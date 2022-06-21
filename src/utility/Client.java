package utility;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends NetzwerkIO
{
    String server;
    Socket linkZumServer;

    public Client(String serverURL, int serverPort)
    {
        super(serverPort);
        server = serverURL;
    }

    @Override public void verbinde()
    {
        while(!verbunden)
        {
            try
            {
                linkZumServer = new Socket(server, port);
                KonsolenIO.ausgeben("[Client] Verbunden mit "
                                   + linkZumServer.getInetAddress()
                                   + ":"
                                   + linkZumServer.getPort());
                empfang = new ObjectInputStream(new BufferedInputStream(linkZumServer.getInputStream()));
                versand = new ObjectOutputStream(new BufferedOutputStream(linkZumServer.getOutputStream()));
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
            linkZumServer.close();
            versand.close();
            empfang.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        verbunden = false;
    }
}
