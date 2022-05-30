package gui.xml;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

import static gui.GuiKonstanten.*;

public class GuiContoller {

    private Stage stage;
    private Scene scene;
    private Parent root;


    protected void wechselZu(ActionEvent event, String pfad) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource(pfad));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void oeffneHilfe (ActionEvent event) throws IOException
    {
        Popup hilfePopup = new Popup();
    }

    @FXML
    public void wechselZuHauptmenue (ActionEvent event) throws IOException
    {
        wechselZu(event, "Hauptmenue.fxml");
    }

    public void wechselZuEinstellungen (ActionEvent event) throws IOException
    {
        wechselZu(event, "Einstellungen.fxml");
    }

    public void wechselZuCharakterauswahl (ActionEvent event) throws IOException
    {
        wechselZu(event, "Charakterauswahl.fxml");
    }

    public void wechselZuHilfeHauptmenue(ActionEvent event) throws IOException
    {
        wechselZu(event, "HilfeHauptmenue.fxml");
    }

    public void wechselZuHilfeEinstellungen (ActionEvent event) throws IOException
    {
        wechselZu(event, "HilfeEinstellungen.fxml");
    }

    public void wechselZuHilfeCharakterauswahl (ActionEvent event) throws IOException
    {
        wechselZu(event, "HilfeCharakterauswahl.fxml");
    }

    public void wechselZuSpielEbene (ActionEvent event) throws IOException
    {
        wechselZu(event, "Spielebene.fxml");
    }

    public void spielBeenden (ActionEvent event)
    {
        Platform.exit();
    }

    public Stage getStage()
    {
        return stage;
    }

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
}