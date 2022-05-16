package resources;

/**
 * Das Interface enthaelt konstante Zeichenketten für die Konfiguration des Programms.
 */
public interface Strings
{
    //  Satzzeichen
    String LEERZEICHEN = " ";
    String ZEILENUMBRUCH = "\n";
    String SENKRECHTER_STRICH = "|";
    String TRENNUNG = "\t" + SENKRECHTER_STRICH + "\t";

    //  Infos
    String SPIELSTAND_DATEI_ERSTELLT = "Der Spielstand wurde in eine neue Datei geschrieben.";
    String SPIELSTAND_DATEI_UEBERSCHRIEBEN = "Der bestehende Spielstand wurde überschrieben.";
    String CHARAKTER_DATEI_ERSTELLT = "Die Charaktere wurden in eine neue Datei geschrieben.";
    String CHARAKTER_DATEI_UEBERSCHRIEBEN = "Der bestehenden Charaktere wurden überschrieben.";
    String EBENE_DATEI_ERSTELLT = "Es wurde eine neue Ebene in die Datei geschrieben.";
    String EBENE_DATEI_UEBERSCHRIEBEN = "Die bestehende Ebenendatei wurde überschrieben.";

    //  Pfade
    String JSON_DATEIENDUNG = ".json";
    String SPIELSTAND_PFAD = "src/resources/Spielstand" + JSON_DATEIENDUNG;
    String CHARAKTER_PFAD = "src/resources/Charaktere" + JSON_DATEIENDUNG;
    String AKTUELLE_EBENE_PFAD = "src/resources/ebenen/EbeneAktuell" + JSON_DATEIENDUNG;
    String EBENE_TEST_PFAD = "src/resources/ebenen/EbeneTest" + JSON_DATEIENDUNG;

    //  Kartendeck-Pfade
    String KARTENDECK_PAKET_PFAD = "src/resources/kartendecks/";
    String START_DECK_PFAD = KARTENDECK_PAKET_PFAD + "%s" + JSON_DATEIENDUNG;   // %s = Name des Charakters
    String SPIEL_DECK_SPIELER_PFAD = KARTENDECK_PAKET_PFAD + "Spieldeck_Spieler" + JSON_DATEIENDUNG;
    String SPIEL_DECK_GEGNER_PFAD = KARTENDECK_PAKET_PFAD + "Spieldeck_Gegner" + JSON_DATEIENDUNG;
    String HAENDLER_DECK_EINS_PFAD = KARTENDECK_PAKET_PFAD +"Haendler1" + JSON_DATEIENDUNG;

    //  Json-Parameter
    String KARTEN_DECK_BEZEICHNUNG = "bezeichnung";
    String KARTEN_DECK_STACK = "karten";
    String KLASSE = "klasse";

    //  Exception Strings
    String START_DECK_FEHLERHAFT_EXCEPTION_INFO =
            "Das Kartendeck %s wurde nicht gefunden!";                          // %s = Name des Kartendecks (optional)
    String JSON_FORMAT_FEHLERHAFT_INFO = "Fehlerhafte Json Formatierung";
}