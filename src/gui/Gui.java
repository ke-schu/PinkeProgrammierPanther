package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("xml/Hauptmenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //Image logo = new Image("C:\\Users\\hendr\\Documents\\GitHub\\PinkeProgrammierPanther\\src\\gui\\bilder\\Logo.png");
        // funktioniert aber nur mit dem Verweis auf meinem Rechner, andere Verweise innerhalb des Projektes hab ich so erstmal
        // nicht zum laufen bekommen.
        stage.setTitle("DungeonDing");
        stage.setScene(scene);
        //stage.getIcons().add(logo); Gehoert zum Logo
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}