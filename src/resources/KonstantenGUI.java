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

    // Components
    int KARTE_GROSS_VBOX_BILD_HOEHE = 300;
    int KARTE_GROSS_VBOX_BILD_BREITE = 200;

    // Spielstand Kartendeckanzeige
    int SPALTENAHNZAHL_KARTENDECK_ANZEIGE = 5;

    //  Hilfefenster
    int VBOX_20 = 20;
    int POPUP_BREITE = 300;
    int POPUP_HOEHE = 400;

    //  Spielfeld
    int KARTENHAND_GROESSE = 100;

    //  Ereignisse
    int POPUP_VBOX = 20;
    int POPUP_VBOX_BREITE1 = 400;
    int POPUP_VBOX_HOEHE1 = 300;
    int POPUP_VBOX_BREITE2 = 700;
    int POPUP_VBOX_HOEHE2 = 500;

    //FXeffects
    double GLOW_INTENS = 1.0;
    int GLOW_DELAY = 2000;
    int BLUR_DELAY = 2000;

    //rueckmeldung angriff
    int RUECKMELDUNG_SCHADEN = 1;
    int RUECKMELDUNG_SCHILDBREAK = 2;
    int RUECKMELDUNG_GESTORBEN = 3;
    int RUECKMELDUNG_ERFOLGLOS = 0;

    //Konstanten fuer die Waffen
    int SCHWERT_MACHT = 2;
    int SCHWERT_REICHWEITE = 1;
    int AXT_MACHT = 3;
    int AXT_REICHWEITE = 1;
    int BOGEN_MACHT = 1;
    int BOGEN_REICHWEITE = 2;
    int FAEUSTE_MACHT = 0;
    int FAEUSTE_REICHWEITE = 1;




    BackgroundSize AUTO_HINTERGRUND =
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,
                               true, true, true);
    Background SCHWARZ = new Background(
            new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
}
