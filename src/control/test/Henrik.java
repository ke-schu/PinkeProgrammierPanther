package control.test;

import model.Ebene;
import model.ereignisse.*;

public class Henrik {

    public static void testen ()
    {
        Ebene ebene = new Ebene();
        Treppe treppe = new Treppe("Treppe", "Beschreibung");
        //ebene = treppe.ausfuehren();
        System.out.println(ebene.toString());
    }
}
