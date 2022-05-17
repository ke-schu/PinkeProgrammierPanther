package model.ereignisse;

import io.KonsolenIO;
import model.SpielStand;

import static resources.StringsEreignisse.EREIGNIS_1;
import static resources.StringsEreignisse.EREIGNIS_2;

/**
 * Diese Klasse ist eine Subklasse von Ereignis und implementiert das
 * Interface Wahrscheinlichkeit. Ein ZufallsEreignis ist fuer den Spieler erst
 * erkennbar, wenn dieser es ausfuehrt.
 */
public class ZufallsEreignis extends Ereignis implements Wahrscheinlichkeit
{
    protected boolean ausgefuehrt = false;
    //Variable, ob das Ereignis ausgefuehrt wurde
    protected double wahrscheinlichkeit;
    //Wert zur Bestimmung, welches Ereignis eintritt
    protected static int ereignismenge;
    //Menge an Ereignissen, welche eintreten koennen.

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ ZufallsEreignis. Ein
     * ZufallsEreignis ist ein Ereignis, dessen genaue Art fuer den Spieler
     * erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt
     * oder ablehnt
     */
    public ZufallsEreignis(String name, String beschreibung,
                           boolean ausgefuehrt)
    {
        super(name, beschreibung);
        this.ausgefuehrt = ausgefuehrt;
    }

    /**
     * Diese Methode dient als Getter um zu ueberpruefen, ob ein Ereignis
     * ausgefuehrt wurde.
     * @return Es wird zurueckgegeben, ob das Ereignis ausgefuehrt wurde.
     */
    public boolean isAusgefuehrt()
    {
        return ausgefuehrt;
    }

    /**
     * Diese Methode dient als Setter um darzustellen, ob ein Ereignis
     * ausgefuehrt wurde oder nicht
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt
     * oder ablehnt
     */
    public void setAusgefuehrt(boolean ausgefuehrt)
    {
        this.ausgefuehrt = ausgefuehrt;
    }

    /**
     * Diese Methode generiert einen Wert fuer das Attribut
     * "wahrscheinlichkeit", indem ein zufaelliger Wert zwischen 0.0 und dem
     * maximalen Wert der Skalierung gebildet wird.
     * @return das Attribut "wahrscheinlichkeit" wird mit dem generierten Wert
     * zurueckgegeben.
     */
    @Override
    public double generiereWahrscheinlichkeit()
    {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }

    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     * Ueber das Attribut "wahrscheinlichkeit" wird bestimmt, welches Ereignis
     * ausgefuehrt wird.
     * @param spielStand der aktuelle Spielstand und seine Attribute
     */
    public void ausfuehren(SpielStand spielStand)
    {
        KonsolenIO.ausgeben(this.getName());
        auswaehlen();
        if (isAuswahl())
        {
            wahrscheinlichkeit = generiereWahrscheinlichkeit();
            if (wahrscheinlichkeit <= FUENFZIG_PROZENT)
            {
                KonsolenIO.ausgeben(EREIGNIS_1);
            } else
            {
                KonsolenIO.ausgeben(EREIGNIS_2);
            }
            ausgefuehrt = true;
        }
    }
}
