package view.fxmlControl;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import control.KartenEinheitController;
import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.scene.paint.Color;
import model.*;
import utility.KonsolenIO;
import utility.SpielStandIO;
import view.components.KarteVBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.HANDGROESSE;
import static resources.KonstantenGUI.*;
import static resources.StringsGUI.*;

public class SpielfeldGuiController extends GuiController
        implements Initializable
{
    @FXML GridPane spielfeldGitter;
    @FXML GridPane kartenhandGitter;
    @FXML MenuBar menueLeiste;
    StackPane targetPane;
    StackPane sourchePane;
    private SpielFeld spielfeld;
    private KartenDeck spieldeck;
    private KartenHand kartenhand;
    private Spieler spieler;
    private final int FELDGROESSE = 80;
    private final int KARTENHAND_GROESSE = 100;

    /**
     * Wird aufgerufen, um diesen Controller zu initialisieren.
     *
     * @param url            Der Standort, der zum Auflösen relativer Pfade
     *                       für das Root-Objekt verwendet wird.
     * @param resourceBundle Die zum Lokalisieren des Root-Objekts
     *                       verwendeten Ressourcen.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle)
    {
        spielfeldhintergrundfestlegen();
        erstellenspielfeldumgebung();

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
                draganddroptarget(feld);
                targetPane = feld;

                if (j == spielfeld.getZeilen() - 1 &&
                    i == spielfeld.getSpalten() - 1)
                {
                    KartenEinheitController.beschwoerenHelden(spieler,spielfeld);
                    KarteVBox spielerKarteVBox = new KarteVBox(spieler);
                    KonsolenIO.ausgeben(spielfeld.toString());
                    feld.getChildren().add(spielerKarteVBox);
                }
                spielfeldGitter.add(feld, i, j);
            }
        }
    }

    private void einheitBeschwören(StackPane feld)
    {

    }

    /**
     * Methode welche die für den kampf benötigten objekte4 erstellt
     */
    private void erstellenspielfeldumgebung()
    {
        try
        {
            spiel      = SpielStandIO.leseDatei();
            spieler    = spiel.getSpieler();
            spieldeck  = spiel.getSpieldeckSpieler();
            kartenhand = new KartenHand(spieler);
            kartenhand.handZiehen(spieldeck);
            spielfeld = new SpielFeld();
            Karteinhandeinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

    }

    public void draganddropsource (StackPane feld)
    {
        feld.setOnMousePressed(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                feld.setMouseTransparent(true);
                //writelog("Event on Source: mouse pressed");
                event.setDragDetect(true);
            }
        });

        feld.setOnMouseReleased(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                feld.setMouseTransparent(false);
                //writelog("Event on Source: mouse released");
            }
        });

        feld.setOnMouseDragged(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                //writelog("Event on Source: mouse dragged");
                event.setDragDetect(false);
            }
        });

        feld.setOnDragDetected(new EventHandler <MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                feld.startFullDrag();
                //writelog("Event on Source: drag detected");
            }
        });
    }

    public void draganddroptarget (StackPane feld)
    {
        feld.setOnMouseDragEntered(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                //writelog("Event on Target: mouse dragged");
            }
        });

        feld.setOnMouseDragOver(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                //writelog("Event on Target: mouse drag over");
            }
        });

        feld.setOnMouseDragReleased(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                feld.getChildren().add(sourchePane);
                System.out.println("Fickt euch");
                //writelog("Event on Target: mouse drag released");
            }
        });

        feld.setOnMouseDragExited(new EventHandler <MouseDragEvent>()
        {
            public void handle(MouseDragEvent event)
            {
                //writelog("Event on Target: mouse drag exited");
            }
        });
    }

    /**
     * Methode welche die kartenhand visualisiert
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
            draganddropsource(feld);
            sourchePane = feld;
            kartenhandGitter.add(feld, i, 0);
        }
    }

    /**
     * Legt das Hintergrundbild des Spielfeldes fest
     */
    private void spielfeldhintergrundfestlegen()
    {
        File meinhintergrund =
                new File(BILDER_PFAD + "Spielplatz" + PNG_DATEI_ENDUNG);
        BackgroundSize backroundsize =
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                                   true, true, true, true);
        Background hintergrund = new Background(new BackgroundImage(
                new Image(meinhintergrund.getAbsolutePath()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, backroundsize));
        spielfeldGitter.setBackground(hintergrund);
    }

    /**
     * Speichert den aktuellen Spielstand
     *
     * @param event Event durch das die Methode ausgeloest wird
     */
    @FXML public void spielSpeichern(ActionEvent event)
    {
        try
        {
            SpielStandIO.schreibeDatei(spiel);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Ueberlagern der Methode wechselZu damit durch die MenueLeiste auf die
     * Methode zugegriffen werden kann.
     *
     * @param event Event durch welches die Methode ausgeloest wird.
     * @param pfad  String mit dem Pfad der .fxml Datei welche geladen werden
     *             soll.
     * @throws IOException Wirft die Exception, welche durch das .load()
     *                     erursacht werden kann weiter.
     */
    @Override protected void wechselZu(ActionEvent event, String pfad)
            throws IOException
    {
        File f = new File(pfad);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        super.setStage(((Stage) menueLeiste.getScene().getWindow()));
        super.getStage().setScene(scene);
        super.getStage().show();
    }

    /**
     * Methode durch welche, ueber die MenueLeiste zum Hauptmenue gewechselt
     * werden kann
     *
     * @param event Event durch welches die Methode ausgeloest wird.
     */
    @FXML public void zurueckHauptmenue(ActionEvent event)
    {
        try
        {
            wechselZuHauptmenue(event);
        }
        catch (IOException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    /**
     * Ermöglicht das einstellen der Aufloesung in der MenueLeiste
     *
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override public void wechselAufloesungFullHD(Event event)
    {
        super.setStage((Stage) menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_FULLHD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_FULLHD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_FULLHD);
    }

    /**
     * Ueberlagern der Methode, damit durch die Menueleiste auf diese Methode
     * zugegriffen werden kann.
     *
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @Override public void wechselAufloesungHD(Event event)
    {
        super.setStage((Stage) menueLeiste.getScene().getWindow());
        super.getStage().setMinHeight(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMaxHeight(AUFLOESUNG_HOEHE_HD);
        super.getStage().setMinWidth(AUFLOESUNG_BREITE_HD);
        super.getStage().setMaxWidth(AUFLOESUNG_BREITE_HD);
    }

    /**
     * Methode um das Spielstandfenster aufzurufen
     * @param event Event durch welches die Methode ausgelöst wird.
     * @throws IOException Kann beim .load() des fxmlLoaders geworfen werden.
     */
    public void spielstandAnzeigen(Event event) throws IOException
    {
        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(SPIELSTAND);
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));
        File f = new File(SPIELSTAND_PFAD);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene popupScene = new Scene(fxmlLoader.load());
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }
}