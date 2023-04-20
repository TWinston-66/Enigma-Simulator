package com.enigma.enigma.Simulator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    String letterOrder = "QWERTYUIOPASDFGHJKLZXCVBNM";
    char[] letters = letterOrder.toCharArray();

    // Window Parameters
    double width = 825;
    double height = 600;

    // Glow-board Parameters
    double keyPadding = 50;
    double keyRadius = 25;

    // Rotor Parameters
    double rotorMenuPadding = 300;
    double rotorMenuY = 150;
    double rotorMenuOffset = 75;
    double rotorMenuFontSize = 17;

    // Plugboard button parameters
    double plugButtonFontSize = 17;

    Enigma enigma = new Enigma(new String[] {"VII", "V", "IV"}, "B", new int[] {10,5,12}, new int[] {1,2,3}, "AD FT WH JO PN");



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


        ComboBox<String> rotor1 = new ComboBox<>(FXCollections.observableArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        ));

        ComboBox<String> rotor2 = new ComboBox<>(FXCollections.observableArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        ));

        ComboBox<String> rotor3 = new ComboBox<>(FXCollections.observableArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        ));


        rotor1.setValue("I");
        rotor2.setValue("II");
        rotor3.setValue("III");



        group.getChildren().add(rotor1);
        group.getChildren().add(rotor2);
        group.getChildren().add(rotor3);

        rotor1.setLayoutX(rotorMenuOffset);
        rotor1.setLayoutY(rotorMenuY);

        rotor2.setLayoutX(rotorMenuOffset + rotorMenuPadding);
        rotor2.setLayoutY(rotorMenuY);

        rotor3.setLayoutX(rotorMenuOffset + (2 * rotorMenuPadding));
        rotor3.setLayoutY(rotorMenuY);

        rotor1.setStyle("-fx-font-size: 15px;");

        // set the background color of the dropdown menu
        rotor1.setStyle("-fx-background-color: gray; -fx-font-size: " + rotorMenuFontSize + "px; -fx-text-fill: black;");
        rotor2.setStyle("-fx-background-color: gray; -fx-font-size: " + rotorMenuFontSize + "px; -fx-text-fill: black;");
        rotor3.setStyle("-fx-background-color: gray; -fx-font-size: " + rotorMenuFontSize + "px; -fx-text-fill: black;");

        Label rotor1Heading = new Label("Rotor 1");
        rotor1Heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        rotor1Heading.setLayoutX(rotorMenuOffset);
        rotor1Heading.setLayoutY(rotorMenuY - 40);
        group.getChildren().add(rotor1Heading);

        Label rotor2Heading = new Label("Rotor 2");
        rotor2Heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        rotor2Heading.setLayoutX(rotorMenuOffset + rotorMenuPadding);
        rotor2Heading.setLayoutY(rotorMenuY - 40);
        group.getChildren().add(rotor2Heading);

        Label rotor3Heading = new Label("Rotor 3");
        rotor3Heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        rotor3Heading.setLayoutX(rotorMenuOffset + (2 * rotorMenuPadding));
        rotor3Heading.setLayoutY(rotorMenuY - 40);
        group.getChildren().add(rotor3Heading);


        rotor1.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
        });
        rotor2.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
        });
        rotor3.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
        });

        /*
          // create a popup
        Popup popup = new Popup();

        // set background
        label.setStyle(" -fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

        // set size of label
        label.setMinWidth(80);
        label.setMinHeight(50);

        // action event
        EventHandler<ActionEvent> event =
        new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e)
            {
                if (!popup.isShowing())
                    popup.show(stage);
                else
                    popup.hide();
            }
        };
         */

        Scene scene = new Scene(group, width, height, Color.DARKSLATEGRAY);
        scene.setFill(Color.GRAY);



        final boolean[] isExecuted = {false};

        AtomicInteger pressedIndex = new AtomicInteger();

        scene.setOnKeyPressed(event -> {
            // check if the flag is false
            if (!isExecuted[0]) {
                // set the flag to true
                isExecuted[0] = true;

                String key = event.getText();
                int letterIndex = letterOrder.indexOf(key.toUpperCase());
                if (letterIndex != -1) {
                    char newLetter = enigma.encrypt(letters[letterIndex]);
                    letterIndex = letterOrder.indexOf(String.valueOf(newLetter).toUpperCase());
                    pressedIndex.set(letterIndex);
                    circles[letterIndex].setFill(Color.YELLOW);
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            if (pressedIndex.get() != -1) {
                circles[pressedIndex.get()].setFill(Color.LIGHTSLATEGRAY);
                isExecuted[0] = false;
            }
        });

        Group plugboardGroup = new Group();
        Scene plugboard = new Scene(plugboardGroup, width, height, Color.DARKSLATEGRAY);
        plugboard.setFill(Color.GRAY);


        Button button = new Button("Plugboard");
        button.setLayoutX(650);
        button.setLayoutY(25);
        group.getChildren().add(button);
        button.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black;");
        button.setOnAction(event -> {
            stage.setScene(plugboard);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });

        Button back = new Button("Back");
        back.setLayoutX(700);
        back.setLayoutY(25);
        plugboardGroup.getChildren().add(back);
        back.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black;");
        back.setOnAction(event -> {
            stage.setScene(scene);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });

        double rectangleSize = 60;
        Rectangle rect1 = new Rectangle(50, 50, rectangleSize, rectangleSize);
        Rectangle rect2 = new Rectangle(250, 50, rectangleSize, rectangleSize);
        rect1.setFill(Color.LIGHTSLATEGRAY);
        rect2.setFill(Color.LIGHTSLATEGRAY);
        Line line = new Line();

        // First Keyboard Row (q -> p)
        Circle[] plugs = new Circle[26];
        // create a list of all the circles for snapping
        List<Circle> plugThings = new ArrayList<>();
        double lastXthing = 0;
        double xThing;
        double plugRadiusThing = 15;
        double plugPaddingThing = 60;

        for (int i = 0; i < 10; i++) {
            xThing = plugPaddingThing + (plugRadiusThing);
            plugs[i] = new Circle(xThing + lastXthing, 350, plugRadiusThing);
            plugs[i].setFill(Color.LIGHTSLATEGRAY);
            plugs[i].setStroke(Color.BLACK);
            plugboardGroup.getChildren().add(plugs[i]);

            letterText[i] = new Text(plugs[i].getCenterX() - 5, plugs[i].getCenterY() + 5, String.valueOf(letters[i]));
            plugboardGroup.getChildren().add(letterText[i]);
            plugThings.add(plugs[i]);

            lastXthing = xThing + lastXthing;
        }
        lastXthing = 0;
        // Second Keyboard Row (a -> l)
        for (int i = 10; i < 19; i++) {
            xThing = plugPaddingThing + plugRadiusThing;
            plugs[i] = new Circle(xThing + lastXthing + 40, 450, plugRadiusThing);
            plugs[i].setFill(Color.LIGHTSLATEGRAY);
            plugs[i].setStroke(Color.BLACK);
            plugboardGroup.getChildren().add(plugs[i]);

            letterText[i] = new Text(plugs[i].getCenterX() - 5, plugs[i].getCenterY() + 5, String.valueOf(letters[i]));
            plugboardGroup.getChildren().add(letterText[i]);
            plugThings.add(plugs[i]);

            lastXthing = xThing + lastXthing;
        }
        lastXthing = 0;
        // Third Keyboard Row (z -> m)
        for (int i = 19; i < 26; i++) {
            xThing = plugPaddingThing + plugRadiusThing;
            plugs[i] = new Circle(xThing + lastXthing + 80, 550, plugRadiusThing);
            plugs[i].setFill(Color.LIGHTSLATEGRAY);
            plugs[i].setStroke(Color.BLACK);
            plugboardGroup.getChildren().add((plugs[i]));
            plugThings.add(plugs[i]);

            letterText[i] = new Text(plugs[i].getCenterX() - 5, plugs[i].getCenterY() + 5, String.valueOf(letters[i]));
            plugboardGroup.getChildren().add(letterText[i]);

            lastXthing = xThing + lastXthing;
        }

        plugboardGroup.getChildren().addAll(rect1, rect2, line);

        line.startXProperty().bind(rect1.xProperty().add(rect1.widthProperty().divide(2)));
        line.startYProperty().bind(rect1.yProperty().add(rect1.heightProperty().divide(2)));
        line.endXProperty().bind(rect2.xProperty().add(rect2.widthProperty().divide(2)));
        line.endYProperty().bind(rect2.yProperty().add(rect2.heightProperty().divide(2)));


        addDraggable(rect1, plugThings);
        addDraggable(rect2, plugThings);


        // Create the scene and set it on the primary stage
        stage.setScene(scene);
        stage.setTitle("Enigma Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // create a method to make a rectangle draggable and snap it to the closest circle
    private void addDraggable(Rectangle rect, List<Circle> circles) {
        rect.setOnMousePressed(event -> {
            rect.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
            unsnapFromCircles(rect, circles);
        });
        rect.setOnMouseDragged(event -> {
            double[] userData = (double[]) rect.getUserData();
            double deltaX = event.getSceneX() - userData[0];
            double deltaY = event.getSceneY() - userData[1];
            rect.setX(rect.getX() + deltaX);
            rect.setY(rect.getY() + deltaY);
            rect.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
            snapToCircles(rect, circles);
        });
    }

    private void snapToCircles(Rectangle rect, List<Circle> circles) {
        double rectCenterX = rect.getX() + rect.getWidth() / 2;
        double rectCenterY = rect.getY() + rect.getHeight() / 2;

        Circle closestCircle = null;
        double minDistance = Double.MAX_VALUE;

        for (Circle circle : circles) {
            double circleCenterX = circle.getCenterX();
            double circleCenterY = circle.getCenterY();
            double distance = Math.sqrt(Math.pow(rectCenterX - circleCenterX, 2) + Math.pow(rectCenterY - circleCenterY, 2));

            if (distance < circle.getRadius() && distance < minDistance) {
                closestCircle = circle;
                minDistance = distance;
            }
        }

        if (closestCircle != null) {
            rect.setX(closestCircle.getCenterX() - rect.getWidth() / 2);
            rect.setY(closestCircle.getCenterY() - rect.getHeight() / 2);
        }
    }

    private void unsnapFromCircles(Rectangle rect, List<Circle> circles) {
        for (Circle circle : circles) {
            double rectCenterX = rect.getX() + rect.getWidth() / 2;
            double rectCenterY = rect.getY() + rect.getHeight() / 2;
            double circleCenterX = circle.getCenterX();
            double circleCenterY = circle.getCenterY();
            double distance = Math.sqrt(Math.pow(rectCenterX - circleCenterX, 2) + Math.pow(rectCenterY - circleCenterY, 2));

            if (distance < circle.getRadius()) {
                rect.setUserData(new double[]{rect.getX(), rect.getY()});
                break;
            }
        }
    }

}