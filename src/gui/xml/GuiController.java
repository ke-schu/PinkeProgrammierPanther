package gui.xml;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static gui.GuiKonstanten.*;

public class GuiController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    protected void wechselZu(ActionEvent event, String pfad) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(GuiController.class.getResource(pfad));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void oeffneHilfe (ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_TEXT);
    }

    public void offneHilfeTextEinsetzen (String hilfeText)
    {
        final Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Hilfe");
        popupStage.getIcons().add(new Image(ICON.getAbsolutePath()));

        VBox vbox = new VBox(20);
        TextArea text = new TextArea();
        text.setText(hilfeText);
        text.setWrapText(true);
        text.setEditable(false);
        vbox.getChildren().add(text);
        Scene popupScene = new Scene(vbox,300,400);
        Button okButton = new Button("CLOSE");
        okButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent arg0)
            {
                popupStage.close();
            }
        });
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(okButton);
        popupStage.setScene(popupScene);
        popupStage.setResizable(false);
        popupStage.setAlwaysOnTop(true);
        popupStage.show();
    }

    @FXML
    public void wechselZuHauptmenue (ActionEvent event) throws IOException
    {
        wechselZu(event, "Hauptmenue.fxml");
    }

    public void wechselZuEinstellungen (ActionEvent event) throws IOException
    {
        wechselZu(event, "Einstellungen.fxml");
    }

    public void wechselZuCharakterauswahl (ActionEvent event) throws IOException
    {
        wechselZu(event, "Charakterauswahl.fxml");
    }

    public void wechselZuSpielEbene (ActionEvent event) throws IOException
    {
        wechselZu(event, "Spielebene.fxml");
    }

    public void spielBeenden (ActionEvent event)
    {
        Platform.exit();
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Scene getScene()
    {
        return scene;
    }

    public void setScene(Scene scene)
    {
        this.scene = scene;
    }

    public Parent getRoot()
    {
        return root;
    }

    public void setRoot(Parent root)
    {
        this.root = root;
    }
}