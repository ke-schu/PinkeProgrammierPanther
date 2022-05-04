package model;

import utilities.MeinInput;

import java.io.IOException;
import java.util.Arrays;

public class Charakterklasse
{
    private String name;
    private int preis;
    private String held;
    private final int anzahlDecks = 3;
    private Kartendeck deck[] = new Kartendeck[anzahlDecks];

    public Charakterklasse() {

    }

    @Override
    public String toString()
    {
        return "Charakterklasse{" +
                "name='" + name + '\'' +
                ", preis=" + preis +
                ", held='" + held + '\'' +
                ", kartenblaetter=" + deck +
                '}';
    }
}
