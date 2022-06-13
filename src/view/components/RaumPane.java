package view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.StackPane;

import static resources.StringsGUI.*;

public class RaumPane extends StackPane
{
    private BooleanProperty beinhaltetSpieler;
    private BooleanProperty nichtig;

    public RaumPane()
    {
        getStyleClass().add(STYLE_CLASS_RAUM);

        beinhaltetSpieler = new SimpleBooleanProperty(false);
        beinhaltetSpieler.addListener(e ->
                                      {
                                          pseudoClassStateChanged(
                                                  PseudoClass.getPseudoClass(
                                                          PSEUDO_CLASS_BEINHALTET_SPIELER),
                                                  beinhaltetSpieler.get());
                                      });

        nichtig = new SimpleBooleanProperty(false);
        nichtig.addListener(e ->
                            {
                                pseudoClassStateChanged(
                                        PseudoClass.getPseudoClass(
                                                PSEUDO_CLASS_NICHTIG),
                                        nichtig.get());
                            });
    }

    public boolean getBeinhaltetSpieler()
    {
        return beinhaltetSpieler.get();
    }

    public void setBeinhaltetSpieler(boolean beinhaltetSpieler)
    {
        this.beinhaltetSpieler.set(beinhaltetSpieler);
    }

    public boolean istNichtig()
    {
        return nichtig.get();
    }

    public void setNichtig(boolean nichtig)
    {
        this.nichtig.set(nichtig);
    }
}
