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
    private ObjectProperty<Position> spielerPosition =
            new SimpleObjectProperty<>();
    private DoubleProperty feldGroesse =
            new SimpleDoubleProperty(100);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            spielfeldhintergrundfestlegen();
            spiel = SpielStandIO.leseDatei();
            spieler = spiel.getSpieler();
            spieldeck = spiel.getSpieldeckSpieler();
            kartenhand = new KartenHand(spieler);
            kartenhand.handZiehen(spieldeck);

            Label spielerLabel = new Label();
            spielerLabel.setText(spiel.getSpieler().getName());
            spielfeld = new SpielFeld();

            spielfeld.einheitEinsetzten(spielfeld.getSpalten()-1, spielfeld.getZeilen()-1, spieler);

            for (int i = 0; i < HANDGROESSE; i++)
            {
                Karteinhandeinfuegen(i);
            }

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

                    feld.setPrefHeight(feldGroesse.get());
                    feld.setPrefWidth(feldGroesse.get());

                    feldGroesse.addListener((observableValue, number, t1) ->
                    {
                        feld.setPrefHeight(feldGroesse.get());
                        feld.setPrefWidth(feldGroesse.get());
                    });

                    if(feld.getHeight() != feldGroesse.get() || feld.getWidth() != feldGroesse.get())
                    {
                        KonsolenIO.ausgeben("Achtung Feldgroesse geändert.");
                    }

                    if(j == spielfeld.getZeilen()-1 && i == spielfeld.getSpalten()-1)
                    {
                        KarteVBox spielerKarteVBox = new KarteVBox(spieler);
                        feld.getChildren().add(spielerKarteVBox);
                    }
                    spielfeldGitter.add(feld, i, j);
                }
            }
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    private void Karteinhandeinfuegen(int x)
    {
            Karte aktuellekarte = kartenhand.getElement(x);
            KarteVBox aktuellekartevbox = new KarteVBox(aktuellekarte);
            kartenhandGitter.add(aktuellekartevbox,x,0);
    }

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

    @Override
    protected void wechselZu(ActionEvent event, String pfad) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiController.class.getResource(pfad));
        Scene scene = new Scene(fxmlLoader.load());
        super.setStage(((Stage)menueLeiste.getScene().getWindow()));
        super.getStage().setScene(scene);
        super.getStage().show();
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
        if(ereignis instanceof Gegner)
        {
            gegnerGuiAusfuehren(ereignis);
        }
        else if(ereignis instanceof Haendler)
        {
            haendlerGuiAusfuehren(ereignis);
        }
        else if(ereignis instanceof Heiler)
        {
            heilerGuiAusfuehren(ereignis);
        }
        else if(ereignis instanceof Schmied)
        {
            schmiedGuiAusfuehren(ereignis);
        }
        else if(ereignis instanceof Tempel)
        {
            tempelGuiAusfuehren(ereignis);
        }
        else if(ereignis instanceof Treppe)
        {
            treppeGuiAusfuehren(ereignis);
        }
        else if(ereignis instanceof Truhe)
        {
            if(!((Truhe) ereignis).isGeleert())
            {
                truheGuiAusfuehren(ereignis);
            }
        }
        else if(ereignis instanceof ZufallsEreignis)
        {
            zufallsEreignisGuiAusfuehren(ereignis);
        }
    }

    public void gegnerGuiAusfuehren (Ereignis ereignis){}
    public void haendlerGuiAusfuehren (Ereignis ereignis){}
    public void heilerGuiAusfuehren (Ereignis ereignis)
    {
        int lebenVorher = spiel.getGold();
        ereignis.ausfuehren(spiel);
        int lebenNachher = spiel.getGold();
        int lebenErhalten = lebenNachher-lebenVorher;

        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(ereignis.getName());
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(20);
        TextArea ereignisText = new TextArea();
        ereignisText.setWrapText(true);
        ereignisText.setEditable(false);
        Scene popupScene = new Scene(vbox, 400, 300);
        Button gehenButton = new Button(EREIGNIS_GEHEN);
        ereignisText.setText(HEILER_AUSFUEHREN_1 + lebenErhalten + HEILER_AUSFUEHREN_2);
        gehenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                popupStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(ereignisText);
        vbox.getChildren().add(gehenButton);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }
    public void schmiedGuiAusfuehren (Ereignis ereignis){}
    public void tempelGuiAusfuehren (Ereignis ereignis){}
    public void treppeGuiAusfuehren (Ereignis ereignis){}

    /**
     *
     * @param ereignis
     */
    public void truheGuiAusfuehren (Ereignis ereignis)
    {
        int goldVorher = spiel.getGold();
        ereignis.ausfuehren(spiel);
        int goldNachher = spiel.getGold();
        int goldGefunden = goldNachher-goldVorher;

        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(ereignis.getName());
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(20);
        TextArea ereignisText = new TextArea();
        ereignisText.setWrapText(true);
        ereignisText.setEditable(false);
        Scene popupScene = new Scene(vbox, 400, 300);
        Button gehenButton = new Button(EREIGNIS_GEHEN);
        ereignisText.setText(TRUHE_AUSFUEHREN_1 + goldGefunden + TRUHE_AUSFUEHREN_2);
        gehenButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                popupStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(ereignisText);
        vbox.getChildren().add(gehenButton);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }

    public void zufallsEreignisGuiAusfuehren (Ereignis ereignis){}

    @Override
    public void wechselAufloesungFullHD (Event event)
    {
        super.setStage((Stage)menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_FULLHD);
    }

    @Override
    public void wechselAufloesungHD (Event event)
    {
        super.setStage((Stage)menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_HD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_HD);
    }
}