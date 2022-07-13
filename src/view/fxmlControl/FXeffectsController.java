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

public class FXeffectsController
{
    protected static void glowangriff(StackPane zielFeld, KarteEinheit verteidiger)
    {
        Glow glow = new Glow();
        glow.setLevel(GLOW_INTENS);//

        zielFeld.setEffect(glow);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                zielFeld.setEffect(null);
                Platform.runLater(() ->
                        {
                            zielFeld.getChildren().clear();
                            zielFeld.getChildren().add(new KarteVBox(verteidiger));
                        });

            }
        }, GLOW_DELAY);

    }
    protected static void glowschildbreak(StackPane zielFeld)
    {
        BoxBlur bb = new BoxBlur();
        zielFeld.setEffect(bb);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                zielFeld.setEffect(null);
            }
        },  BLUR_DELAY);//
    }
}
