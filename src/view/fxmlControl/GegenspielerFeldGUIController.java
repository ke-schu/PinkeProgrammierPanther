package view.fxmlControl;

import control.Spielstatus;
import model.*;
import utility.Client;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static resources.Konstanten.*;

public class GegenspielerFeldGUIController extends FeldGuiController
{
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        initNetzwerk();
        hintergrundFestlegen();
        erstelleSpielfeldUmgebung();
        initZugBeendenButton();

    }

    @Override
    public void initNetzwerk ()
    {
        String hostname = "localhost";
        SpielstatusKommunikation = new Client(hostname, SPIELSTATUS_PORT,
                Spielstatus.class);

        SpielstatusKommunikation.getInputThread().start();
        try
        {
            SpielstatusKommunikation.getInputThread().join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        aktualisiereSpielStatus();

        while(spieler == null)
        {
            try
            {
                sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void erstelleSpielfeldUmgebung()
    {
        aktuellekartenhand = new KartenHand(gegenspieler);
            aktuellekartenhand.handZiehen(gegenspielerDeck);

        aktuellermanaTank = new ManaTank(gegenspieler);
            Manabar.setStyle("-fx-accent: blue;");
        altuellesmanaMaximum = aktuellermanaTank.getMana();
            double manaWert = altuellesmanaMaximum / aktuellermanaTank.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();

    }
}
