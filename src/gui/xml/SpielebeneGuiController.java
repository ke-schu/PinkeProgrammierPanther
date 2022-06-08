package gui.xml;

import control.SpielfigurEbeneController;
import exceptions.JsonNichtLesbarException;
import gui.modelFx.RaumPane;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private ObjectProperty<Position> spielerPosition =
            new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            spiel = SpielStandIO.leseDatei();
            spielerLabel.setText(spiel.getSpieler().getName());
            Ebene ebene = spiel.getAktuelleEbene();

            for (int i = 0; i < ebene.getEbenenZeile(); i++)
            {
                spielebenenGitter.addRow(0);
            }

            for (int i = 0; i < ebene.getEbenenSpalte(); i++)
            {
                spielebenenGitter.addColumn(0);
                for (int j = 0; j < ebene.getEbenenZeile(); j++)
                {
                    raumEinfuegen(ebene, i, j);
                }
            }
            spielerPosition.set(ebene.getSpielfigur().getPosition());
        } catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    private void raumEinfuegen(Ebene ebene, int x, int y)
    {
        Raum aktuellerRaum = ebene.getRaumAnPosition(x, y);
        RaumPane raum = new RaumPane();
        if (aktuellerRaum == null)
        {
            raum.setNichtig(true);
        } else if (aktuellerRaum.getEreignis() == null)
        {
        } else
        {
            raum.getChildren()
                .add(new Label(aktuellerRaum.getEreignis().getName()));
        }

        ObjectProperty<Position> aktuellePosition =
                new SimpleObjectProperty<>(new Position(x, y));
        spielerPosition.addListener(
                (observableValue, position, t1) -> raum.setBeinhaltetSpieler(
                        spielerPosition.get().equals(aktuellePosition.get())));

        raum.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override public void handle(MouseEvent mouseEvent)
            {
                if(SpielfigurEbeneController.bewegen(ebene, x, y, spiel))
                {
                    spielerPosition.bind(aktuellePosition);
                }
            }
        });

        spielebenenGitter.add(raum, x, y);
    }

    @FXML
    public void spielSpeichern(ActionEvent event)
    {
        try
        {
            SpielStandIO.schreibeDatei(spiel);
        } catch (IOException e)
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
        } catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
