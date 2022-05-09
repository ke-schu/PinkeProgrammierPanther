package model;

import java.io.File;
import java.util.Stack;

public class KartenDeck extends Stack<Karte>
{
    private File datei;
    private String deckBezeichnung;
    private final static String dateiPfad = "resources/kartendecks/";

    /**
     * Konstruktor der alle Attribute setzt.
     * @param datei .json Datei in der das KartenDeck hinterlegt ist.
     * @param deckBezeichnung deckBezeichnung String der DeckBezeichnung.
     */
    public KartenDeck (File datei, String deckBezeichnung)
    {
        this.datei = datei;
        this.deckBezeichnung = deckBezeichnung;
    }

    /**
     * Konstruktor der Klasse KartenDeck, welcher alle Attribute setzt.
     * @param deckBezeichnung String der DeckBezeichnung
     */
    public KartenDeck (String deckBezeichnung)
    {
        this.deckBezeichnung = deckBezeichnung;
    }

    /**
     *
     * @return
     */
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

    /**
     * Methode die File des Attributes datei wiedergibt.
     * @return gibt den Inhalt des Attributes datei.
     */
    public File getDatei ()
    {
        return datei;
    }

    /**
     * Methode um den String des Attributes deckBezeichnung zu bekommen.
     * @return gibt den String des Attributes deckBezeichnung.
     */
    public String getDeckBezeichnung ()
    {
        return deckBezeichnung;
    }
}
