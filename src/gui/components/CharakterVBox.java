package gui.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.VBox;

public class CharakterVBox extends VBox
{
    private BooleanProperty gewaehlt;
    private BooleanProperty freigeschaltet;
    private final static String STYLE_CLASS = "charakter-vbox";
    private final static String PSEUDO_CLASS_GEWAEHLT = "ausgewaehlt";
    private final static String PSEUDO_CLASS_FREIGESCHALTET = "nicht-freigeschaltet";

    public CharakterVBox()
    {
        getStyleClass().add(STYLE_CLASS);

        gewaehlt = new SimpleBooleanProperty(false);
        gewaehlt.addListener(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_GEWAEHLT), gewaehlt.get());
        });

        freigeschaltet = new SimpleBooleanProperty(false);
        freigeschaltet.addListener(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_FREIGESCHALTET), freigeschaltet.get());
        });
    }

    public boolean istGewaehlt() {
        return gewaehlt.get();
    }

    public void setGewaehlt(boolean gewaehlt) {
        this.gewaehlt.set(gewaehlt);
    }

    public boolean istFreigeschaltet() {
        return !freigeschaltet.get();
    }

    public void setFreigeschaltet(boolean freigeschaltet)
    {
        this.freigeschaltet.set(!freigeschaltet);
    }
}
