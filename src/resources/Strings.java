package resources;

/**
 * Das Interface enthaelt konstante Zeichenketten fuer die Konfiguration des
 * Programms.
 */
public interface Strings
{
    //  Satzzeichen
    String LEERZEICHEN = " ";
    String ZEILENUMBRUCH = "\n";
    String SENKRECHTER_STRICH = "|";
    String TRENNUNG = ",\t";

    //  Infos
    String NETZWERK_GETRENNT = "Verbindung getrennt.";
    String NETZWERK_GESENDET = "Nachricht gesendet.";
    String NETZWERK_WARTE = "Warte auf Verbindung auf Port: ";
    String NETZWERK_VERBUNDEN = "Verbunden zu ";
    String SPIELFELD_LEERES_FELD = "0";
    String EBENE_LEERES_FELD = "0";

    //  Pfade
    String JSON_DATEIENDUNG = ".json";
    String JSON_SPIELSTAND_PFAD = "src/resources/Spielstand" + JSON_DATEIENDUNG;
    String CHARAKTER_PFAD = "src/resources/Charaktere" + JSON_DATEIENDUNG;
    String AKTUELLE_EBENE_PFAD =
            "src/resources/ebenen/EbeneAktuell" + JSON_DATEIENDUNG;
    String EBENEN_PFAD =
            "src/resources/ebenen/Ebene%d" + JSON_DATEIENDUNG;
    String EBENE_TEST_PFAD =
            "src/resources/ebenen/EbeneTest" + JSON_DATEIENDUNG;

    //  Kartendeck-Pfade
    String KARTENDECK_PAKET_PFAD = "src/resources/kartendecks/";
    String START_DECK_PFAD = KARTENDECK_PAKET_PFAD + "%s" + JSON_DATEIENDUNG;
    // %s = Name des Charakters
    String SPIEL_DECK_SPIELER_PFAD =
            KARTENDECK_PAKET_PFAD + "Spieldeck_Spieler" + JSON_DATEIENDUNG;
    String SPIEL_DECK_GEGNER_PFAD =
            KARTENDECK_PAKET_PFAD + "Spieldeck_Gegner" + JSON_DATEIENDUNG;
    String HAENDLER_DECK_EINS_PFAD =
            KARTENDECK_PAKET_PFAD + "Haendler1" + JSON_DATEIENDUNG;

    //  Json-Parameter
    String KARTEN_DECK_BEZEICHNUNG = "bezeichnung";
    String KARTEN_DECK_STACK = "karten";
    String JSON_KLASSE = "klasse";
    String JSON_EREIGNIS = "ereignis";
    String JSON_EBENEN_SPALTE = "ebenenSpalte";
    String JSON_EBENEN_ZEILE = "ebenenZeile";
    String JSON_SPIELFIGUR_EBENE = "spielfigur";
    String JSON_EBENE = "ebene";

    //  Exception Strings
    String JSON_NICHT_LESBAR_INFO =
            "Die Json-Datei konnte nicht gelesen werden.";
    String KARTE_NICHT_VERBESSERT_INFO =
            "Die Karte konnte nicht verbessert werden.";
    String KARTEN_DECK_ZU_KLEIN_INFO =
            "Das Kartendeck enthält zu wenige Karten für die Operation.";
    String NICHT_GENUG_GOLD_INFO = "Du hast nicht genug Gold.";
    String KARTE_BILD_NICHT_GEFUNDEN_INFO = "Das Bild der Karte \"%s\" konnte nicht gefunden werden!";
}