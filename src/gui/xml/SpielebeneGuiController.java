package gui.xml;

import exceptions.JsonNichtLesbarException;
import io.EbeneIO;
import io.KonsolenIO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Ebene;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Strings.AKTUELLE_EBENE_PFAD;

public class SpielebeneGuiController
        extends GuiController
        implements Initializable
{
    @FXML GridPane spielebenenGitter;
    final String STYLE_CLASS_FELD = "feld";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            Ebene test = EbeneIO.leseDatei(new File(AKTUELLE_EBENE_PFAD));

            for(int i = 0; i < test.getEbenenZeile(); i++)
            {
                spielebenenGitter.addRow(1);
            }

            for(int i = 0; i < test.getEbenenSpalte(); i++)
            {
                spielebenenGitter.addColumn(1);
                for(int j = 0; j < test.getEbenenZeile(); j++)
                {
                    StackPane meinePane = new StackPane();
                    Label meinLabel = new Label();
                    meinePane.setId(STYLE_CLASS_FELD);
                    if(test.getRaumAnPosition(i, j) != null && test.getRaumAnPosition(i, j).getEreignis() != null)
                    {
                        meinLabel.setText(test.getRaumAnPosition(i, j).getEreignis().getName());
                    }
                    else
                    {
                        meinLabel.setText("");
                    }
                    meinePane.getChildren().add(meinLabel);
                    spielebenenGitter.add(meinePane, i, j);
                }
            }
        }
        catch (JsonNichtLesbarException e)
        {
            KonsolenIO.ausgeben(e.getMessage());
        }
    }
}
