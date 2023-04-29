package com.enigma.enigma.Simulator.UI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TextEncoder {

    private Button toTextEncoder, back;

    private int fontSize = 17;

    private Stage stage;
    private Scene mainScene, textEncoderScene;
    private Group mainGroup, textEncoderGroup;

    private TextField textArea;

    public TextEncoder(Stage stage, Scene mainScene, Scene textEncoderScene, Group mainGroup, Group textEncoderGroup) {
        this.stage = stage;
        this.mainScene = mainScene;
        this.textEncoderScene = textEncoderScene;
        this.mainGroup = mainGroup;
        this.textEncoderGroup = textEncoderGroup;

        drawTextEncoder();
    }

    private void drawTextEncoder() {
        drawButtons();
    }

    private void drawButtons() {
        toTextEncoder = new Button("Text Encoder");
        back = new Button("Back");

        // Main Screen to Text Encoder Screen Button
        toTextEncoder.setLayoutX(500);
        toTextEncoder.setLayoutY(25);
        this.mainGroup.getChildren().add(toTextEncoder);
        toTextEncoder.setStyle("-fx-background-color: gray; -fx-font-size: " + fontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        toTextEncoder.setOnAction(event -> {
            this.stage.setScene(this.textEncoderScene);
            this.stage.setTitle("Enigma Simulator");
            this.stage.show();
        });

        // Text Encoder Screen to Main Screen Button (Back)
        back.setLayoutX(725);
        back.setLayoutY(25);
        this.textEncoderGroup.getChildren().add(back);
        back.setStyle("-fx-background-color: gray; -fx-font-size: " + fontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        back.setOnAction(event -> {
            this.stage.setScene(this.mainScene);
            this.stage.setTitle("Enigma Simulator");
            this.stage.show();
        });
    }

    private void drawTextBox() {
        textArea = new TextField();
        textArea.setLayoutX(200);
        textArea.setLayoutY(200);
        textArea.setPrefSize(200, 200);
        this.textEncoderGroup.getChildren().add(textArea);
        textArea.setStyle("-fx-background-color: gray; -fx-font-size: " + fontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 3;");
    }
}
