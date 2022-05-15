package model.ereignisse;

import model.SpielStand;

public class Truhe extends Ereignis implements Wahrscheinlichkeit
{
    protected boolean geleert = false;
    protected double wahrscheinlichkeit;

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Truhe. Ein ZufallsEreignis ist ein Ereignis, dessen
     * genaue Art für den Spieler erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param geoeffnet: Der Zustand der Truhe, ob sie geoeffnet ist.
     */
    public Truhe (String name, String beschreibung, boolean geoeffnet)
    {
        super(name, beschreibung);
        this.geleert = geoeffnet;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "ZufallsEreignis". Ueber das Attribut "wahrscheinlichkeit"
     * wird bestimmt, welches Ereignis ausgefuehrt wird.
     */
    public void ausfuehren (SpielStand spielStand)
    {
        System.out.println(this.getName());
        auswaehlen();
        if(isAuswahl())
        {
            wahrscheinlichkeit = generiereWahrscheinlichkeit();
            if (wahrscheinlichkeit <= FUENFZIG_PROZENT)
            {
                System.out.println("Truhe 1");
            }
            else
            {
                System.out.println("Truhe 2");
            }
            geleert = true;
        }
    }

    /**
     * Dies ist eine Methode zum Generieren des Attributs "Wahrscheinlichkeit". Dieses Attribut wird
     * benötigt um zu bestimmen, welcher Inhalt sich in der Truhe befindet.
     * @return Attribut zur Bestimmung des Truheninhalts
     */
    @Override
    public double generiereWahrscheinlichkeit() {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }

    public boolean isGeleert() {
        return geleert;
    }

    public void setGeleert(boolean geleert) {
        this.geleert = geleert;
    }
}
