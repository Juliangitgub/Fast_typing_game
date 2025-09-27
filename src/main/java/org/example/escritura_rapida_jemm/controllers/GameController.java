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
    private Label alertsLabel;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label hitsLabel;

    @FXML
    private Button startGameButton;
    @FXML
    private Label timeLabel;
    private List<String> words = Arrays.asList(
            "event",
            "eventHandler",
            "eventListener",
            "trigger",
            "callback",
            "signal",
            "slot",
            "clickEvent",
            "mouseEvent",
            "keyboardEvent",
            "touchEvent",
            "timerEvent",
            "systemEvent",
            "onClick",
            "onHover",
            "onKeyPress",
            "onLoad",
            "emit",
            "dispatch",
            "subscribe",
            "publish",
            "eventLoop",
            "observerPattern",
            "publisherSubscriber",
            "eventQueue",
            "asynchronous",
            "nonBlocking",
            "listenerRegistration",
            "addEventListener",
            "removeEventListener",
            "handleEvent",
            "preventDefault",
            "stopPropagation",
            "eventDriven",
            "reactive",
            "callbackBased"
    );

    private Random random = new Random();
    private int level = 1;
    private int timeLeft = 20;
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
        if (timeline != null) {
            timeline.stop(); // Detener timeline anterior
        }

        timeLeft = getLevelTime(level);
        timeLabel.setText("Time: " + timeLeft);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timeLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                timeline.stop();
                inputWordTextField.setEditable(false);
                writeWordLabel.setText("⏱ THE TIME IS OVER!");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void setNickname(String nickname) {
        nicknameLabel.setText("Player: " + nickname);
    }

    private void nextWord() {
        alertsLabel.setText("");
        String next = words.get(random.nextInt(words.size()));
        writeWordLabel.setText(next);
    }

    private void checkWord() {
        String typed = inputWordTextField.getText().trim();
        if (typed.equals(writeWordLabel.getText()) && timeLeft >= 0) {
            level++;
            hitsLabel.setText("Hits: " + level);
            inputWordTextField.clear();
            nextWord();
            startTimer();
        } else {
            alertsLabel.setStyle("-fx-text-fill: red;");
            alertsLabel.setText("WRONG WORD");
        }
    }
    public int getLevelTime(int level) {
        int time = 20 - 2 * ((level - 1) / 5);
        if (time < 2) {
            time = 2; // tiempo mínimo
        }
        return time;
    }
}

