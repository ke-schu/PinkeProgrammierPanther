package gui.xml;


import javafx.event.ActionEvent;

import static gui.GuiKonstanten.HILFE_HAUPTMENUE;

public class HauptmenueGuiController extends GuiController
{
    @Override
    public void oeffneHilfe(ActionEvent event) {
        offneHilfeTextEinsetzen(HILFE_HAUPTMENUE);
    }

}


