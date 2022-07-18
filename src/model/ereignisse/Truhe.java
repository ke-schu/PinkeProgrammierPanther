package model.ereignisse;

import model.SpielStand;

import static resources.Konstanten.*;

/**
 Diese Klasse ist eine Subklasse von Ereignis und implementiert das Interface
 Wahrscheinlichkeit. Die Truhe enthaelt mehrere verschiedene moegliche
 Inhalte. */
public class Truhe extends Ereignis implements Wahrscheinlichkeit
{
    protected double wahrscheinlichkeit;
    
    /**
     Der Konstruktor erstellt ein Ereignis vom Typ Truhe. Ein ZufallsEreignis
     ist ein Ereignis, dessen genaue Art fuer den Spieler erst bekannt wird,
     wenn er den zugehoerigen Raum betritt.
     @param name: Der Name des Ereignisses.
     @param beschreibung: Die Beschreibung fuer den Spieler.
     */
    public Truhe (String name, String beschreibung)
    {
        super(name, beschreibung);
    }
    
    /**
     Diese Methode ueberlagert die Methode aus der Superklasse
     "ZufallsEreignis". Ueber das Attribut "wahrscheinlichkeit" wird bestimmt,
     welches Ereignis ausgefuehrt wird.
     @param spielStand der aktuelle Spielstand und seine Attribute.
     */
    public void ausfuehren (SpielStand spielStand)
    {
        if (!ausgefuehrt)
        {
            if (isAuswahl())
            {
                wahrscheinlichkeit = generiereWahrscheinlichkeit();
                if (wahrscheinlichkeit <= EIN_PROZENT)
                {
                    spielStand.setGold(
                            spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_EINS);
                }
                else if (wahrscheinlichkeit <= ZEHN_PROZENT)
                {
                    spielStand.setGold(
                            spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_ZWEI);
                }
                else if (wahrscheinlichkeit <= VIERZIG_PROZENT)
                {
                    spielStand.setGold(
                            spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_DREI);
                }
                else if (wahrscheinlichkeit <= ACHTZIG_PROZENT)
                {
                    spielStand.setGold(
                            spielStand.getGold() + TRUHE_GOLD_ERHOEHUNG_VIER);
                }
                else if (wahrscheinlichkeit <= HUNDERT_PROZENT)
                {
                    spielStand.setGold(
                            spielStand.getGold() +
                            TRUHE_GOLD_ERHOEHUNG_FUENF);
                }
                ausgefuehrt = true;
            }
        }
    }
    
    /**
     Dies ist eine Methode zum Generieren des Attributs "Wahrscheinlichkeit".
     Dieses Attribut wird benoetigt um zu bestimmen, welcher Inhalt sich in
     der
     Truhe befindet.
     @return Attribut zur Bestimmung des Truheninhalts.
     */
    @Override public double generiereWahrscheinlichkeit ()
    {
        wahrscheinlichkeit = Math.random() * WAHRSCHEINLICHKEIT_MAX;
        return wahrscheinlichkeit;
    }
}
