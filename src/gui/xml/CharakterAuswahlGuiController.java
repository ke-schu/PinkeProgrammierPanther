package gui.xml;

import control.SpielStandController;
import exceptions.JsonNichtLesbarException;
import io.CharakterIO;
import io.KonsolenIO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Charakter;
import java.net.URL;
import java.util.ResourceBundle;

public class CharakterAuswahlGuiController
        extends GuiContoller
        implements Initializable
{
    @FXML HBox charaktere;
    @FXML VBox kartenDeck;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            for (int i = 0; i < CharakterIO.leseDatei().size(); i++)
            {
                charaktere.getChildren().add(einfuegenCharakter(SpielStandController.leseCharakter(i)));
            }
            kartenDeckAktualisieren(SpielStandController.leseCharakter(0));
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    private void kartenDeckAktualisieren(Charakter aktiverCharakter)
    {
        kartenDeck.setSpacing(20);
        final int padding = 20;
        kartenDeck.setPadding(new Insets(padding, padding, padding, padding));
        kartenDeck.getChildren().clear();
        Label deckBezeichnung = new Label(aktiverCharakter.getStartDeck().getDeckBezeichnung());
        deckBezeichnung.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 16));
        kartenDeck.getChildren().add(deckBezeichnung);
        for (int i = 0; i < aktiverCharakter.getStartDeck().size(); i++)
        {
            kartenDeck.getChildren().add(einfuegenKarte(aktiverCharakter, i));
        }
    }

    private Node einfuegenKarte(Charakter charakter, int i)
    {
        return new Label(charakter.getStartDeck().get(i).getName());
    }

    private VBox einfuegenCharakter(Charakter charakter)
    {
        VBox v = new VBox();
        v.setBackground(Background.fill(Color.WHITE));
        v.setSpacing(20);
        final int padding = 20;
        v.setPadding(new Insets(padding, padding, padding, padding));

        v.getChildren().add(new Label(charakter.getName()));
        v.getChildren().add(new Label(String.valueOf(charakter.getFreischaltgebuehr())));
        v.getChildren().add(new Label(String.valueOf(charakter.getFreigeschaltet())));
        Button waehlen = new Button("Auswählen");
        v.getChildren().add(waehlen);

        v.setOnMouseEntered(new EventHandler<>() {
            @Override public void handle(MouseEvent mouseEvent)
            {
                kartenDeckAktualisieren(charakter);
                v.setBackground(Background.fill(Color.VIOLET));
            }
        });

        v.setOnMouseExited(new EventHandler<>() {
            @Override public void handle(MouseEvent mouseEvent)
            {
                v.setBackground(Background.fill(Color.WHITE));
            }
        });

        return v;
    }
}
