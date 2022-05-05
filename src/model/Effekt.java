package model;

public class Effekt
{

    private EffektTyp typ;
    private int schaden;

    /**
     * Konstruktor der die Attribute typ und schaden setzt.
     * @param typ EffektTyp der in das Attribut typ gegeben wird.
     * @param schaden Int der in das Attribut schaden gegeben wird.
     */
    public Effekt (EffektTyp typ, int schaden){
        this.typ = typ;
        this.schaden = schaden;
    }

    private EffektTyp typ;
    private int schaden;


    public Effekt (EffektTyp typ, int schaden)
    {
        this.typ = typ;
        this.schaden = schaden;
    }

    /**
     * Gibt den Inhalt des Attributes typ aus.
     * @return gibt den EffektTyp aus dem Attribut Typ der Instanz wieder.
     */
    public EffektTyp getTyp()
    {
        return typ;
    }

    /**
     * Gibt den Inhalt des Attributes schaden wieder.
     * @return gibt den Wert des Attributes schaden als Int wieder.
     */
    public int getSchaden()
    {
        return schaden;
    }
}
