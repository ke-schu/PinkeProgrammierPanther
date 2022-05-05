package model;

import java.io.File;
import java.util.Stack;

public class KartenDeck extends Stack<Karte>
{
    private File datei;
    private String deckBezeichnung;
    private Charakterklasse besitzer;
    private final static String dateiPfad = "src/carddecks/";

    public KartenDeck(String DeckBezeichnung, Charakterklasse besitzer)
    {
        super();
        this.deckBezeichnung = DeckBezeichnung;
        this.besitzer = besitzer;
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

    public Charakterklasse getBesitzer()
    {
        return besitzer;
    }

    public File getDatei()
    {
        return datei;
    }

    public String getDeckBezeichnung()
    {
        return deckBezeichnung;
    }

    public void setDeckBezeichnung(String deckBezeichnung)
    {
        this.deckBezeichnung = deckBezeichnung;
    }
}
