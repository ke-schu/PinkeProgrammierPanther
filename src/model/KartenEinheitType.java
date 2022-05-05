package model;

public enum KartenEinheitType
{
    EricKarte("Eric", 1, "Nahkaempfer", 1, 1, 2, 1, 1, EffektTyp.LETZTEWORTE),
    KennyKarte("Kenny", 1, "Nahkaempfer", 1, 1, 2, 1, 1,EffektTyp.LETZTEWORTE),
    KyleKarte("Kyle", 1, "Fernkaempfer", 1, 1, 2, 1, 2,EffektTyp.LETZTEWORTE),
    StanKarte("Stan", 1, "Fernkaempfer", 1, 1, 2, 1, 2,EffektTyp.LETZTEWORTE);

    private final String NAME;
    private int level;
    private final String KLASSE;
    private int macht;
    private int lebenspunkte;
    private int mana;
    private int beweglichkeit;
    private int reichweite;
    private  EffektTyp effekt;


    KartenEinheitType(String name, int level, String klasse, int macht, int lebenspunkte, int mana, int beweglichkeit, int reichweite, EffektTyp effekt)
    {
        this.NAME = name;
        this.level = level;
        this.KLASSE = klasse;
        this.macht = macht;
        this.lebenspunkte = lebenspunkte;
        this.mana = mana;
        this.beweglichkeit = beweglichkeit;
        this.reichweite = reichweite;
        this.effekt = effekt;
    }

    public String getName()
    {
        return this.NAME;
    }

    public int getLevel()
    {
        return this.level;
    }

    public String getKlasse()
    {
        return this.KLASSE;
    }

    public int getMacht()
    {
        return this.macht;
    }

    public int getLebensPunkte()
    {
        return this.lebenspunkte;
    }

    public int getMana()
    {
        return this.mana;
    }

    public int getBeweglichkeit()
    {
        return this.beweglichkeit;
    }

    public int getReichweite()
    {
        return this.reichweite;
    }

    public EffektTyp getEffekt()
    {
        return effekt;
    }
}
