package view.fxmlControl;

import control.KartenEinheitController;
import control.RundenController;
import control.Spielstatus;
import exceptions.JsonNichtLesbarException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import model.KartenHand;
import model.ManaTank;
import model.SpielFeld;
import utility.KonsolenIO;
import utility.Server;

import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.SPIELSTATUS_PORT;
import static resources.Konstanten.spielStandIO;

public class SpielerFeldGUIController extends FeldGuiController
{
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        binSpieler = true;
        updateSpielBackend();
        ladeSpielfeld(spielfeld, true);
        new Thread(new NetzwerkTask()).start();
    }

    private class NetzwerkTask extends Task
    {
        @Override protected Void call() throws Exception
        {
            SpielstatusKommunikation =
                    new Server(SPIELSTATUS_PORT, Spielstatus.class);

            SpielstatusKommunikation.senden(new Spielstatus(
                    spieler, gegenspieler,
                    spielfeld, spielerDeck,
                    gegenspielerDeck,
                    RundenController.getZugZaehler()));

            SpielstatusKommunikation.objektProperty().addListener(
                    new ChangeListener()
                    {
                        @Override
                        public void changed(ObservableValue observableValue,
                                            Object o, Object t1)
                        {
                            updateSpielStatus(SpielstatusKommunikation.getObjekt());
                            SpielstatusKommunikation.getInputService().restart();
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
                    }
                });

            SpielstatusKommunikation.getInputService().start();
            return null;
        }
    }

    @Override
    public void updateSpielBackend()
    {
        try
        {
            spiel      = spielStandIO.leseSpielstand();

            spieler    = spiel.getSpieler();
            spielerDeck = spiel.getSpieldeckSpieler();

            gegenspieler = spiel.getGegenSpieler();
            gegenspielerDeck = spiel.getSpieldeckGegner();

            kartenHand = new KartenHand(spieler);
            kartenHand.handZiehen(spielerDeck);

            spielfeld = new SpielFeld();
            manaTank = new ManaTank(spieler);

            KartenEinheitController.beschwoerenHelden(spieler,spielfeld);
            KartenEinheitController.beschwoerenHelden(gegenspieler,spielfeld);

            Manabar.setStyle("-fx-accent: blue ;");
            manaMaximum = manaTank.getMana();
            double manaWert = manaMaximum / manaTank.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}

