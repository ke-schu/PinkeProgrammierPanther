package model;

import utilities.MeinInput;

import java.io.IOException;
import java.util.Arrays;

public class Charakterklasse
{
    String name;
    int preis;
    String held;
    Deck[] kartenblaetter;

    public Charakterklasse() {

    }

    @Override
    public String toString()
    {
        return "Charakterklasse{" +
                "name='" + name + '\'' +
                ", preis=" + preis +
                ", held='" + held + '\'' +
                ", kartenblaetter=" + Arrays.toString(kartenblaetter) +
                '}';
    }
}
