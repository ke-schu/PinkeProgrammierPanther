package model;

import static resources.Strings.*;
import static resources.Zahlen.ZAHL_0;

/**
 * Diese Klasse stellt das Spielfeld dar, indem sich der Spieler durch Raeume bewegt und verschiedene Ereignisse
 * antreffen kann.
 */
public class Ebene
{
    private Raum[][] ebene = null;
    private int ebenenSpalte = ZAHL_0;
    private int ebenenZeile = ZAHL_0;

    /**
     * Konstruktor der Klasse Ebene, welcher einen 2D-Array erstellt der Raeume beinhaltet.
     * Der Raumarray hat dabei die Abmaße der beiden übergebenen Integer.
     * @param ebenenZeile Abmessung der Zeilengröße des Arrays
     * @param ebenenSpalte Abmessung der Spaltengröße des Arrays
     */
    public Ebene (int ebenenZeile, int ebenenSpalte)
    {
        this.setEbene(new Raum[ebenenZeile][ebenenSpalte]);
        this.setEbenenZeile(ebenenZeile);
        this.setEbenenSpalte(ebenenSpalte);
    }

    /**
     * Konstruktor der Klasse Ebene, welcher alle Attribute uebergeben bekommt und setzt.
     * @param ebenenZeile Int-Wert der in das Attribut ebeneZeile gesetzt werden soll.
     * @param ebenenSpalte Int-Wert der in das Attribut ebeneSpalte gesetzt werden soll.
     * @param ebene 2D-Raumarray, welcher in das Attribut ebene gesetzt werden soll.
     */
    public Ebene (int ebenenZeile, int ebenenSpalte, Raum[][] ebene)
    {
        this.setEbene(ebene);
        this.setEbenenZeile(ebenenZeile);
        this.setEbenenSpalte(ebenenSpalte);
    }

    /**
     * Konstruktor der Klasse Ebene, welcher aus der uebergebenen Ebene alle Attribute übernimmt.
     * @param ebene Instanz der Klasse Ebene dessen Attribute uebernommen werden sollen.
     */
    public Ebene (Ebene ebene)
    {
        this.ebene = ebene.getEbene();
        this.ebenenSpalte = ebene.getEbenenSpalte();
        this.ebenenZeile = ebene.getEbenenZeile();
    }

    /**
     * Methode die das Attribut ebene wiedergibt.
     * @return liefert den 2D-Array gefüllt mit Raeumen.
     */
    public Raum[][] getEbene ()
    {
        return this.ebene;
    }

    /**
     * Methode um das Attribut ebene zu setzen.
     * @param ebene 2D-Array bestehend aus Raeumen, welches in das Attribut ebene gesetzt werden soll.
     */
    public final void setEbene (Raum[][] ebene)
    {
        this.ebene = ebene;
    }

    /**
     * Methode um den Wert des Attributes ebenenZeile zu bekommen.
     * @return gibt den Int des Attributes ebeneZeile.
     */
    public int getEbenenZeile ()
    {
        return this.ebenenZeile;
    }

    /**
     * Methode um einen Wert in das Attribut ebeneZeile zu setzen.
     * @param ebenenZeile Int der in das Attribut ebeneZeile gesetzt wird.
     */
    public final void setEbenenZeile (int ebenenZeile)
    {
        this.ebenenZeile=ebenenZeile;
    }

    /**
     * Methode um den Wert des Attributes ebeneSpalte zu bekommen.
     * @return gibt den Int des Attributes ebeneSpalte.
     */
    public int getEbenenSpalte ()
    {
        return this.ebenenSpalte;
    }

    /**
     * Methode um einen Wert in das Attribut ebeneSpalte zu setzen.
     * @param ebenenSpalte Int der in das Attribut ebeneSpalte gesetzt wird.
     */
    public final void setEbenenSpalte (int ebenenSpalte)
    {
        this.ebenenSpalte=ebenenSpalte;
    }

    /**
     * Methode um eine Instanz der Klasse Raeume an eine bestimmte Position in dem Attribut ebene zu setzen.
     * @param spalte Position in der Spalte, beginnend bei 0.
     * @param zeile Position in der Zeile, beginnend bei 0.
     * @param raum Instanz der Klasse Raeume, welche an die gezielte Stelle gesetzt werden soll.
     */
    public void raumEinsetzen (int spalte, int zeile, Raum raum)
    {
        this.ebene[zeile][spalte] = raum;
    }

    /**
     * Methode um an einer bestimmten Position den Inhalt des Attributes ebene zu bekommen.
     * @param spalte Position der Spalte von dem der Inhalt zurueck gegeben wird.
     * @param zeile Position der Zeile von dem der Inhalt zurueck gegeben wird.
     * @return gibt den Inhalt an der bestimmten Position des Attributes ebene.
     */
    public Raum getRaumAnPosition (int spalte, int zeile)
    {
        return this.ebene[zeile][spalte];
    }

    /**
     * Methode, die die Raeume der Ebene in der Konsole formatiert ausgibt.
     * @return gibt einen String der die Matrix des Attributes ebene repraesentiert wieder.
     */
    @Override
    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.ebenenZeile; i++)
        {
            for (int j = 0; j < this.ebenenSpalte; j++)
            {
                if (this.ebene[i][j] != null)
                {
                    if (this.ebene[i][j].getEreignis() != null)
                    {
                        sb.append(this.ebene[i][j].getEreignis().getName());
                        sb.append(LEERZEICHEN);
                        sb.append(SENKRECHTER_STRICH);
                        sb.append(LEERZEICHEN);
                    }
                }
                else
                {
                    sb.append(ZAHL_0);
                    sb.append(LEERZEICHEN);
                    sb.append(SENKRECHTER_STRICH);
                    sb.append(LEERZEICHEN);
                }
            }
        sb.append(ZEILENUMBRUCH);
        }
    return sb.toString();
    }
}

