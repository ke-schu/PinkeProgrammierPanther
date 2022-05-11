package model;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import resources.Artefakte;
import resources.Talente;

import java.util.Stack;

import static resources.Strings.*;

public class SpielStand
{
    private int guthaben;
    private int levelSpieler;
    private transient KartenDeck spieldeckSpieler;
    private transient KartenDeck spieldeckGegner;
    private Stack<Talente> talenteSpieler;
    private Stack<Talente> talenteGegner;
    private Artefakte[] artefakteSpieler;
    private Level level;
    private int position;

    public SpielStand(int guthaben,
                      int levelSpieler,
                      Stack<Talente> talenteSpieler,
                      Stack<Talente> talenteGegner,
                      Artefakte[] artefakteSpieler,
                      Level level,
                      int position) throws KartenDeckFehlerhaftException
    {
        this.guthaben = guthaben;
        this.levelSpieler = levelSpieler;
        this.talenteSpieler = talenteSpieler;
        this.talenteGegner = talenteGegner;
        this.artefakteSpieler = artefakteSpieler;
        this.level = level;
        this.position = position;
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
    }

    public SpielStand (SpielStand stand) throws KartenDeckFehlerhaftException
    {
        this(stand.getGuthaben(),
                stand.getLevelSpieler(),
                stand.getTalenteSpieler(),
                stand.getTalenteGegner(),
                stand.getArtefakteSpieler(),
                stand.getLevel(),
                stand.getPosition());
        this.spieldeckSpieler = KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD);
        this.spieldeckGegner = KartenDeckIO.leseDatei(SPIEL_DECK_GEGNER_PFAD);
    }

    public int getGuthaben()
    {
        return guthaben;
    }

    public void setGuthaben(int guthaben)
    {
        this.guthaben = guthaben;
    }

    public int getLevelSpieler()
    {
        return levelSpieler;
    }

    public void setLevelSpieler(int levelSpieler)
    {
        this.levelSpieler = levelSpieler;
    }

    public KartenDeck getSpieldeckSpieler()
    {
        return spieldeckSpieler;
    }

    public void setSpieldeckSpieler(KartenDeck spieldeckSpieler)
    {
        this.spieldeckSpieler = spieldeckSpieler;
    }

    public KartenDeck getSpieldeckGegner()
    {
        return spieldeckGegner;
    }

    public void setSpieldeckGegner(KartenDeck spieldeckGegner)
    {
        this.spieldeckGegner = spieldeckGegner;
    }

    public Stack<Talente> getTalenteSpieler()
    {
        return talenteSpieler;
    }

    public void setTalenteSpieler(Stack<Talente> talenteSpieler)
    {
        this.talenteSpieler = talenteSpieler;
    }

    public Stack<Talente> getTalenteGegner()
    {
        return talenteGegner;
    }

    public void setTalenteGegner(Stack<Talente> talenteGegner)
    {
        this.talenteGegner = talenteGegner;
    }

    public Artefakte[] getArtefakteSpieler()
    {
        return artefakteSpieler;
    }

    public void setArtefakteSpieler(Artefakte[] artefakteSpieler)
    {
        this.artefakteSpieler = artefakteSpieler;
    }

    public Level getLevel()
    {
        return level;
    }

    public void setLevel(Level level)
    {
        this.level = level;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }
}
