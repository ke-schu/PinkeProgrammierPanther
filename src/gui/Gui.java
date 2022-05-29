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
        stage.setMaxHeight(AUFLOESUNG_BREITE_HD); //Auloesung bei Start
        stage.setMinHeight(AUFLOESUNG_BREITE_HD);
        stage.setMaxWidth(AUFLOESUNG_HOEHE_HD);
        stage.setMinWidth(AUFLOESUNG_HOEHE_HD);
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