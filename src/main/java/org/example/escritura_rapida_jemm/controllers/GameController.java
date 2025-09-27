package org.example.escritura_rapida_jemm.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
            "event", "eventHandler", "eventListener", "trigger", "callback",
            "signal", "slot", "clickEvent", "mouseEvent", "keyboardEvent",
            "touchEvent", "timerEvent", "systemEvent", "onClick", "onHover",
            "onKeyPress", "onLoad", "emit", "dispatch", "subscribe",
            "publish", "eventLoop", "observerPattern", "publisherSubscriber",
            "eventQueue", "asynchronous", "nonBlocking", "listenerRegistration",
            "addEventListener", "removeEventListener", "handleEvent", "preventDefault",
            "stopPropagation", "eventDriven", "reactive", "callbackBased", "customEvent",
            "DOMEvent", "UIEvent", "focusEvent", "blurEvent", "changeEvent",
            "inputEvent", "animationEvent", "transitionEvent", "wheelEvent", "pointerEvent",
            "dragEvent", "dropEvent", "touchStart", "touchMove", "touchEnd",
            "mousedown", "mouseup", "mousemove", "keydown", "keyup",
            "keypress", "loadEvent", "unloadEvent", "resizeEvent", "scrollEvent",
            "networkEvent", "fetchEvent", "promise", "asyncFunction", "await",
            "eventEmitter", "onceListener", "removeAllListeners", "debounce", "throttle",
            "customEventListener", "broadcast", "signalSlotPattern", "reactiveStreams", "observable",
            "subject", "subscriber", "subscription", "hotObservable", "coldObservable",
            "eventBus", "queueEvent", "processEvent", "handleAsync", "notifyListeners",
            "attachHandler", "detachHandler", "triggerEvent", "chainCallback", "eventScheduler",
            "priorityQueue", "eventDispatcher", "listenerCount", "registerCallback", "emitSignal"
    );


    private Random random = new Random();
    private int level = 1;
    private int timeLeft = 20;
    private Timeline timeline;
    @FXML
    public void initialize() {

        startGameButton.setText("Start");
        inputWordTextField.setEditable(false);
        hitsLabel.setText("Level: 1");
        writeWordLabel.setText("Press Start to play");
        inputWordTextField.setStyle("-fx-border-color: black; -fx-border-width: 3;");
        alertsLabel.setText("");
        inputWordTextField.setText("Type here...");
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
        } else if(startGameButton.getText().equals("Restart")) {
            initialize();
        }else{
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
            timeline.stop();
        }
        timeLeft = getLevelTime(level);
        timeLabel.setText("Time: " + timeLeft);
        inputWordTextField.setEditable(true);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timeLabel.setText("Time: " + timeLeft);

            if (timeLeft <= 0) {
                timeline.stop();
                inputWordTextField.setEditable(false);


                boolean correct = inputWordTextField.getText().trim().equals(writeWordLabel.getText());
                if (!correct) {
                    alertsLabel.setStyle("-fx-text-fill: red;");
                    alertsLabel.setText("⏱ TIME IS OVER! WRONG WORD");
                    inputWordTextField.setStyle("-fx-border-color: red; -fx-border-width: 3;");
                    showSummary();
                } else {
                    level++;
                    hitsLabel.setText("Level: " + level);
                    alertsLabel.setStyle("-fx-text-fill: green;");
                    alertsLabel.setText("NEXT LEVEL");
                    inputWordTextField.setStyle("-fx-border-color: green; -fx-border-width: 3;");

                    inputWordTextField.clear();
                    nextWord();
                    startTimer();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    public void setNickname(String nickname) {
        nicknameLabel.setText("Player: " + nickname);
    }

    private void nextWord() {

        String next = words.get(random.nextInt(words.size()));
        writeWordLabel.setText(next);
    }

    private void checkWord() {
        String typed = inputWordTextField.getText().trim();
        if (typed.equals(writeWordLabel.getText()) && timeLeft >= 0) {
            level++;
            hitsLabel.setText("level: " + level);
            inputWordTextField.clear();
            alertsLabel.setStyle("-fx-text-fill: green;");
            inputWordTextField.setStyle("-fx-border-color: green; -fx-border-width: 3;");
            alertsLabel.setText("NEXT LEVEL");
            nextWord();
            startTimer();
        } else {
            alertsLabel.setStyle("-fx-text-fill: red;");
            alertsLabel.setText("WRONG WORD");
            inputWordTextField.setStyle("-fx-border-color: red; -fx-border-width: 3;");
        }
    }
    public int getLevelTime(int level) {
        int time = 20 - 2 * ((level - 1) / 5);
        if (time < 2) {
            time = 2; // tiempo mínimo
        }
        return time;
    }
    private void showSummary() {
        startGameButton.setText("Restart");
        inputWordTextField.setText("CORRECT WORD: " + writeWordLabel.getText() +
                " | LEVEL REACHED: " + level);
    }


}

