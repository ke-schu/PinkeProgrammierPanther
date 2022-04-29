package model;

public abstract class Charakterklasse
{
    private int freischaltgebuehr = -1;
    private Held anfuehrer = null;

    public Charakterklasse(int freischaltgebuehr, Held anführer) {
        this.freischaltgebuehr = freischaltgebuehr;
        this.anfuehrer = anfuehrer;
    }

    public int getFreischaltgebuehr ()
    {
        return freischaltgebuehr;
    }
}
