package model;

public class Spielstatus
{
    private Spieler spieler;
    private Gegenspieler gegenspieler;
    private SpielFeld spielfeld;
    private KartenDeck spielerDeck;
    private KartenDeck gegenspielerDeck;
    private int zugzaehler;

    public Spieler getSpieler ()
    {
        return spieler;
    }

    public Gegenspieler getGegenspieler ()
    {
        return gegenspieler;
    }

    public SpielFeld getSpielfeld ()
    {
        return spielfeld;
    }

    public KartenDeck getSpielerDeck ()
    {
        return spielerDeck;
    }

    public KartenDeck getGegenspielerDeck ()
    {
        return gegenspielerDeck;
    }

    public int getZugzaehler ()
    {
        return zugzaehler;
    }

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
