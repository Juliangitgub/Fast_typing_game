package org.example.escritura_rapida_jemm.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.escritura_rapida_jemm.views.GameView;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WelcomeController {

    @FXML
    private TextField nicknameTextField;

    @FXML
    void onActionInstruction(ActionEvent event) {
        /*
        try {
            File file = new File("src/main/resources/org/example/escritura_rapida_jemm/instructions.html");
            Desktop.getDesktop().browse(file.toURI()); // abre en el navegador predeterminado
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
    }

    @FXML
    void onActionStartButton(ActionEvent event) throws IOException {
        // get nickname
        String nickname = nicknameTextField.getText().trim();

        // Close Welcome window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Open Game window
        GameView gameView = new GameView();

        if (!nickname.isEmpty()) {
            gameView.getController().setNickname(nickname);
        }

        gameView.show();
    }

}
