package view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Charakter;

import static resources.StringsGUI.*;

/**
 Modelliert eine VBox mit eigenen Priority-Klassen um die Charaktere in der
 Szene darstellen zu koennen. */
public class CharakterVBox extends VBox
{
    private BooleanProperty gewaehlt;
    private BooleanProperty freigeschaltet;
    
    /**
     Konstruiert die CharakterVBox und fügt Listener für die benoetigten
     Priority-Klassen hinzu.
     */
    public CharakterVBox (Charakter c)
    {
        getStyleClass().add(STYLE_CLASS_CHARAKTER);
        
        freigeschaltet = new SimpleBooleanProperty(c.getFreigeschaltet());
        freigeschaltet.addListener(e ->
                                   {
                                       this.setFreigeschaltet(
                                               freigeschaltet.get());
                                       initialisiere(c);
                                   });
        gewaehlt = new SimpleBooleanProperty(false);
        gewaehlt.addListener(e -> pseudoClassStateChanged(
                PseudoClass.getPseudoClass(PSEUDO_CLASS_GEWAEHLT),
                gewaehlt.get()));
        initialisiere(c);
    }
    
    /**
     Diese Methode designt und initialisiert die CharakterVBox.
     @param c der Charakter, welcher eingefügt werden soll.
     */
    private void initialisiere (Charakter c)
    {
        pseudoClassStateChanged(
                PseudoClass.getPseudoClass(PSEUDO_CLASS_FREIGESCHALTET),
                !freigeschaltet.get());
        this.getChildren().clear();
        Label name = new Label(c.getName());
        name.setId(STYLE_CHARAKTER_NAME);
        this.getChildren().add(name);
        
        if (!freigeschaltet.get())
        {
            this.getChildren().add(new Label(
                    String.format(CHARAKTER_KAUFEN,
                                  c.getFreischaltgebuehr())));
        }
        else
        {
            this.getChildren().add(new Label(SCHON_FREIGESCHALTET));
        }
    }
    
    /**
     Setzt den Zustand freigeschaltet des Charakters.
     @param freigeschaltet true, wenn freigeschaltet.
     */
    public void setFreigeschaltet (boolean freigeschaltet)
    {
        this.freigeschaltet.set(freigeschaltet);
    }
    
    /**
     Gibt zurück, ob ein Charakter aktuell gewaehlt ist.
     @return true, wenn er gewaehlt ist.
     */
    public boolean istGewaehlt ()
    {
        return gewaehlt.get();
    }
    
    /**
     Setzt den Zustand gewaehlt des Charakters.
     @param gewaehlt true, wenn gewaehlt.
     */
    public void setGewaehlt (boolean gewaehlt)
    {
        this.gewaehlt.set(gewaehlt);
    }
    
    /**
     Gibt zurueck, ob ein Charakter freigeschaltet ist.
     @return true, wenn er freigeschaltet ist.
     */
    public boolean istFreigeschaltet ()
    {
        return freigeschaltet.get();
    }
}
