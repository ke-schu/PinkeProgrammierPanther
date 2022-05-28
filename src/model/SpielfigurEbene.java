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
        this.position.setX(SPIELFIGUR_EBENE_STARTPOSITION_X);
        this.position.setY(SPIELFIGUR_EBENE_STARTPOSITION_Y);
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
     * Diese Methode setzt die Attribute der Instanz von Position, welche in
     * im Attribut position hinterlegt ist.
     * @param x Int-Wert der in das Attribut der Instanz von Position im
     * Attribut position gesetzt wird.
     * @param y Int-Wert der in das Attribut der Instanz von Position im
     * Attribut position gesetzt wird.
     */
    public void setPosition(int x, int y)
    {
        this.position.setX(x);
        this.position.setY(y);
    }
}
