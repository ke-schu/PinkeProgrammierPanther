package view.fxmlControl;

import control.KartenEinheitController;
import control.RundenController;
import control.Spielstatus;
import exceptions.JsonNichtLesbarException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import model.*;
import utility.KonsolenIO;
import utility.Server;
import utility.SpielStandIO;
import view.components.KarteVBox;

import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.*;

public class SpielerFeldGUIController extends FeldGuiController
{
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        hintergrundFestlegen();
        erstelleSpielfeldUmgebung();
        initZugBeendenButton();
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
                    RundenController.getzugZaehler()));

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
            return null;
        }
    }

    @Override
    public void erstelleSpielfeldUmgebung ()
    {
        try
        {
            spiel      = SpielStandIO.leseDatei();

            spieler    = spiel.getSpieler();
            spielerDeck = spiel.getSpieldeckSpieler();

            gegenspieler = spiel.getGegenSpieler();
            gegenspielerDeck = spiel.getSpieldeckGegner();

            aktuellekartenhand = new KartenHand(spieler);
            aktuellekartenhand.handZiehen(spielerDeck);

            spielfeld = new SpielFeld();
            aktuellermanaTank = new ManaTank(spieler);

            KartenEinheitController.beschwoerenHelden(spieler,spielfeld);
            KartenEinheitController.beschwoerenHelden(gegenspieler,spielfeld);

            Manabar.setStyle("-fx-accent: blue ;");
            altuellesmanaMaximum = aktuellermanaTank.getMana();
            double manaWert = altuellesmanaMaximum / aktuellermanaTank.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}

