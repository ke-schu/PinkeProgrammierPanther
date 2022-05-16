package control;
import control.test.*;

/**
 * In dieser Klasse startet das Programm.
 */
public class Main
{
    /**
     * Diese main-Methode wird beim Start des Programms aufgerufen.
     * @param args mitgegebene Kommandozeilenargumente.
     */
    public static void main (String[] args)
    {
        teste();
    }

    /**
     * Ruft die verschiedenen Testmethoden auf.
     */
    private static void teste()
    {
        switch(2)
        {
            case 1: Alex.ausfuehren();
            break;
            case 2: EbeneTest.testeEbene();
            break;
            case 3: SpielStandTest.schreibeCharacter();
            case 4: SpielStandTest.speichereSpielstand();
            case 5: SpielStandTest.leseSpielstand();
            break;
            case 6: KartenDeckTest.erstelleDeck();
            case 7: KartenDeckTest.leseDeck();
        }
    }
}