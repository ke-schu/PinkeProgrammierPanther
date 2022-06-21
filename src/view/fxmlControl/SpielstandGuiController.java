package view.fxmlControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SpielstandGuiController extends GuiController
{

    @FXML public void schliesseSpielstand(ActionEvent event)
    {
        Stage spielstandPopUp= (Stage) ((Node) event.getSource()).getScene().getWindow();
        spielstandPopUp.close();
    }
}
