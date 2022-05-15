package model.ereignisse;

public class ZufallsEreignis extends Ereignis implements Wahrscheinlichkeit
{
    protected boolean ausgefuehrt = false;  //Variable, ob das Ereignis ausgefuehrt wurde
    protected double wahrscheinlichkeit;    //Wert zur Bestimmung, welches Ereignis eintritt

    protected static int ereignismenge;     //Menge an Ereignissen, welche eintreten können.

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ ZufallsEreignis. Ein ZufallsEreignis ist ein Ereignis, dessen
     * genaue Art für den Spieler erst bekannt wird, wenn er den zugehoerigen Raum betritt.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public ZufallsEreignis (String name, String beschreibung, boolean ausgefuehrt)
    {
        super(name, beschreibung);
        this.ausgefuehrt = ausgefuehrt;
    }

    /**
     * Diese Methode dient als Getter um zu ueberpruefen, ob ein Ereignis ausgefuehrt wurde.
     * @return Es wird zurückgegeben ob das Ereignis ausgefuehrt wurde.
     */
    public boolean isAusgefuehrt ()
    {
        return ausgefuehrt;
    }

    /**
     * Diese Methode dient als Setter um darzustellen, ob ein Ereignis ausgefuehrt wurde oder nicht
     * @param ausgefuehrt: Die Wahl des Spielers, ob er das Ereignis annimmt oder ablehnt
     */
    public void setAusgefuehrt (boolean ausgefuehrt)
    {
        this.ausgefuehrt = ausgefuehrt;
    }

    /**
     * Diese Methode generiert einen Wert für das Attribut "wahrscheinlichkeit", indem ein zufaelliger
     * Wert zwischen 0.0 und dem maximalen Wert der Skalierung gebildet wird.
     * @return das Attribut "wahrscheinlichkeit" wird mit dem generierten Wert zurückgegeben.
     */
    @Override
    public double generiereWahrscheinlichkeit ()
    {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }

    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Ueber das Attribut "wahrscheinlichkeit"
     * wird bestimmt, welches Ereignis ausgefuehrt wird.
     */
    public void ausfuehren ()
    {
        auswaehlen();
        if(isAuswahl())
        {
            wahrscheinlichkeit = generiereWahrscheinlichkeit();
            if (wahrscheinlichkeit <= FUENFZIG_PROZENT)
            {
                System.out.println("Ereignis 1");
            }
            else
            {
                System.out.println("Ereignis 2");
            }
            ausgefuehrt = true;
        }
    }
}
