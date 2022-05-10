package model;

public class KarteEinheit extends Karte
{
  private KartenEinheitType kartenType;
  private String kartenName;
  private int kartenLevel;
  private String kartenKlasse;
  private int kartenMacht;
  private int kartenLebenspunkte;
  private int kartenMana;
  private int kartenBeweglichkeit;
  private int kartenReichweite;
  private EffektTyp kartenEffekteins;
  private EffektTyp kartenEffektzwei;


  public String getKartenKlasse ()
  {
    return kartenKlasse;
  }

  public int getKartenMacht ()
  {
    return kartenMacht;
  }

  public void setKartenMacht (int kartenMacht)
  {
    this.kartenMacht = kartenMacht;
  }

  public int getKartenLebenspunkte ()
  {
    return kartenLebenspunkte;
  }

  public void setKartenLebenspunkte (int kartenLebenspunkte)
  {
    this.kartenLebenspunkte = kartenLebenspunkte;
  }

  public int getKartenMana ()
  {
    return kartenMana;
  }

  public void setKartenMana (int kartenMana)
  {
    this.kartenMana = kartenMana;
  }

  public int getKartenBeweglichkeit ()
  {
    return kartenBeweglichkeit;
  }

  public void setKartenBeweglichkeit (int kartenBeweglichkeit)
  {
    this.kartenBeweglichkeit = kartenBeweglichkeit;
  }

  public int getKartenReichweite ()
  {
    return kartenReichweite;
  }

  public void setKartenReichweite (int kartenReichweite)
  {
    this.kartenReichweite = kartenReichweite;
  }

  public EffektTyp getKartenEffekteins ()
  {
    return kartenEffekteins;
  }
  public EffektTyp getKartenEffektzwei ()
  {
    return kartenEffektzwei;
  }

  public String getKlasse ()
  {
    return this.kartenKlasse;
  }
}

