package gui;

import java.io.File;
import java.net.URL;

public interface GuiKonstanten
{
    File ICON = new File("src/gui/bilder/Logo.png");
    String MAIN_CSS_PFAD = "css/Main.css";

    //Integer fuer die Aufloesung
    int AUFLOESUNG_HOEHE_HD = 1280;
    int AUFLOESUNG_BREITE_HD = 720;
    int AUFLOESUNG_HOEHE_FULLHD = 1920;
    int AUFLOESUNG_BREITE_FULLHD = 1080;

    //Strings fuer Texte der Hilfefenster
    String HILFE_TEXT = "Hier steht ein Text, der hilft.";
    String HILFE_HAUPTMENUE = "Hier ist der String der nachher in unserem Hilfefenster des Hauptmenüs steht." +
            "Ich hoffe das ist hilfreich und man kann damit was anfagen.";
    String HILFE_EINSTELLUNGEN = "Hier ist der String der nachher in unserem Hilfefenster der Einstellungen steht." +
            "Ich hoffe das ist hilfreich und man kann damit was anfagen.";
    String HILFE_CHARAKTERAUSWAHL = "Hier ist der String der nachher in unserem Hilfefenster der Charakterauswahl steht." +
            "Ich hoffe das ist hilfreich und man kann damit was anfagen.";
    String STYLE_CHARAKTER_NAME = "charakter-name";
    String CHARAKTER_KAUFEN = "Kaufen für %d €";
    String SCHON_FREIGESCHALTET = "Im Besitz";
    String GOLD_BESTAND = "Goldbestand: ";

}
