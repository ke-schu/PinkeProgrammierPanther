package gui.xml;

import exceptions.JsonNichtLesbarException;
import gui.mp3Controller;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.*;


/**
 * Klasse, welche alle Methoden der Einstellungs Szene enthaelt.
 */
public class EinstellungenGuiController extends GuiController
        implements Initializable
{
    @FXML private Slider lautstaerkeSlider;
    @FXML private ComboBox FenstergroesseBox;
    @FXML private String[] aufloesungsgroessen = {"1280x720", "1920x1080"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            spiel = SpielStandIO.leseDatei();
            lautstaerkeSlider.setValue(spiel.getLautstaerke());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

        erstelleCombobox(aufloesungsgroessen,FenstergroesseBox);
        FenstergroesseBox.setOnAction(this::wechselFenstergroesse);
        FenstergroesseBox.setPromptText(aufloesungsgroessen[0]);

        lautstaerkeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int lautstaerke = (int)lautstaerkeSlider.getValue();
                mp3Controller.wechselLautstaerke(lautstaerke);
            }
        });
    }

    /**
     * Methode oeffneHilfe wird ueberlagert, hierbei wird der String mit dem Text des Popups geaendert.
     * @param event ActionEvent, welches mit dieser Methode verknuepft wird.
     */
    @Override
    public void oeffneHilfe(ActionEvent event) {
        offneHilfeTextEinsetzen(HILFE_EINSTELLUNGEN);
    }

    /**
     * Methode um die Fenstergroesse zu aendern, ausgehend von der aktuellen Groesse.
     * @param event ActionEvent, welches diese mit dieser Methode verknuepft wird.
     */
    public void wechselFenstergroesse(Event event)
    {

        if (FenstergroesseBox.getValue()== "1280x720")
        {
            wechselAufloesungHD(event);
        }
        if (FenstergroesseBox.getValue() == "1920x1080")
        {
            wechselAufloesungFullHD(event);
        }
    }
}
