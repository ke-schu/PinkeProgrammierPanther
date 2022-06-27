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

            new InputThread().start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }/*
        finally
        {
            try
            {
                if(netIn != null)
                    netIn.close();
                if(netOut != null)
                    netOut.close();
                if(socket != null)
                    socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }*/
    }

    public static void main(String[] args)
    {
        Client<String> c = new Client("localhost", 8000, String.class);
        c.senden("Test");
    }
}
