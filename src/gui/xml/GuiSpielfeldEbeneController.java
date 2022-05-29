package gui.xml;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiSpielfeldEbeneController{

    private Stage stage;
    private Scene scene;
    private Parent root;

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
}
