package control;

import model.CharakterKlasse;
import model.KartenDeck;

import java.io.File;
import java.io.IOException;

public class CharakterKlasseController
{
    public static CharakterKlasse erstelleCharakterklasse (String name, int preis) throws IOException
    {
        CharakterKlasse klasse = new CharakterKlasse(name, preis);

        for (int i = 1; i < (klasse.getAnzahlDecks() + 1); i++)
        {
            String pfad = "resources/carddecks/" + klasse.getName() + i + ".json";
            if (KartenDeckController.pruefeDatei(pfad))
            {
                klasse.setDeck(KartenDeckController.leseDatei(pfad), i);
            }
            else
            {
                File meineDatei = new File(pfad);
                KartenDeck defaultDeck = new KartenDeck(meineDatei, "Standard-Bezeichnung");
                klasse.setDeck(defaultDeck, i);
                KartenDeckController.schreibeDatei(defaultDeck);
            }
        }
        return klasse;
    }
}
