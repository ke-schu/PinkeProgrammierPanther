package gui;

import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.FAKTOR_FUER_LAUTSTAERKE;

/**
 * Diese Klasse dient zur Erstellung und Kontrolle eines Mediaplayers zu dem Abspielen von MP3s.
 */
public class mp3Controller implements Initializable
{
    private static MediaPlayer mediaplayer = new MediaPlayer(new Media(new File("src/gui/mp3/fun-life-112188.mp3").toURI().toString()));
    private Media media;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        media = new Media(new File("src/gui/mp3/fun-life-112188.mp3").toURI().toString());
        mediaplayer = new MediaPlayer(media);

    }

    public static void test ()
    {
        mediaplayer.play();
    }

    /**
     * Mit dieser Methode wird die Lautstaerke der Instanz der Mediaplayers angepasst.
     * @param lautstaerke der Integer welcher mit einem Faktor als Lautstaerke eingestellt wird.
     */
    public static void wechselLautstaerke(int lautstaerke)
    {
        mediaplayer.setVolume(lautstaerke * FAKTOR_FUER_LAUTSTAERKE);
    }


}
