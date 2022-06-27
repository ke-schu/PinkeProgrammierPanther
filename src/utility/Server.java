package utility;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;

public class Server<T> extends NetzwerkIO<T>
{
    private static int nbConnections = 1;
    ServerSocket server = null;

    public Server(int port, Class<T> typ)
    {
        super(typ);
        try
        {
            server = new ServerSocket(port, nbConnections);
            infoOut.println("Warte auf Verbindung auf Port: " + port);

            socket = server.accept();
            infoOut.println("Verbunden zu " + socket.getInetAddress());
            verbunden = true;

            netIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (BindException e)
        {
            infoOut.println("Server läuft bereits auf Port " + port);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Server<String> meinServer = new Server(8000, String.class);
        meinServer.objProperty().addListener(
                (observableValue, s, t1) ->
                {
                    KonsolenIO.ausgeben(meinServer.getObj());
                    meinServer.senden("hey, hier ist der Server");
                });
        meinServer.starteInputThread();
    }
}