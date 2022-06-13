package control;

import control.test.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utility.KonsolenIO;
import view.mp3Controller;

import java.io.File;
import java.io.IOException;

import static resources.GuiKonstanten.*;

/**
 * In dieser Klasse startet das Programm.
 */
public class Main extends Application
{
    /**
     * Diese main-Methode wird beim Start des Programms aufgerufen.
     * @param args mitgegebene Kommandozeilenargumente.
     */
    public static void main(String[] args)
    {
        launch();
    }

    /**
     * Mit dieser Methode wird die GUI in einem neuen Window geoeffnet.
     * @param stage die Buehne des Programms
     */
    @Override
    public void start(Stage stage)
    {
        File f = new File(HAUPTMENUE_PFAD);
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle(SPIELTITEL);
            stage.setScene(scene);
            // Setze Aufloesung beim Starten
            stage.setMaxHeight(AUFLOESUNG_HOEHE_HD);
            stage.setMinHeight(AUFLOESUNG_HOEHE_HD);
            stage.setMaxWidth(AUFLOESUNG_BREITE_HD);
            stage.setMinWidth(AUFLOESUNG_BREITE_HD);
            stage.setResizable(false);
            stage.getIcons().add(new Image(ICON.getAbsolutePath()));
            stage.show();
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
        mp3Controller.spieleHintergrundmusik();
    }

    /**
     * Ruft die verschiedenen Testmethoden auf.
     */
    private static void teste()
    {
        switch (2)
        {
            case 1:
                SpielzugTest.spielzugTesten();
            case 2:
                EbeneTest.testeEbene();
            case 3:
                SpielStandTest.schreibeCharacter();
            case 4:
                SpielStandTest.speichereSpielstand();
            case 5:
                SpielStandTest.leseSpielstand();
            case 6:
                KartenDeckTest.erstelleDeck();
            case 7:
                KartenDeckTest.leseDeck();
                break;
            default:
                return;
        }
    }
}