package view.fxmlControl;

import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

                if (j == spielfeld.getZeilen() - 1 &&
                    i == spielfeld.getSpalten() - 1)
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
            spiel      = SpielStandIO.leseDatei();
            spieler    = spiel.getSpieler();
            spieldeck  = spiel.getSpieldeckSpieler();
            kartenhand = new KartenHand(spieler);
            kartenhand.handZiehen(spieldeck);
            spielfeld = new SpielFeld();
            spielfeld.einheitEinsetzten(spielfeld.getSpalten() - 1,
                                        spielfeld.getZeilen() - 1, spieler);
            Karteinhandeinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

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