package utility;

import java.io.*;
import java.net.ServerSocket;

import static resources.Strings.*;

/**
 Modelliert einen Server, welcher in unserem Spiel durch den Spieler
 dargestellt wird.
 @param <T> Die Klasse, die mit dem Server ausgetauscht werden soll */
public class Server<T> extends NetzwerkIO<T>
{
    private final static int MAX_WARTENDE_VERBINDUNGEN = 1;
    private ServerSocket server = null;
    
    /**
     Konstruiert einen neuen Server
     @param port der Port fuer diesen Objektaustausch
     @param typ der Typ der Klasse, die ausgetauscht werden soll.
     */
    public Server (int port, Class<T> typ)
    {
        super(typ);
        try
        {
            server = new ServerSocket(port, MAX_WARTENDE_VERBINDUNGEN);
            KonsolenIO.ausgeben(NETZWERK_WARTE + port);
            
            socket = server.accept();
            socket.setSoTimeout(0);
            KonsolenIO.ausgeben(NETZWERK_VERBUNDEN + socket.getInetAddress());
            verbunden = true;
            
            netIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            beenden();
        }
    }
    
    public void beenden ()
    {
        try
        {
            socket.close();
            server.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        KonsolenIO.ausgeben(NETZWERK_GETRENNT);
        this.getInputService().cancel();
    }
}