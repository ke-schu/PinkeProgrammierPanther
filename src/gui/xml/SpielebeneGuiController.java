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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Ebene;
import model.Position;
import model.Raum;
import model.SpielStand;
import model.ereignisse.Ereignis;
import model.ereignisse.Truhe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.*;

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
                    oeffneEreignis(ebene.getRaumAnPosition(x, y).getEreignis());
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

    /**
     * Diese Methode oeffnet ein Pop-Up Fenster auf der Spielebene, welches das Ereignis repraesentiert
     * @param ereignis das zu oeffnende Ereignis
     */
    public void oeffneEreignis (Ereignis ereignis)
    {

        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(ereignis.getName());
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(20);
        TextArea text = new TextArea();
        text.setText(ereignis.getBeschreibung());
        text.setWrapText(true);
        text.setEditable(false);
        vbox.getChildren().add(text);
        Scene popupScene = new Scene(vbox, 700, 500);
        Button annehmenButton = new Button(EREIGNIS_ANNEHMEN);
        Button ablehnenButton = new Button(EREIGNIS_ABLEHNEN);
        annehmenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                ereignis.setAuswahl(true);
                ereignis.ausfuehren(spiel);
                ereignisGuiAusfuehren(ereignis);
                popupStage.close();
            }
        });
        ablehnenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                ereignis.setAuswahl(false);
                popupStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(annehmenButton);
        vbox.getChildren().add(ablehnenButton);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }

    public void ereignisGuiAusfuehren (Ereignis ereignis)
    {
        if(ereignis instanceof Truhe)
        {
            final Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle(ereignis.getName());
            popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

            VBox vbox = new VBox(20);
            TextArea ereignisText = new TextArea();
            ereignisText.setText(ereignis.getBeschreibung());
            ereignisText.setWrapText(true);
            ereignisText.setEditable(false);
            vbox.getChildren().add(ereignisText);
            Scene popupScene = new Scene(vbox, 400, 300);
            Button gehenButton = new Button(EREIGNIS_GEHEN);
            ereignisText.setText(TRUHE_AUSFUEHREN_1 + spiel.getGold() + TRUHE_AUSFUEHREN_2);
            gehenButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent arg0)
                {
                    popupStage.close();
                }
            });
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(gehenButton);
            popupStage.setScene(popupScene);
            popupStage.setResizable(false);
            popupStage.setAlwaysOnTop(true);
            popupStage.show();
        }
    }
}
