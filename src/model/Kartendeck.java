package model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Kartendeck extends ArrayList<Karte>
{
    private String name;
    private Random zufallsGenerator = new Random();
    private Gson meinGson = new Gson();

    public Kartendeck (String name)
    {
        super();
        this.name = name;
    }

    public void mischen ()
    {
        Collections.shuffle(this, zufallsGenerator);
    }

    public String serialisieren ()
    {
        return meinGson.toJson(this);
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
