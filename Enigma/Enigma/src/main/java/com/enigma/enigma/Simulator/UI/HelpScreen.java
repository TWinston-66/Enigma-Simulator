package com.enigma.enigma.Simulator.UI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelpScreen {

    private Button help;
    private Button helpBack;

    private double plugButtonFontSize = 17;

    public HelpScreen() {
        help = new Button("Help");
        helpBack = new Button("Back");
    }

    public void drawHelpScreen(Group group, Stage stage, Scene scene) {

        // Main Screen to Help Screen Button
        help.setLayoutX(50);
        help.setLayoutY(25);
        group.getChildren().add(help);
        help.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        help.setOnAction(event -> {
            stage.setScene(scene);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });

        // Help Screen to Main Screen Button
        helpBack.setLayoutX(50);
        helpBack.setLayoutY(25);
        group.getChildren().add(helpBack);
        helpBack.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        helpBack.setOnAction(event -> {
            stage.setScene(scene);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });
    }
}
