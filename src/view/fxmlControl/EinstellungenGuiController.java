package view.fxmlControl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import view.mp3.mp3Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static resources.Konstanten.spielStandIO;
import static resources.StringsGUI.*;


/**
 * Klasse, welche alle Methoden der Einstellungs Szene enthaelt.
 */
public class EinstellungenGuiController extends GuiController
        implements Initializable
{
    @FXML private Slider lautstaerkeMusikSlider;
    @FXML private Slider lautstaerkeEffektSlider;
    @FXML private ComboBox FenstergroesseBox;
    @FXML private String[] aufloesungsgroessen =
            {AUFLOESUNG_GROESSE_HD, AUFLOESUNG_GROESSE_FULLHD};

    /**
     * Wird aufgerufen, um diesen Controller zu initialisieren.
     *
     * @param url            Der Standort, der zum Auflösen relativer Pfade
     *                       für das
     *                       Root-Objekt verwendet wird.
     * @param resourceBundle Die zum Lokalisieren des Root-Objekts
     *                       verwendeten Ressourcen.
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lautstaerkeMusikSlider.setValue(spiel.getLautstaerkeMusik());
        lautstaerkeEffektSlider.setValue(spiel.getLaustaerkeEffekte());
        erstelleCombobox(aufloesungsgroessen, FenstergroesseBox);
        FenstergroesseBox.setOnAction(this::wechselFenstergroesse);
        FenstergroesseBox.setPromptText(aufloesungsgroessen[0]);

        lautstaerkeMusikSlider.valueProperty()
                              .addListener(new ChangeListener<Number>()
                              {
                                  @Override public void changed(
                                          ObservableValue<? extends Number> observableValue,
                                          Number number, Number t1)
                                  {
                                      int lautstaerke =
                                              (int) lautstaerkeMusikSlider.getValue();
                                      mp3Controller.wechselLautstaerkeMusik(
                                              lautstaerke);
                                  }
                              });

        lautstaerkeEffektSlider.valueProperty()
                               .addListener(new ChangeListener<Number>()
                               {
                                   @Override public void changed(
                                           ObservableValue<? extends Number> observableValue,
                                           Number number, Number t1)
                                   {
                                       int lautstaerke =
                                               (int) lautstaerkeEffektSlider.getValue();
                                       mp3Controller.wechselLautstaerkeEffekte(
                                               lautstaerke);
                                   }
                               });
    }

    /**
     * Methode oeffneHilfe wird ueberlagert, hierbei wird der String mit dem
     * Text des Popups geaendert.
     *
     * @param event ActionEvent, welches mit dieser Methode verknuepft wird.
     */
    @Override public void oeffneHilfe(ActionEvent event)
    {
        offneHilfeTextEinsetzen(HILFE_EINSTELLUNGEN);
    }

    /**
     * Methode um die Fenstergroesse zu aendern, ausgehend von der aktuellen
     * Groesse.
     *
     * @param event ActionEvent, welches diese mit dieser Methode verknuepft wird.
     */
    public void wechselFenstergroesse(Event event)
    {

        if (FenstergroesseBox.getValue() == AUFLOESUNG_GROESSE_HD)
        {
            wechselAufloesungHD(event);
        }
        if (FenstergroesseBox.getValue() == AUFLOESUNG_GROESSE_FULLHD)
        {
            wechselAufloesungFullHD(event);
        }
    }

    @Override public void wechselZuHauptmenue(ActionEvent event)
            throws IOException
    {
        wechselZu(event, HAUPTMENUE_PFAD);
        spiel.setLautstaerkeMusik(lautstaerkeMusikSlider.getValue());
        spiel.setLaustaerkeEffekte(lautstaerkeEffektSlider.getValue());
        spielStandIO.schreibeDatei(spiel);
    }
}
