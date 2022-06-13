package model.ereignisse;

/**
 * Dieses Interface enthaelt Konstanten und Methoden, welche fuer die
 * Ermittlung von Wahrscheinlichkeiten fuer Ereignisse relevant sind.
 */
public interface Wahrscheinlichkeit
{
    //Obergrenze der Skalierung der Wahrscheinlichkeit.
    double WAHRSCHEINLICHKEIT_MAX = 100.0;

    //Werte, mit welcher Wahrscheinlichkeit, ein Ereignis auftritt.
   double EIN_PROZENT = 1.0;
    double ZEHN_PROZENT = 10.0;
    double ZWANZIG_PROZENT = 20.0;
    double VIERZIG_PROZENT = 40.0;
    double FUENFZIG_PROZENT = 50.0;

    /**
     * Diese Methode dient zum generieren einer Wahrscheinlichkeit.
     * Die jeweilige Klasse formuliert die Methode selbst aus.
     * @return generierte Wahrscheinlichkeit zur Bestimmung des jeweiligen
     * Ereignisses.
     */
    double generiereWahrscheinlichkeit();
}
