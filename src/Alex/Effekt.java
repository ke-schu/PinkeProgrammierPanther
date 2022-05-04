package src.Alex;

public class Effekt
{
    public Effekt (EffektTyp typ, int schaden){
        this.typ = typ;
        this.schaden = schaden;
    }
    private EffektTyp typ;
    private int schaden;

    public EffektTyp getTyp()
    {
        return typ;
    }

    public int getSchaden()
    {
        return schaden;
    }
}
