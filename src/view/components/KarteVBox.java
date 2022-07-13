package view.components;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.Karte;
import model.KarteEinheit;
import model.KarteZauber;
import utility.KonsolenIO;

import java.io.File;
import java.io.FileNotFoundException;

import static resources.KonstantenGUI.AUTO_HINTERGRUND;
import static resources.Strings.KARTE_BILD_NICHT_GEFUNDEN_INFO;
import static resources.StringsGUI.*;

/**
 Eine Karte ist im Spiel als VBox implementiert. Sie wird mithilfe dieser
 Klasse modelliert und naeher ausformuliert. */
public class KarteVBox extends VBox
{
    private Karte karte;
    
    /**
     Konstruiert eine neue KarteVBox.
     @param karte die Karte, die dargestellt werden soll.
     */
    public KarteVBox (Karte karte)
    {
        this.karte = karte;
        getStyleClass().add(STYLE_CLASS_KARTE);
        
        try
        {
            hintergrundSetzen();
        }
        catch (FileNotFoundException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
        
        this.getChildren().add(new Label(karte.getName()));
    
        if (karte instanceof KarteEinheit)
        {
            einfuegenKarteEinheit((KarteEinheit) karte);
        }
        if (karte instanceof KarteZauber)
        {
            einfuegenKarteZauber((KarteZauber) karte);
        }
    }
    
    /**
     Setzt das Bild der Karte in den Hintergrund ein.
     * @throws FileNotFoundException Entsteht, falls kein Bild mit identischem Kartennamem gefunden wurde.
     */
    private void hintergrundSetzen () throws FileNotFoundException
    {
        File meinBild =
                new File(BILDER_PFAD + karte.getName() + PNG_DATEI_ENDUNG);
        if (!meinBild.exists())
        {
            throw new FileNotFoundException(String.format(
                    KARTE_BILD_NICHT_GEFUNDEN_INFO, karte.getName()));
        }
        Background hintergrund = new Background(
                new BackgroundImage(new Image(meinBild.getAbsolutePath()),
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.DEFAULT,
                                    AUTO_HINTERGRUND));
        this.setBackground(hintergrund);
    }
    
    /**
     Fuegt einige Labels fuer eine Karte Einheit in die Box ein.
     @param einheit die einzufuegende Einheit
     */
    private void einfuegenKarteEinheit (KarteEinheit einheit)
    {
        this.getChildren().add(new Label(MANA_STAT + einheit.getManaKosten()));
        this.getChildren().add(new Label(MACHT_STAT + einheit.getMacht()));
        this.getChildren().add(new Label(LP_STAT + einheit.getLebenspunkte()));
    }
    
    /**
     Fuegt einige Labels fuer eine Karte Zauber in die Box ein.
     @param zauber der einzufuegende Zauber
     */
    private void einfuegenKarteZauber (KarteZauber zauber)
    {
    }
}
