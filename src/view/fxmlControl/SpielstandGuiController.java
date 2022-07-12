package view.fxmlControl;

import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SpielStand;
import view.components.ArtefakteTalenteVBox;
import view.components.KarteGrossVBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.kartenDeckIO;
import static resources.Konstanten.spielStandIO;
import static resources.Strings.SPIEL_DECK_SPIELER_PFAD;
import static resources.KonstantenGUI.AUFLOESUNG_HOEHE_HD;
import static resources.KonstantenGUI.SPALTENAHNZAHL_KARTENDECK_ANZEIGE;
import static resources.StringsGUI.*;

public class SpielstandGuiController extends GuiController
{
    @FXML Label goldLabel;
    @FXML Label levelLabel;
    @FXML Label decknameLabel;
    @FXML ImageView deckBild;
    @FXML Label lebenspunkteLabel;
    @FXML Label erfahrungspunkteLabel;
    @FXML Label manaLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            SpielStand spielstand = spielStandIO.leseSpielstand();
            this.goldLabel.setText(GOLD + spielstand.getGold());
            this.levelLabel.setText(LEVEL_STAT + spielstand.getSpieler().getLevel());
            this.erfahrungspunkteLabel.setText(ERFAHRUNGSPUNKTE + spielstand.getSpieler().getErfahrungspunkte() + SCHRAEGSTRICH + spielstand.getSpieler().getLevelGrenze());
            this.lebenspunkteLabel.setText(LP_STAT + spielstand.getSpieler().getLebenspunkte() + SCHRAEGSTRICH +spielstand.getSpieler().getMaxleben());
            this.manaLabel.setText(MANA_MAX + spielstand.getSpieler().getMana());
            this.decknameLabel.setText(DECKNAHME + kartenDeckIO.leseKartenDeck(SPIEL_DECK_SPIELER_PFAD).getDeckBezeichnung());
            File charakterbild = new File(BILDER_PFAD + spielstand.getSpieler().getName() + PNG_DATEI_ENDUNG);
            this.deckBild.setImage(new Image(charakterbild.getAbsolutePath()));
        }
        catch (JsonNichtLesbarException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Methode um das Popup des Spielstandes zu schliessen.
     * @param event Event, welche diese Methode ausloest.
     */
    @FXML public void schliesseSpielstand(ActionEvent event)
    {
        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.close();
    }

    /**
     * Methode um das Spielerdeck in einem Fenster anzuzeigen.
     * @param event Event, welche diese Methode ausloest.
     */
    public void kartenDeckAnzeigen(ActionEvent event)
    {
        int k = spiel.getSpieldeckSpieler().size();
        int h = 0;

        Stage spielstandPopUp = (Stage)((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.setMaxHeight(AUFLOESUNG_HOEHE_HD);
        ScrollPane spane = new ScrollPane();
        GridPane pane = new GridPane();
        spane.getStylesheets().add(SpielstandGuiController.class.getResource(SPIELSTAND_KARTENDECK_CSS_PFAD).toExternalForm());
        spane.setContent(pane);

            for (int i=0; h < k; i++)
            {
                for (int j = 0; j < SPALTENAHNZAHL_KARTENDECK_ANZEIGE && h < k; j++)
                {
                    Node karte = new KarteGrossVBox(spiel.getSpieldeckSpieler().get(h));
                    pane.add((karte), j, i);
                    pane.setHalignment(karte, HPos.CENTER);
                    pane.setValignment(karte, VPos.CENTER);
                    h++;
                }
            }

        int zeilenAnzahl = pane.getRowCount();
        Button schliessenButton = new Button();
        schliessenButton.setText(POPUP_BUTTON_SCHLIESSEN);
        schliessenButton.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override public void handle(ActionEvent arg0)
        {
            spielstandPopUp.close();
        }
        });
        pane.add(schliessenButton,2,zeilenAnzahl + 1 );
        pane.setHalignment(schliessenButton,HPos.CENTER);
        Scene sc = new Scene(spane);
        spielstandPopUp.setScene(sc);
        spielstandPopUp.show();
    }

    /**
     * Methode um die Talente des Spielers in einem neuen Popup anzuzeigen.
     * @param event Event, welches diese Methode ausloest.
     */
    public void TalenteAnzeigen(ActionEvent event)
    {
        if (!(spiel.getSpieler().getTalente().empty()))
        {
            int k = spiel.getSpieler().getTalente().size();
            int h = 0;

            Stage spielstandPopUp =
                    (Stage) ((Node) event.getSource()).getScene().getWindow();
            spielstandPopUp.setMaxHeight(AUFLOESUNG_HOEHE_HD);
            ScrollPane spane = new ScrollPane();
            GridPane pane = new GridPane();
            spane.getStylesheets().add(SpielstandGuiController.class.getResource(
                    SPIELSTAND_ARTEFAKTE_TALENTE_CSS_PFAD).toExternalForm());
            spane.setContent(pane);

            for (int i = 0; h < k; i++)
            {
                for (int j = 0; j < SPALTENAHNZAHL_KARTENDECK_ANZEIGE && h < k;
                     j++)
                {

                    Node talent = new ArtefakteTalenteVBox(
                            spiel.getSpieler().getTalente().get(h));
                    pane.add((talent), j, i);
                    pane.setHalignment(talent, HPos.CENTER);
                    pane.setValignment(talent, VPos.CENTER);
                    h++;
                }
            }

            Button schliessenButton = new Button();
            schliessenButton.setText(POPUP_BUTTON_SCHLIESSEN);
            schliessenButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent arg0)
                {
                    spielstandPopUp.close();
                }
            });
            pane.add(schliessenButton, 2, 0);
            pane.setHalignment(schliessenButton, HPos.CENTER);
            Scene sc = new Scene(spane);
            spielstandPopUp.setScene(sc);
            spielstandPopUp.show();
        }
    }

    /**
     * Methode um die Artefakte des Spielers in einem neuen Popup anzuzeigen.
     * @param event Event, welches diese Methode ausloest.
     */
    public void ArtefakteAnzeigen(ActionEvent event)
    {
        if (spiel.getSpieler().getArtefakte()[0] != null )
        {
            Stage spielstandPopUp =
                    (Stage) ((Node) event.getSource()).getScene().getWindow();
            spielstandPopUp.setMaxHeight(AUFLOESUNG_HOEHE_HD);
            ScrollPane spane = new ScrollPane();
            GridPane pane = new GridPane();
            spane.getStylesheets().add(SpielstandGuiController.class.getResource(
                    SPIELSTAND_ARTEFAKTE_TALENTE_CSS_PFAD).toExternalForm());
            spane.setContent(pane);
            Node artefakt = new ArtefakteTalenteVBox(
                    spiel.getSpieler().getArtefakte()[0]);
            pane.add((artefakt), 0, 0);
            pane.setHalignment(artefakt, HPos.CENTER);
            pane.setValignment(artefakt, VPos.CENTER);

            if (spiel.getSpieler().getArtefakte()[1] != null )
            {
                Node artefakt1 = new ArtefakteTalenteVBox(

                        spiel.getSpieler().getArtefakte()[1]);
                pane.add((artefakt1), 1, 0);
                pane.setHalignment(artefakt1, HPos.CENTER);
                pane.setValignment(artefakt1, VPos.CENTER);
            }

            Button schliessenButton = new Button();
            schliessenButton.setText(POPUP_BUTTON_SCHLIESSEN);
            schliessenButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override public void handle(ActionEvent arg0)
                {
                    spielstandPopUp.close();
                }
            });
            pane.add(schliessenButton, 2, 0);
            pane.setHalignment(schliessenButton, HPos.CENTER);
            Scene sc = new Scene(spane);
            spielstandPopUp.setScene(sc);
            spielstandPopUp.show();
        }
    }
}
