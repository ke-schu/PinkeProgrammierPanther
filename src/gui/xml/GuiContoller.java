package gui.xml;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static gui.GuiKonstanten.*;

public class GuiContoller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    private void wechselZu(ActionEvent event, String pfad) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource(pfad));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    public void wechselAufloesungFullHD (ActionEvent event)
    {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setMinHeight(AUFLOESUNG_BREITE_FULLHD);
        stage.setMaxHeight(AUFLOESUNG_BREITE_FULLHD);
        stage.setMinWidth(AUFLOESUNG_HOEHE_FULLHD);
        stage.setMaxWidth(AUFLOESUNG_HOEHE_FULLHD);
    }

    public void wechselAufloesungHD (ActionEvent event)
    {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setMinHeight(AUFLOESUNG_BREITE_HD);
        stage.setMaxHeight(AUFLOESUNG_BREITE_HD);
        stage.setMinWidth(AUFLOESUNG_HOEHE_HD);
        stage.setMaxWidth(AUFLOESUNG_HOEHE_HD);
    }

    public void spielBeenden (ActionEvent event)
    {
        Platform.exit();
    }
}