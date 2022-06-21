package utility;

import java.io.*;

public abstract class NetzwerkIO<E>
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

    public void schreibeDatei(E element) throws IOException
    {
        if(!verbunden)
        {
            verbinde();
        }

        ObjectOutputStream dos = new ObjectOutputStream(versand);

        if(element != null)
        {

        }
        else
        {

        }

        dos.flush();
        dos.close();
        trenne();
    }

    public E leseDatei() throws IOException
    {
        if(!verbunden)
        {
            verbinde();
        }

        ObjectInputStream dis = new ObjectInputStream(empfang);
        return null;
    }
}