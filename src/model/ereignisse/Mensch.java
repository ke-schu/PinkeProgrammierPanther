package model.ereignisse;

import static resources.Konstanten.*;

/**
 Diese Klasse ist eine Subklasse von Ereignis. Sie ist ebenfalls eine
 Superklasse fuer weitere Ereignisse und formuliert Methoden und Attribute,
 die
 alle Subklassen gemein haben. */
public abstract class Mensch extends Ereignis
{
    protected int gratisInteraktionen =
            AUSGANGSWERT_ANZAHL_GRATIS_HANDLUNGEN_MENSCH;
    protected int interaktionsZaehler = 0;
    private int kosten = AUSGANGSWERT_KOSTEN_HANDLUNG_MENSCH;
    
    /**
     Der Konstruktor von Mensch.
     @param name Der Name des Ereignisses.
     @param beschreibung Die Beschreibung fuer den Spieler.
     */
    public Mensch (String name, String beschreibung)
    {
        super(name, beschreibung);
    }
    
    /**
     Diese Methode prueft, ob der Spieler gratis sein Deck veraendern kann,
     wenn er die Dienste der Einrichtungen in Anspruch nimmt.
     */
    public boolean pruefeGratisInteraktion ()
    {
        return gratisInteraktionen > 0;
    }
    
    /**
     Diese Methode erhoeht automatisch die Kosten der Dienste, wenn der
     Spieler
     sie haeufig nutzt.
     */
    public void kostenErhoehen ()
    {
        if (interaktionsZaehler % KOSTEN_ERHOEHUNG_ANZAHL_HANDLUNGEN == 0)
        {
            setKosten(kosten + KOSTEN_ERHOEHUNG_GOLD);
        }
    }
    
    /**
     Getter fuer die Hoehe der Kosten, die das jeweilige Ereignis verlangt.
     @return Hoehe der Kosten.
     */
    public int getKosten ()
    {
        return kosten;
    }
    
    /**
     Setter fuer die Hoehe der Kosten, die das jeweilige Ereignis verlangt.
     @param kosten Hoehe der Kosten.
     */
    public void setKosten (int kosten)
    {
        this.kosten = kosten;
    }
    
    /**
     Getter fuer die gratis Interaktionen.
     @return Integerwert der gratis Interaktionen.
     */
    public int getGratisInteraktionen ()
    {
        return gratisInteraktionen;
    }
    
    /**
     Getter fuer den Interaktionszaehler.
     @return Integerwert des Interaktionszaehlers.
     */
    public int getInteraktionsZaehler ()
    {
        return interaktionsZaehler;
    }
    
    /**
     Diese Methode rechnet aus, wie viele Zuege verbleiben, bis der Preis
     erhoeht wird.
     @return Verbleibende Zuege bis Preiserhoehung.
     */
    public int aktionenBisPreisErhoehung ()
    {
        int i = 0;
        if (interaktionsZaehler % KOSTEN_ERHOEHUNG_ANZAHL_HANDLUNGEN == 0)
        {
            i = 3;
        }
        else if (interaktionsZaehler % KOSTEN_ERHOEHUNG_ANZAHL_HANDLUNGEN == 1)
        {
            i = 2;
        }
        else if (interaktionsZaehler % KOSTEN_ERHOEHUNG_ANZAHL_HANDLUNGEN == 2)
        {
            i = 1;
        }
        return i;
    }
}
