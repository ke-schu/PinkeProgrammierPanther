package model.ereignisse;

import static resources.Konstanten.*;

/**
 * Diese Klasse ist eine Subklasse von Ereignis. Sie ist ebenfalls eine
 * Superklasse fuer weitere Ereignisse und formuliert Methoden und Attribute,
 * die alle Subklassen gemein haben.
 */
public abstract class Mensch extends Ereignis
{
    protected int gratisInteraktionen =
            AUSGANGSWERT_ANZAHL_GRATIS_HANDLUNGEN_MENSCH;
    //  Gold, welches für die Interaktion mit dem Ereignis verlangt wird
    private int kosten = AUSGANGSWERT_KOSTEN_HANDLUNG_MENSCH;
    //  Zaehlt die Menge an Interaktionen mit dem jeweiligen Objekt
    protected int interaktionsZaehler = 0;

    /**
     * Der Konstruktor von Mensch.
     * @param name Der Name des Ereignisses.
     * @param beschreibung Die Beschreibung fuer den Spieler.
     */
    public Mensch(String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode prueft, ob der Spieler gratis sein Deck veraendern kann,
     * wenn er die Dienste der Einrichtungen in Anspruch nimmt.
     */
    public boolean pruefeGratisInteraktion()
    {
        if (gratisInteraktionen > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Diese Methode erhoeht automatisch die Kosten der Dienste, wenn der
     * Spieler sie haeufig nutzt.
     */
    public void kostenErhoehen()
    {
        if (interaktionsZaehler % KOSTEN_ERHOEHUNG_ANZAHL_HANDLUNGEN == 0)
        {
            setKosten(kosten + KOSTEN_ERHOEHUNG_GOLD);
        }
    }

    /**
     * Getter fuer die Hoehe der Kosten, die das jeweilige Ereignis verlangt.
     * @return Hoehe der Kosten.
     */
    public int getKosten()
    {
        return kosten;
    }

    /**
     * Setter fuer die Hoehe der Kosten, die das jeweilige Ereignis verlangt.
     * @param kosten Hoehe der Kosten.
     */
    public void setKosten(int kosten)
    {
        this.kosten = kosten;
    }
}
