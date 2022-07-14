package control;

import model.Ebene;
import utility.KonsolenIO;

import java.io.IOException;

import static resources.Konstanten.ebeneIO;
import static resources.Strings.*;

/**
 Diese Klasse enthaelt Methoden, um mit Instanzen der Klasse Ebene zu
 interagieren. */
public class EbeneController
{
    /**
     Ein leerer Konstruktor mit dem Modifier private um sicherzustellen, dass
     keine Instanzen dieser Klasse gebildet werden.
     */
    private EbeneController ()
    {
    }
    
    public static void ueberschreibeAktuelleEbene (int EbeneNummer)
            throws IOException
    {
        String ebenePfad = String.format(EBENEN_PFAD, EbeneNummer);
        Ebene ebeneEins = ebeneIO.leseDatei(ebenePfad);
        ebeneIO.schreibeDatei(ebeneEins, AKTUELLE_EBENE_PFAD);
        KonsolenIO.ausgeben(String.format(EBENE_UEBERSCHRIEBEN, ebenePfad));
    }
}