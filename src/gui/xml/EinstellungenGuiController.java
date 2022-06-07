package gui.xml;

import gui.mp3Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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

    private int[]aufloesungsgrößen = {AUFLOESUNG_HOEHE_HD,AUFLOESUNG_HOEHE_FULLHD};
    private int lautstaerke=0;


    public void initialize()
    {
        erstelleCombobox(aufloesungsgrößen,FenstergroesseBox);
        FenstergroesseBox.setOnAction(this::wechselFenstergroesse);

        lautstaerkeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lautstaerke = (int)lautstaerkeSlider.getValue();
                lautstaerkeLabel.setText(Integer.toString(lautstaerke));
                mp3Controller.wechselLautstaerke(lautstaerke);
            }
        });
    }


    public void wechselAufloesungFullHD (Event event)
    {
        super.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_HOEHE_FULLHD);
    }

    public void wechselAufloesungHD (Event event)
    {
        super.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_BREITE_HD);
        super.getStage().setMaxHeight(AUFLOESUNG_BREITE_HD);
        super.getStage().setMinWidth(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMaxWidth(AUFLOESUNG_HOEHE_HD);
    }


    @Override
    public void oeffneHilfe(ActionEvent event) {
        offneHilfeTextEinsetzen(HILFE_EINSTELLUNGEN);
    }

    public void wechselFenstergroesse(Event event)
    {
        if (Integer.parseInt(FenstergroesseBox.getValue().toString())== AUFLOESUNG_HOEHE_HD)
        {
            wechselAufloesungHD(event);
        }
        if (Integer.parseInt(FenstergroesseBox.getValue().toString())== AUFLOESUNG_HOEHE_FULLHD)
        {
            wechselAufloesungFullHD(event);
        }
    }


}
