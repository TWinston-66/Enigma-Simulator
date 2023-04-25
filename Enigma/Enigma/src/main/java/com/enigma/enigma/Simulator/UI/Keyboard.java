package com.enigma.enigma.Simulator.UI;

import com.enigma.enigma.Simulator.Enigma.Enigma;
import com.enigma.enigma.Simulator.UI.Controllers.KeyboardController;
import com.enigma.enigma.Simulator.Util.Letters;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    private Circle[] circles = new Circle[26];
    private Text[] letterText = new Text[26];
    private double lastX = 0;
    private double x;

    // Parameters
    private double keyPadding, keyRadius;

    private Group group;

    private KeyboardController controller;


    List<Circle> plugThings = new ArrayList<>();

    public Keyboard(double keyPadding, double keyRadius, Group group) {
        this.keyPadding = keyPadding;
        this.keyRadius = keyRadius;
        this.group = group;

        controller = new KeyboardController();
        drawKeyboard();
    }

    private void drawKeyboard() {

        // First Keyboard Row (q -> p)
        for (int i = 0; i < 10; i++) {
            x = keyPadding + (keyRadius);
            circles[i] = new Circle(x + lastX, 350, keyRadius);
            circles[i].setFill(Color.LIGHTSLATEGRAY);
            circles[i].setStroke(Color.BLACK);
            group.getChildren().add(circles[i]);

            letterText[i] = new Text(circles[i].getCenterX() - 5, circles[i].getCenterY() + 5, String.valueOf(Letters.letters[i]));
            group.getChildren().add(letterText[i]);
            plugThings.add(circles[i]);

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

            letterText[i] = new Text(circles[i].getCenterX() - 5, circles[i].getCenterY() + 5, String.valueOf(Letters.letters[i]));
            group.getChildren().add(letterText[i]);
            plugThings.add(circles[i]);

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

            letterText[i] = new Text(circles[i].getCenterX() - 5, circles[i].getCenterY() + 5, String.valueOf(Letters.letters[i]));
            group.getChildren().add(letterText[i]);
            plugThings.add(circles[i]);

            lastX = x + lastX;
        }
    }

    public Circle[] getCircles() {
        return this.circles;
    }

    public Text[] getLetterText() {
        return this.letterText;
    }

    public void initKeyboard(Enigma enigma, Keyboard keyboard, Scene scene) {
        controller.initKeyboard(enigma, keyboard, scene);
    }

    public List<Circle> getPlugThings() {
        return plugThings;
    }
}
