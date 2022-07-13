package view.fxmlControl;

import control.RundenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.KartenHand;
import model.ManaTank;
import model.Spielstatus;
import utility.Client;
import utility.UtilityController;

import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.SPIELSTATUS_PORT;
import static resources.StringsGUI.PSEUDO_CLASS_ERROR;

/**
 Controllerklasse, welche den View auf das Spielfeld des Gegenspielers kontrolliert
 */
public class GegenspielerFeldGUIController extends FeldGuiController
{
    @FXML TextField hostnameFeld;
    @FXML VBox ipEingabe;
    private String hostname;
    
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        binSpieler = false;
        //karteInHandEinfuegen();
    }
    
    /**
     Methode, welche durch Mausinteraktion die Verbindung zu einem Server aufbaut
     * @param actionEvent Mausinteraktion
     */
    @FXML
    public void verbinden (ActionEvent actionEvent)
    {
        if (UtilityController.isGueltigeIP(hostnameFeld.getText()))
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
    
    @Override
    /**
     Methode, welche die fuer die initialisierung des Kampfes noetigen Objekte erstellt und Aktionen ausfuert
     */
    public void initalisieren ()
    {
        if (RundenController.getZugZaehler() == 1)
        {
            kartenHand = new KartenHand(gegenspieler);
            kartenHand.handZiehen(gegenspielerDeck);
        }
        manaTank = new ManaTank(gegenspieler);
        Manabar.setStyle("-fx-accent: blue;");
        manaMaximum = manaTank.getMana();
        double manaWert = manaMaximum / manaTank.getMana();
        Manabar.setProgress(manaWert);
        karteInHandEinfuegen();
        
    }
    
    /**
     Klasse, welche die Kommunikation zwischen Server und Client regelt und den Spielstatus aktuell haelt
     */
    private class NetzwerkTask extends Task
    {
        @Override protected Void call ()
        {
            SpielstatusKommunikation = new Client(hostname, SPIELSTATUS_PORT,
                                                  Spielstatus.class);
            
            SpielstatusKommunikation.objektProperty().addListener(
                    new ChangeListener()
                    {
                        @Override
                        public void changed (ObservableValue observableValue,
                                             Object o, Object t1)
                        {
                            Spielstatus postEingang =
                                    SpielstatusKommunikation.getInputService()
                                                            .getValue();
                            if (postEingang != null)
                            {
                                updateSpielStatus(postEingang);
                                initalisieren();
                            }
                            SpielstatusKommunikation.getInputService()
                                                    .restart();
                        }
                    });
            
            SpielstatusKommunikation.getInputService().setOnSucceeded(
                    new EventHandler<WorkerStateEvent>()
                    {
                        @Override public void handle (
                                WorkerStateEvent workerStateEvent)
                        {
                            Spielstatus postEingang =
                                    SpielstatusKommunikation.getInputService()
                                                            .getValue();
                            if (postEingang != null)
                            {
                                updateSpielStatus(postEingang);
                                initalisieren();
                            }
                            SpielstatusKommunikation.getInputService()
                                                    .restart();
                        }
                    });
            
            SpielstatusKommunikation.getInputService().start();
            return null;
        }
    }
}
