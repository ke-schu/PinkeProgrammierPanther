package model;

import static resources.Zahlen.ZAHL_4;

public class SpielfigurEbene
{
    private Position position = new Position();

    public SpielfigurEbene ()
    {
        this.position.setx(ZAHL_4);
        this.position.sety(ZAHL_4);
    }

    public Position getPosition()
    {
        return position;
    }

    public int getPosition_x ()
    {
        return this.position.getx();
    }

    public int getPosition_y ()
    {
        return this.position.gety();
    }


    public void setPosition(int x, int y)
    {
        this.position.setx(x);
        this.position.sety(y);
    }
}
