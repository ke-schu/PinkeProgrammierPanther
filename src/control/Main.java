package control;
import control.test.Alex;
import control.test.Hendrik;
import control.test.Keno;
import exceptions.KartenDeckFehlerhaftException;
import exceptions.SpielfeldDimensionGleichNullException;
import exceptions.SpielfeldNichtQuadratischException;
import model.*;
import java.io.File;
import java.io.IOException;
import static model.KartenEinheitType.*;

public class Main
{
    public static void main (String[] args) throws SpielfeldNichtQuadratischException
    {
        testen();
    }

    private static void testen()
    {
        switch(3)
        {
            case 1: Alex.ausfuehren();
            case 2: Keno.ausfuehren();
            case 3: Hendrik.ausfuehren();
        }
    }
}