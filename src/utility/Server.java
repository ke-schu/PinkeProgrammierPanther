package utility;

import model.KartenDeck;
import resources.Strings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server<T> extends NetzwerkIO<T>
{
    ServerSocket serverSocket;
    Socket clientVerbindung;

    public Server(int serverPort, Class<T> typ)
    {
        super(serverPort, typ);
    }

    @Override public void verbinde()
    {
        try
        {
            serverSocket = new ServerSocket(port);
            KonsolenIO.ausgeben("[Server] Warte auf Verbindung...");
            clientVerbindung = serverSocket.accept();
            KonsolenIO.ausgeben("[Server] Verbunden mit "
                                + clientVerbindung.getInetAddress()
                                + ":"
                                + clientVerbindung.getPort());
            empfang = new DataInputStream(new BufferedInputStream(
                    clientVerbindung.getInputStream()));
            versand = new DataOutputStream(new BufferedOutputStream(
                    clientVerbindung.getOutputStream()));
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
            clientVerbindung.close();
            versand.close();
            empfang.close();
            serverSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        verbunden = false;
        KonsolenIO.ausgeben("[Server] Verbindung getrennt!");
    }

    public static void main(String[] args)
    {
        Server<KartenDeck> s = new Server(8000, KartenDeck.class);
        while(true)
        {
            try
            {
                KonsolenIO.ausgeben(s.empfangen().getDeckBezeichnung());
                s.senden(KartenDeckIO.leseDatei(Strings.SPIEL_DECK_GEGNER_PFAD));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}