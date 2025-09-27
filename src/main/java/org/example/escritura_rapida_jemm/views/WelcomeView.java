package org.example.escritura_rapida_jemm.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.escritura_rapida_jemm.HelloApplication;

import java.io.IOException;

public class WelcomeView extends Stage {

    public WelcomeView()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        this.setTitle("Hello!");
        this.setScene(scene);
        this.setFullScreen(true);
        this.show();
    }


}
