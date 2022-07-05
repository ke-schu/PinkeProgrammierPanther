package view.fxmlControl;

import control.KartenEinheitController;
import control.Spielstatus;
import exceptions.JsonNichtLesbarException;
import javafx.scene.layout.StackPane;
import model.*;
import utility.KonsolenIO;
import utility.Server;
import utility.SpielStandIO;
import view.components.KarteVBox;

import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.*;

public class SpielerFeldGUIController extends FeldGuiController
{
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle)
    {
        hintergrundFestlegen();
        erstelleSpielfeldUmgebung();
        initNetzwerk();
        initZugBeendenButton();

        for (int i = 0; i < spielfeld.getZeilen(); i++)
        {
            spielfeldGitter.addRow(0);
        }

        for (int i = 0; i < spielfeld.getSpalten(); i++)
        {
            spielfeldGitter.addColumn(0);
            for (int j = 0; j < spielfeld.getZeilen(); j++)
            {
                StackPane feld = feldErstellen();
                if(j == 0 && i == 0)
                {
                    heldEinsetzen((Gegenspieler)gegenspieler, feld);
                }

                if
                (j == spielfeld.getZeilen() - 1 &&
                 i == spielfeld.getSpalten() - 1)
                {
                    heldEinsetzen((Spieler)spieler, feld);
                }
                spielfeldGitter.add(feld, i, j);
            }
        }
    }

    public void initNetzwerk ()
    {
        SpielstatusKommunikation = new Server(SPIELSTATUS_PORT, Spielstatus.class);

        SpielstatusKommunikation.postEingangProperty().addListener(
                (observableValue, s, t1) -> aktualisiereSpielStatus());

        SpielstatusKommunikation.getInputThread().start();
        /* schicken und mit methode regeln welche guckt ob es die erste runde ist
        Server<Gegenspieler> SpielerServer = new Server(SPIELER_PORT, Gegenspieler.class);
        SpielerServer.senden((Gegenspieler) gegenspieler);*/
    }

    @Override
    public void erstelleSpielfeldUmgebung ()
    {
        try
        {
            spiel      = SpielStandIO.leseDatei();

            spieler    = spiel.getSpieler();
            spielerDeck = spiel.getSpieldeckSpieler();

            gegenspieler = spiel.getGegenSpieler();
            gegenspielerDeck = spiel.getSpieldeckGegner();

            kartenhandSpieler = new KartenHand((Spieler)spieler);
            kartenhandSpieler.handZiehen(spielerDeck);

            spielfeld = new SpielFeld();
            manaTankSpieler = new ManaTank(spieler);

            KartenEinheitController.beschwoerenHelden((Spieler) spieler,spielfeld);
            KartenEinheitController.beschwoerenHelden(
                    (Gegenspieler)gegenspieler,spielfeld);

            Manabar.setStyle("-fx-accent: blue ;");
            manaMaximumSpieler = manaTankSpieler.getMana();
            double manaWert = manaMaximumSpieler / manaTankSpieler.getMana();
            Manabar.setProgress(manaWert);
            karteInHandEinfuegen();
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }

    private static void heldEinsetzen(Karte held, StackPane feld)
    {
        KarteVBox gegenspielerKarteVBox = new KarteVBox(held);
        feld.getChildren().add(gegenspielerKarteVBox);
    }
}

