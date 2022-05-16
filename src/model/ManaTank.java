package model;

/**
 * ManaTank repraesentiert die Manareserve welche einem Spieler zur verfuegung steht,
 * um Einheiten zu beschwoeren
 */
public class ManaTank
{
    private int mana;

    /**
     * Erstellt einen ManaTank
     * @param spieler welcher die Information uber die Groesse des ManaTanks in sich treagt
     */
    public ManaTank (Spielbar spieler)
    {
        this.mana = spieler.getMana();
    }

    public int getMana ()
    {
        return this.mana;
    }
    public void setMana (int mana)
    {
        this.mana = mana;
    }

    /**
     * Verringert den Manawert im Manatank um die höhe der gezahlten kosten
     * @param kosten, Kosten welche gezahlt werden
     */
    public void manaBezahlen (int kosten)
    {
        this.mana = mana-kosten;
    }
}