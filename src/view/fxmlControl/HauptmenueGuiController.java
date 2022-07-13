package view.fxmlControl;

import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.spielStandIO;
import static resources.StringsGUI.HILFE_HAUPTMENUE;

/**
 Klasse, welche alle Methoden der Hautpmenue Szene enthaelt. */
public class HauptmenueGuiController extends GuiController
{
    @FXML Button fortsetzten;
    
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        try
        {
            spiel = spielStandIO.leseSpielstand();
            if (spiel.getSpieler().getLebenspunkte() <= 0)
            {
                fortsetzten.setDisable(true);
            }
        }
        catch (JsonNichtLesbarException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     Methode oeffneHilfe wird ueberlagert, hierbei wird der String mit dem
     Text
     des Popups geaendert.
     @param event ActionEvent, welches mit dieser Methode verknuepft wird.
     */
    @Override public void oeffneHilfe (ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_HAUPTMENUE);
    }
    
    /**
     Methode, welche die Methode wechselZuSpielEbene() aufruft.
     @param event Event, welche diese Methode ausloest.
     @throws IOException Wird weiter gereicht.
     */
    @FXML public void weiterSpielen (ActionEvent event) throws IOException
    {
        wechselZuSpielEbene(event);
    }
}


