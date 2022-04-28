package model;

public abstract class Held
{
    private int angriffsPunkte = -1;
    private int lebensPunkte = -1;
    private Waffe startWaffe = null;

    public Held(int lebensPunkte, Waffe startWaffe) {
        this.lebensPunkte = lebensPunkte;
        this.startWaffe = startWaffe;
        this.angriffsPunkte = startWaffe.getAngriffsPunkte();
    }

    public int getAngriffsPunkte () {
        return angriffsPunkte;
    }

    public void setAngriffsPunkte (int angriffsPunkte) {
        this.angriffsPunkte = angriffsPunkte;
    }

    public int getLebensPunkte () {
        return lebensPunkte;
    }

    public void setLebensPunkte (int lebensPunkte) {
        this.lebensPunkte = lebensPunkte;
    }
}
