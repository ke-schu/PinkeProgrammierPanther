package view;

import java.io.File;

public interface GuiKonstanten
{
    //Strings fuer die Oberflaeche
    String SPIELTITEL = "DungeonDing";

    //Strings für Pfade
    String BILDER_PFAD = "src/view/bilder/";
    String PNG_DATEI_ENDUNG = ".png";
    File ICON = new File(BILDER_PFAD + "Logo" + PNG_DATEI_ENDUNG);
    String HAUPTMENUE_PFAD = "Hauptmenue.fxml";
    String HAUPTMENUE_PFAD_START = "xml/Hauptmenue.fxml";
    String EINSTELLUNG_PFAD = "Einstellungen.fxml";
    String CHARAKTERAUSWAHL_PFAD = "Charakterauswahl.fxml";
    String SPIELEBENE_PFAD = "Spielebene.fxml";
    String SPIELFELD_PFAD = "Spielfeld.fxml";



    //Integer fuer die Aufloesung
    int AUFLOESUNG_HOEHE_HD = 720;
    int AUFLOESUNG_BREITE_HD = 1280;
    int AUFLOESUNG_HOEHE_FULLHD = 1080;
    int AUFLOESUNG_BREITE_FULLHD = 1920;

    //Strings fuer die Aufloesung
    String AUFLOESUNGSGROESSENHD = "1280x720";
    String AUFLOESUNGSGROESSENFULLHD = "1920x1080";


    //Werte für die Sound Einstellungen
    double FAKTOR_FUER_LAUTSTAERKE = 0.01;
    String HAUPTMENUE_MUSIK = "src/view/mp3/fun-life-112188.mp3";
    String KLICK_SOUND = "src/view/mp3/Klick.wav";

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




    String STYLE_CHARAKTER_NAME = "charakter-name";
    String CHARAKTER_KAUFEN = "Kaufen für %d €";
    String SCHON_FREIGESCHALTET = "Im Besitz";
    String GOLD_BESTAND = "Goldbestand: ";
    String EREIGNIS_ANNEHMEN = "Annehmen";
    String EREIGNIS_ABLEHNEN = "Ablehnen";
    String EREIGNIS_GEHEN = "Gehen";
    String TRUHE_AUSFUEHREN_1 = "Ich hab hier ";
    String TRUHE_AUSFUEHREN_2 = " Gold gefunden! Wie cool.";
    String HEILER_AUSFUEHREN_1 = "Ich hab ";
    String HEILER_AUSFUEHREN_2 = " Lebenspunkte zurueckerhalten";
    String ZUFALLS_EREIGNIS_AUSFUEHREN = "Zufallsereignis wurde ausgefuehrt.";
}
