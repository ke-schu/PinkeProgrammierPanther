package model;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import static resources.Strings.*;

public class SpielStand
{
    private int guthaben;
    private transient KartenDeck spieldeck_spieler;
    private transient KartenDeck spieldeck_gegner;
    private Level level;
    private int position;

    public SpielStand (int guthaben, Level level, int position) throws KartenDeckFehlerhaftException
    {
        this.guthaben = guthaben;
        this.spieldeck_spieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeck_gegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
        this.level = level;
        this.position = position;
    }

    public SpielStand (SpielStand stand) throws KartenDeckFehlerhaftException
    {
        this(stand.getGuthaben(), stand.getLevel(), stand.getPosition());
        this.spieldeck_spieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeck_gegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
    }

    public int getGuthaben ()
    {
        return guthaben;
    }

    public void setGuthaben (int guthaben)
    {
        this.guthaben = guthaben;
    }

    public KartenDeck getSpieldeck_spieler()
    {
        return spieldeck_spieler;
    }

    public void setSpieldeck_spieler(KartenDeck spieldeck_spieler)
    {
        this.spieldeck_spieler = spieldeck_spieler;
    }

    public KartenDeck getSpieldeck_gegner()
    {
        return spieldeck_gegner;
    }

    public void setSpieldeck_gegner(KartenDeck spieldeck_gegner)
    {
        this.spieldeck_gegner = spieldeck_gegner;
    }

    public Level getLevel ()
    {
        return level;
    }

    public void setLevel (Level level)
    {
        this.level = level;
    }

    public int getPosition ()
    {
        return position;
    }

    public void setPosition (int position)
    {
        this.position = position;
    }
}
