package model.ereignisse;

import control.KartenController;
import utility.KonsolenIO;
import model.SpielStand;

import static resources.Konstanten.*;

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
    private int ereignisnummer;
    //Attribut zur Bestimmung, welches Ereignis ausgefuehrt wurde

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ ZufallsEreignis. Ein
     * ZufallsEreignis ist ein Ereignis, dessen genaue Art fuer den Spieler
     * erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt
     * oder ablehnt.
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
     * ausgefuehrt wurde oder nicht.
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt
     * oder ablehnt.
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
     * @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren(SpielStand spielStand)
    {
        KonsolenIO.ausgeben(this.getName());
        //auswaehlen();
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
                spielStand.getSpieler().setMacht(spielStand.getSpieler().getMacht()+ZE_MACHT_ERHOEHUNG);
                ereignisnummer = ZE_2;
            }
            // Wahrscheinlichkeit 10Prozent
            else if (wahrscheinlichkeit <= ZWANZIG_PROZENT)
            {
                spielStand.getSpieler().setMana(spielStand.getSpieler().getMana()+ZE_MANA_ERHOEHUNG);
                ereignisnummer = ZE_3;
            }
            // Wahrscheinlichkeit 20Prozent
            else if (wahrscheinlichkeit <= VIERZIG_PROZENT)
            {
                spielStand.getSpieler().setLebenspunkte(spielStand.getSpieler().getLebenspunkte()-ZE_SCHADEN);
                ereignisnummer = ZE_4;
            }
            // Wahrscheinlichkeit 10Prozent
            else if (wahrscheinlichkeit <= FUENFZIG_PROZENT)
            {
                KartenController.karteVerbessern(spielStand.getSpieldeckSpieler().get(KonsolenIO.eingabeInt()));
                ereignisnummer = ZE_5;
            }
            ausgefuehrt = true;
        }
    }

    /**
     * Getter fuer die Nummer, welches Ereignis ausgefuehrt wurde.
     * @return Nummer des ausgefuehrten Ereignisses.
     */
    public int getEreignisnummer ()
    {
        return ereignisnummer;
    }
}
