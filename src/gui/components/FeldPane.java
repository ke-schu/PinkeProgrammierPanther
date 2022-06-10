package gui.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.StackPane;



public class FeldPane extends StackPane
{
    private BooleanProperty beinhaltetKarte;
    private BooleanProperty keineKarte;
    private final static String STYLE_CLASS = "feld-pane";
    private final static String PSEUDO_CLASS_BEINHALTET_KARTE = "karte";
    private final static String PSEUDO_CLASS_KEINEKARTE = "keinekarte";

    public FeldPane ()
    {
        getStyleClass().add(STYLE_CLASS);

        beinhaltetKarte = new SimpleBooleanProperty(false);
        beinhaltetKarte.addListener(e ->
        {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_BEINHALTET_KARTE), beinhaltetKarte.get());
        });

        keineKarte = new SimpleBooleanProperty(false);
        keineKarte.addListener(e ->
        {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_KEINEKARTE), keineKarte.get());
        });

    }


    public boolean getBeinhaltetKarte ()
    {
        return beinhaltetKarte.get();
    }

    public void setBeinhaltetKarte (boolean beinhaltetSpieler)
    {
        this.beinhaltetKarte.set(beinhaltetSpieler);
    }

    public boolean getkeineKarte ()
    {
        return keineKarte.get();
    }

    public void setkeineKarte (boolean keineKarte)
    {
        this.keineKarte.set(keineKarte);
    }
}
