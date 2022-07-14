package model;

import java.io.File;
import java.util.Stack;

import static resources.Strings.TRENNUNG;

/**
 KarteEinheit repraesentiert einen Stapel aus Karten */
public class KartenDeck extends Stack<Karte>
{
    private File datei;
    private String deckBezeichnung;
    
    /**
     Konstruktor der alle Attribute setzt.
     @param datei .json Datei in der das KartenDeck hinterlegt ist.
     @param deckBezeichnung deckBezeichnung String der DeckBezeichnung.
     */
    public KartenDeck (File datei, String deckBezeichnung)
    {
        this.datei = datei;
        this.deckBezeichnung = deckBezeichnung;
    }
    
    /**
     Konstruktor der Klasse KartenDeck, welcher alle Attribute setzt.
     @param deckBezeichnung String der DeckBezeichnung.
     */
    public KartenDeck (String deckBezeichnung)
    {
        this.deckBezeichnung = deckBezeichnung;
    }
    
    /**
     Ueberlagert die toString-Methode hier fuer ein Kartendeck.
     @return die Deckbezeichnung und die einzelnen Karten.
     */
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++)
        {
            sb.append(this.get(i).toString());
            sb.append(TRENNUNG);
        }
        return this.getDeckBezeichnung() + TRENNUNG + sb;
    }
    
    /**
     Methode um den String des Attributes deckBezeichnung zu bekommen.
     @return gibt den String des Attributes deckBezeichnung.
     */
    public String getDeckBezeichnung ()
    {
        return deckBezeichnung;
    }
    
    /**
     Gibt die Datei wieder, in der das Kartendeck gespeichert ist.
     @return die Kartendeck-Datei.
     */
    public File getDatei ()
    {
        return datei;
    }
    
    /**
     Setzt die Datei des Kartendecks.
     @param datei Die Datei des Kartendecks.
     */
    public void setDatei (File datei)
    {
        this.datei = datei;
    }
}
