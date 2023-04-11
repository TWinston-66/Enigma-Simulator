package com.enigma.enigma.Simulator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    String letterOrder = "QWERTYUIOPASDFGHJKLZXCVBNM";
    char[] letters = letterOrder.toCharArray();

    // Window Parameters
    double width = 825;
    double height = 600;

    // Glow-board Parameters
    double keyPadding = 50;
    double keyRadius = 25;


    @Override
    public void start(Stage stage) {
        // Object Group
        Group group = new Group();

        // First Keyboard Row (q -> p)
        Circle[] circles = new Circle[26];
        Text[] letterText = new Text[26];
        double lastX = 0;
        double x;

        for (int i = 0; i < 10; i++) {
            x = keyPadding + (keyRadius);
            circles[i] = new Circle(x + lastX, 350, keyRadius);
            circles[i].setFill(Color.LIGHTSLATEGRAY);
            circles[i].setStroke(Color.BLACK);
            group.getChildren().add(circles[i]);

            letterText[i] = new Text(circles[i].getCenterX() - 5, circles[i].getCenterY() + 5, String.valueOf(letters[i]));
            group.getChildren().add(letterText[i]);

            lastX = x + lastX;
        }
        lastX = 0;
        // Second Keyboard Row (a -> l)
        for (int i = 10; i < 19; i++) {
            x = keyPadding + keyRadius;
            circles[i] = new Circle(x + lastX + 40, 450, keyRadius);
            circles[i].setFill(Color.LIGHTSLATEGRAY);
            circles[i].setStroke(Color.BLACK);
            group.getChildren().add(circles[i]);

            letterText[i] = new Text(circles[i].getCenterX() - 5, circles[i].getCenterY() + 5, String.valueOf(letters[i]));
            group.getChildren().add(letterText[i]);

            lastX = x + lastX;
        }
        lastX = 0;
        // Third Keyboard Row (z -> m)
        for (int i = 19; i < 26; i++) {
            x = keyPadding + keyRadius;
            circles[i] = new Circle(x + lastX + 80, 550, keyRadius);
            circles[i].setFill(Color.LIGHTSLATEGRAY);
            circles[i].setStroke(Color.BLACK);
            group.getChildren().add((circles[i]));

            letterText[i] = new Text(circles[i].getCenterX() - 5, circles[i].getCenterY() + 5, String.valueOf(letters[i]));
            group.getChildren().add(letterText[i]);

            lastX = x + lastX;
        }

        Scene scene = new Scene(group, width, height, Color.DARKSLATEGRAY);
        scene.setFill(Color.GRAY);

        scene.setOnKeyPressed(event -> {
            String key = event.getText();
            int letterIndex = letterOrder.indexOf(key.toUpperCase());
            if (letterIndex != -1) {
                circles[letterIndex].setFill(Color.YELLOW);
            }
        });

        scene.setOnKeyReleased(event -> {
            String key = event.getText();
            int letterIndex = letterOrder.indexOf(key.toUpperCase());
            if (letterIndex != -1) {
                circles[letterIndex].setFill(Color.LIGHTSLATEGRAY);
            }
        });

        // Create the scene and set it on the primary stage
        stage.setScene(scene);
        stage.setTitle("Enigma Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}