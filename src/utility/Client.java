package utility;

import java.io.*;
import java.net.Socket;

import static resources.Strings.NETZWERK_GETRENNT;


public class Client<T> extends NetzwerkIO<T>
{
    public Client(String hostname, int port, Class<T> typ)
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
            KonsolenIO.ausgeben("Fehler:" + e.getMessage());
            beenden();
        }
    }

    public void beenden()
    {
        try
        {
            socket.close();
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
