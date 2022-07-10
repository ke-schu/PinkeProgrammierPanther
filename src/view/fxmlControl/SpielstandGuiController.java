package view.fxmlControl;

import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
import utility.KartenDeckIO;
import utility.SpielStandIO;
import view.components.KarteGrossVBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Strings.SPIEL_DECK_SPIELER_PFAD;
import static resources.StringsGUI.BILDER_PFAD;
import static resources.StringsGUI.PNG_DATEI_ENDUNG;

public class SpielstandGuiController extends GuiController implements
                                                           Initializable
{
    @FXML Label goldLabel;
    @FXML Label levelLabel;
    @FXML Label decknameLabel;
    @FXML ImageView deckBild;
    @Override public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            SpielStand spielstand = SpielStandIO.leseDatei();
            this.goldLabel.setText(Integer.toString(spielstand.getGold()));
            this.levelLabel.setText(Integer.toString(spielstand.getSpieler().getLevel()));
            this.decknameLabel.setText(KartenDeckIO.leseDatei(SPIEL_DECK_SPIELER_PFAD).getDeckBezeichnung());
            File charakterbild = new File(BILDER_PFAD + spielstand.getSpieler().getName() + PNG_DATEI_ENDUNG);
            this.deckBild.setImage(new Image(charakterbild.getAbsolutePath()));
        }
        catch (JsonNichtLesbarException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML public void schliesseSpielstand(ActionEvent event)
    {
        Stage spielstandPopUp = (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.close();
    }

    public void kartenDeckAnzeigen(ActionEvent event)
    {
        int k = spiel.getSpieldeckSpieler().size();
        int h = 0;

        Stage spielstandPopUp = (Stage)((Node) event.getSource()).getScene().getWindow();
        ScrollPane spane = new ScrollPane();
        GridPane pane = new GridPane();
        spane.getStylesheets().add(SpielstandGuiController.class.getResource("/view/css/Spielstand.css").toExternalForm());
        spane.setContent(pane);

            for (int i=0; i < 2 && h < k; i++)
            {
                for (int j = 0; j < 5 && h < k; j++)
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
        schliessenButton.setText("Schließen");
        schliessenButton.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override public void handle(ActionEvent arg0)
        {
            spielstandPopUp.close();
        }
        });
        pane.add(schliessenButton,2,zeilenAnzahl +1 );
        pane.setHalignment(schliessenButton,HPos.CENTER);
        Scene sc = new Scene(spane);
        spielstandPopUp.setScene(sc);
        spielstandPopUp.show();
    }
}
