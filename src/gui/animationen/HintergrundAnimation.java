package gui.animationen;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HintergrundAnimation extends Transition
{
    private Region objekt;
    private Color startFarbe;
    private Color endFarbe;

    public HintergrundAnimation(Region objekt, Color startFarbe, Color endFarbe,
                                int zeit)
    {
        this.objekt = objekt;
        this.startFarbe = startFarbe;
        this.endFarbe = endFarbe;
        setCycleDuration(Duration.millis(zeit));
        setInterpolator(Interpolator.EASE_OUT);
    }

    @Override
    protected void interpolate(double frac) {
        Color vColor = new Color(startFarbe.getRed() + endFarbe.getRed()/frac*10,
                                 startFarbe.getGreen() + endFarbe.getGreen()/frac*10,
                                 startFarbe.getBlue() + endFarbe.getBlue()/frac*10,
                                 startFarbe.getOpacity() + endFarbe.getOpacity()/frac*10);
        objekt.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
