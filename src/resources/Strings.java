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
    String START_DECK_PFAD = KARTENDECK_PAKET_PFAD + "%s_StartDeck" + JSON_DATEIENDUNG;
    String HAENDLER_DECK_PFAD = KARTENDECK_PAKET_PFAD + "%s_HaendlerDeck" + JSON_DATEIENDUNG;
    String TEMPEL_DECK_PFAD = KARTENDECK_PAKET_PFAD + "%s_TempelDeck" + JSON_DATEIENDUNG;
    String SPIELSTAND_PFAD = "src/resources/Spielstand" + JSON_DATEIENDUNG;
    String CHARAKTER_PFAD = "src/resources/charakter" + JSON_DATEIENDUNG;

    //Exception Strings
    String SPIELFELD_DIMENSION_GLEICH_NULL_EXCEPTION_INFO = "Die Spielfelddimension ist in einer Ausdehnung gleich 0!";
    String SPIELFELD_NICHT_QUADRATISCH_EXCEPTION_INFO = "Das Spielfeld wurde nicht quadratisch generiert!";
    String START_DECK_FEHLERHAFT_EXCEPTION_INFO = "Das Kartendeck %s der Charakterklasse wurde nicht gefunden!";
    String JSON_FORMAT_FEHLERHAFT_INFO = "Fehlerhafte Json Formatierung";
}