package model.ereignisse;

public class ZufallsEreignis extends Ereignis
{
    protected boolean ausgefuehrt = false;              //Variable, ob das Ereignis ausgefuehrt wurde
    protected double wahrscheinlichkeit;                //Wert zur Bestimmung, welches Ereignis eintritt
    protected final int WAHRSCHEINLICHKEIT_MAX = 100;   //Obergrenze der Skalierung um Ereignissen eine Wahrscheinlichkeit zu geben.

    protected static int ereignismenge;                 //Menge an Ereignissen, welche eintreten können.

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ ZufallsEreignis. Ein ZufallsEreignis ist ein Ereignis, dessen
     * genaue Art für den Spieler erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param position: Die Position auf der Oberkarte
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public ZufallsEreignis (String name, String beschreibung, int position, boolean ausgefuehrt) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.position = position;
        this.ausgefuehrt = ausgefuehrt;
        this.wahrscheinlichkeit = generiereWahrscheinlichkeit();
    }

    /**
     * Dieser Konstruktor ist ein leerer Konstruktor.
     */
    public ZufallsEreignis () {}

    /**
     * Diese Methode dient als Getter um zu ueberpruefen, ob ein Ereignis ausgefuehrt wurde.
     * @return Es wird zurückgegeben ob das Ereignis ausgefuehrt wurde.
     */
    public boolean isAusgefuehrt () {
        return ausgefuehrt;
    }

    /**
     * Diese Methode dient als Setter um darzustellen, ob ein Ereignis ausgefuehrt wurde oder nicht
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public void setAusgefuehrt (boolean ausgefuehrt) {
        this.ausgefuehrt = ausgefuehrt;
    }

    /**
     * Diese Methode generiert einen Wert für das Attribut "wahrscheinlichkeit", indem ein zufaelliger
     * Wert zwischen 0.0 und dem maximalen Wert der Skalierung gebildet wird.
     * @return das Attribut "wahrscheinlichkeit" wird mit dem generierten Wert zurückgegeben.
     */
    public double generiereWahrscheinlichkeit () {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Ueber das Attribut "wahrscheinlichkeit"
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
