package view.fxmlControl;

import control.RundenController;
import model.Spielstatus;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.KartenHand;
import model.ManaTank;
import utility.Client;
import utility.UtilityController;

import java.net.URL;
import java.util.ResourceBundle;


import static resources.Konstanten.SPIELSTATUS_PORT;
import static resources.StringsGUI.PSEUDO_CLASS_ERROR;

public class GegenspielerFeldGUIController extends FeldGuiController
{
    @FXML TextField hostnameFeld;
    @FXML VBox ipEingabe;
    private String hostname;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        binSpieler = false;
    }

    @FXML
    public void verbinden(ActionEvent actionEvent)
    {
        if(UtilityController.isGueltigeIP(hostnameFeld.getText()))
        {
            this.hostname = hostnameFeld.getText();
            new Thread(new NetzwerkTask()).start();
            ipEingabe.setVisible(false);
        }
        else
        {
            hostnameFeld.getStyleClass().add(PSEUDO_CLASS_ERROR);
            hostnameFeld.requestFocus();
        }
    }

    private class NetzwerkTask extends Task
    {
        @Override protected Void call()
        {
            SpielstatusKommunikation = new Client(hostname, SPIELSTATUS_PORT,
                                                  Spielstatus.class);

            SpielstatusKommunikation.objektProperty().addListener(
                    new ChangeListener()
                    {
                        @Override
                        public void changed(ObservableValue observableValue,
                                            Object o, Object t1)
                        {
                            updateSpielStatus(SpielstatusKommunikation.getObjekt());
                            SpielstatusKommunikation.getInputService().restart();
                            initalisieren();
                        }
                    });

            SpielstatusKommunikation.getInputService().setOnSucceeded(
                    new EventHandler<WorkerStateEvent>()
                    {
                        @Override public void handle(
                                WorkerStateEvent workerStateEvent)
                        {
                            updateSpielStatus(
                                    SpielstatusKommunikation.getInputService().getValue());
                            SpielstatusKommunikation.getInputService().restart();
                                initalisieren();
                        }
                    });

            SpielstatusKommunikation.getInputService().start();
            return null;
        }
    }

    @Override
    public void initalisieren ()
    {

            kartenHand = new KartenHand(gegenspieler);
            if(RundenController.getZugZaehler() == 0)
            {
                kartenHand.handZiehen(gegenspielerDeck);
            }

            manaTank = new ManaTank(gegenspieler);
            Manabar.setStyle("-fx-accent: blue;");
            manaMaximum = manaTank.getMana();
            double manaWert = manaMaximum / manaTank.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();

    }
}
