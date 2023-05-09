package com.enigma.enigma.Simulator.UI;

import com.enigma.enigma.Simulator.Enigma.Enigma;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TextEncoder {

    private Button toTextEncoder, back, save;

    private int fontSize = 17;

    private Stage stage;
    private Scene mainScene, textEncoderScene;
    private Group mainGroup, textEncoderGroup;

    private TextField textAreaLeft;
    private TextField textAreaRight;

    private TextArea textArea;
    private Label english, encoded;

    private int MAX_FONT_SIZE = 20;

    private Enigma enigma;
    public TextEncoder(Stage stage, Scene mainScene, Scene textEncoderScene, Group mainGroup, Group textEncoderGroup, Enigma enigma) {
        this.stage = stage;
        this.mainScene = mainScene;
        this.textEncoderScene = textEncoderScene;
        this.mainGroup = mainGroup;
        this.textEncoderGroup = textEncoderGroup;
        this.enigma = enigma;

        drawTextEncoder();
    }

    private void drawTextEncoder() {
        drawButtons();
        drawTextBox();
        drawLabels();
    }

    private void drawButtons() {
        toTextEncoder = new Button("Text Encoder");
        back = new Button("Back");
        save = new Button("Execute");

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

        // Save Button
        save.setLayoutX(360);
        save.setLayoutY(525);
        this.textEncoderGroup.getChildren().add(save);
        save.setStyle("-fx-background-color: gray; -fx-font-size: " + fontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        save.setOnAction(event -> {

            String toEncode = textAreaLeft.getText().toUpperCase();
            textAreaRight.setText(enigma.encrypt(toEncode));

            System.out.println("Left Text: " + textAreaLeft.getText());
            System.out.println("Right Text: " + textAreaRight.getText());
        });
    }

    private void drawTextBox() {

        // Left Box
        textAreaLeft = new TextField();
        textAreaLeft.setLayoutX(25);
        textAreaLeft.setLayoutY(200);
        textAreaLeft.setPrefSize(362.5, 300);
        this.textEncoderGroup.getChildren().add(textAreaLeft);
        textAreaLeft.setStyle("-fx-background-color: gray; -fx-font-size: " + fontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 3;");

        // Right Box
        textAreaRight = new TextField();
        textAreaRight.setLayoutX(437.5);
        textAreaRight.setLayoutY(200);
        textAreaRight.setPrefSize(362.5, 300);
        this.textEncoderGroup.getChildren().add(textAreaRight);
        textAreaRight.setStyle("-fx-background-color: gray; -fx-font-size: " + fontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 3;");
    }

    private void drawLabels() {
        english = new Label("English");
        encoded = new Label("Encoded");

        // English Side (left)
        english.setLayoutY(515);
        english.setLayoutX(165);
        this.textEncoderGroup.getChildren().add(english);
        english.setFont(new Font(MAX_FONT_SIZE));

        // Encoded Side (right)
        encoded.setLayoutY(515);
        encoded.setLayoutX(575);
        this.textEncoderGroup.getChildren().add(encoded);
        encoded.setFont(new Font(MAX_FONT_SIZE));
    }
}
