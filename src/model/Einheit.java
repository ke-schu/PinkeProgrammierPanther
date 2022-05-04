package model;

public class Einheit
{
    private KartenEinheitType type;
    private String name;
    private int level;
    private String klasse;
    private int macht;
    private int lebenspunkte;
    private int mana;

    private int beweglichkeit;
    private int reichweite;
    private Effekt effekt;


   public Einheit (KarteEinheit karte)
    {
        this.type = karte.gettype();
        this.name=karte.getName();
        this.level = karte.gettype().getLevel();
        this.klasse = karte.gettype().getKlasse();
        this.macht = karte.gettype().getMacht();
        this.lebenspunkte = karte.gettype().getLebensPunkte();
        this.mana = karte.gettype().getMana();
        this.beweglichkeit = karte.gettype().getBeweglichkeit();
        this.reichweite = karte.gettype().getReichweite();
        this.effekt = karte.gettype().getEffekt();
    }

    public Einheit erschaffen(KarteEinheit karte, ManaTank reserve)
    {
        if(karte.getMana()<= reserve.getMana())
        {
            return new Einheit(karte);
        }
        else return null;
    }
public void schadenNehmen(int schaden)
    {
       this.lebenspunkte = lebenspunkte-schaden;
    }


    public KartenEinheitType getType()
    {
        return type;
    }

    public String getName()
    {
        return name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getKlasse()
    {
        return klasse;
    }

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

    public int getMana()
    {
        return mana;
    }

    public void setMana(int mana)
    {
        this.mana = mana;
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
}
