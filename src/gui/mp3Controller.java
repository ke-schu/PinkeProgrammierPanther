package gui;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class mp3Controller implements Initializable
{
    private static MediaPlayer mediaplayer = new MediaPlayer(new Media(new File("C:\\Users\\hendr\\Documents\\GitHub\\PinkeProgrammierPanther\\src\\gui\\mp3\\fun-life-112188.mp3").toURI().toString()));

    private Media media;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        media = new Media(new File("C:\\Users\\hendr\\Documents\\GitHub\\PinkeProgrammierPanther\\src\\gui\\mp3\\fun-life-112188.mp3").toURI().toString());
        mediaplayer = new MediaPlayer(media);

    }

    public static void test ()
    {
        mediaplayer.play();
    }

}
