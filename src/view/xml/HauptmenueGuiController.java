package view.xml;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

import static view.GuiKonstanten.HILFE_HAUPTMENUE;

/**
 * Klasse, welche alle Methoden der Hautpmenue Szene enthaelt.
 */
public class HauptmenueGuiController extends GuiController
{
    /**
     * Methode oeffneHilfe wird ueberlagert, hierbei wird der String mit dem Text des Popups geaendert.
     * @param event ActionEvent, welches mit dieser Methode verknuepft wird.
     */
    @Override
    public void oeffneHilfe(ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_HAUPTMENUE);
    }

    @FXML
    public void weiterSpielen (ActionEvent event) throws IOException
    {
        wechselZuSpielEbene(event);
    }
}


