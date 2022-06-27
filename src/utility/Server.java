package utility;

import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.SocketException;

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
            while(true)
            {
                infoOut.println("Warte auf Verbindung auf Port: " + port);

                socket = server.accept();
                infoOut.println("Verbunden zu " + socket.getInetAddress());
                verbunden = true;

                netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                new InputThread().start();
            }
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
        new Server<String>(8000, String.class);
    }
}