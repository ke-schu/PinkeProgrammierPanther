package model;

import com.google.gson.Gson;
import src.Alex.Karte;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class Kartendeck extends Stack<Karte>
{
    private String DeckBezeichnung;
    private Random zufallsGenerator = new Random();
    private Gson meinGson = new Gson();

    public Kartendeck (String DeckBezeichnung)
    {
        super();
        this.DeckBezeichnung = DeckBezeichnung;
    }

    public void mischen ()
    {
        Collections.shuffle(this, zufallsGenerator);
    }

    public String serialisieren ()
    {
        return meinGson.toJson(this);
    }

    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++)
        {
            sb.append(this.get(i).toString());
            sb.append("\t\t");
        }
        return sb.toString();
    }

    public String getDeckBezeichnung()
    {
        return DeckBezeichnung;
    }

    public void setDeckBezeichnung(String deckBezeichnung)
    {
        this.DeckBezeichnung = deckBezeichnung;
    }
}
