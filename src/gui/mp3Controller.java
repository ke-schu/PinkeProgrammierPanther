package gui;

import exceptions.JsonNichtLesbarException;
import gui.xml.GuiController;
import io.KonsolenIO;
import io.SpielStandIO;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static gui.GuiKonstanten.*;

/**
 * Diese Klasse dient zur Erstellung und Kontrolle eines Mediaplayers zu dem Abspielen von MP3s.
 */
public class mp3Controller implements Initializable
{
    private static MediaPlayer mediaplayer = new MediaPlayer(new Media(new File(HAUPTMENUE_MUSIK).toURI().toString()));
    private Media media;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            media = new Media(new File(HAUPTMENUE_MUSIK).toURI().toString());
            mediaplayer = new MediaPlayer(media);
            wechselLautstaerke(SpielStandIO.leseDatei().getLautstaerke());
        }
        catch (JsonNichtLesbarException e)
        {
              KonsolenIO.ausgeben(e.getMessage());
        }


    }

    public static void play()
    {
        mediaplayer.play();
        mediaplayer.setAutoPlay(true);
        mediaplayer.setCycleCount(mediaplayer.INDEFINITE);

    }

    public static void mute()
    {
        mediaplayer.setMute(true);
    }

    /**
     * Mit dieser Methode wird die Lautstaerke der Instanz der Mediaplayers angepasst.
     * @param lautstaerke der Integer welcher mit einem Faktor als Lautstaerke eingestellt wird.
     */
    public static void wechselLautstaerke(double lautstaerke)
    {
            mediaplayer.setVolume(lautstaerke * FAKTOR_FUER_LAUTSTAERKE);
            GuiController.getSpiel().setLautstaerke(lautstaerke);
    }


}
