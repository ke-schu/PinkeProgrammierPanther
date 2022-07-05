package view.fxmlControl;

import control.Spielstatus;
import javafx.scene.layout.StackPane;
import model.*;
import utility.Client;
import utility.KonsolenIO;
import view.components.KarteVBox;

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
                if(spielfeld.getSpielfeldplatz(i,j) != null)
                {
                    KarteVBox
                            karteVBox = new KarteVBox(spielfeld.getSpielfeldplatz(i, j));
                    feld.getChildren().add(karteVBox);
                }
                spielfeldGitter.add(feld, i, j);
            }
        }
    }

    @Override
    public void initNetzwerk ()
    {
        String hostname = "localhost";
        SpielstatusKommunikation = new Client(hostname, SPIELSTATUS_PORT,
                Spielstatus.class);

        SpielstatusKommunikation.postEingangProperty().addListener(
                (observableValue, s, t1) -> aktualisiereSpielStatus());

        SpielstatusKommunikation.getInputThread().start();

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
        kartenhandSpieler = new KartenHand((Gegenspieler) spieler);
        kartenhandSpieler.handZiehen(spielerDeck);

        manaTankSpieler = new ManaTank(spieler);
        Manabar.setStyle("-fx-accent: blue;");
        manaMaximumSpieler = manaTankSpieler.getMana();
        double manaWert = manaMaximumSpieler / manaTankSpieler.getMana();
        Manabar.setProgress(manaWert);
        karteInHandEinfuegen();
    }
}
