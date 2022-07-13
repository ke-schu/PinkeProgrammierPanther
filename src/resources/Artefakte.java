package resources;

import static resources.StringsGUI.*;

/**
 * Die folgenden Artefakte kann der Spieler im Laufe des Spiels erhalten.
 */
public enum Artefakte
{
    SCHUTZENGEL(SCHUTZENGEL_NAME,SCHUTZENGEL_BESCHREIBUNG);
    private final String NAME;
    private final String BESCHREIBUNG;

    /**
     * Konstruktor der Variablen des Enums
     * @param name String, welcher der Name des Artefaktes ist.
     * @param beschreibung String, welcher die Beschreibung des Artefaktes ist.
     */
    Artefakte (String name, String beschreibung)
    {
        this.NAME         = name;
        this.BESCHREIBUNG = beschreibung;
    }

    /**
     * Getter des Attributes Name.
     * @return gibt den String des Attributes name wieder.
     */
    public String getNAME ()
    {
        return NAME;
    }

    /**
     * Getter des Attributes Beschreibung.
     * @return gibt den String des Attributes beschreibung wieder.
     */
    public String getBESCHREIBUNG ()
    {
        return BESCHREIBUNG;
    }
}