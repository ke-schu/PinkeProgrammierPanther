package gui.xml;

import control.SpielfigurEbeneController;
import exceptions.JsonNichtLesbarException;
import gui.modelFx.RaumPane;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Ebene;
import model.Position;
import model.Raum;
import model.SpielStand;

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
                spielebenenGitter.addRow(0);
            }

            for(int i = 0; i < ebene.getEbenenSpalte(); i++)
            {
                spielebenenGitter.addColumn(0);
                for(int j = 0; j < ebene.getEbenenZeile(); j++)
                {
                    raumEinfuegen(ebene, i, j);
                }
            }
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    private void raumEinfuegen(Ebene ebene, int x, int y)
    {
        Raum aktuellerRaum = ebene.getRaumAnPosition(x, y);
        RaumPane raum = new RaumPane();
        if(aktuellerRaum == null)
        {
            raum.setNichtig(true);
        }
        else if (aktuellerRaum.getEreignis() == null)
        {
        }
        else
        {
            raum.getChildren().add(new Label(aktuellerRaum.getEreignis().getName()));
        }
        Position spielerPosition = ebene.getSpielfigur().getPosition();
        Position aktuellePosition = new Position(x, y);
        raum.setBeinhaltetSpieler(spielerPosition.equals(aktuellePosition));
        spielebenenGitter.add(raum, x, y);

        raum.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override public void handle(MouseEvent mouseEvent)
            {
                SpielfigurEbeneController.bewegen(ebene, x, y, spiel);
            }
        });
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
