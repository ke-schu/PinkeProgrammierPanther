package gui.xml;

import control.SpielStandController;
import exceptions.JsonNichtLesbarException;
import exceptions.NichtGenugGoldException;
import gui.modelFx.CharakterVBox;
import io.CharakterIO;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import model.SpielStand;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.*;

public class CharakterAuswahlGuiController
        extends GuiController
        implements Initializable
{
    @FXML HBox charaktere;
    @FXML VBox kartenDeck;
    @FXML Button spielButton;
    @FXML Label gold;
    ObjectProperty<Charakter> aktiverCharakter = new SimpleObjectProperty<>();
    private SpielStand spiel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        spielButton.setDisable(true);
        try
        {
            spiel = SpielStandIO.leseDatei();
            gold.setText(GOLD_BESTAND + spiel.getGold());
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

        Label name = new Label(meinCharakter.getName());
        name.setId(STYLE_CHARAKTER_NAME);
        v.getChildren().add(name);

        updateFreigeschaltet(v, meinCharakter);
        updateGewaehlt(v, meinCharakter);

        return v;
    }

    private void updateFreigeschaltet(CharakterVBox v,  Charakter c)
    {
        v.setFreigeschaltet(c.getFreigeschaltet());
        if(!v.istFreigeschaltet())
        {
            v.getChildren()
             .add(new Label(String.format(CHARAKTER_KAUFEN, c.getFreischaltgebuehr())));
        }
        else
        {
            v.getChildren()
             .add(new Label(SCHON_FREIGESCHALTET));
        }
    }

    private void updateGewaehlt(CharakterVBox v,  Charakter c)
    {
        ObjectProperty<Charakter> dieserCharakter =
                new SimpleObjectProperty<>(c);

        v.setOnMouseClicked(mouseEvent ->
                            {
                                if(v.istFreigeschaltet())
                                {
                                    aktiverCharakter.bind(dieserCharakter);
                                }
                                else
                                {
                                    kaufen(dieserCharakter.get());
                                }
                            });

        aktiverCharakter.addListener((observableValue, charakter, t1) ->
                                             v.setGewaehlt(aktiverCharakter.get() == dieserCharakter.get()));
    }

    private void kaufen(Charakter charakter)
    {
        //SpielStandController.charakterKaufen(charakter, spiel);
    }

    public void spielen(ActionEvent event)
    {
        SpielStandController.spielErstellen(aktiverCharakter.get(), spiel);
        try
        {
            wechselZuSpielEbene(event);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void oeffneHilfe(ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_CHARAKTERAUSWAHL);
    }
}
