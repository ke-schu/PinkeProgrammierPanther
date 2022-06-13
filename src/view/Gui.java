package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static resources.GuiKonstanten.*;

public class Gui extends Application
{

    @Override
    public void start(Stage stage) throws IOException
    {
        File f = new File(HAUPTMENUE_PFAD);
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());

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
        mp3Controller.spieleHintergrundmusik();
    }

    public static void main(String[] args)
    {
        launch();
    }

}