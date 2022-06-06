package gui.xml;

import exceptions.JsonNichtLesbarException;
import gui.modelFx.RaumPane;
import io.EbeneIO;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Ebene;
import model.Position;
import model.Raum;
import model.SpielStand;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SpielebeneGuiController
        extends GuiController
        implements Initializable
{
    @FXML GridPane spielebenenGitter;
    @FXML Label spielerLabel;
    private SpielStand spiel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            spiel = SpielStandIO.leseDatei();
            spielerLabel.setText(spiel.getSpieler().getName());
            Ebene ebene = spiel.getAktuelleEbene();

            for(int i = 0; i < ebene.getEbenenZeile(); i++)
            {
                spielebenenGitter.addRow(1);
            }

            for(int i = 0; i < ebene.getEbenenSpalte(); i++)
            {
                spielebenenGitter.addColumn(1);
                for(int j = 0; j < ebene.getEbenenZeile(); j++)
                {
                    Raum aktuellerRaum = ebene.getRaumAnPosition(i, j);
                    Position aktuellePosition = new Position(i, j);
                    RaumPane raum = new RaumPane();
                    if(ebene.getRaumAnPosition(i, j) == null)
                    {
                        raum.setNichtig(true);
                    }
                    else if (ebene.getRaumAnPosition(i, j).getEreignis() == null)
                    {
                    }
                    else
                    {
                        raum.getChildren().add(new Label(aktuellerRaum.getEreignis().getName()));
                    }
                    raum.setBeinhaltetSpieler(spiel.getSpieler().getPosition().equals(aktuellePosition));
                    spielebenenGitter.add(raum, i, j);
                }
            }
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    @FXML
    public void spielSpeichern(ActionEvent event)
    {
        try
        {
            SpielStandIO.schreibeDatei(spiel);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    @FXML
    public void zurueckHauptmenue(ActionEvent event)
    {
        try
        {
            wechselZuHauptmenue(event);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
