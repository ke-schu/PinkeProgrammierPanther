package gui.xml;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.io.IOException;

public class HauptmenueGuiController extends GuiContoller
{
    //ich muss noch genauer gucken warum das jetzt funktioniert wie es ist.
    @Override
    public void oeffneHilfe (ActionEvent event) throws IOException
    {

        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.WINDOW_MODAL);

        Button okButton = new Button("CLOSE");
        okButton.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent arg0)
            {
                myDialog.close();
            }

        });

        Scene myDialogScene = new Scene(new VBox());

        myDialog.setScene(myDialogScene);
        myDialog.show();
    }
}


