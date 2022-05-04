package control;

import model.Charakterklasse;
import utilities.MeinInput;

import java.io.IOException;

public class Main
{
    public static void main (String[] args)
    {
        erstelleKlasse();
    }

    public static void erstelleKlasse() {
        Charakterklasse klasse;
        {
            try
            {
                klasse = MeinInput.leseJson("src//resources//Charaktere.json");
                System.out.println("Erfolg!");
                System.out.println(klasse);
            } catch (IOException e)
            {
                System.out.println("Fehler!");
                System.out.println(e.getMessage());
            }
        }
    }
}