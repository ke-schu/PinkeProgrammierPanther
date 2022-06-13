package resources;

import model.KartenDeck;
import model.Position;

/**
 * Hier werden finale, numerische Werte fuer das Spiel gesammelt.
 */
public interface Konstanten
{
    int HANDGROESSE = 5;
    int LEVEL_SUMMAND_SPIELER = 1;
    int AUSGANGSWERT_ERFAHRUNGSPUNKTE_SPIELER = 0;
    int LEVEL_GRENZE_FAKTOR = 2;
    int AUSGANGSWERT_ANZAHL_GRATIS_HANDLUNGEN_MENSCH = 1;
    int AUSGANGSWERT_KOSTEN_HANDLUNG_MENSCH = 20;
    int KOSTEN_ERHOEHUNG_ANZAHL_HANDLUNGEN = 3;
    int KOSTEN_ERHOEHUNG_GOLD = 10;
    int SPIELFELD_GENERATOR_MIN = 4;
    int SPIELFELD_GENERATOR_MAX = 6;
    Position SPIELFIGUR_EBENE_STARTPOSITION = new Position(4,4);

    int HEILER_AUFWERTUNG_LEBENSPUNKTE = 2;
    int SPIELER_WECHSEL_NACH_ZUEGEN = 2;

    int SPIELER_KAMPFFELD_STARTPOSITION_X = 0;
    int SPIELER_KAMPFFELD_STARTPOSITION_Y = 0;
    int GEGNER_KAMPFFELD_STARTPOSITION_X = 1;
    int GEGNER_KAMPFFELD_STARTPOSITION_Y = 1;

    // Konstanten, welche beim Ereignis Truhe verwendet werden
    int TRUHE_GOLD_ERHOEHUNG_EINS = 100;
    int TRUHE_GOLD_ERHOEHUNG_ZWEI = 50;
    int TRUHE_GOLD_ERHOEHUNG_DREI = 25;
    int TRUHE_GOLD_ERHOEHUNG_VIER = 10;
    int TRUHE_GOLD_ERHOEHUNG_FUENF = 5;

    // Konstanten, welche beim Ereignis ZufallsEreignis verwendet werden
    int ZE_SCHADEN = 5;
    int ZE_MANA_ERHOEHUNG = 1;
    int ZE_MACHT_ERHOEHUNG = 1;

    int ZE_1 = 1;
    int ZE_2 = 2;
    int ZE_3 = 3;
    int ZE_4 = 4;
    int ZE_5 = 5;

    // Konstanten fuer Talente
    double CHARME_FAKTOR = 0.9;
    int WERT_SCHILD = 1;
    int SCHUTZENGEL_ANTEIL_MAXLEBEN = 2;
}
