package model;

public class Effekt
{

    private EffektTyp typ;
    private int schaden;

    public Effekt (EffektTyp typ, int schaden)
    {
        this.typ = typ;
        this.schaden = schaden;
    }

    public EffektTyp getTyp()
    {
        return typ;
    }

    public int getSchaden()
    {
        return schaden;
    }

}
