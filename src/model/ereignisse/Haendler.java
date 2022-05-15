package model.ereignisse;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import model.KartenDeck;
import model.SpielStand;

import java.util.Scanner;

import static resources.Strings.HAENDLERDECK1_PFAD;

/**
 *
 */
public class Haendler extends Mensch
{
    protected transient KartenDeck haendlerDeck = null;   //Das Deck des Haendlers, aus dem der Spieler Karten kaufen kann.

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler. Haendler sind Ereignisse, die es dem
     * Spieler ermoeglichen Karten zu dem bestehenden Deck hinzuzufuegen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     * @param haendlerDeck: Das Deck aus dem der Spieler neue Karten kaufen kann
     */
    public Haendler (String name, String beschreibung, KartenDeck haendlerDeck)
    {
        super(name, beschreibung);
        this.haendlerDeck = haendlerDeck;
    }

    /**
     * Der Konstruktor erstellt ein Ereignis vom Typ Haendler ohne eine Instanz der Klasse KartenDeck.
     * Haendler sind Ereignisse, die es dem Spieler ermoeglichen Karten zu dem bestehenden Deck hinzuzufuegen.
     * @param name: Der Name des Ereignisses
     * @param beschreibung: Die Beschreibung fuer den Spieler
     */
    public Haendler (String name, String beschreibung)
    {
        super(name, beschreibung);
    }

    public KartenDeck getHaendlerDeck()
    {
        return haendlerDeck;
    }

    public void setHaendlerDeck(KartenDeck haendlerDeck)
    {
        this.haendlerDeck = haendlerDeck;
    }


    /**
     * Diese Methode überlagert die Methode aus der Superklasse "Ereignis". Der Haendler prueft ob die Interaktion
     * eine Bezahlung erfordert. Je nach Resultat wird entweder kostenlos eine Karte dem Deck hinzugefuegt oder
     * vorher die Zahlung durchgefuehrt.
     * @param spielStand
     */
    public void ausfuehren (SpielStand spielStand)
    {
        System.out.println(this.getName());
        auswaehlen();
        if(isAuswahl())
        {
            int kartenPosition;
            try
            {
                this.setHaendlerDeck(KartenDeckIO.leseDatei(HAENDLERDECK1_PFAD));
            }
            catch (KartenDeckFehlerhaftException e)
            {
                e.getMessage();
            }

            Scanner sc = new Scanner(System.in);
            kartenPosition = sc.nextInt();
            if (pruefeGratisInteraktion())
            {
                spielStand.getSpieldeckSpieler().push(haendlerDeck.get(kartenPosition));
                haendlerDeck.remove(kartenPosition);
                gratisInteraktion--;
            }
            else
            {
                spielStand.setGold(spielStand.getGold() - kosten);
                spielStand.getSpieldeckSpieler().push(haendlerDeck.get(kartenPosition));
                haendlerDeck.remove(kartenPosition);
                interaktionsZaehler++;
                kostenErhoehen();
            }
        }
    }
}
