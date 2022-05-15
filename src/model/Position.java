package model;

/**
 * Klasse Position welche informationen über die Position einer Karte auf dem Spielfeld in sich treagt
 */
public class Position
{
    private int x;
    private int y;

    /**
     * Leerer Konstruktor, der keine Attribute setzt.
     */
    public Position()
    {
    }

    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getx()
    {
        return x;
    }

    public void setx(int x)
    {
        this.x = x;
    }


    public int gety()
    {
        return y;
    }

    public void sety(int y)
    {
        this.y = y;
    }
}

