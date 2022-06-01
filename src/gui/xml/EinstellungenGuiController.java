package gui.xml;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import static gui.GuiKonstanten.*;
import static gui.GuiKonstanten.AUFLOESUNG_HOEHE_HD;

public class EinstellungenGuiController extends GuiController
{
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
}
