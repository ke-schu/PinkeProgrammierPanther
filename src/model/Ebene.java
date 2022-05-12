package model;
//import model.ereignisse.Ereignis;
import model.ereignisse.*;
import resources.Strings;
import resources.Zahlen;

public class Ebene
{
    private Raeume[][] ebene = null;
    private int ebenenSpalte = 0;
    private int ebenenZeile = 0;

    /**
     * Konstruktor der Klasse Ebene, welcher einen 2D-Array erstellt der Raeume beinhaltet.
     * @param ebenenZeile Abmessung der Zeilengröße des Arrays
     * @param ebenenSpalte Abmessung der Spaltengröße des Arrays
     */
    public Ebene (int ebenenZeile, int ebenenSpalte)
    {
        this.setEbene(new Raeume[ebenenZeile][ebenenSpalte]);
        this.setEbenenZeile(ebenenZeile);
        this.setEbenenSpalte(ebenenSpalte);
    }

    public Ebene (int ebenenZeile, int ebenenSpalte, Raeume[][] ebene)
    {
        this.setEbene(ebene);
        this.setEbenenZeile(ebenenZeile);
        this.setEbenenSpalte(ebenenSpalte);
    }

    /**
     * Methode die das Attribut ebene wiedergibt.
     * @return liefert den 2D-Array gefüllt mit Raeumen.
     */
    public Raeume[][] getEbene()
    {
        return this.ebene;
    }

    /**
     * Methode um das Attribut ebene zu setzen.
     * @param ebene 2D-Array bestehend aus Raeumen, welches in das Attribut ebene gesetzt werden soll.
     */
    public final void setEbene(Raeume[][] ebene)
    {
        this.ebene = ebene;
    }

    /**
     * Methode um den Wert des Attributes ebenenZeile zu bekommen.
     * @return gibt den Int des Attributes ebeneZeile.
     */
    public int getEbenenZeile()
    {
        return this.ebenenZeile;
    }

    /**
     * Methode um einen Wert in das Attribut ebeneZeile zu setzen.
     * @param ebenenZeile Int der in das Attribut ebeneZeile gesetzt wird.
     */
    public final void setEbenenZeile(int ebenenZeile)
    {
        this.ebenenZeile=ebenenZeile;
    }

    /**
     * Methode um den Wert des Attributes ebeneSpalte zu bekommen.
     * @return gibt den Int des Attributes ebeneSpalte.
     */
    public int getEbenenSpalte()
    {
        return this.ebenenSpalte;
    }

    /**
     * Methode um einen Wert in das Attribut ebeneSpalte zu setzen.
     * @param ebenenSpalte Int der in das Attribut ebeneSpalte gesetzt wird.
     */
    public final void setEbenenSpalte(int ebenenSpalte)
    {
        this.ebenenSpalte=ebenenSpalte;
    }

    /**
     * Methode um eine Instanz der Klasse Raeume an eine bestimmte Position in dem Attribut ebene zu setzen.
     * @param spalte Position in der Spalte, beginnend bei 0.
     * @param zeile Position in der Zeile, beginnend bei 0.
     * @param raum Instanz der Klasse Raeume, welche an die gezielte Stelle gesetzt werden soll.
     */
    public void raumEinsetzen(int spalte, int zeile, Raeume raum)
    {
        this.ebene[zeile][spalte] = raum;
    }

    /**
     * Methode um an einer bestimmen Position innerhalb dem Attribut ebene ein null-Literal zu setzen.
     * @param spalte Position der Spalte an dem das null-Literal eingefügt werden soll.
     * @param zeile Position der Zeile an dem das null-Literal eingefügt werden soll.
     */
    public void raumLoeschen (int spalte, int zeile)
    {
        this.ebene[zeile][spalte] = null;
    }

    /**
     * Methode um an einer bestimmten Position den Inhalt des Attributes ebene zu bekommen.
     * @param spalte Position der Spalte von dem der Inhalt zurueck gegeben wird.
     * @param zeile Position der Zeile von dem der Inhalt zurueck gegeben wird.
     * @return gibt den Inhalt an der bestimmten Position des Attributes ebene.
     */
    public Raeume getRaumAnPosition(int spalte, int zeile)
    {
        return this.ebene[zeile][spalte];
    }


    /**
     * Methode, die die Raeume der Ebene in der Konsole formatiert ausgibt.
     * @return gibt einen String der die Matrix des Attributes ebene repraesentiert wieder.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.ebenenZeile; i++)
        {
            for (int j = 0; j < this.ebenenSpalte; j++)
            {
                if (this.ebene[i][j] != null)
                {
                    sb.append(this.ebene[i][j].getEreignis().getName());
                    sb.append(Strings.LEERZEICHEN);
                }
                else
                {
                    sb.append(Zahlen.ZAHL_0);
                    sb.append(Strings.LEERZEICHEN);
                }

            }
        sb.append(Strings.ZEILENUMBRUCH);
        }
    return sb.toString();
    }
}

