package gui.xml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
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


    public void wechselAufloesungFullHD (ActionEvent event)
    {
        super.setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_HOEHE_FULLHD);
    }

    public void wechselAufloesungHD (ActionEvent event)
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

    public void wechslFenstergroesse (ActionEvent event)
    {

    }


}
