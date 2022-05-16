package resources;

import static resources.Strings.KARTENDECK_PAKET_PFAD;

/**
 * Für das Paket control.test werden Konstanten zum Testen benötigt, die hier festgehalten sind.
 */
public interface TestKonstanten
{
    String TESTBEZEICHNUNG_SPIELER = "meinSpieler";
    String TESTBEZEICHNUNG_WAFFE = "meineWaffe";
    String TESTBEZEICHNUNG_CHARAKTER = "meinCharakter";
    String TESTBEZEICHNUNG_KARTENDECK = "IchBinDasSpieldeck";

    String TESTPFAD_KARTENDECK = KARTENDECK_PAKET_PFAD + "Spieldeck_Gegner.json";
    String KARTEN_NAME = "HarryPotter";
    int ANZAHL_KARTEN = 1;

    String BJOERN = " Bjoern ";
    String SPIELFELDBREITE = " Spielfeldbreite ";
    String SPIELFELDZEILEN = " Spielfeldhöhe ";

    String VORSTELLEN_SPIELER = " Hallo, ich bin der Spieler ";
    String VORSTELLEN_EINHEIT = " Hallo, ich bin eine beschworene Einheit und heiße ";
    String VORSTELLEN_GEGNER = " Hallo, ich bin der Gegner ";

    String POSITIONS_ANGABE_NULL_EINS = " ich stehe an Position 0,1 ";
    String POSITIONS_ANGABE_EINS_EINS = " nach dem Bewegen bin ich stehe an Position 1,1 ";
    String POSITIONS_ANGABE_EINHEIT = " und ich denke ich stehe in Spalte ";
    String POSITIONS_ANGABE_NULL_EINS_ENDE = " an Stelle 0, 1 befindet sich nun ";
    String POSITIONS_ANGABE_KAMPF = " an Stelle 0, 1 befindet sich vor dem Kampf ";

    String LEBENSPUNKTE_FREUND = " Lebenspunkte HarryPotter: ";
    String LEBENSPUNKTE_NACH_KAMPF = " Lebenspunkte HarryPotter nach Kampf: ";

    String ZEILE = " und Zeile ";
    String VON = " und vor dem Bewegen stehe ich an Position ";
    String BEWEGEN = " jetzt bewege ich mich...";
    String KAEMPFEN = " jetzt wird gerade gekämpft...";
}
