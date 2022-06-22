package utility;

import java.io.*;

public abstract class NetzwerkIO<T>
{
    boolean verbunden;
    final int port;
    Serialisierung serialisierung;
    DataInputStream empfang;
    DataOutputStream versand;
    final Class<T> classType;

    public NetzwerkIO(int port, Class<T> typ)
    {
        serialisierung = new Serialisierung<T>();
        this.port = port;
        this.verbunden = false;
        this.classType = typ;
    }

    public abstract void verbinde();
    public abstract void trenne();

    public void senden(T nachricht) throws IOException
    {
        String jsonNachricht = serialisierung.serialisieren(nachricht);

        if(!verbunden)
        {
            verbinde();
        }

        if(nachricht != null)
        {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(versand));
            out.write(jsonNachricht);
            out.flush();
        }
        KonsolenIO.ausgeben("Nachricht gesendet.");
        trenne();
    }

    public T empfangen() throws IOException
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
        r.close();
        trenne();
        return (T) serialisierung.deserialisieren(sb.toString(), classType);
    }
}