package gui.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.StackPane;

public class RaumPane extends StackPane
{
    private BooleanProperty beinhaltetSpieler;
    private BooleanProperty nichtig;
    private final static String STYLE_CLASS = "raum-pane";
    private final static String PSEUDO_CLASS_BEINHALTET_SPIELER = "spieler";
    private final static String PSEUDO_CLASS_NICHTIG = "nichtig";

    public RaumPane()
    {
        getStyleClass().add(STYLE_CLASS);

        beinhaltetSpieler = new SimpleBooleanProperty(false);
        beinhaltetSpieler.addListener(e -> {
            pseudoClassStateChanged(
                    PseudoClass.getPseudoClass(PSEUDO_CLASS_BEINHALTET_SPIELER), beinhaltetSpieler.get());
        });

        nichtig = new SimpleBooleanProperty(false);
        nichtig.addListener(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(
                    PSEUDO_CLASS_NICHTIG), nichtig.get());
        });
    }

    public boolean getBeinhaltetSpieler() {
        return beinhaltetSpieler.get();
    }

    public void setBeinhaltetSpieler(boolean beinhaltetSpieler) {
        this.beinhaltetSpieler.set(beinhaltetSpieler);
    }

    public boolean istNichtig() {
        return nichtig.get();
    }

    public void setNichtig(boolean nichtig)
    {
        this.nichtig.set(nichtig);
    }
}
