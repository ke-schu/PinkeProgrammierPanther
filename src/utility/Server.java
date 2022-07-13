package utility;

import java.io.*;
import java.net.ServerSocket;

import static resources.Strings.*;

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

    public void beenden()
    {
        try
        {
            socket.close();
            server.close();
            netOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        KonsolenIO.ausgeben(NETZWERK_GETRENNT);
        this.getInputService().cancel();
    }
}