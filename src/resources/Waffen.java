package resources;

import static resources.KonstantenGUI.*;
import static resources.StringsGUI.*;

/**
 In diesem Enum liegen Waffen, welche dem Helden ausgeruestet werden koennen
 . */
public enum Waffen
{
    TACKER(TACKER_NAME, TACKER_BESCHREIBUNG, SCHWERT_MACHT,
           SCHWERT_REICHWEITE),
    SCHEERE(SCHEERE_NAME, SCHEERE_BESCHREIBUNG, AXT_MACHT, AXT_REICHWEITE),
    LINEAL(LINEAL_NAME, LINEAL_BESCHREIBUNG, BOGEN_MACHT, BOGEN_REICHWEITE),
    FAEUSTE(FAEUSTE_NAME, FAEUSTE_BESCHREIBUNG, FAEUSTE_MACHT,
            FAEUSTE_REICHWEITE);
    
    private final String NAME;
    private final String BESCHREIBUNG;
    private final int MACHT;
    private final int REICHWEITE;
    
    /**
     Konstruktor der Waffen
     @param name Name der Waffe
     @param beschreibung Beschreibung der Waffe
     @param macht Wert der Macht, die die Waffe dem Helden hinzufuegt
     @param reichweite Wert der Reichweite, die die Waffe dem Helden
     hinzufuegt
     */
    Waffen (String name, String beschreibung, int macht, int reichweite)
    {
        this.NAME = name;
        this.BESCHREIBUNG = beschreibung;
        this.MACHT = macht;
        this.REICHWEITE = reichweite;
    }
    
    /**
     Getter fuer den Waffennamen
     @return der Waffenname
     */
    public String getNAME ()
    {
        return NAME;
    }
    
    /**
     Getter fuer die Waffenbeschreibung
     @return die Waffenbeschreibung
     */
    public String getBESCHREIBUNG ()
    {
        return BESCHREIBUNG;
    }
    
    /**
     Getter fuer die Waffenmacht
     @return die Waffenmacht
     */
    public int getMACHT ()
    {
        return MACHT;
    }
    
    /**
     Getter fuer die Waffenreichweite
     @return die Waffenreichweite
     */
    public int getREICHWEITE ()
    {
        return REICHWEITE;
    }
}
