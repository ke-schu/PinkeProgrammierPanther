package utility;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;

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
            KonsolenIO.ausgeben("Warte auf Verbindung auf Port: " + port);

            socket = server.accept();
            socket.setSoTimeout(0);
            KonsolenIO.ausgeben("Verbunden zu " + socket.getInetAddress());
            verbunden = true;

            netIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (BindException e)
        {
            beenden();
            KonsolenIO.ausgeben("Server läuft bereits auf Port " + port);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben("Fehler:" + e.getMessage());
            beenden();
        }
    }
}