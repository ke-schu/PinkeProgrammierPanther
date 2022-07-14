package view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import model.Raum;

import static resources.StringsGUI.*;

/**
 RaumPane erbt von StackPane und wird zu der visualisierung von Raeumen
 benutzt. */
public class RaumPane extends StackPane
{
    private BooleanProperty beinhaltetSpieler;
    private BooleanProperty nichtig;
    
    /**
     Konstruktor von RaumPane.
     @param raum Instanz der Klasse Raum, die mit einem RaumPane visualisiert
     werden soll.
     */
    public RaumPane (Raum raum)
    {
        getStyleClass().add(STYLE_CLASS_RAUM);
        Label meinLabel = new Label();
        meinLabel.setWrapText(true);
        meinLabel.setTextAlignment(TextAlignment.CENTER);
        beinhaltetSpieler = new SimpleBooleanProperty(false);
        beinhaltetSpieler.addListener(e ->
                                      {
                                          pseudoClassStateChanged(
                                                  PseudoClass.getPseudoClass(
                                                          PSEUDO_CLASS_BEINHALTET_SPIELER),
                                                  beinhaltetSpieler.get());
                                          if (beinhaltetSpieler.get())
                                          {
                                              meinLabel.setOpacity(0);
                                          }
                                          else
                                          {
                                              meinLabel.setOpacity(1);
                                          }
                                      });
        nichtig = new SimpleBooleanProperty(false);
        nichtig.addListener(e ->
                            {
                                pseudoClassStateChanged(
                                        PseudoClass.getPseudoClass(
                                                PSEUDO_CLASS_NICHTIG),
                                        nichtig.get());
                            });
        if (raum == null || raum.getEreignis() == null)
        {
            this.setNichtig(true);
        }
        else
        {
            StackPane zimmer = new StackPane();
            zimmer.setId(ZIMMER);
            meinLabel.setText(raum.getEreignis().getName());
            zimmer.getChildren().add(meinLabel);
            this.getChildren().add(zimmer);
        }
    }
    
    /**
     Methode um das Attribut nichtig zu setzen.
     @param nichtig boolescher Wert, welcher in das Attribut gesetzt werden
     soll.
     */
    public void setNichtig (boolean nichtig)
    {
        this.nichtig.set(nichtig);
    }
    
    /**
     Methode um das Attribut beinhaltetSpieler zu setzen.
     @param beinhaltetSpieler boolescher Wert, welcher in das Attribut gesetzt
     werden soll.
     */
    public void setBeinhaltetSpieler (boolean beinhaltetSpieler)
    {
        this.beinhaltetSpieler.set(beinhaltetSpieler);
    }
}
