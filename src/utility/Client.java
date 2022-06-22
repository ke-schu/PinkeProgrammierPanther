package utility;

import model.KartenDeck;
import resources.Strings;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client<T> extends NetzwerkIO<T>
{
    String server;
    Socket serverVerbindung;

    public Client(String serverURL, int serverPort, Class<T> typ)
    {
        super(serverPort, typ);
        server = serverURL;
    }

    @Override public void verbinde()
    {
        try
        {
            serverVerbindung = new Socket(server, port);
            KonsolenIO.ausgeben("[Client] Verbunden mit "
                                + serverVerbindung.getInetAddress()
                                + ":"
                                + serverVerbindung.getPort());
            versand = new DataOutputStream(new BufferedOutputStream(
                    serverVerbindung.getOutputStream()));
            empfang = new DataInputStream(new BufferedInputStream(
                    serverVerbindung.getInputStream()));
            verbunden = true;
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
            trenne();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            trenne();
        }
    }

    @Override public void trenne()
    {
        if(!verbunden)
            return;
        try
        {
            serverVerbindung.close();
            versand.close();
            empfang.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        verbunden = false;
        KonsolenIO.ausgeben("[Client] Verbindung getrennt!");
    }

    public static void main(String[] args)
    {
        Client<KartenDeck> c = new Client("localhost", 8000, KartenDeck.class);
        try
        {
            c.senden(KartenDeckIO.leseDatei(Strings.SPIEL_DECK_SPIELER_PFAD));
            Thread.sleep(1000);
            KonsolenIO.ausgeben(c.empfangen().getDeckBezeichnung());
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        c.trenne();
    }
}
