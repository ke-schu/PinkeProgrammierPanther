package view.xmlControl;

import exceptions.JsonNichtLesbarException;
import view.mp3Controller;
import utility.SpielStandIO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SpielStand;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.GuiKonstanten.*;

/**
 * Klasse welche alle generellen Methoden enthaelt, die in den weiteren Controllern der GUI benutzt werden.
 */
public class GuiController implements Initializable
{

    private Stage stage;
    private Scene scene;
    private Parent root;

    protected static SpielStand spiel;


    /**
     * Methode um eine Scenen zu laden und in die Stage zu setzen.
     * @param event Event durch welches die Methode ausgeloest wird.
     * @param pfad String mit dem Pfad der .fxml Datei welche geladen werden soll.
     * @throws IOException kann durch den fxmlLoader.load() geworfen werden.
     */
    protected void wechselZu(ActionEvent event, String pfad) throws IOException
    {
        mp3Controller.spieleEffekt(KLICK_SOUND);
        File f = new File(pfad);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Methode, welche die Methode offneHilfeTextEinsetzen aufruft. Diese Methode wird durch
     * @param event ActionEvent, welches mit dieser Methode verknuepft wird.
     */
    @FXML public void oeffneHilfe (ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_TEXT);
    }

    /**
     * Methode, welche ein Popupfenster erstellt und fokussiert vor dem Hauptfenster oeffnet.
     * @param hilfeText String welcher in dem Popup als Text eingesetzt werden soll.
     */
    @FXML public void offneHilfeTextEinsetzen (String hilfeText)
    {
        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(HILFE);
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(VBOX_20);
        TextArea text = new TextArea();
        text.setText(hilfeText);
        text.setWrapText(true);
        text.setEditable(false);
        vbox.getChildren().add(text);
        Scene popupScene = new Scene(vbox,POPUP_BREITE,POPUP_HOEHE);
        Button okButton = new Button(POPUP_BUTTON_SCHLIESSEN);
        okButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                popupStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(okButton);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }

    /**
     * Mehtode mit der zu der Hauptmenue Scene gewechselt wird.
     * @param event ActionEvent, welches diese Methode ausloest.
     * @throws IOException durch den Aufruf der Methode wechelZu ausgeloest werden.
     */
    @FXML public void wechselZuHauptmenue (ActionEvent event) throws IOException
    {
        wechselZu(event, HAUPTMENUE_PFAD);
    }

    /**
     * Mehtode mit der zu der Einstellung Scene gewechselt wird.
     * @param event ActionEvent, welches diese Methode ausloest.
     * @throws IOException durch den Aufruf der Methode wechelZu ausgeloest werden.
     */
    @FXML public void wechselZuEinstellungen (ActionEvent event) throws IOException
    {
        wechselZu(event, EINSTELLUNG_PFAD);
    }

    /**
     * Mehtode mit der zu der Charakterauswahl Scene gewechselt wird.
     * @param event ActionEvent, welches diese Methode ausloest.
     * @throws IOException durch den Aufruf der Methode wechelZu ausgeloest werden.
     */
    @FXML public void wechselZuCharakterauswahl (ActionEvent event) throws IOException
    {
        wechselZu(event, CHARAKTERAUSWAHL_PFAD);
    }

    /**
     * Mehtode mit der zu der SpielEbene Scene gewechselt wird.
     * @param event ActionEvent, welches diese Methode ausloest.
     * @throws IOException durch den Aufruf der Methode wechelZu ausgeloest werden.
     */
    @FXML public void wechselZuSpielEbene (ActionEvent event) throws IOException
    {
        wechselZu(event, SPIELEBENE_PFAD);
    }

    /**
     * Mehtode mit der zu der Spielfeld Scene gewechselt wird.
     * @param event ActionEvent, welches diese Methode ausloest.
     * @throws IOException durch den Aufruf der Methode wechelZu ausgeloest werden.
     */
    @FXML public void wechselZuSpielfeld (ActionEvent event) throws IOException
    {
        wechselZu(event, SPIELFELD_PFAD);
    }

    /**
     * Methode mit welcher die Anwendung geschlossen wird.
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    @FXML public void spielBeenden (ActionEvent event)
    {
        Platform.exit();
    }

    /**
     * Methode, welche die Stage des Attribut Stage wieder gibt.
     * @return gibt die Stage wieder.
     */
    public Stage getStage()
    {
        return stage;
    }

    /**
     * Methode um eine Stage in das Attribut Stage zu setzen.
     * @param stage Stage, welche in das Attribut gesetzt werden soll.
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Scene getScene()
    {
        return scene;
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    public Parent getRoot()
    {
        return root;
    }

    public void setRoot(Parent root)
    {
        this.root = root;
    }

    /**
     * Methode um eine ComboBox zu erstellen.
     * @param comboBoxArray StringArray welche in die ComboBox eingesetzte werden soll.
     * @param meineCombobox ComboBox, in welche das StringArray eingesetzt werden soll.
     */
    public void erstelleCombobox(String[] comboBoxArray, ComboBox meineCombobox)
    {
        for(int i = 0; i < comboBoxArray.length; i++)
        {
            meineCombobox.getItems().add(comboBoxArray[i]);
        }
    }

    /**
     * Methode um die Fenstergroesse auf 1920x1080 zu wechseln.
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    public void wechselAufloesungFullHD (Event event)
    {
        setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        getStage().setMaxHeight(AUFLOESUNG_HOEHE_FULLHD);
        getStage().setMinHeight(AUFLOESUNG_HOEHE_FULLHD);
        getStage().setMaxWidth(AUFLOESUNG_BREITE_FULLHD);
        getStage().setMinWidth(AUFLOESUNG_BREITE_FULLHD);
    }

    /**
     * Methode um die Fenstergroesse auf 1280x720 zu wechseln.
     * @param event ActionEvent, welches diese Methode ausloest.
     */
    public void wechselAufloesungHD (Event event)
    {
        setStage((Stage)((Node)event.getSource()).getScene().getWindow());
        getStage().setMaxHeight(AUFLOESUNG_HOEHE_HD);
        getStage().setMinHeight(AUFLOESUNG_HOEHE_HD);
        getStage().setMaxWidth(AUFLOESUNG_BREITE_HD);
        getStage().setMinWidth(AUFLOESUNG_BREITE_HD);
    }

    /**
     * Methode, welche das Objekt des Attributs spiel wiedergibt.
     * @return gibt ein Objekt des Typs SpielStand wieder.
     */
    public static SpielStand getSpiel()
    {
        return spiel;
    }

    public static void setSpiel(SpielStand spiel)
    {
        GuiController.spiel = spiel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try {
            spiel  =  SpielStandIO.leseDatei();
        } catch (JsonNichtLesbarException e) {
            throw new RuntimeException(e);
        }
    }
}