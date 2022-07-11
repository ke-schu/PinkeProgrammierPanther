package view.fxmlControl;

import control.Spielstatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import model.*;
import utility.Client;

import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.*;

public class GegenspielerFeldGUIController extends FeldGuiController
{
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        new Thread(new NetzwerkTask()).start();
    }

    private class NetzwerkTask extends Task
    {
        String hostname = "localhost";
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
                            updateSpielBackend();
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
                            updateSpielBackend();
                        }
                    });

            SpielstatusKommunikation.getInputService().start();
            return null;
        }
    }

    @Override
    public void updateSpielBackend()
    {
        kartenHand = new KartenHand(gegenspieler);
            kartenHand.handZiehen(gegenspielerDeck);

        manaTank = new ManaTank(gegenspieler);
            Manabar.setStyle("-fx-accent: blue;");
        manaMaximum = manaTank.getMana();
            double manaWert = manaMaximum / manaTank.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();

    }
}
