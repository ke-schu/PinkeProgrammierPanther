package view.mp3;

import exceptions.JsonNichtLesbarException;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utility.KonsolenIO;
import view.fxmlControl.GuiController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.spielStandIO;
import static resources.KonstantenGUI.FAKTOR_FUER_LAUTSTAERKE;
import static resources.StringsGUI.HAUPTMENUE_MUSIK;

/**
 Diese Klasse dient zur Erstellung und Kontrolle eines Mediaplayers zu dem
 Abspielen von MP3s. */
public class mp3Controller implements Initializable
{
    private static MediaPlayer hintergrundmusik = new MediaPlayer(
            new Media(new File(HAUPTMENUE_MUSIK).toURI().toString()));
    private static MediaPlayer effekt;
    
    /**
     Methode die einen Mediaplayer erstellt und einen Sound abspielt, der per
     Parameterliste uebergeben wird.
     @param pfad Pfad mit dem abzuspielenden Soundfile.
     */
    public static void spieleEffekt (String pfad)
    {
        try
        {
            Media soundEffekt = new Media(new File(pfad).toURI().toString());
            effekt = new MediaPlayer(soundEffekt);
            wechselLautstaerkeEffekte(
                    spielStandIO.leseSpielstand().getLaustaerkeEffekte());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
        effekt.play();
    }
    
    /**
     Mit dieser Methode wird die Lautstaerke der Instanz der Mediaplayers
     angepasst.
     @param lautstaerke der Integer welcher mit einem Faktor als Lautstaerke
     eingestellt wird.
     */
    public static void wechselLautstaerkeEffekte (double lautstaerke)
    {
        effekt.setVolume(lautstaerke * FAKTOR_FUER_LAUTSTAERKE);
        GuiController.getSpiel().setLaustaerkeEffekte(lautstaerke);
    }
    
    /**
     Ruft die Methode spieleHintergrundmusik() auf, somit wird bei Start Musik
     gespielt.
     @param url Pfad fuer das root Objekt, bei null Speicherort nicht bekannt.
     @param resourceBundle Ressourcen zum root Objekt, bei null Speicherort
     nicht lokalisiert.
     */
    @Override public void initialize (URL url, ResourceBundle resourceBundle)
    {
        spieleHintergrundmusik();
    }
    
    /**
     Methode die einen Mediaplayer erstellt und eine Hauptmenuemusik auf
     Dauerschleife laeuft.
     */
    public static void spieleHintergrundmusik ()
    {
        try
        {
            Media titel =
                    new Media(new File(HAUPTMENUE_MUSIK).toURI().toString());
            hintergrundmusik = new MediaPlayer(titel);
            wechselLautstaerkeMusik(
                    spielStandIO.leseSpielstand().getLautstaerkeMusik());
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
        hintergrundmusik.play();
        hintergrundmusik.setAutoPlay(true);
        hintergrundmusik.setCycleCount(hintergrundmusik.INDEFINITE);
    }
    
    /**
     Mit dieser Methode wird die Lautstaerke der Instanz der Mediaplayers
     angepasst.
     @param lautstaerke der Integer welcher mit einem Faktor als Lautstaerke
     eingestellt wird.
     */
    public static void wechselLautstaerkeMusik (double lautstaerke)
    {
        hintergrundmusik.setVolume(lautstaerke * FAKTOR_FUER_LAUTSTAERKE);
        GuiController.getSpiel().setLautstaerkeMusik(lautstaerke);
    }
}
