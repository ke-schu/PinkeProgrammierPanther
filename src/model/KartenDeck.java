package model;

import java.io.File;
import java.util.Stack;

public class KartenDeck extends Stack<Karte>
{
    private File datei;
    private String deckBezeichnung;
    private final static String dateiPfad = "src/carddecks/";

    public KartenDeck(String DeckBezeichnung)
    {
        super();
        this.deckBezeichnung = DeckBezeichnung;
        this.datei = new File(dateiPfad + DeckBezeichnung + ".json");
    }

    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++)
        {
            sb.append(this.get(i).toString());
            sb.append("\t\t");
        }
        return this.getDeckBezeichnung() + "\t\t" + sb.toString();
    }

    public File getDatei()
    {
        return datei;
    }

    public String getDeckBezeichnung()
    {
        return deckBezeichnung;
    }
}
