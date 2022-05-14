package control.test;

import exceptions.KartenDeckFehlerhaftException;
import io.KartenDeckIO;
import model.KarteEinheit;
import model.KartenDeck;
import resources.Effekte;
import resources.Einheiten;

import java.io.File;

public class KartenDeckTest
{
    private static KarteEinheit erstelleKarte ()
    {
        return new KarteEinheit(
                "HarryPotter",
                40,
                Einheiten.NAHKAEMPFER,
                3,
                10,
                1,
                1,
                1,
                1,
                Effekte.LETZTEWORTE,
                Effekte.ZURUECKWERFEN);
    }

    public static void erstelleDeck () throws KartenDeckFehlerhaftException
    {
        KartenDeck meinDeck = new KartenDeck(new File(
                "src/resources/kartendecks/Spieldeck_Spieler.json"),
                "IchBinDasSpieldeck");
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        meinDeck.push(erstelleKarte());
        KartenDeckIO.schreibeDatei(meinDeck);
    }
}
