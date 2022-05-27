package gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.io.IOException;

public class Gui extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        Image icon = new Image("C:\\Users\\hendr\\Documents\\GitHub\\PinkeProgrammierPanther\\src\\resources\\grafiken\\Logo.png");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.getIcons().add(icon);
        stage.setTitle("DungeonDing");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}