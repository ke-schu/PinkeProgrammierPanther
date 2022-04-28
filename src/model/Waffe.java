package model;

public abstract class Waffe
{
    private int angriffsPunkte = -1;

    public Waffe(int angriffsPunkte) {
        this.angriffsPunkte = angriffsPunkte;
    }

    public int getAngriffsPunkte() {
        return angriffsPunkte;
    }

    public void setAngriffsPunkte(int angriffsPunkte) {
        this.angriffsPunkte = angriffsPunkte;
    }
}
