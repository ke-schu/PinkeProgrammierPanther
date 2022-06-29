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

        StackPane zimmer = new StackPane();
        zimmer.setId("zimmer");

        if (raum == null || raum.getEreignis() == null)
        {
            this.setNichtig(true);
        }
        else
        {
            Label meinLabel = new Label(raum.getEreignis().getName());
            meinLabel.setWrapText(true);
            meinLabel.setTextAlignment(TextAlignment.CENTER);
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
