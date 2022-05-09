package model.ereignisse;

public abstract class Mensch extends EreignisKlasse
{
    protected int gratisInteraktion;    //Kostenloses Nutzen der Dienste von Händlern, Tempeln und Schmieden.
    protected final int KEINE_GRATIS_INTERAKTION = 0;   //Konstante für keine weiteren gratis Interaktionen.

    /**
     * Diese Methode prueft ob der Spieler gratis sein Deck verändern kann, wenn er die Dienste der
     * Einrichtungen in Anspruch nimmt.
     * @return Es wird zurückgegeben, ob der Spieler noch gratis Interaktionen zur Verfuegung hat.
     */
    public boolean pruefeGratisInteraktion()
    {
        if(gratisInteraktion > KEINE_GRATIS_INTERAKTION)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
