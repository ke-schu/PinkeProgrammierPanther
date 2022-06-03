package gui.xml;

import control.SpielStandController;
import exceptions.JsonNichtLesbarException;
import gui.CharakterVBox;
import io.CharakterIO;
import io.KonsolenIO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Charakter;

import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.HILFE_CHARAKTERAUSWAHL;

public class CharakterAuswahlGuiController
        extends GuiController
        implements Initializable
{
    @FXML
    HBox charaktere;
    @FXML
    VBox kartenDeck;
    @FXML
    Button spielButton;
    ObjectProperty<Charakter> aktiverCharakter = new SimpleObjectProperty<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        String cssPfad = this.getClass().getResource("Charakter.css").toExternalForm();
        charaktere.getStylesheets().add(cssPfad);
        kartenDeck.getStylesheets().add(cssPfad);

        spielButton.setDisable(true);

        try
        {
            for (int i = 0; i < CharakterIO.leseDatei().size(); i++)
            {
                charaktere.getChildren().add(einfuegenCharakter(
                        CharakterIO.leseCharakter(i)));
            }
        } catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

        aktiverCharakter.addListener(
                (observableValue, charakter, t1) -> kartenDeckSetzen());
    }

    private void kartenDeckSetzen()
    {
        if (aktiverCharakter.isBound())
        {
            spielButton.setDisable(false);
        }
        kartenDeck.getChildren().clear();
        Label deckBezeichnung = new Label(
                aktiverCharakter.get().getStartDeck().getDeckBezeichnung());
        deckBezeichnung.setFont(
                Font.font("Arial", FontWeight.EXTRA_BOLD, 16));
        kartenDeck.getChildren().add(deckBezeichnung);
        for (int i = 0; i < aktiverCharakter.get().getStartDeck().size(); i++)
        {
            kartenDeck.getChildren()
                    .add(einfuegenKarte(aktiverCharakter.get(), i));
        }
    }

    private Node einfuegenKarte(Charakter charakter, int i)
    {
        return new Label(charakter.getStartDeck().get(i).getName());
    }

    private VBox einfuegenCharakter(Charakter meinCharakter)
    {
        CharakterVBox v = new CharakterVBox();

        v.getChildren()
                .add(new Label(meinCharakter.getName()));
        v.getChildren()
                .add(new Label(String.valueOf(meinCharakter.getFreischaltgebuehr())));
        v.getChildren()
                .add(new Label(String.valueOf(meinCharakter.getFreigeschaltet())));

        ObjectProperty<Charakter> charakterProperty =
                new SimpleObjectProperty<>(meinCharakter);

        v.setOnMouseClicked(
                mouseEvent -> aktiverCharakter.bind(charakterProperty));

        aktiverCharakter.addListener((observableValue, charakter, t1) ->
        {
            if (aktiverCharakter.get() == charakterProperty.get())
                v.setGewaehlt(true);
            else
                v.setGewaehlt(false);
        });

        return v;
    }

    public void spielen(ActionEvent event)
    {
        SpielStandController.spielErstellen(aktiverCharakter.get());
        //wechselZuSpielEbene(event);
    }

    @Override
    public void oeffneHilfe(ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_CHARAKTERAUSWAHL);
    }
}
