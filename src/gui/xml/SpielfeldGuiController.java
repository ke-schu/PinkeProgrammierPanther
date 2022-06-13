package gui.xml;

import exceptions.JsonNichtLesbarException;
import gui.components.KarteVBox;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.ereignisse.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.*;
import static resources.Konstanten.HANDGROESSE;

public class SpielfeldGuiController
        extends GuiController
        implements Initializable
{
    @FXML
    GridPane spielfeldGitter;
    @FXML
    GridPane kartenhandGitter;
    @FXML
    MenuBar menueLeiste;
    private SpielFeld spielfeld;
    private KartenDeck spieldeck;
    private KartenHand kartenhand;
    private Spieler spieler;
    private final int FELDGROESSE = 80;
    private final int KARTENHAND_GROESSE = 100;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
            spielfeldhintergrundfestlegen();
            erstellenspielfeldumgebung();
            Label spielerLabel = new Label();
            spielerLabel.setText(spiel.getSpieler().getName());

            for (int i = 0; i < spielfeld.getZeilen(); i++)
            {
                spielfeldGitter.addRow(0);
            }

            for (int i = 0; i < spielfeld.getSpalten(); i++)
            {
                spielfeldGitter.addColumn(0);
                for (int j = 0; j < spielfeld.getZeilen(); j++)
                {
                    StackPane feld = new StackPane();
                    feld.setPrefWidth(FELDGROESSE);
                    feld.setPrefHeight(FELDGROESSE);

                    if(j == spielfeld.getZeilen()-1 && i == spielfeld.getSpalten()-1)
                    {
                        KarteVBox spielerKarteVBox = new KarteVBox(spieler);
                        feld.getChildren().add(spielerKarteVBox);
                    }
                    spielfeldGitter.add(feld, i, j);
                }
            }
    }

    /**
     * Methode welche die für den kampf benötigten objekte4 erstellt
     */
    private void erstellenspielfeldumgebung()
    {
        try
        {
            spiel = SpielStandIO.leseDatei();
            spieler = spiel.getSpieler();
            spieldeck = spiel.getSpieldeckSpieler();
            kartenhand = new KartenHand(spieler);
            kartenhand.handZiehen(spieldeck);
            spielfeld = new SpielFeld();
            spielfeld.einheitEinsetzten(spielfeld.getSpalten()-1, spielfeld.getZeilen()-1, spieler);
            Karteinhandeinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

    }

    /**
     * methode welche die kartenhand visualisiert
     */
    private void Karteinhandeinfuegen()
    {
        for (int i = 0; i < HANDGROESSE; i++)
        {
            StackPane feld = new StackPane();
            feld.setPrefWidth(KARTENHAND_GROESSE);
            feld.setPrefHeight(KARTENHAND_GROESSE);

            Karte aktuellekarte = kartenhand.getElement(i);
            KarteVBox aktuellekartevbox = new KarteVBox(aktuellekarte);
            feld.getChildren().add(aktuellekartevbox);

            kartenhandGitter.add(feld,i,0);
        }
    }

    /**
     * legt das Hintergrundbild des Spielfeldes fest
     */
    private void spielfeldhintergrundfestlegen()
    {
        File meinhintergrund = new File(BILDER_PFAD + "Spielplatz" + PNG_DATEI_ENDUNG);
        BackgroundSize backroundsize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,true,true,true);
        Background hintergrund = new Background(new BackgroundImage(
                new Image(meinhintergrund.getAbsolutePath()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backroundsize));
        spielfeldGitter.setBackground(hintergrund);
    }

    /**
     * speichert den aktuellen Spielstand
     * @param event Event durch das die Methode ausgeloest wird
     */
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

    /**
     * Ueberlagern der Methode wechselZu damit durch die MenueLeiste auf die Methode zugegriffen werden kann.
     * @param event Event durch welches die Methode ausgeloest wird.
     * @param pfad String mit dem Pfad der .fxml Datei welche geladen werden soll.
     * @throws IOException
     */
    @Override
    protected void wechselZu(ActionEvent event, String pfad) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiController.class.getResource(pfad));
        Scene scene = new Scene(fxmlLoader.load());
        super.setStage(((Stage)menueLeiste.getScene().getWindow()));
        super.getStage().setScene(scene);
        super.getStage().show();
    }

    /**
     * methode durch welche, ueber die MenueLeiste zum Hauptmenue gewechselt werden kann
     * @param event Event durch welches die Methode ausgeloest wird.
     */
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
     * ermöglicht das einstellen der Aufloesung in der MenueLeiste
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override
    public void wechselAufloesungFullHD (Event event)
    {
        super.setStage((Stage)menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_FULLHD);
    }
}