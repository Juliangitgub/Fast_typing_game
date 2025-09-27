package org.example.escritura_rapida_jemm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.escritura_rapida_jemm.views.GameView;
import org.example.escritura_rapida_jemm.views.WelcomeView;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        WelcomeView welcomeView = new WelcomeView();

    }
}
