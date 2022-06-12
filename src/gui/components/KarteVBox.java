package gui.components;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.Karte;
import model.KarteEinheit;
import model.KarteZauber;
import model.Spieler;

import java.io.File;

import static gui.GuiKonstanten.*;

public class KarteVBox extends VBox
{
    private final static String STYLE_CLASS = "karte-vbox";
    private final static BackgroundSize
            AUTO_HINTERGRUND = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,true,true,true);
    private final int KARTEN_BREITE = 100;
    private final int KARTEN_HOEHE = 100;

    public KarteVBox(Karte karte)
    {
        getStyleClass().add(STYLE_CLASS);
        this.setMaxHeight(KARTEN_HOEHE);
        this.setMaxWidth(KARTEN_BREITE);
        this.setMinHeight(KARTEN_HOEHE);
        this.setMinWidth(KARTEN_BREITE);

        //Image bild = new Image("https://cdn.pixabay.com/photo/2021/01/24/20/21/cloud-5946381_960_720.jpg");
        File meinBild = new File(BILDER_PFAD + karte.getName() + PNG_DATEI_ENDUNG);
        Background hintergrund = new Background(new BackgroundImage(
                new Image(meinBild.getAbsolutePath()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                AUTO_HINTERGRUND));
        this.setBackground(hintergrund);

        this.getChildren().add(new Label(karte.getName()));

        if(karte instanceof KarteEinheit)
            einfuegenKarteEinheit((KarteEinheit) karte);
        if(karte instanceof KarteZauber)
            einfuegenKarteZauber((KarteZauber) karte);
    }

    private void einfuegenKarteEinheit(KarteEinheit einheit)
    {
        this.getChildren().add(new Label("Mana: " + einheit.getManaKosten()));
        this.getChildren().add(new Label("Macht: " + einheit.getMacht()));
        this.getChildren().add(new Label("HP: " + einheit.getLebenspunkte()));
    }

    private void einfuegenKarteZauber(KarteZauber zauber)
    {
    }

   /* private String ersetzeUmlaute(String s)
    {
        s.replaceAll("ä", "ae");
        s.replaceAll("ü", "ue");
        s.replaceAll("ö", "oe");
        s.replaceAll("ß", "ss");
        return s;
    }*/
}
