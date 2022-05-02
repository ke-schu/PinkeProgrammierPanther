package Alex;

public class Einheit
{
    private KartenEinheitType type;
    private int hp;

   public Einheit (KarteEinheit karte)
    {
        this.type = karte.gettype();
        this.hp=karte.gettype().getLebensPunkte();
    }
public void schadenNehmen(int schaden){
       hp=hp-schaden;
    }




}
