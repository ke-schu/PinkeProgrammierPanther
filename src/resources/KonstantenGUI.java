package resources;

import javafx.scene.layout.BackgroundSize;

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
    Integer VBOX_20 = 20;
    Integer POPUP_BREITE = 300;
    Integer POPUP_HOEHE = 400;

    //  Ereignisse
    Integer POPUP_VBOX = 20;
    Integer POPUP_VBOX_BREITE1 = 400;
    Integer POPUP_VBOX_HOEHE1 = 300;
    Integer POPUP_VBOX_BREITE2 = 700;
    Integer POPUP_VBOX_HOEHE2 = 500;

    BackgroundSize AUTO_HINTERGRUND =
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,
                               true, true, true);
}
