package control.test;

import control.CharakterController;
import io.KartenDeckIO;
import io.SpielStandIO;
import exceptions.KartenDeckFehlerhaftException;
import model.*;
import resources.*;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import static resources.Artefakte.*;
import static resources.Talente.*;

public class Keno
{
    public static void ausfuehren()
    {
    }

    private static void waehleCharakter()
    {
        try
        {
            Charakter meineKlasse = CharakterController.leseCharakter(0);
            System.out.println(meineKlasse.getStartDeck());
        }
        catch (IOException | KartenDeckFehlerhaftException e)
        {
            System.out.println(e.getMessage());
        }
    }


}
