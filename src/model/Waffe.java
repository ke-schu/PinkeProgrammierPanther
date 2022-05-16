package model;

/**
 * Klasse dessen Instanzen eine Waffe des Helden darstellen.
 */
public class Waffe
{
    private String name;
    private int angriffsPunkte = -1;

    /**
     * Konstruktor der die uebergebenen Variablen in die Attribute setzt.
     * @param name String der in das Attribut name gesetzt werden soll.
     * @param angriffsPunkte Int-Wert der in das Attribut angriffsPunkte gesetzt werden soll.
     */
    public Waffe(String name, int angriffsPunkte)
    {
        this.name = name;
        this.angriffsPunkte = angriffsPunkte;
    }

    /**
     * Methode die den String des Attributes name wiedergibt.
     * @return gibt den String des Attributes name wieder.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Methode die den Int-Wert des Attributes angriffsPunkte wiedergibt.
     * @return gibt den Int-Wert des Attributes angriffsPunkte wieder.
     */
    public int getAngriffsPunkte ()
    {
        return angriffsPunkte;
    }
}
