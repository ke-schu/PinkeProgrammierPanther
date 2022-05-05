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
  private Effekt kartenEffekt;

  /**
   * Konstruktor der aus den angegebenen Attributen eine Instanz der Klasse KarteEinheit erstellt.
   * @param kartenType Instanz der Klasse KartenEinheitType der in das Attribut kartenType gesetzt wird.
   */
  public KarteEinheit(KartenEinheitType kartenType)
  {
    super(kartenType.getName(), kartenType.getLevel());
    this.kartenType = kartenType;
    this.kartenName = kartenType.getName();
    this.kartenLevel = kartenType.getLevel();
    this.kartenKlasse = kartenType.getKlasse();
    this.kartenMacht = kartenType.getMacht();
    this.kartenLebenspunkte = kartenType.getLebensPunkte();
    this.kartenMana = kartenType.getMana();
    this.kartenBeweglichkeit = kartenType.getBeweglichkeit();
    this.kartenReichweite = kartenType.getReichweite();
    this.kartenEffekt = kartenType.getEffekt();
  }

  public KartenEinheitType gettype()
  {
    return this.kartenType;
  }

  public void setKartenType(KartenEinheitType kartenType)
  {
    this.kartenType = kartenType;
  }


  public KartenEinheitType getKartenType()
  {
    return kartenType;
  }

  public String getKartenName()
  {
    return kartenName;
  }

  public int getKartenLevel()
  {
    return kartenLevel;
  }

  public void setKartenLevel(int kartenLevel)
  {
    this.kartenLevel = kartenLevel;
  }

  public String getKartenKlasse()
  {
    return kartenKlasse;
  }

  public int getKartenMacht()
  {
    return kartenMacht;
  }

  public void setKartenMacht(int kartenMacht)
  {
    this.kartenMacht = kartenMacht;
  }

  public int getKartenLebenspunkte()
  {
    return kartenLebenspunkte;
  }

  public void setKartenLebenspunkte(int kartenLebenspunkte)
  {
    this.kartenLebenspunkte = kartenLebenspunkte;
  }

  public int getKartenMana()
  {
    return kartenMana;
  }

  public void setKartenMana(int kartenMana)
  {
    this.kartenMana = kartenMana;
  }

  public int getKartenBeweglichkeit()
  {
    return kartenBeweglichkeit;
  }

  public void setKartenBeweglichkeit(int kartenBeweglichkeit)
  {
    this.kartenBeweglichkeit = kartenBeweglichkeit;
  }

  public int getKartenReichweite()
  {
    return kartenReichweite;
  }

  public void setKartenReichweite(int kartenReichweite)
  {
    this.kartenReichweite = kartenReichweite;
  }

  public Effekt getKartenEffekt()
  {
    return kartenEffekt;
  }

  public String getKlasse()
  {
    return this.kartenKlasse;
  }
}

