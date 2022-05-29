package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static gui.GuiKonstanten.*;

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
        stage.setMaxHeight(ZAHL_720); //Auloesung bei Start
        stage.setMinHeight(ZAHL_720);
        stage.setMaxWidth(ZAHL_1280);
        stage.setMinWidth(ZAHL_1280);
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.getIcons().add(logo);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

}