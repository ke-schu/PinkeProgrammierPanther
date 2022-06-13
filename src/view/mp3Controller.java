package view;

import exceptions.JsonNichtLesbarException;
import view.xmlControl.GuiController;
import utility.KonsolenIO;
import utility.SpielStandIO;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.GuiKonstanten.*;

/**
 * Diese Klasse dient zur Erstellung und Kontrolle eines Mediaplayers zu dem Abspielen von MP3s.
 */
public class mp3Controller implements Initializable
{
    private static MediaPlayer hintergrundmusik = new MediaPlayer(new Media(new File(HAUPTMENUE_MUSIK).toURI().toString()));
    private static MediaPlayer effekt;
    private static Media titel;
    private static Media soundEffekt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        spieleHintergrundmusik();


    }

    public static void spieleHintergrundmusik()
    {
        try
        {
            titel = new Media(new File(HAUPTMENUE_MUSIK).toURI().toString());
            hintergrundmusik = new MediaPlayer(titel);
            wechselLautstaerkeMusik(SpielStandIO.leseDatei().getLautstaerkeMusik());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
        hintergrundmusik.play();
        hintergrundmusik.setAutoPlay(true);
        hintergrundmusik.setCycleCount(hintergrundmusik.INDEFINITE);

    }

    public static void spieleEffekt(String pfad)
    {
        try
        {
            soundEffekt = new Media(new File(pfad).toURI().toString());
            effekt = new MediaPlayer(soundEffekt);
            wechselLautstaerkeEffekte(SpielStandIO.leseDatei().getLaustaerkeEffekte());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }

        effekt.play();
    }

    public static void mute()
    {
        hintergrundmusik.setMute(true);
    }

    /**
     * Mit dieser Methode wird die Lautstaerke der Instanz der Mediaplayers angepasst.
     * @param lautstaerke der Integer welcher mit einem Faktor als Lautstaerke eingestellt wird.
     */
    public static void wechselLautstaerkeMusik(double lautstaerke)
    {
        hintergrundmusik.setVolume(lautstaerke * FAKTOR_FUER_LAUTSTAERKE);
        GuiController.getSpiel().setLautstaerkeMusik(lautstaerke);
    }

    public static void wechselLautstaerkeEffekte(double lautstaerke)
    {
        effekt.setVolume(lautstaerke * FAKTOR_FUER_LAUTSTAERKE);
        GuiController.getSpiel().setLaustaerkeEffekte(lautstaerke);
    }


}
