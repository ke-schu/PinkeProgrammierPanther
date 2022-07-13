package model.ereignisse;

import control.KartenController;
import control.WaffenController;
import model.SpielStand;
import resources.Waffen;
import utility.KonsolenIO;

import static resources.Konstanten.*;

/**
 * Diese Klasse ist eine Subklasse von Ereignis und implementiert das
 * Interface Wahrscheinlichkeit. Ein ZufallsEreignis ist fuer den Spieler erst
 * erkennbar, wenn dieser es ausfuehrt.
 */
public class ZufallsEreignis extends Ereignis implements Wahrscheinlichkeit
{
    protected double wahrscheinlichkeit;
    //Wert zur Bestimmung, welches Ereignis eintritt
    private int ereignisnummer;
    //Attribut zur Bestimmung, welches Ereignis ausgefuehrt wurde

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ ZufallsEreignis. Ein
     * ZufallsEreignis ist ein Ereignis, dessen genaue Art fuer den Spieler
     * erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     *
     * @param name:         Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     * @param ausgefuehrt:  Die Wahl des Spielers, ob er das Ereignis annimmt
     *                      oder ablehnt.
     */
    public ZufallsEreignis(String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode generiert einen Wert fuer das Attribut
     * "wahrscheinlichkeit", indem ein zufaelliger Wert zwischen 0.0 und dem
     * maximalen Wert der Skalierung gebildet wird.
     *
     * @return das Attribut "wahrscheinlichkeit" wird mit dem generierten Wert
     * zurueckgegeben.
     */
    @Override public double generiereWahrscheinlichkeit()
    {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }

    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     * Ueber das Attribut "wahrscheinlichkeit" wird bestimmt, welches Ereignis
     * ausgefuehrt wird.
     *
     * @param spiel der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren(SpielStand spiel)
    {
        if(!ausgefuehrt)
        {
            if (isAuswahl())
            {
                wahrscheinlichkeit = generiereWahrscheinlichkeit();
                // Wahrscheinlichkeit 1Prozent
                if (wahrscheinlichkeit <= EIN_PROZENT)
                {
                    ereignisnummer = ZE_1;
                }
                // Wahrscheinlichkeit 9Prozent
                else if (wahrscheinlichkeit <= ZEHN_PROZENT)
                {
                    spiel.getSpieler().setMacht(spiel.getSpieler().getMacht() + ZE_MACHT_ERHOEHUNG);
                    ereignisnummer = ZE_2;
                }
                // Wahrscheinlichkeit 10Prozent
                else if (wahrscheinlichkeit <= ZWANZIG_PROZENT)
                {
                    spiel.getSpieler().setMana(spiel.getSpieler().getMana() + ZE_MANA_ERHOEHUNG);
                    ereignisnummer = ZE_3;
                }
                // Wahrscheinlichkeit 20Prozent
                else if (wahrscheinlichkeit <= VIERZIG_PROZENT)
                {
                    spiel.getSpieler().setLebenspunkte(spiel.getSpieler().getLebenspunkte() - ZE_SCHADEN);
                    ereignisnummer = ZE_4;
                }
                // Wahrscheinlichkeit 10Prozent
                else if(wahrscheinlichkeit <= FUENFZIG_PROZENT)
                {
                    ereignisnummer = ZE_5;
                }
                ausgefuehrt = true;
            }
        }
    }

    /**
     * Getter fuer die Nummer, welches Ereignis ausgefuehrt wurde.
     *
     * @return Nummer des ausgefuehrten Ereignisses.
     */
    public int getEreignisnummer()
    {
        return ereignisnummer;
    }
}
