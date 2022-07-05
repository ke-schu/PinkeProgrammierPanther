package resources;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Hier werden finale, numerische Werte fuer die grafische
 * Benutzeroberflaeche gesammelt.
 */
public interface KonstantenGUI
{
    //  Aufloesung
    int AUFLOESUNG_HOEHE_HD = 720;
    int AUFLOESUNG_BREITE_HD = 1280;
    int AUFLOESUNG_HOEHE_FULLHD = 1080;
    int AUFLOESUNG_BREITE_FULLHD = 1920;

    //  Sound Einstellungen
    double FAKTOR_FUER_LAUTSTAERKE = 0.01;

    //  Hilfefenster
    int VBOX_20 = 20;
    int POPUP_BREITE = 300;
    int POPUP_HOEHE = 400;

    //  Spielfeld
    int FELD_GROESSE = 80;
    int KARTENHAND_GROESSE = 100;

    //  Ereignisse
    int POPUP_VBOX = 20;
    int POPUP_VBOX_BREITE1 = 400;
    int POPUP_VBOX_HOEHE1 = 300;
    int POPUP_VBOX_BREITE2 = 700;
    int POPUP_VBOX_HOEHE2 = 500;

    BackgroundSize AUTO_HINTERGRUND =
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,
                               true, true, true);
    Background SCHWARZ = new Background(
            new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
}
