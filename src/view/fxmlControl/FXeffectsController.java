package view.fxmlControl;

import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;

import java.util.Timer;
import java.util.TimerTask;

public class FXeffectsController
{
    protected static void glow(StackPane zielFeld)
    {
        Glow glow = new Glow();
        glow.setLevel(0.9);
        zielFeld.setEffect(glow);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                zielFeld.setEffect(null);
                //what you want to do
            }
        }, 1000);
    }

}
