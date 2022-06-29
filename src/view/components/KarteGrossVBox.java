package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Karte;
import model.KarteEinheit;

import java.io.File;

import static resources.StringsGUI.BILDER_PFAD;
import static resources.StringsGUI.PNG_DATEI_ENDUNG;

public class KarteGrossVBox extends VBox
{

    public KarteGrossVBox(Karte karte)
    {
        alignmentProperty().set(Pos.CENTER);
        Label name = new Label();
        name.setText("Name: " + karte.getName());
        this.getChildren().add(name);
        File charakterbild = new File(BILDER_PFAD + karte.getName() + PNG_DATEI_ENDUNG);
        ImageView bild = new ImageView();
        bild.setFitHeight(300);
        bild.setFitWidth(200);
        Image b = new Image(charakterbild.getAbsolutePath());
        bild.setImage(b);
        this.getChildren().add(bild);
        this.getChildren().add(new Label("Level: " + Integer.toString(karte.getLevel())));
        if (testeKarteEinheit(karte))
        {
            KarteEinheit ekarte = (KarteEinheit)karte;
            this.getChildren().add(new Label("LP: "+ Integer.toString(ekarte.getLebenspunkte())));
            this.getChildren().add(new Label("Reichweite: " + Integer.toString(ekarte.getReichweite())));
            this.getChildren().add(new Label("Macht: " + Integer.toString(ekarte.getMacht())));
            this.getChildren().add(new Label("Verteidigung: " + Integer.toString(ekarte.getVerteidigung())));
            this.getChildren().add(new Label("Schild: " + Integer.toString(ekarte.getSchild())));
            this.getChildren().add(new Label("Mana: " + Integer.toString(ekarte.getManaKosten())));
        }
    }

    public boolean testeKarteEinheit (Karte karte)
    {
        if(karte.getClass() == KarteEinheit.class)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
