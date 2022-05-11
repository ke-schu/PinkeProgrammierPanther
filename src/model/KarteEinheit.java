package model;

import resources.Effekte;
import resources.Einheiten;

public class KarteEinheit extends Karte
{
  private final Einheiten einheiten;
  private int macht;
  private int lebenspunkte;
  private int mana;
  private int beweglichkeit;
  private int reichweite;
  private int schild;
  private int verteidigung;
  private Effekte effektEins;
  private Effekte effektZwei;

  public KarteEinheit(String name, int level, Einheiten einheiten, int macht, int lebenspunkte, int mana, int beweglichkeit, int reichweite, int verteidigung, Effekte effektEins, Effekte effektZwei)
  {
    super(name, level);
    this.einheiten = einheiten;
    this.macht = macht;
    this.lebenspunkte = lebenspunkte;
    this.mana = mana;
    this.beweglichkeit = beweglichkeit;
    this.reichweite = reichweite;
    this.verteidigung = verteidigung;
    this.effektEins = effektEins;
    this.effektZwei = effektZwei;
  }

  public Einheiten getTyp()
  {
    return einheiten;
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

  public Effekte getEffektEins()
  {
    return effektEins;
  }

  public Effekte getEffektZwei()
  {
    return effektZwei;
  }
  public int getVerteidigung()
  {
    return verteidigung;
  }

  public void setVerteidigung(int verteidigung)
  {
    this.verteidigung = verteidigung;
  }
  public int getSchild()
  {
    return schild;
  }

  public void setSchild(int schild)
  {
    this.schild = schild;
  }
}

