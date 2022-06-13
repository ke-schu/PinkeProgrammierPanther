package gui.xml;

import control.SpielfigurEbeneController;
import exceptions.JsonNichtLesbarException;
import gui.components.RaumPane;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import model.ereignisse.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.*;

/**
 * Klasse, welche alle Methoden der Spielebene Szene enthaelt.
 */
public class SpielebeneGuiController
        extends GuiController
        implements Initializable
{
    @FXML
    GridPane spielebenenGitter;
    @FXML
    Label spielerLabel;
    @FXML
    MenuBar menueLeiste;
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
                ereignisGuiAusfuehren(ereignis, arg0);
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

    public void ereignisGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        if(ereignis instanceof Gegner)
        {
            gegnerGuiAusfuehren(ereignis, event);
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

    public void gegnerGuiAusfuehren (Ereignis ereignis, ActionEvent event)
    {
        try
        {
            wechselZuSpielfeld(event);
        }
        catch (IOException e)
        {
            System.out.println("Zum Spielfeld wechseln hat nicht geklappt");
        }
    }

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

    /**
     * Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode zugegriffen werden kann.
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

    /**
     * Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode zugegriffen werden kann.
     * @param event ActionEvent, welches diese Methode ausloest.
     */
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
