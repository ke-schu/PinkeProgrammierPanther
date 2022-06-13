package model.ereignisse;

import model.SpielStand;

import static resources.Konstanten.*;

public class Truhe extends Ereignis implements Wahrscheinlichkeit
{
    protected boolean geleert = false;
    protected double wahrscheinlichkeit;

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Truhe. Ein
     * ZufallsEreignis ist ein Ereignis, dessen genaue Art fuer den Spieler
     * erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param geoeffnet: Der Zustand der Truhe, ob sie geoeffnet ist.
     */
    public Truhe(String name, String beschreibung, boolean geoeffnet)
    {
        super(name, beschreibung);
        this.geleert = geoeffnet;
    }

    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse
     * "ZufallsEreignis". Ueber das Attribut "wahrscheinlichkeit" wird
     * bestimmt, welches Ereignis ausgefuehrt wird.
     */
    public void ausfuehren(SpielStand spielStand)
    {
        if(!geleert)
        {
            if (isAuswahl())
            {
                wahrscheinlichkeit = generiereWahrscheinlichkeit();
                if (wahrscheinlichkeit <= EIN_PROZENT)
                {
                    spielStand.setGold(spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_EINS);
                }
                else if (wahrscheinlichkeit <= ZEHN_PROZENT)
                {
                    spielStand.setGold(spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_ZWEI);
                }
                else if (wahrscheinlichkeit <= ZWANZIG_PROZENT)
                {
                    spielStand.setGold(spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_DREI);
                }
                else if (wahrscheinlichkeit <= VIERZIG_PROZENT)
                {
                    spielStand.setGold(spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_VIER);
                }
                else if (wahrscheinlichkeit > VIERZIG_PROZENT)
                {
                    spielStand.setGold(spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_FUENF);
                }
                geleert = true;
            }
        }
    }

    /**
     * Dies ist eine Methode zum Generieren des Attributs
     * "Wahrscheinlichkeit". Dieses Attribut wird benoetigt um zu bestimmen,
     * welcher Inhalt sich in der Truhe befindet.
     * @return Attribut zur Bestimmung des Truheninhalts
     */
    @Override
    public double generiereWahrscheinlichkeit()
    {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }

    /**
     * Gibt wieder, ob eine Truhe geleert ist
     * @return ob die Truhe geleert ist
     */
    public boolean isGeleert()
    {
        return geleert;
    }

    /**
     * Setze das Attribut geleert auf den uebergebenen boolean Wert
     * @param geleert boolean, welches in das Attribut geleert gesetzt werden
     * soll.
     */
    public void setGeleert(boolean geleert)
    {
        this.geleert = geleert;
    }
}
