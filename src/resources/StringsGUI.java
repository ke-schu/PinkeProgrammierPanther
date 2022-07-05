package resources;

import java.io.File;

public interface StringsGUI
{
    String SPIELTITEL = "DungeonDing";
    String SPIELSTAND = "Spielstand";

    //  Pfade
    String BILDER_PFAD = "src/view/bilder/";
    String PNG_DATEI_ENDUNG = ".png";
    String ICON_PFAD = BILDER_PFAD + "Logo" + PNG_DATEI_ENDUNG;
    File ICON = new File(ICON_PFAD);
    String HAUPTMENUE_MUSIK = "src/view/mp3/fun-life-112188.mp3";
    String KLICK_SOUND = "src/view/mp3/Klick.wav";

    //  FXML Pfade
    String FXML_PFADE = "src/view/fxml/";
    String HAUPTMENUE_PFAD = FXML_PFADE + "Hauptmenue.fxml";
    String EINSTELLUNG_PFAD = FXML_PFADE + "Einstellungen.fxml";
    String CHARAKTERAUSWAHL_PFAD = FXML_PFADE + "Charakterauswahl.fxml";
    String SPIELEBENE_PFAD = FXML_PFADE + "Spielebene.fxml";
    String SPIELER_FELD_PFAD = FXML_PFADE + "Spielfeld.fxml";
    String GEGENSPIELER_FELD_PFAD = FXML_PFADE + "Gegenspielerfeld.fxml";
    String SPIELSTAND_PFAD = FXML_PFADE + "Spielstand.fxml";

    //  Aufloesung
    String AUFLOESUNG_GROESSE_HD = "1280x720";
    String AUFLOESUNG_GROESSE_FULLHD = "1920x1080";

    //  Strings fuer Ereignisse
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
    String TEMPEL_AUSFUEHREN = "Wähle die Karte, welche aus deinem Deck entfernt werden soll.";
    String SCHMIED_AUSFUEHREN = "Wähle die Karte, welche verbessert werden soll";
    String HAENDLER_AUSFUEHREN = "Wähle die Karte, welche du deinem Deck hinzufügen willst";

    //  Exception
    String STRING_FEHLER_WECHSEL_SPIELFELD =
            "Zum Spielfeld wechseln hat nicht geklappt.";

    //  CSS
    String STYLE_CLASS_CHARAKTER = "charakter-vbox";
    String PSEUDO_CLASS_GEWAEHLT = "ausgewaehlt";
    String PSEUDO_CLASS_FREIGESCHALTET = "nicht-freigeschaltet";
    String STYLE_CLASS_KARTE = "karte-vbox";
    String STYLE_CLASS_RAUM = "raum-pane";
    String PSEUDO_CLASS_BEINHALTET_SPIELER = "spieler";
    String PSEUDO_CLASS_NICHTIG = "nichtig";

    //  Hilfe
    String HILFE_HAUPTMENUE =
            "Das hier ist das Hauptmenü. Hier kann über die Knöpfe ein neues " +
            "Spiel begonnen werden," +
            "falls bereits ein Spielstand existiert kann dieser über `Weiter`" +
            " fortgesetzt werden." +
            "In den Einstellungen sind Möglichkeiten um die Lautstärke und " +
            "Fenstergrößen anzupassen." +
            "Dies ist aber auch später im Spiel selbst über eine Menüleiste " +
            "möglich. Viel Spaß!";
    String HILFE_EINSTELLUNGEN =
            "Hier können allgemeine Anwendungseinstellungen getätigt werden. " +
            "Über das " +
            "Fenstergröße-Menü kann die Rahmengröße eingestellt werden. Der " +
            "Slider kontrolliert die Lautstärke" +
            "aller Sounds dieser Anwendung.";
    String HILFE_CHARAKTERAUSWAHL =
            "Hier kann ein Charakter mit Deck für den nächsten Versuch " +
            "ausgewählt werden." +
            "Es kann sein, dass noch nicht alle Charaktere freigeschaltet " +
            "sind. Dies kann im laufe der Zeit" +
            "mit dem gefunden Gold geändert werden.";
    String HILFE = "Hilfe";
    String HILFE_TEXT = "Hier steht ein Text, der hilft.";
    String POPUP_BUTTON_SCHLIESSEN = "Schließen";
}
