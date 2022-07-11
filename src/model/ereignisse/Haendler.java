package model.ereignisse;

import control.TalentController;
import exceptions.JsonNichtLesbarException;
import model.KartenDeck;
import model.SpielStand;
import utility.KonsolenIO;

import java.io.IOException;

import static resources.Konstanten.kartenDeckIO;
import static resources.Strings.HAENDLER_DECK_EINS_PFAD;
import static utility.KonsolenIO.eingabeInt;

/**
 * Diese Klasse ist eine Subklasse von Mensch. Ein Haendler ist ein Ereignis,
 * welches innerhalb einer Ebene angetroffen werden kann. Haendler enthaelt
 * alle Methoden aus den Superklassen und eigene Getter und Setter fuer
 * Attribute.
 */
public class Haendler extends Mensch
{
    //Das Deck des Haendlers, aus dem der Spieler Karten kaufen kann.
    private transient KartenDeck haendlerDeck = null;

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler. Haendler sind
     * Ereignisse, die es dem Spieler ermoeglichen Karten zu dem bestehenden
     * Deck hinzuzufuegen.
     *
     * @param name:         Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     * @param haendlerDeck: Das Deck aus dem der Spieler neue Karten kaufen
     *                    kann.
     */
    public Haendler(String name, String beschreibung, KartenDeck haendlerDeck)
    {
        super(name, beschreibung);
        this.haendlerDeck = haendlerDeck;
    }

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler ohne eine
     * Instanz der Klasse KartenDeck. Haendler sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten zu dem bestehenden Deck hinzuzufuegen.
     *
     * @param name:         Der Name des Ereignisses.
     * @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Haendler(String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    /**
     * Diese Methode dient als Getter um auf das aktuelle Deck des Haendlers
     * zuzugreifen.
     *
     * @return das aktuelle Deck des Haendlers.
     */
    public KartenDeck getHaendlerDeck()
    {
        return haendlerDeck;
    }

    /**
     * Diese Methode dient als Setter um dem Haendler ein neues Deck
     * zuzuweisen.
     *
     * @param haendlerDeck das Deck, welches der Haendler erhalten soll.
     */
    public void setHaendlerDeck(KartenDeck haendlerDeck)
    {
        this.haendlerDeck = haendlerDeck;
    }


    /**
     * Diese Methode ueberlagert die Methode aus der Superklasse "Ereignis".
     * Der Haendler prueft ob die Interaktion eine Bezahlung erfordert. Je
     * nach Resultat wird entweder kostenlos eine Karte dem Deck hinzugefuegt
     * oder vorher die Zahlung durchgefuehrt.
     *
     * @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren(SpielStand spielStand)
    {
        KonsolenIO.ausgeben(this.getName());
        if (isAuswahl())
        {
            int indexKarte;
            try
            {
                this.setHaendlerDeck(
                        kartenDeckIO.leseKartenDeck(HAENDLER_DECK_EINS_PFAD));
            }
            catch (JsonNichtLesbarException e)
            {
                KonsolenIO.ausgeben(e.getMessage());
            }

            indexKarte = eingabeInt();
            if (pruefeGratisInteraktion())
            {
                spielStand.getSpieldeckSpieler()
                          .push(haendlerDeck.get(indexKarte));
                haendlerDeck.remove(indexKarte);
                gratisInteraktionen--;
            }
            else
            {
                TalentController.charme(spielStand.getSpieler(), this);
                spielStand.setGold(spielStand.getGold() - this.getKosten());
                spielStand.getSpieldeckSpieler()
                          .push(haendlerDeck.get(indexKarte));
                haendlerDeck.remove(indexKarte);
                interaktionsZaehler++;
                kostenErhoehen();
            }
            try
            {
                kartenDeckIO.schreibeDatei(spielStand.getSpieldeckSpieler());
            }
            catch (IOException e)
            {
                e.getMessage();
            }
        }
    }
}
