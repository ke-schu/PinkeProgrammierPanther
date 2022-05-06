package model.ereignisse;

public abstract class Mensch extends Ereignis {
    protected int gratisInteraktion;    //Kostenloses Nutzen der Dienste von Händlern, Tempeln und Schmieden

    /**
     * Diese Methode prueft ob der Spieler gratis sein Deck verändern kann, wenn er die Dienste der
     * Einrichtungen in Anspruch nimmt.
     * @return Es wird zurückgegeben, ob der Spieler noch gratis Interaktionen zur Verfuegung hat.
     */
    public boolean pruefeGratisInteraktion() {
        if(gratisInteraktion > 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
