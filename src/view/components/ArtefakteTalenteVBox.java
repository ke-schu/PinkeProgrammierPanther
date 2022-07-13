package view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import resources.Artefakte;
import resources.Talente;

import java.io.File;

import static resources.KonstantenGUI.KARTE_GROSS_VBOX_BILD_BREITE;
import static resources.KonstantenGUI.KARTE_GROSS_VBOX_BILD_HOEHE;
import static resources.StringsGUI.*;

/**
 * Diese Klasse enthaelt eine gesonderte VBox fuer Artefakte und Talente
 */
public class ArtefakteTalenteVBox extends VBox
{
    /**
     * Konstruktor, welcher mit dem uebergebenen Artefakt eine entsprechdende VBox erstellt.
     * @param artefakt Artefakt, mit welchem die VBox erstellt wird.
     */
    public ArtefakteTalenteVBox(Artefakte artefakt)
    {
        getStyleClass().add(STYLE_CLASS_ARTEFAKTE_TALENTE);
        alignmentProperty().set(Pos.CENTER);
        this.setMaxWidth(KARTE_GROSS_VBOX_BILD_BREITE);
        Label name = new Label();
        name.setText(artefakt.getNAME());
        this.getChildren().add(name);
        File charakterbild = new File(BILDER_PFAD + artefakt.name() + PNG_DATEI_ENDUNG);
        ImageView bild = new ImageView();
        bild.setFitHeight(KARTE_GROSS_VBOX_BILD_HOEHE);
        bild.setFitWidth(KARTE_GROSS_VBOX_BILD_BREITE);
        Image b = new Image(charakterbild.getAbsolutePath());
        bild.setImage(b);
        this.getChildren().add(bild);
        Label beschreibung = new Label();
        beschreibung.setWrapText(true);
        beschreibung.setText(artefakt.getBESCHREIBUNG());
       this.getChildren().add(beschreibung);
    }

    /**
     * Konstruktor, welcher mit dem uebergebenen Talent eine entsprechdende VBox erstellt.
     * @param talent Artefakt, mit welchem die VBox erstellt wird.
     */
    public ArtefakteTalenteVBox(Talente talent)
    {
        getStyleClass().add(STYLE_CLASS_ARTEFAKTE_TALENTE);
        alignmentProperty().set(Pos.CENTER);
        this.setMaxWidth(KARTE_GROSS_VBOX_BILD_BREITE);
        Label name = new Label();
        name.setText(talent.getNAME());
        this.getChildren().add(name);
        File charakterbild = new File(BILDER_PFAD + talent.name() + PNG_DATEI_ENDUNG);
        ImageView bild = new ImageView();
        bild.setFitHeight(KARTE_GROSS_VBOX_BILD_HOEHE);
        bild.setFitWidth(KARTE_GROSS_VBOX_BILD_BREITE);
        Image b = new Image(charakterbild.getAbsolutePath());
        bild.setImage(b);
        this.getChildren().add(bild);
        Label beschreibung = new Label();
        beschreibung.setWrapText(true);
        beschreibung.setText(talent.getBESCHREIBUNG());
        this.getChildren().add(beschreibung);
    }
}
