package model;

public abstract class Charakterklasse
{
    private int freischaltgebuehr = -1;
    private Held anführer = null;

    public Charakterklasse(int freischaltgebuehr, Held anführer) {
        this.freischaltgebuehr = freischaltgebuehr;
        this.anführer = anführer;
    }

    public int getFreischaltgebuehr ()
    {
        return freischaltgebuehr;
    }
}
