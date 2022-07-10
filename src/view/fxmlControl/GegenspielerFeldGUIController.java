package view.fxmlControl;

import control.RundenController;
import control.Spielstatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
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
        hintergrundFestlegen();
        initZugBeendenButton();
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
                            aktualisiereSpielStatus(SpielstatusKommunikation.getObjekt());
                            SpielstatusKommunikation.getInputService().restart();
                        }
                    });

            SpielstatusKommunikation.getInputService().setOnSucceeded(
                    new EventHandler<WorkerStateEvent>()
                    {
                        @Override public void handle(
                                WorkerStateEvent workerStateEvent)
                        {
                            aktualisiereSpielStatus(
                                    SpielstatusKommunikation.getInputService().getValue());
                            SpielstatusKommunikation.getInputService().restart();
                        }
                    });

            SpielstatusKommunikation.getInputService().start();
            erstelleSpielfeldUmgebung();
            return null;
        }
    }

    @Override
    public void erstelleSpielfeldUmgebung()
    {
        aktuellekartenhand = new KartenHand(gegenspieler);
            aktuellekartenhand.handZiehen(gegenspielerDeck);

        aktuellermanaTank = new ManaTank(gegenspieler);
            Manabar.setStyle("-fx-accent: blue;");
        altuellesmanaMaximum = aktuellermanaTank.getMana();
            double manaWert = altuellesmanaMaximum / aktuellermanaTank.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();

    }
}
