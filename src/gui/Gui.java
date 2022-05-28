package gui;

import io.KonsolenIO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Gui extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("xml/Hauptmenue.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        File f = new File("src/gui/bilder/Logo.png");
        Image logo = new Image(f.getAbsolutePath());

        stage.setTitle("DungeonDing");
        stage.setScene(scene);
        stage.getIcons().add(logo);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}