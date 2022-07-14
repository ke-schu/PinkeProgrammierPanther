package resources;

import java.io.File;

import static resources.Konstanten.*;

/**
 Das Interface enthaelt konstante Zeichenketten fuer die Konfiguration der grafischen Buntzeroberflaeche. */
public interface StringsGUI
{
    String SPIELTITEL = "AbenteuerStadt";
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
    
    // CSS Pfade
    String SPIELSTAND_KARTENDECK_CSS_PFAD =
            "/view/css/SpielstandKartendeck.css";
    String SPIELSTAND_ARTEFAKTE_TALENTE_CSS_PFAD =
            "/view/css/SpielstandArtefakteTalente.css";
    String SPIELSTAND_CSS_PFAD = "/view/css/Spielstand.css";
    
    
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
    String TEMPEL_AUSFUEHREN =
            "Wähle die Karte, welche aus deinem Deck entfernt werden soll.";
    String SCHMIED_AUSFUEHREN =
            "Wähle die Karte, welche verbessert werden soll";
    String HAENDLER_AUSFUEHREN =
            "Wähle die Karte, welche du deinem Deck hinzufügen willst";
    String ZE_1_AUSGEFUEHRT = "Hier ist nichts...";
    String ZE_2_AUSGEFUEHRT =
            "Meine Macht wurde um " + ZE_MACHT_ERHOEHUNG + " erhöht.";
    String ZE_3_AUSGEFUEHRT =
            "Mein Mana wurde um " + ZE_MANA_ERHOEHUNG + " erhöht.";
    String ZE_4_AUSGEFUEHRT =
            "Meine Lebenspunkte wurden um " + ZE_SCHADEN + " verringert.";
    String ZE_5_AUSGEFUEHRT =
            "Ich hab hier diese Waffe gefunden. Wenn ich sie mitnehmen will" +
            " muss ich aber meine bisherige hier lassen. ";
    String MITNEHMEN_BUTTON = "Mitnehmen";
    String WAFFE_MACHT = "Macht: ";
    String WAFFE_REICHWEITE = "Reichweite: ";
    String GRATIS_HANDELN = "Gratisinteraktionen bei diesem Ereignis: ";
    String KOSTEN = "Der aktuelle Preis bei diesem Ereignis: ";
    String PREISERHOEHUNG = "Aktionen bis Preiserhöhung: ";
    String AKTUELLES_GOLD = "Mein aktuelles Gold: ";
    String AKTUELLE_LEBENSPUNKTE = "Mein aktuelles Leben: ";
    String ZU_WENIG_GOLD = "Ich habe zu wenig Gold zum handeln.";
    String WIR_SIND_AM_ZUG = "Wir sind in Zug: ";
    
    //  Exception
    String STRING_FEHLER_WECHSEL_SPIELFELD =
            "Zum Spielfeld wechseln hat nicht geklappt.";
    
    //  CSS
    String STYLE_CLASS_ARTEFAKTE_TALENTE = "artefakte-talente-vbox";
    String STYLE_CLASS_CHARAKTER = "charakter-vbox";
    String STYLE_CLASS_KARTEGROSS = "karte-vbox-gross";
    String PSEUDO_CLASS_GEWAEHLT = "ausgewaehlt";
    String PSEUDO_CLASS_FREIGESCHALTET = "nicht-freigeschaltet";
    String STYLE_CLASS_KARTE = "karte-vbox";
    String STYLE_CLASS_RAUM = "raum-pane";
    String PSEUDO_CLASS_BEINHALTET_SPIELER = "spieler";
    String PSEUDO_CLASS_NICHTIG = "nichtig";
    String PSEUDO_CLASS_ERROR = "error";
    
    // Strings der KarteGrossVBox
    String LEVEL_STAT = "Level: ";
    String LP_STAT = "LP: ";
    String REICHWEITE_STAT = "Reichweite: ";
    String MACHT_STAT = "Macht: ";
    String VERTEIDIGUNG_STAT = "Verteidigung: ";
    String SCHILD_STAT = "Schild: ";
    String MANA_STAT = "Mana: ";

    // Strings der eigenen Komponenten
    String ZIMMER = "zimmer";
    String FELD = "feld";
    
    // Strings der Spielstandanzeige
    String GOLD = "Gold: ";
    String DECKNAHME = "Deckname: ";
    String SCHRAEGSTRICH = "/";
    String ERFAHRUNGSPUNKTE = "EP: ";
    String MANA_MAX = "Mana: ";
    String WAFFEN_ANZEIGE = "Waffe: ";
    
    String CHARME_BESCHREIBUNG = "Charme gibt einen Rabatt bei Händlern";
    String CHARME_NAME = "Charme";
    String GROSSE_HAND_BESCHREIBUNG =
            "Erhöht die Kapazität der Kartenhand um +1 Karte.";
    String GROSSE_HAND_NAME = "Große Hand";
    String SCHUTZENGEL_BESCHREIBUNG =
            "Sollte der Spieler sterben, während der den Schutzengel hat," +
            "so wird er mit der Hälfte des maximalen Leben wiederbelebt." +
            "Das Artefakt Schutzengel ist nur einmal benutzbar.";
    String SCHUTZENGEL_NAME = "Schutzengel";
    String TACKER_NAME = "Tacker";
    String TACKER_BESCHREIBUNG =
            "Ein guter Tacker, welcher meine Macht erhöht.";
    String SCHEERE_NAME = "Scheere";
    String SCHEERE_BESCHREIBUNG =
            "Eine scharfe Scheere, welche meine Macht deutlich erhöht.";
    String LINEAL_NAME = "Lineal";
    String LINEAL_BESCHREIBUNG =
            "Ein solides Lineal, welcher meine Reichweite erhöht.";
    String FAEUSTE_NAME = "Faust";
    String FAEUSTE_BESCHREIBUNG =
            "Meine Fäuste müssen wohl als Waffe reichen";
    
    
    //  Hilfe
    String HILFE_HAUPTMENUE =
            "Das hier ist das Hauptmenü. Hier kann über die Knöpfe ein " +
            "neues " +
            "Spiel begonnen werden," +
            "falls bereits ein Spielstand existiert kann dieser über " +
            "`Weiter`" +
            " fortgesetzt werden." +
            "In den Einstellungen sind Möglichkeiten um die Lautstärke und " +
            "Fenstergrößen anzupassen." +
            "Dies ist aber auch später im Spiel selbst über eine Menüleiste" +
            " " +
            "möglich. Viel Spaß!";
    String HILFE_EINSTELLUNGEN =
            "Hier können allgemeine Anwendungseinstellungen getätigt werden" +
            ". " +
            "Über das " +
            "Fenstergröße-Menü kann die Rahmengröße eingestellt werden. Der" +
            " " +
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
