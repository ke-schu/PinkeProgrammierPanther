package resources;

import javafx.scene.layout.BackgroundSize;

/**
 * Hier werden finale, numerische Werte fuer die grafische Benutzeroberflaeche gesammelt.
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

    //Konstanten fuer Texte der Hilfefenster
    String HILFE = "Hilfe";
    String HILFE_TEXT = "Hier steht ein Text, der hilft.";
    String HILFE_HAUPTMENUE = "Das hier ist das Hauptmenü. Hier kann über die Knöpfe ein neues Spiel begonnen werden," +
            "falls bereits ein Spielstand existiert kann dieser über `Weiter` fortgesetzt werden." +
            "In den Einstellungen sind Möglichkeiten um die Lautstärke und Fenstergrößen anzupassen." +
            "Dies ist aber auch später im Spiel selbst über eine Menüleiste möglich. Viel Spaß!";
    String HILFE_EINSTELLUNGEN = "Hier können allgemeine Anwendungseinstellungen getätigt werden. Über das " +
            "Fenstergröße-Menü kann die Rahmengröße eingestellt werden. Der Slider kontrolliert die Lautstärke" +
            "aller Sounds dieser Anwendung.";
    String HILFE_CHARAKTERAUSWAHL = "Hier kann ein Charakter mit Deck für den nächsten Versuch ausgewählt werden." +
            "Es kann sein, dass noch nicht alle Charaktere freigeschaltet sind. Dies kann im laufe der Zeit" +
            "mit dem gefunden Gold geändert werden.";
    String POPUP_BUTTON_SCHLIESSEN = "Schließen";
    Integer VBOX_20 = 20;
    Integer POPUP_BREITE = 300;
    Integer POPUP_HOEHE = 400;

    //  Ereignisse
    Integer POPUP_VBOX = 20;
    Integer POPUP_VBOX_BREITE1 = 400;
    Integer POPUP_VBOX_HOEHE1 = 300;
    Integer POPUP_VBOX_BREITE2 = 700;
    Integer POPUP_VBOX_HOEHE2 = 500;

    BackgroundSize AUTO_HINTERGRUND = new BackgroundSize(
                    BackgroundSize.AUTO,
                    BackgroundSize.AUTO,
                    true,true, true,true);
}
