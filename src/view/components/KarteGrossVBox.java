package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Karte;

import java.io.File;

import static resources.StringsGUI.BILDER_PFAD;
import static resources.StringsGUI.PNG_DATEI_ENDUNG;

public class KarteGrossVBox extends VBox
{

    public KarteGrossVBox(Karte karte)
    {
        alignmentProperty().set(Pos.CENTER);
        Label name = new Label();
        name.setText(karte.getName());
        this.getChildren().add(name);
        File charakterbild = new File(BILDER_PFAD + karte.getName() + PNG_DATEI_ENDUNG);
        ImageView bild = new ImageView();
        bild.setFitHeight(300);
        bild.setFitWidth(200);
        Image b = new Image(charakterbild.getAbsolutePath());
        bild.setImage(b);
        this.getChildren().add(bild);
        this.getChildren().add(new Label(Integer.toString(karte.getLevel())));

    }

}
