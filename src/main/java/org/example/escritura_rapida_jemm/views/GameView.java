package org.example.escritura_rapida_jemm.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.escritura_rapida_jemm.HelloApplication;
import org.example.escritura_rapida_jemm.controllers.GameController;

import java.io.IOException;

public class GameView extends Stage {

    private GameController controller;

    public GameView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("game_view.fxml")
        );
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root, 320, 240);
        this.setTitle("Game View");
        this.setScene(scene);
        this.setFullScreen(true);

    }

    public GameController getController() {
        return controller;
    }
}
