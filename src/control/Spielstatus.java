package control;

import model.Gegenspieler;
import model.KartenDeck;
import model.SpielFeld;
import model.Spieler;

public class Spielstatus
{
    public Spieler getSpieler ()
    {
        return spieler;
    }

    public void setSpieler (Spieler spieler)
    {
        this.spieler = spieler;
    }

    private Spieler spieler;

    public Gegenspieler getGegenspieler ()
    {
        return gegenspieler;
    }

    public void setGegenspieler (Gegenspieler gegenspieler)
    {
        this.gegenspieler = gegenspieler;
    }

    private Gegenspieler gegenspieler;

    public SpielFeld getSpielfeld ()
    {
        return spielfeld;
    }

    public void setSpielfeld (SpielFeld spielfeld)
    {
        this.spielfeld = spielfeld;
    }

    private SpielFeld spielfeld;

    public KartenDeck getSpielerDeck ()
    {
        return spielerDeck;
    }

    public void setSpielerDeck (KartenDeck spielerDeck)
    {
        this.spielerDeck = spielerDeck;
    }

    private KartenDeck spielerDeck;

    public KartenDeck getGegenspielerDeck ()
    {
        return gegenspielerDeck;
    }

    public void setGegenspielerDeck (KartenDeck gegenspielerDeck)
    {
        this.gegenspielerDeck = gegenspielerDeck;
    }

    private KartenDeck gegenspielerDeck;

    public int getZugzaehler ()
    {
        return zugzaehler;
    }

    public void setZugzaehler (int zugzaehler)
    {
        this.zugzaehler = zugzaehler;
    }

    private int zugzaehler;

    public Spielstatus( Spieler spieler,  Gegenspieler gegenspieler,
                       SpielFeld spielfeld, KartenDeck spielerDeck,
                       KartenDeck gegenspielerDeck, int zugzaehler)
    {
        this.spieler = spieler;
        this.gegenspieler = gegenspieler;
        this.spielfeld = spielfeld;
        this.spielerDeck = spielerDeck;
        this.gegenspielerDeck = gegenspielerDeck;
        this.zugzaehler = zugzaehler;
    }
}
