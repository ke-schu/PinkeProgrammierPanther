package model;

import static resources.Konstanten.*;

/**
 * Eine Instanz dieser Klasse soll sich durch das Attribut ebene der Klasse
 * Ebene bewegt werden.
 */
public class SpielfigurEbene
{
    private Position position = new Position();

    /**
     * Konstruktor der die Werte des Attributes Position direkt in den
     * Startraum jeder Ebene initialisiert.
     */
    public SpielfigurEbene()
    {
        this.position.setX(SPIELFIGUR_EBENE_STARTPOSITION.getX());
        this.position.setY(SPIELFIGUR_EBENE_STARTPOSITION.getY());
    }

    /**
     * Methode die das Attribut position wiedergibt.
     * @return gibt das die Instanz des Attributes position wieder.
     */
    public Position getPosition()
    {
        return position;
    }

    /**
     * Gibt das Attribut x der Instanz der Klasse Position, welche im Attribut
     * position liegt, wieder.
     * @return gibt den Int-Wert der im Attribut position hinterlegten Instanz
     * der Klasse Position.
     */
    public int getPosition_x()
    {
        return this.position.getX();
    }

    /**
     * Gibt das Attribut y der Instanz der Klasse Position, welche im Attribut
     * position liegt, wieder.
     * @return gibt den Int-Wert der im Attribut position hinterlegten Instanz
     * der Klasse Position.
     */
    public int getPosition_y()
    {
        return this.position.getY();
    }

    /**
     * Setzt die aktuelle Position an eine andere Position
     * @param position die neue Position
     */
    public void setPosition(Position position)
    {
        this.position = position;
    }
}
