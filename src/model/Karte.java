package model;

public abstract class Karte
{
    private int id = this.hashCode();
    private String name;
    private int level;
    private String klasse = this.getClass().getCanonicalName();
}
