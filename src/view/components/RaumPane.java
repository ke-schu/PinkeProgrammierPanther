package view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import model.Raum;

import static resources.KonstantenGUI.SCHWARZ;
import static resources.StringsGUI.*;

public class RaumPane extends StackPane
{
    private BooleanProperty beinhaltetSpieler;
    private BooleanProperty nichtig;

    public RaumPane(Raum raum)
    {
        getStyleClass().add(STYLE_CLASS_RAUM);
        /*StackPane stackPane = new StackPane();
        stackPane.setBackground(SCHWARZ);
        stackPane.setMaxWidth(15);
        stackPane.setPrefHeight(this.getHeight());
        this.getChildren().add(stackPane);*/
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
                                          if(beinhaltetSpieler.get())
                                              meinLabel.setOpacity(0);
                                          else meinLabel.setOpacity(1);
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
            zimmer.setId("zimmer");
            meinLabel.setText(raum.getEreignis().getName());
            zimmer.getChildren().add(meinLabel);
            this.getChildren().add(zimmer);
        }
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
