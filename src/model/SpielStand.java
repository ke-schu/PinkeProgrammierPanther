package model;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import static resources.Strings.SPIEL_DECK_PFAD;

public class SpielStand
{
    private int guthaben;
    private transient KartenDeck spieldeck;
    private Level level;
    private int position;

    public SpielStand (int guthaben, Level level, int position) throws KartenDeckFehlerhaftException
    {
        this.guthaben = guthaben;
        this.spieldeck = KartenDeckIO.leseDatei(SPIEL_DECK_PFAD);
        this.level = level;
        this.position = position;
    }

    public SpielStand (SpielStand stand) throws KartenDeckFehlerhaftException
    {
        this(stand.getGuthaben(), stand.getLevel(), stand.getPosition());
        this.spieldeck = KartenDeckIO.leseDatei(SPIEL_DECK_PFAD);
    }

    public int getGuthaben ()
    {
        return guthaben;
    }

    public void setGuthaben (int guthaben)
    {
        this.guthaben = guthaben;
    }

    public KartenDeck getSpieldeck ()
    {
        return spieldeck;
    }

    public void setSpieldeck (KartenDeck spieldeck)
    {
        this.spieldeck = spieldeck;
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
