package control;

import model.Ebene;
import model.SpielfigurEbene;
import utility.KonsolenIO;

import java.io.IOException;

import static resources.Konstanten.SPIELFIGUR_EBENE_STARTPOSITION;
import static resources.Konstanten.ebeneIO;
import static resources.Strings.AKTUELLE_EBENE_PFAD;
import static resources.Strings.EBENEN_PFAD;

/**
 * Diese Klasse enthaelt Methoden, um mit Instanzen der Klasse Ebene zu
 * interagieren.
 */
public class EbeneController
{
    /**
     * Ein leerer Konstruktor mit dem Modifier private um sicherzustellen,
     * dass keine Instanzen dieser Klasse gebildet werden.
     */
    private EbeneController()
    {
    }

    /**
     * Diese Methode initialisiert eine Instanz der Klasse SpielfigurEbene an
     * den Indexen (4,4) in einer Ebene.
     *
     * @param spielfigur Die Spielfigur, welche in der Ebene initialisiert
     *                   werden soll.
     * @param ebene      Die Ebene, in welcher die Instanz der Klasse
     *                   SpielfigurEbene initialisiert werden soll.
     */
    public static void initSpielerInEbene(SpielfigurEbene spielfigur,
                                          Ebene ebene)
    {
        ebene.setSpielfigur(spielfigur);
        spielfigur.setPosition(SPIELFIGUR_EBENE_STARTPOSITION);
    }

    public static void ueberschreibeAktuelleEbene(int EbeneNummer) throws IOException
    {
        String ebenePfad = String.format(EBENEN_PFAD, EbeneNummer);
        Ebene ebeneEins = ebeneIO.leseDatei(ebenePfad);
        ebeneIO.schreibeDatei(ebeneEins, AKTUELLE_EBENE_PFAD);
        KonsolenIO.ausgeben("Die aktuelle Ebene wurde mit " + ebenePfad + " überschrieben.");
    }
}