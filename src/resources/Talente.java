package resources;

import static resources.StringsGUI.*;

/**
 * Neben Artefakten kann der Spieler auch Talente besitzen. Alle moeglichen
 * Talente sind hier festgehalten.
 */
public enum Talente
{
    CHARME(CHARME_NAME,CHARME_BESCHREIBUNG),
    GROSSE_HAND(GROSSE_HAND_NAME,GROSSE_HAND_BESCHREIBUNG);

    private final String name;
    private final String beschreibung;

    /**
     * Konstruktor der Variablen des Enums
     * @param name String, welcher der Name des Talentes ist.
     * @param beschreibung String, welcher die Beschreibung des Talentes ist.
     */
    Talente (String name, String beschreibung)
    {
        this.name = name;
        this.beschreibung = beschreibung;
    }
    /**
     * Getter des Attributes Name.
     * @return gibt den String des Talentes name wieder.
     */
    public String getName ()
    {
        return name;
    }

    /**
     * Getter des Attributes Beschreibung.
     * @return gibt den String des Talentes beschreibung wieder.
     */
    public String getBeschreibung ()
    {
        return beschreibung;
    }
}
