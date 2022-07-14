package model;

/**
 Klasse Position welche Informationen ueber die Position einer Karte auf dem
 Spielfeld in sich traegt. */
public class Position
{
    private int x;
    private int y;
    
    /**
     Leerer Konstruktor, der keine Attribute setzt.
     */
    public Position ()
    {
    }
    
    /**
     Konstruktor fuer eine Position.
     @param x Zahl der Spalte.
     @param y Zahl der Zeile.
     */
    public Position (int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     Methode welche ueberprueft ob der Spalten und Zeilenwert einer Position
     gleich sind.
     @param pos welche ueberprueft wird.
     @return boolean, ob Spalten und Zeilenwert einer Position gleich sind.
     */
    public boolean equals (Position pos)
    {
        return this.x == pos.getX() && this.y == pos.getY();
    }
    
    /**
     Getter fuer die Zahl der Spalte der Position.
     @return Spaltenzahl.
     */
    public int getX ()
    {
        return x;
    }
    
    /**
     Setter fuer die Zahl der Spalte der Position.
     @param x Spaltenzahl.
     */
    public void setX (int x)
    {
        this.x = x;
    }
    
    
    /**
     Getter fuer die Zahl der Zeile der Position.
     @return Zeilenzahl.
     */
    public int getY ()
    {
        return y;
    }
    
    /**
     Setter fuer die Zahl der Zeile der Position.
     @param y Zeilenzahl.
     */
    public void setY (int y)
    {
        this.y = y;
    }
}

