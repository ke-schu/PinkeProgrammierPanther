package model;

public class KarteEinheit extends Karte
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

  public KarteEinheit(KartenEinheitType type)
  {
    this.type = type;
    this.name= type.getName();
    this.level = type.getLevel();
    this.klasse = type.getKlasse();
    this.macht = type.getMacht();
    this.lebenspunkte = type.getLebensPunkte();
    this.mana = type.getMana();
    this.beweglichkeit = type.getBeweglichkeit();
    this.reichweite = type.getReichweite();
    this.effekt = type.getEffekt();
  }

  public KartenEinheitType gettype()
  {
    return this.type;
  }

  public void setType(KartenEinheitType type)
  {
    this.type = type;
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

  public Effekt getEffekt()
  {
    return effekt;
  }
}

