package gui.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.css.PseudoClass;
import javafx.scene.layout.VBox;

/**
 * Modelliert eine VBox mit eigenen Priority-Klassen um die Charaktere
 * in der Szene darstellen zu können.
 */
public class CharakterVBox extends VBox
{
    private BooleanProperty gewaehlt;
    private BooleanProperty freigeschaltet;
    private final static String STYLE_CLASS = "charakter-vbox";
    private final static String PSEUDO_CLASS_GEWAEHLT = "ausgewaehlt";
    private final static String PSEUDO_CLASS_FREIGESCHALTET = "nicht-freigeschaltet";

    /**
     * Konstruiert die CharakterVBox und fügt Listener für die benötigten Priority-Klassen hinzu.
     */
    public CharakterVBox()
    {
        getStyleClass().add(STYLE_CLASS);

        gewaehlt = new SimpleBooleanProperty(false);
        gewaehlt.addListener(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_GEWAEHLT), gewaehlt.get());
        });

        /*
        Der Zustand, ob eine CharakterVBox freigeschaltet ist, ist in dieser
        Klasse invertiert implementiert, damit in der CSS-Datei die Pseudo-Klasse
        "nicht-freigeschaltet" angewendet werden kann (siehe Getter & Setter)
         */
        freigeschaltet = new SimpleBooleanProperty(false);
        freigeschaltet.addListener(e -> {
            pseudoClassStateChanged(PseudoClass.getPseudoClass(PSEUDO_CLASS_FREIGESCHALTET), freigeschaltet.get());
        });
    }

    /**
     * Gibt zurück, ob ein Charakter aktuell gewählt ist.
     * @return true, wenn er gewaehlt ist.
     */
    public boolean istGewaehlt() {
        return gewaehlt.get();
    }

    /**
     * Setzt den Zustand gewaehlt des Charakters.
     * @param gewaehlt true, wenn gewaehlt.
     */
    public void setGewaehlt(boolean gewaehlt) {
        this.gewaehlt.set(gewaehlt);
    }

    /**
     * Gibt zurück, ob ein Charakter freigeschaltet ist.
     * @return true, wenn er freigeschaltet ist.
     */
    public boolean istFreigeschaltet() {
        return !freigeschaltet.get();
    }

    /**
     * Setzt den Zustand freigeschaltet des Charakters.
     * @param freigeschaltet true, wenn freigeschaltet.
     */
    public void setFreigeschaltet(boolean freigeschaltet)
    {
        this.freigeschaltet.set(!freigeschaltet);
    }
}
