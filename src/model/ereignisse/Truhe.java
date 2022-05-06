package model.ereignisse;

public class Truhe extends ZufallsEreignis
{
    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Truhe. Ein ZufallsEreignis ist ein Ereignis, dessen
     * genaue Art für den Spieler erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param position: Die Position auf der Oberkarte
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public Truhe (String name,String beschreibung, int position, boolean ausgefuehrt) {
        super();
        this.name = name;
        this.beschreibung = beschreibung;
        this.position = position;
        this.ausgefuehrt = ausgefuehrt;
        this.wahrscheinlichkeit = generiereWahrscheinlichkeit();
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "ZufallsEreignis". Ueber das Attribut "wahrscheinlichkeit"
     * wird bestimmt, welches Ereignis ausgefuehrt wird.
     */
    @Override
    public void ausfuehren () {
        if(wahrscheinlichkeit <= 50.0) {
            //Erstes zufälliges Ereignis mit einer Wahrscheinlichkeit von 50%
            ausgefuehrt = true;
        }
        else {
            //Zweites zufälliges Ereignis mit einer Wahrscheinlichkeit von 50%
            ausgefuehrt = true;
        }
    }
}
