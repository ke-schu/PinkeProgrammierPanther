package model;

import resources.EffektTyp;
import resources.EinheitTyp;

public class KarteEinheit extends Karte
{
  private final EinheitTyp einheitTyp;
  private int macht;
  private int lebenspunkte;
  private int mana;
  private int beweglichkeit;
  private int reichweite;
  private EffektTyp effektEins;
  private EffektTyp effektZwei;

  public KarteEinheit(String name, int level, EinheitTyp einheitTyp, int macht, int lebenspunkte, int mana, int beweglichkeit, int reichweite, EffektTyp effektEins, EffektTyp effektZwei)
  {
    super(name, level);
    this.einheitTyp = einheitTyp;
    this.macht = macht;
    this.lebenspunkte = lebenspunkte;
    this.mana = mana;
    this.beweglichkeit = beweglichkeit;
    this.reichweite = reichweite;
    this.effektEins = effektEins;
    this.effektZwei = effektZwei;
  }

  public EinheitTyp getTyp()
  {
    return einheitTyp;
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

  public EffektTyp getEffektEins()
  {
    return effektEins;
  }

  public EffektTyp getEffektZwei()
  {
    return effektZwei;
  }
}

