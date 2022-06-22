package utility;

import java.io.*;
import java.lang.reflect.Type;

public abstract class NetzwerkIO<T>
{
    boolean verbunden;
    int port;
    JsonIO meinJsonIO;
    DataInputStream empfang;
    DataOutputStream versand;

    public NetzwerkIO(int port)
    {
        meinJsonIO = new JsonIO<T>();
        this.port = port;
        this.verbunden = false;
    }

    public abstract void verbinde();
    public abstract void trenne();

    public void schreibeDatei(T nachricht) throws IOException
    {
        String jsonNachricht = meinJsonIO.serialisieren(nachricht);

        if(!verbunden)
        {
            verbinde();
        }

        if(nachricht != null)
        {
            Writer out = new BufferedWriter(new OutputStreamWriter(versand));
            out.write(jsonNachricht);
            out.flush();
        }
        else
        {

        }
    }

    public T leseDatei(Type typ) throws IOException
    {
        if(!verbunden)
        {
            verbinde();
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(empfang));
        StringBuilder sb = new StringBuilder();

        String zeile;
        while ((zeile = r.readLine()) != null)
        {
            sb.append(zeile);
        }

        T nachricht = (T) meinJsonIO.deserialisieren(sb.toString(), typ);
        return nachricht;
    }
}