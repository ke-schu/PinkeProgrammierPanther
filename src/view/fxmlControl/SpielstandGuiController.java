package view.fxmlControl;

import exceptions.JsonNichtLesbarException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.SpielStand;
import utility.KartenDeckIO;
import utility.SpielStandIO;

import java.net.URL;
import java.util.ResourceBundle;

import static resources.Strings.SPIEL_DECK_SPIELER_PFAD;

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
        }
        catch (JsonNichtLesbarException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FXML public void schliesseSpielstand(ActionEvent event)
    {
        Stage spielstandPopUp= (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.close();
    }
}
