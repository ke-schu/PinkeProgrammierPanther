package model;

public class InitialisierungKarteEinheit
{
    private int macht;
    private int lebenspunkte;
    private int beweglichkeit;
    private int reichweite;
    private int schild;
    private int verteidigung;
    private Position position;
    private boolean schlafend = true;

    public int getMacht()
    {
        return macht;
    }

    public void setMacht(int macht)
    {
        this.macht = macht;
    }

    public int getLebenspunkte()
    {
        return lebenspunkte;
    }

    public void setLebenspunkte(int lebenspunkte)
    {
        this.lebenspunkte = lebenspunkte;
    }

    public int getBeweglichkeit()
    {
        return beweglichkeit;
    }

    public void setBeweglichkeit(int beweglichkeit)
    {
        this.beweglichkeit = beweglichkeit;
    }

    public int getReichweite()
    {
        return reichweite;
    }

    public void setReichweite(int reichweite)
    {
        this.reichweite = reichweite;
    }

    public int getSchild()
    {
        return schild;
    }

    public void setSchild(int schild)
    {
        this.schild = schild;
    }

    public int getVerteidigung()
    {
        return verteidigung;
    }

    public void setVerteidigung(int verteidigung)
    {
        this.verteidigung = verteidigung;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public boolean isSchlafend()
    {
        return schlafend;
    }

    public void setSchlafend(boolean schlafend)
    {
        this.schlafend = schlafend;
    }
}

