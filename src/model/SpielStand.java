package model;

public class SpielStand
{
    private int guthaben;
    private KartenDeck spieldeck;
    private Level level;
    private int position;

    public SpielStand(int guthaben, KartenDeck spieldeck, Level level, int position)
    {
        this.guthaben = guthaben;
        this.spieldeck = spieldeck;
        this.level = level;
        this.position = position;
    }

    public int getGuthaben()
    {
        return guthaben;
    }

    public void setGuthaben(int guthaben)
    {
        this.guthaben = guthaben;
    }

    public KartenDeck getSpieldeck()
    {
        return spieldeck;
    }

    public void setSpieldeck(KartenDeck spieldeck)
    {
        this.spieldeck = spieldeck;
    }

    public Level getLevel()
    {
        return level;
    }

    public void setLevel(Level level)
    {
        this.level = level;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }
}
