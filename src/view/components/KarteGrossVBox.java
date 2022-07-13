package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Karte;
import model.KarteEinheit;

import java.io.File;

import static resources.KonstantenGUI.KARTE_GROSS_VBOX_BILD_BREITE;
import static resources.KonstantenGUI.KARTE_GROSS_VBOX_BILD_HOEHE;
import static resources.StringsGUI.*;

/**
 Diese Klasse beinhaltet eine eigene VBox um die Karten in grosser Darstellung
 anzuzeigen */
public class KarteGrossVBox extends VBox
{
    
    /**
     Konstruktor der mit der uebergebenen Instanz der Klasse Karte eine
     ausfuehrliche VBox fuellt.
     @param karte Instanz der Klasse Karte mit welcher die VBox gefuellt
     werden
     soll.
     */
    public KarteGrossVBox (Karte karte)
    {
        getStyleClass().add(STYLE_CLASS_KARTEGROSS);
        alignmentProperty().set(Pos.CENTER);
        Label name = new Label();
        name.setText(karte.getName());
        this.getChildren().add(name);
        File charakterbild =
                new File(BILDER_PFAD + karte.getName() + PNG_DATEI_ENDUNG);
        ImageView bild = new ImageView();
        bild.setFitHeight(KARTE_GROSS_VBOX_BILD_HOEHE);
        bild.setFitWidth(KARTE_GROSS_VBOX_BILD_BREITE);
        Image b = new Image(charakterbild.getAbsolutePath());
        bild.setImage(b);
        this.getChildren().add(bild);
        this.getChildren()
            .add(new Label(LEVEL_STAT + (karte.getLevel())));
        if (testeKarteEinheit(karte))
        {
            KarteEinheit ekarte = (KarteEinheit) karte;
            this.getChildren().add(new Label(
                    LP_STAT + (ekarte.getLebenspunkte())));
            this.getChildren().add(new Label(REICHWEITE_STAT +
                                             (
                                                     ekarte.getReichweite())));
            this.getChildren().add(new Label(
                    MACHT_STAT + (ekarte.getMacht())));
            this.getChildren().add(new Label(VERTEIDIGUNG_STAT +
                                             (
                                                     ekarte.getVerteidigung())));
            this.getChildren().add(new Label(
                    SCHILD_STAT + (ekarte.getSchild())));
            this.getChildren().add(new Label(
                    MANA_STAT + (ekarte.getManaKosten())));
        }
    }
    
    /**
     Testet, ob die uebergebene Karte der Klasse Einheit oder Zauber
     zugerhoerig
     ist.
     @param karte Instanz der Klasse Karte, welche geprüft werden soll.
     @return gibt ein boolschen Wert zurueck, der angibt zu welcher Klasse die
     Karte gehört.
     */
    public boolean testeKarteEinheit (Karte karte)
    {
        if (karte.getClass() == KarteEinheit.class)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
