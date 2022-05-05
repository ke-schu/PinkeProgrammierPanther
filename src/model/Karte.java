package model;

public abstract class Karte
{
    private int id = this.hashCode();
    private String name;
    private int level;
    private String klasse = this.getClass().getCanonicalName();

    /**
     * Ueberlagerung der toString Methode um das Attribut id richtig als string wiederzugeben.
     * @return gibt einen String aus dem Attribut id zurueck.
     */
    @Override
    public String toString()
    {
        return String.valueOf(id);
    }
}
