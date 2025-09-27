package org.example.escritura_rapida_jemm.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameController {

    @FXML
    private TextField inputWordTextField;

    @FXML
    private Label writeWordLabel;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label hitsLabel;

    @FXML
    private Button startGameButton;
    @FXML
    private Label timeLabel;

    private String nickname;
    private List<String> words = Arrays.asList("java", "codigo", "rapido", "pantalla", "teclado", "programa");
    private Random random = new Random();
    private int hits = 0;
    private int timeLeft = 10;
    private Timeline timeline;
    @FXML
    public void initialize() {
        startGameButton.setText("Start");
        inputWordTextField.setEditable(false);
        hitsLabel.setText("Hits: 0");
        writeWordLabel.setText("Press Start to play");
    }

    @FXML
    void onActionVerifyButton(ActionEvent event) {
        if (startGameButton.getText().equals("Start")) {
            // first time: start game
            startGameButton.setText("Verify");
            inputWordTextField.setEditable(true);
            inputWordTextField.clear();
            nextWord();
            startTimer();
        } else {
            //Then check word
            checkWord();
        }
    }

    @FXML
    void onKeyEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            checkWord(); // check first
        }
    }


    private void startTimer() {
        timeLabel.setText("Time: " + timeLeft);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timeLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                timeline.stop();
                inputWordTextField.setEditable(false);
                checkWord();
                writeWordLabel.setText("⏱ THE TIME IS OVER!");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        nicknameLabel.setText("Player: " + nickname);
    }

    private void nextWord() {
        String next = words.get(random.nextInt(words.size()));
        writeWordLabel.setText(next);
    }

    private void checkWord() {
        String typed = inputWordTextField.getText().trim();
        if (typed.equals(writeWordLabel.getText()) && timeLeft >= 0) {
            hits++;
            hitsLabel.setText("Hits: " + hits);
            inputWordTextField.clear();
            nextWord();
        } else if (timeLeft <= 0) {
            writeWordLabel.setText("THE TIME IS OVER");
            inputWordTextField.setEditable(false);
        }
    }
}
