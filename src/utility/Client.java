package utility;

import java.io.*;
import java.net.Socket;

import static resources.Strings.FEHLER;
import static resources.Strings.NETZWERK_GETRENNT;

/**
 Modelliert einen Client, welcher in unserem Spiel durch den Gegenspieler
 dargestellt wird.
 @param <T> Die Klasse, die mit dem Server ausgetauscht werden soll */
public class Client<T> extends NetzwerkIO<T>
{
    /**
     Konstruiert einen neuen Client
     @param hostname die IP-Adresse des Servers
     @param port der Port fuer diesen Objektaustausch
     @param typ der Typ der Klasse, die ausgetauscht werden soll.
     */
    public Client (String hostname, int port, Class<T> typ)
    {
        super(typ);
        try
        {
            socket = new Socket(hostname, port);
            socket.setSoTimeout(0);
            verbunden = true;
            KonsolenIO.ausgeben(socket.getInetAddress());
            
            netIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(FEHLER + e.getMessage());
            beenden();
        }
    }
    
    public void beenden ()
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        KonsolenIO.ausgeben(NETZWERK_GETRENNT);
        this.getInputService().cancel();
    }
}
