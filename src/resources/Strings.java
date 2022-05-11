package resources;
public interface Strings
{
    //Satzzeichen
    String LEERZEICHEN = " ";
    String ZEILENUMBRUCH = "\n";

    //Infos
    String SPIELSTAND_DATEI_ERSTELLT = "Der Spielstand wurde in eine neue Datei geschrieben.";
    String SPIELSTAND_DATEI_UEBERSCHRIEBEN = "Der bestehende Spielstand wurde überschrieben.";
    String CHARAKTER_DATEI_ERSTELLT = "Die Charaktere wurden in eine neue Datei geschrieben.";
    String CHARAKTER_DATEI_UEBERSCHRIEBEN = "Der bestehenden Charaktere wurden überschrieben.";

    //Pfade
    String JSON_DATEIENDUNG = ".json";
    String KARTENDECK_PAKET_PFAD = "src/resources/kartendecks/";
    String START_DECK_PFAD = KARTENDECK_PAKET_PFAD + "%s" + JSON_DATEIENDUNG;   // %s = Name des Charakters
    String SPIEL_DECK_SPIELER_PFAD = KARTENDECK_PAKET_PFAD + "Spieldeck_Spieler" + JSON_DATEIENDUNG;
    String SPIEL_DECK_GEGNER_PFAD = KARTENDECK_PAKET_PFAD + "Spieldeck_Gegner" + JSON_DATEIENDUNG;
    String SPIELSTAND_PFAD = "src/resources/Spielstand" + JSON_DATEIENDUNG;
    String CHARAKTER_PFAD = "src/resources/Charakter" + JSON_DATEIENDUNG;

    //Exception Strings
    String SPIELFELD_DIMENSION_GLEICH_NULL_EXCEPTION_INFO = "Die Spielfelddimension ist in einer Ausdehnung gleich 0!";
    String SPIELFELD_NICHT_QUADRATISCH_EXCEPTION_INFO = "Das Spielfeld wurde nicht quadratisch generiert!";
    String START_DECK_FEHLERHAFT_EXCEPTION_INFO =
            "Das Kartendeck %s der Charakterklasse wurde nicht gefunden!";      // %s = Name des Kartendecks (optional)
    String JSON_FORMAT_FEHLERHAFT_INFO = "Fehlerhafte Json Formatierung";
}