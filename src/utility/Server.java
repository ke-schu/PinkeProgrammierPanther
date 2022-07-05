package utility;

import exceptions.JsonNichtLesbarException;
import model.KartenDeck;
import resources.Konstanten;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;

import static resources.Strings.SPIEL_DECK_SPIELER_PFAD;

public class Server<T> extends NetzwerkIO<T>
{
    private final static int MAX_WARTENDE_VERBINDUNGEN = 1;
    private ServerSocket server = null;

    public Server(int port, Class<T> typ)
    {
        super(typ);
        try
        {
            server = new ServerSocket(port, MAX_WARTENDE_VERBINDUNGEN);
            infoOut.println("Warte auf Verbindung auf Port: " + port);

            socket = server.accept();
            socket.setSoTimeout(0);
            infoOut.println("Verbunden zu " + socket.getInetAddress());
            verbunden = true;

            netIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (BindException e)
        {
            beenden();
            infoOut.println("Server läuft bereits auf Port " + port);
        }
        catch (IOException e)
        {
            infoOut.println("Fehler:" + e.getMessage());
            beenden();
        }
    }
}