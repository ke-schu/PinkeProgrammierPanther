package model;

import resources.Effekte;
import resources.Einheiten;

public class KarteEinheit extends Karte
{
  private final Einheiten typ;
  private int macht;
  private int lebenspunkte;
  private int manaKosten;
  private int beweglichkeit;
  private int reichweite;
  private int schild;
  private int verteidigung;
  private Effekte effektEins;
  private Effekte effektZwei;
  private Position position = new Position();
  private boolean schlafend = true;
  private InitialisierungKarteEinheit init;
  private boolean freundlich;


  public KarteEinheit(String name, int level, Einheiten typ, int macht, int lebenspunkte, int manaKosten, int beweglichkeit, int reichweite, int verteidigung, Effekte effektEins, Effekte effektZwei)
  {
    super(name, level);
    this.typ = typ;
    this.macht = macht;
    this.lebenspunkte = lebenspunkte;
    this.manaKosten = manaKosten;
    this.beweglichkeit = beweglichkeit;
    this.reichweite = reichweite;
    this.verteidigung = verteidigung;
    this.effektEins = effektEins;
    this.effektZwei = effektZwei;
  }

  public Einheiten getTyp()
  {
    return typ;
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

  public int getManaKosten()
  {
    return manaKosten;
  }

  public void setManaKosten(int manaKosten)
  {
    this.manaKosten = manaKosten;
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

  public Position getPosition()
  {
    return position;
  }

  public int getPosition_x ()
  {
    return this.position.getx();
  }

  public int getPosition_y ()
  {
    return this.position.gety();
  }


  public void setPosition(int x, int y)
  {
    this.position.setx(x);
    this.position.sety(y);
  }

  public void setPosition(Position posi)
  {
    this.position = posi;

  }

  public boolean getSchlafend()
  {
    return schlafend;
  }

  public void setSchlafend(boolean schlafend)
  {
    this.schlafend = schlafend;
  }

  public boolean getFreundlich()
  {
    return freundlich;
  }

  public void setFreundlich(boolean freundlich)
  {
    this.freundlich = freundlich;
  }

  public void schadenNehmen (int schaden)
  {
    this.lebenspunkte = lebenspunkte-schaden;
  }

  public void startwertespeichern ()
  {
    this.init = new InitialisierungKarteEinheit();
    this.init.setMacht(macht);
    this.init.setLebenspunkte(lebenspunkte);
    this.init.setBeweglichkeit(beweglichkeit);
    this.init.setReichweite(reichweite);
    this.init.setSchild(schild);
    this.init.setVerteidigung(verteidigung);
    position = null;
    schlafend = true;
  }

  public void initialisieren()
  {
    this.macht = init.getMacht();
    this.lebenspunkte = init.getLebenspunkte();
    this.beweglichkeit = init.getBeweglichkeit();
    this.reichweite = init.getReichweite();
    this.schild = init.getSchild();
    this.verteidigung = init.getVerteidigung();
    this.position = init.getPosition();
    this.schlafend = init.getSchlafend();
  }

  public InitialisierungKarteEinheit getInit()
  {
    return init;
  }

  public void setInit(InitialisierungKarteEinheit init)
  {
    this.init = init;
  }
}

