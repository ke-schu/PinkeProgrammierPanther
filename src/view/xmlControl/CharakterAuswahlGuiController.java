package view.xmlControl;

import control.SpielStandController;
import exceptions.JsonNichtLesbarException;
import exceptions.NichtGenugGoldException;
import view.components.CharakterVBox;
import utility.CharakterIO;
import utility.KonsolenIO;
import utility.SpielStandIO;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import static resources.GuiKonstanten.*;

/**
 * Klasse, welche alle Methoden der CharakterAuswahl Szene enthaelt.
 */
public class CharakterAuswahlGuiController
        extends GuiController
        implements Initializable
{
    @FXML HBox charaktere;
    @FXML VBox kartenDeck;
    @FXML Button spielButton;
    @FXML Label gold;
    ObjectProperty<Charakter> aktiverCharakter = new SimpleObjectProperty<>();
    private Stack<Charakter> charakterStack;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        spielButton.setDisable(true);
        try
        {
            charakterStack = CharakterIO.leseDatei();
            spiel = SpielStandIO.leseDatei();
            for (int i = 0; i < charakterStack.size(); i++)
            {
                Charakter meinChar = new Charakter(charakterStack.get(i));
                CharakterVBox meineBox = new CharakterVBox(meinChar);
                charaktere.getChildren().add(meineBox);
                updateGewaehlt(meineBox, meinChar, i);
            }
        } catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

        aktiverCharakter.addListener(
                (observableValue, charakter, t1) -> kartenDeckSetzen());

        gold.setText(GOLD_BESTAND + spiel.getGold());
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

    private void updateGewaehlt(CharakterVBox v,  Charakter c, int pos)
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
                                    if(kaufen(v, pos))
                                    {
                                        aktiverCharakter.bind(dieserCharakter);
                                    }
                                }
                            });

        aktiverCharakter.addListener((observableValue, charakter, t1) ->
                                             v.setGewaehlt(aktiverCharakter.get() == dieserCharakter.get()));
    }

    private boolean kaufen(CharakterVBox v, int pos)
    {
        try
        {
            SpielStandController.charakterKaufen(charakterStack, pos, spiel);
            gold.setText(GOLD_BESTAND + spiel.getGold());
            v.setFreigeschaltet(true);
            return true;
        }
        catch (NichtGenugGoldException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
            return false;
        }
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

    /**
     * Methode oeffneHilfe wird ueberlagert, hierbei wird der String mit dem Text des Popups geaendert.
     * @param event ActionEvent, welches dieser mit diese Methode verknuepft wird.
     */
    @Override
    public void oeffneHilfe(ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_CHARAKTERAUSWAHL);
    }
}