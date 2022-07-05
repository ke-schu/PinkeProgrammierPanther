package view.fxmlControl;

import control.KartenEinheitController;
import control.RundenController;
import control.Spielstatus;
import exceptions.JsonNichtLesbarException;
import javafx.beans.property.SimpleObjectProperty;
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
        initNetzwerk();
        hintergrundFestlegen();
        erstelleSpielfeldUmgebung();
        initZugBeendenButton();

        if(RundenController.getzugZaehler()== 0)
        {
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
                        heldEinsetzen(gegenspieler, feld );
                    }

                    if
                    (j == spielfeld.getZeilen() - 1 &&
                     i == spielfeld.getSpalten() - 1)
                    {
                        heldEinsetzen(spieler, feld);
                    }
                    spielfeldGitter.add(feld, i, j);
                }
            }
        }

        empfangenerSpielstatus = new SimpleObjectProperty<>();
        empfangenerSpielstatus.addListener(
                (observableValue, spielstatus, t1) -> aktualisiereGUI());
    }

    public void initNetzwerk ()
    {
        SpielstatusKommunikation = new Server(SPIELSTATUS_PORT, Spielstatus.class);
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

            aktuellekartenhand = new KartenHand(spieler);
            aktuellekartenhand.handZiehen(spielerDeck);

            spielfeld = new SpielFeld();
            aktuellermanaTank = new ManaTank(spieler);

            KartenEinheitController.beschwoerenHelden(spieler,spielfeld);
            KartenEinheitController.beschwoerenHelden(gegenspieler,spielfeld);

            Manabar.setStyle("-fx-accent: blue ;");
            altuellesmanaMaximum = aktuellermanaTank.getMana();
            double manaWert = altuellesmanaMaximum / aktuellermanaTank.getMana();
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

