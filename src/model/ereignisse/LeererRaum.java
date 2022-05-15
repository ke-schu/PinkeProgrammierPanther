package model.ereignisse;

public class LeererRaum extends Ereignis
{
    public LeererRaum (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    public void ausfuehren()
    {
        System.out.println("Dies ist ein leerer Raum");
    }
}
