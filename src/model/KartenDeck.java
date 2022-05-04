package model;

import java.net.URI;
import java.util.Stack;

public class KartenDeck extends Stack<Karte>
{
    private URI dateiPfad;
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

    public URI getDateiPfad()
    {
        return dateiPfad;
    }

    public void setDateiPfad(URI dateiPfad)
    {
        this.dateiPfad = dateiPfad;
    }

    public String getDeckBezeichnung()
    {
        return DeckBezeichnung;
    }

    public void setDeckBezeichnung(String deckBezeichnung)
    {
        DeckBezeichnung = deckBezeichnung;
    }
}
