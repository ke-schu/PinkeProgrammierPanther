package model;

/**
 * ManaTank repraesentiert die Manareserve welche einem Spieler zur Verfuegung
 * steht, um Einheiten zu beschwoeren.
 */
public class ManaTank
{
    private int mana;

    /**
     * Erstellt einen ManaTank.
     * @param spieler welcher die Information uber die Groesse des ManaTanks
     * in sich treagt.
     */
    public ManaTank(Spielbar spieler)
    {
        this.mana = spieler.getMana();
    }

    /**
     * Getter Methode um den Wert des Attributes mana zu erhalten.
     * @return gibt den Wert des Attributes mana wieder.
     */
    public int getMana()
    {
        return this.mana;
    }

    /**
     * Setter Methode um das Attribut mana zu setzen.
     * @param mana auf welchen das Attribut gesetzt werden soll.
     */
    public void setMana(int mana)
    {
        this.mana = mana;
    }

    /**
     * Verringert den Manawert im Manatank um die hoehe der gezahlten kosten.
     * @param kosten, Kosten welche gezahlt werden.
     */
    public void manaBezahlen(int kosten)
    {
        this.mana = mana - kosten;
    }
}