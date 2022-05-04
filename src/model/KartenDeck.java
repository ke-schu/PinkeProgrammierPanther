package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;
import java.util.Stack;

public class KartenDeck extends Stack<Karte>
{
    private String DeckBezeichnung;

    public KartenDeck(String DeckBezeichnung)
    {
        super();
        this.DeckBezeichnung = DeckBezeichnung;
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
