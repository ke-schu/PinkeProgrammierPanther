package utility;

import model.Ebene;
import model.KartenDeck;
import resources.Strings;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client<E> extends NetzwerkIO<E>
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
                versand = new DataOutputStream(linkZumServer.getOutputStream());
                empfang = new DataInputStream(linkZumServer.getInputStream());
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

    public static void main(String[] args)
    {
        Client<KartenDeck> c = new Client("localhost", 8000);
        c.verbinde();
        try
        {
            c.schreibeDatei(KartenDeckIO.leseDatei(Strings.SPIEL_DECK_SPIELER_PFAD));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        c.trenne();
    }
}
