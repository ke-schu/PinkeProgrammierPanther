package utility;

import java.io.*;

public abstract class NetzwerkIO<T>
{
    boolean verbunden;
    int port;
    ObjectInputStream empfang;
    ObjectOutputStream versand;

    public NetzwerkIO(int port)
    {
        this.port = port;
        this.verbunden = false;
    }

    public abstract void verbinde();
    public abstract void trenne();

    public void schreibeDatei(T nachricht) throws IOException
    {
        if(!verbunden)
        {
            verbinde();
        }

        if(nachricht != null)
        {
            versand.writeObject(nachricht);
            versand.flush();
        }
        else
        {

        }
    }

    public T leseDatei() throws IOException
    {
        if(!verbunden)
        {
            verbinde();
        }

        try
        {
            T nachricht = (T) empfang.readObject();
            return nachricht;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}