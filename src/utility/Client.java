package utility;

import java.io.*;
import java.net.Socket;

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

            netIn = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Client<String> meinClient = new Client("localhost", 8000, String.class);
        meinClient.senden("Hallo, ich bin der Client");
        meinClient.objProperty().addListener(
                (observableValue, s, t1) ->
                {
                    KonsolenIO.ausgeben(meinClient.getObj());
                });
        meinClient.starteInputThread();
    }
}
