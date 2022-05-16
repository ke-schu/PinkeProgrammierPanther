package model;

/**
 * Klasse Position welche informationen über die Position einer Karte auf dem Spielfeld in sich traegt
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

    public int getX ()
    {
        return x;
    }

    public void setX (int x)
    {
        this.x = x;
    }


    public int getY ()
    {
        return y;
    }

    public void setY (int y)
    {
        this.y = y;
    }
}

