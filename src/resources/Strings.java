package resources;
public interface Strings
{

    //Satzzeichen
    String LEERZEICHEN = " ";
    String ZEILENUMBRUCH = "\n";

    //Pfade
    String KARTENDECK_PFAD = "src/resources/carddecks/";
    String JSON_DATEIENDUNG = ".json";
    String REST_DECK_DATEI = "Rest" + JSON_DATEIENDUNG;

    //Exception Strings
    String SPIELFELD_DIMENSION_GLEICH_NULL_EXCEPTION_INFO = "Die Spielfelddimension ist in einer Ausdehung gleich 0";
    String SPIELFELD_NICHT_QUADRATISCH_EXCEPTION_INFO = "Das Spielfeld wurde nicht quadratisch generiert";
    String START_DECK_FEHLERHAFT_EXCEPTION_INFO = "Das Kartendeck %s der Charakterklasse wurde nicht gefunden";
}