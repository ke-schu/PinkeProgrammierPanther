package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static gui.GuiKonstanten.*;

public class Gui extends Application
{

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource(HAUPTMENUE_PFAD_START));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle(SPIELTITEL);
        stage.setScene(scene);
        stage.setMaxHeight(AUFLOESUNG_HOEHE_HD); //Aufloesung bei Start
        stage.setMinHeight(AUFLOESUNG_HOEHE_HD);
        stage.setMaxWidth(AUFLOESUNG_BREITE_HD);
        stage.setMinWidth(AUFLOESUNG_BREITE_HD);
        stage.setResizable(false);
        stage.getIcons().add(new Image(ICON.getAbsolutePath()));
        stage.show();
        mp3Controller.play();
    }

    public static void main(String[] args)
    {
        launch();
    }

}