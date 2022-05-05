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
   * Konstruktor der eine Instanz der Klasse KartenEinheitTyp nimmt und aus deren Attribute die Attribute der zu
   * erzeugenden KarteEinheit Klasse zieht.
   * @param kartenType Instanz der Klasse KartenEinheitType aus deren Attribute die Attribute der zu erzeugenden Instanz
   *                   gezogen werden.
   */
  public KarteEinheit(KartenEinheitType kartenType)
  {
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

  /**
   * Konstruktor der aus den angegebenen Attributen eine Instanz der Klasse KarteEinheit erstellt.
   * @param kartenType Instanz der Klasse KartenEinheitType der in das Attribut kartenType gesetzt wird.
   * @param kartenName String der in das Attribut kartenName gesetzt wird.
   * @param kartenLevel Int der in das Attribut kartenLevel gesetzt wird.
   * @param kartenKlasse String der in das Attribut kartenKlasse gesetzt wird.
   * @param kartenMacht Int der in das Attribut kartenMacht gesetzt wird.
   * @param kartenLebenspunkte Int der in das Attribut kartenLebenspunkte gesetzt wird.
   * @param kartenMana Int der in das Attribut kartenMana gesetzt wird.
   * @param kartenBeweglichkeit Int der in das Attribut kartenBeweglichkeit gesetzt wird.
   * @param kartenReichweite Int der in das Attribut kartenReichweite gesetzt wird.
   * @param kartenEffekt Instanz der Klasse Effekt der in das Attribut kartenEffekt gesetzt wird.
   */
  public KarteEinheit(KartenEinheitType kartenType, String kartenName, int kartenLevel, String kartenKlasse, int kartenMacht, int kartenLebenspunkte, int kartenMana, int kartenBeweglichkeit, int kartenReichweite, Effekt kartenEffekt)
  {
    this.kartenType = kartenType;
    this.kartenName = kartenName;
    this.kartenLevel = kartenLevel;
    this.kartenKlasse = kartenKlasse;
    this.kartenMacht = kartenMacht;
    this.kartenLebenspunkte = kartenLebenspunkte;
    this.kartenMana = kartenMana;
    this.kartenBeweglichkeit = kartenBeweglichkeit;
    this.kartenReichweite = kartenReichweite;
    this.kartenEffekt = kartenEffekt;
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

