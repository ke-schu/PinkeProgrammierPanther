package gui.xml;

import gui.mp3Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import static gui.GuiKonstanten.*;
import static gui.GuiKonstanten.AUFLOESUNG_HOEHE_HD;

public class EinstellungenGuiController extends GuiController
{
    @FXML
    private Slider lautstaerkeSlider;
    @FXML
    private ComboBox FenstergroesseBox;
    @FXML
    private Label lautstaerkeLabel;

    private String[] aufloesungsgroessen = {"1280x720", "1920x1080"};
    private int lautstaerke=0;


    public void initialize()
    {
        erstelleCombobox(aufloesungsgroessen,FenstergroesseBox);
        FenstergroesseBox.setOnAction(this::wechselFenstergroesse);

        lautstaerkeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lautstaerke = (int)lautstaerkeSlider.getValue();
                mp3Controller.wechselLautstaerke(lautstaerke);
            }
        });
    }





    @Override
    public void oeffneHilfe(ActionEvent event) {
        offneHilfeTextEinsetzen(HILFE_EINSTELLUNGEN);
    }

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
