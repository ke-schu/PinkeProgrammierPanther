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

    private  EffektTyp effekt;

    private int position_x = 0;

    private int position_y = 0;


   public Einheit (KarteEinheit karte)
    {
        this.type = karte.gettype();
        this.name=karte.getKartenName();
        this.level = karte.getKartenLevel();
        this.klasse = karte.getKlasse();
        this.macht = karte.getKartenMacht();
        this.lebenspunkte = karte.getKartenLebenspunkte();
        this.mana = karte.getKartenMana();
        this.beweglichkeit = karte.getKartenBeweglichkeit();
        this.reichweite = karte.getKartenReichweite();
        this.effekt = karte.getKartenEffekt();
    }

    public Einheit erschaffen(KarteEinheit karte, ManaTank reserve)
    {
        if(karte.getKartenMana()<= reserve.getMana())
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

    public int getPosition_x()
    {
        return this.position_x;
    }

    public void setPosition_x(int getPosition_x)
    {
        this.position_x = getPosition_x;
    }

    public int getPosition_y()
    {
        return this.position_y;
    }

    public void setPosition_y(int getPosition_y)
    {
        this.position_x = getPosition_y;
    }

    public EffektTyp getEffekt()
    {
        return effekt;
    }
}
