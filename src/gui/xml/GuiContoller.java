package gui.xml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiContoller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void wechselZuHauptmenue (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource("Hauptmenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void wechselZuEinstellungen (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource("Einstellungen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void wechselZuCharakterauswahl (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource("Charakterauswahl.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void wechselZuHilfeHauptmenue(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource("HilfeHauptmenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void wechselZuHilfeEinstellungen (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource("HilfeEinstellungen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void wechselZuHilfeCharakterauswahl (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiContoller.class.getResource("HilfeCharakterauswahl.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}