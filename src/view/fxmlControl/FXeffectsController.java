package view.fxmlControl;

import javafx.application.Platform;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import model.KarteEinheit;
import view.components.KarteVBox;

import java.util.Timer;
import java.util.TimerTask;

import static resources.KonstantenGUI.*;

/**
 Controllerklasse welche die visuelle rueckmeldung von Karten auf dem
 aktuellen
 Spielfeld kontrolliert */
public class FXeffectsController
{
    /**
     Methode welche Eine Karte temporaeren aufleuchten laesst und daraufhin
     ihre Darstellung aktualisiert
     @param zielFeld Pane welches aktualisiert wird
     @param verteidiger Karte dessen darstellung aktualisiert wird
     */
    protected static void glowAngriff (StackPane zielFeld,
                                       KarteEinheit verteidiger)
    {
        Glow glow = new Glow();
        glow.setLevel(GLOW_INTENS);//
        
        zielFeld.setEffect(glow);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run ()
            {
                zielFeld.setEffect(null);
                Platform.runLater(() ->
                                  {
                                      zielFeld.getChildren().clear();
                                      zielFeld.getChildren()
                                              .add(new KarteVBox(
                                                      verteidiger));
                                  });
                
            }
        }, GLOW_DELAY);
        
    }
    
    /**
     Methode welche Eine Karte mit einem temporaeren blur-effekt versieht
     @param zielFeld feld welches mit einem effekt versehen wird
     */
    protected static void glowSchildBrechen (StackPane zielFeld)
    {
        BoxBlur bb = new BoxBlur();
        zielFeld.setEffect(bb);
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run ()
            {
                zielFeld.setEffect(null);
            }
        }, BLUR_DELAY);//
    }
}
