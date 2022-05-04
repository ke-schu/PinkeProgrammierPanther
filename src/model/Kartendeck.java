package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Kartendeck extends ArrayList<Karte>
{
    private Random zufallsGenerator = new Random();

    public Kartendeck ()
    {
        super();
    }

    public void mischen ()
    {
        Collections.shuffle(this, zufallsGenerator);
    }

    public String toString ()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++)
        {
            sb.append(this.get(i).toString());
            sb.append("\t\t");
        }
        return sb.toString();
    }
}
