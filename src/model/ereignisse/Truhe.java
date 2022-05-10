package model.ereignisse;

public class Truhe extends EreignisKlasse implements Wahrscheinlichkeit
{
    protected boolean geoeffnet = false;
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
        super();
        this.name = name;
        this.beschreibung = beschreibung;
        this.geoeffnet = geoeffnet;
        this.wahrscheinlichkeit = generiereWahrscheinlichkeit();
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "ZufallsEreignis". Ueber das Attribut "wahrscheinlichkeit"
     * wird bestimmt, welches Ereignis ausgefuehrt wird.
     */
    public void ausfuehren ()
    {
        if(wahrscheinlichkeit <= FUENFZIG_PROZENT)
        {
            //Erstes zufälliges Ereignis mit einer Wahrscheinlichkeit von 50%
            geoeffnet = true;
        }
        else
        {
            //Zweites zufälliges Ereignis mit einer Wahrscheinlichkeit von 50%
            geoeffnet = true;
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
}
