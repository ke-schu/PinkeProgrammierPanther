package control;

import exceptions.JsonNichtLesbarException;
import io.CharakterIO;
import io.KonsolenIO;
import io.SpielStandIO;
import model.Charakter;
import resources.Artefakte;

import java.util.Stack;

import static resources.Artefakte.SCHUTZENGEL;

/**
 * Beinhaltet verschiedene Methoden, die an und mit Charakteren arbeiten.
 */
public class SpielStandController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private SpielStandController()
    {
    }

    /**
     * Liest einen Charakter aus der Auswahl an Charakteren ein.
     * @param position die Position in der Liste an Charakteren
     * @return den Charakter
     * @throws JsonNichtLesbarException wenn ein Fehler beim Einlesen auftritt.
     */
    public static Charakter leseCharakter(int position)
            throws JsonNichtLesbarException
    {
        Stack meineCharaktere = CharakterIO.leseDatei();
        Charakter meinCharakter =
                new Charakter((Charakter) meineCharaktere.get(position));
        return meinCharakter;
    }

    public static void sterben()
    {
        /*if(ArtefaktController.hatArtefakt(SCHUTZENGEL, SpielStandIO.leseDatei().getSpieler()))
        {

        }*/
    }
}