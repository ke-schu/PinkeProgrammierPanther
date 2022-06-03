package gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.VBox;

public class CharakterVBox extends VBox
{
    BooleanProperty gewaehlt;
    private final static String STYLE_CLASS = "charakter-vbox";
    private final static String PSEUDO_CLASS = "ausgewaehlt";

    public CharakterVBox()
    {
        getStyleClass().add(STYLE_CLASS);

        gewaehlt = new SimpleBooleanProperty(false);
        gewaehlt.addListener(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS), gewaehlt.get());
        });
    }

    public boolean istGewaehlt() {
        return gewaehlt.get();
    }

    public void setGewaehlt(boolean shiny) {
        this.gewaehlt.set(shiny);
    }
}
