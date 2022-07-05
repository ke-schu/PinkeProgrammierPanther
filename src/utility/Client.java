package utility;

import exceptions.JsonNichtLesbarException;
import model.KartenDeck;
import resources.Konstanten;

import java.io.*;
import java.net.Socket;

import static resources.Strings.SPIEL_DECK_GEGNER_PFAD;

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
            infoOut.println("Fehler:" + e.getMessage());
            beenden();
        }
    }
}
