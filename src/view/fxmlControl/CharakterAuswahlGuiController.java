package view.fxmlControl;

import control.SpielStandController;
import exceptions.JsonNichtLesbarException;
import exceptions.NichtGenugGoldException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Charakter;
import utility.KonsolenIO;
import view.components.CharakterVBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import static resources.Konstanten.charakterIO;
import static resources.Konstanten.spielStandIO;
import static resources.StringsGUI.GOLD_BESTAND;
import static resources.StringsGUI.HILFE_CHARAKTERAUSWAHL;

/**
 Klasse, welche alle Methoden der CharakterAuswahl Szene enthaelt. */
public class CharakterAuswahlGuiController extends GuiController
{
    @FXML HBox charaktere;
    @FXML VBox kartenDeck;
    @FXML Label kartenDeckBezeichnung;
    @FXML VBox karten;
    @FXML Button spielButton;
    @FXML Label gold;
    private ObjectProperty<Charakter> aktiverCharakter =
            new SimpleObjectProperty<>();
    private Stack<Charakter> charakterStack;
    
    /**
     Wird aufgerufen, um diesen Controller zu initialisieren.
     @param url Der Standort, der zum Aufloesen relativer Pfade für das
     Root-Objekt verwendet wird.
     @param resourceBundle Die zum Lokalisieren des Root-Objekts verwendeten
     Ressourcen.
     */
    @Override public void initialize (URL url, ResourceBundle resourceBundle)
    {
        spielButton.setDisable(true);
        try
        {
            charakterStack = charakterIO.leseCharaktere();
            spiel = spielStandIO.leseSpielstand();
            for (int i = 0; i < charakterStack.size(); i++)
            {
                Charakter meinChar = new Charakter(charakterStack.get(i));
                CharakterVBox meineBox = new CharakterVBox(meinChar);
                charaktere.getChildren().add(meineBox);
                charakterWaehlen(meineBox, meinChar, i);
            }
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
        aktiverCharakter.addListener(
                (observableValue, charakter, t1) -> initialisiereKartenDeck());
        
        gold.setText(GOLD_BESTAND + spiel.getGold());
    }
    
    /**
     Initialisiert das Kartendeck vom aktiven Charakter.
     */
    private void initialisiereKartenDeck ()
    {
        if (aktiverCharakter.isBound())
        {
            spielButton.setDisable(false);
        }
        karten.getChildren().clear();
        kartenDeckBezeichnung.setText(
                aktiverCharakter.get().getStartDeck().getDeckBezeichnung());
        for (int i = 0; i < aktiverCharakter.get().getStartDeck().size(); i++)
        {
            String bezeichnung = (i + 1) + ". " +
                                 aktiverCharakter.get().getStartDeck().get(i)
                                                 .getName();
            karten.getChildren().add(new Label(bezeichnung));
        }
    }
    
    /**
     Fügt der Charakter VBox ein Event beim Mausklick hinzu, welches den
     Charakter auswaehlt und als aktiven Charakter setzt. Dafuer wird auch
     ueberprueft, ob er bereits freigeschaltet ist.
     @param v Die Charakter VBox
     @param c Der Charakter
     @param pos Die Position im Charakter-Stack
     */
    private void charakterWaehlen (CharakterVBox v, Charakter c, int pos)
    {
        ObjectProperty<Charakter> dieserCharakter =
                new SimpleObjectProperty<>(c);
        
        v.setOnMouseClicked(mouseEvent ->
                            {
                                if (v.istFreigeschaltet())
                                {
                                    aktiverCharakter.bind(dieserCharakter);
                                }
                                else
                                {
                                    if (kaufen(v, pos))
                                    {
                                        aktiverCharakter.bind(
                                                dieserCharakter);
                                    }
                                }
                            });
        
        aktiverCharakter.addListener(
                (observableValue, charakter, t1) -> v.setGewaehlt(
                        aktiverCharakter.get() == dieserCharakter.get()));
    }
    
    /**
     Falls ein Charakter nicht freigeschaltet ist, kann er mit dieser Methode
     gekauft werden.
     @param v die Charakter-VBox
     @param pos die Position im Charakter-Stack
     @return true, wenn er erfolgreich gekauft wurde
     */
    private boolean kaufen (CharakterVBox v, int pos)
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
    
    /**
     Methode um mit ausgewaehltem Charakter ein Spiel zu beginnen.
     @param event Event, welches diese Methode ausloest.
     */
    public void spielen (ActionEvent event)
    {
        SpielStandController.spielErstellen(aktiverCharakter.get(), spiel);
        try
        {
            wechselZuSpielEbene(event);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
    
    /**
     Methode oeffneHilfe wird ueberlagert, hierbei wird der String mit dem
     Text
     des Popups geaendert.
     @param event ActionEvent, welches dieser mit diese Methode verknuepft
     wird.
     */
    @Override public void oeffneHilfe (ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_CHARAKTERAUSWAHL);
    }
}
