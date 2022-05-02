package Alex;

public class KarteEinheit extends Karte
{
  private KartenEinheitType type;
  private int level = 0;

  private Effekt effekt;

  public KarteEinheit(KartenEinheitType type)
  {
    this.level = type.getLevel();
    this.effekt=new Effekt(type.getEffekt().getTyp(), type.getEffekt().getSchaden());
  }

  public KartenEinheitType gettype()
  {
    return this.type;
  }

  public void setType(KartenEinheitType type)
  {
    this.type = type;
  }

  public Effekt getEffekt()
  {
    return effekt;
  }

}

