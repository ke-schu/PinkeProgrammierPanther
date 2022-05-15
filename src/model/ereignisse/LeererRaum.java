package model.ereignisse;

import model.SpielStand;

public class LeererRaum extends Ereignis
{
    public LeererRaum (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    public void ausfuehren(SpielStand spielStand)
    {
        System.out.println("Dies ist ein leerer Raum");
    }
}
