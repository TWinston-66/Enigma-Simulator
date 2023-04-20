package com.enigma.enigma.Simulator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        // create a popup
        Popup popup = new Popup();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText("Cannot Use Two Of the Same Rotors!");

        // create a label
        Label label = new Label("Cannot Use Two Of the Same Rotors!");

        // set background
        label.setStyle("-fx-background-color: white;");

        // add the label
        popup.getContent().add(label);

        // set size of label
        label.setMinWidth(80);
        label.setMinHeight(50);


        rotor1.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
            if (Objects.equals(rotor1.getValue(), rotor2.getValue()) || Objects.equals(rotor2.getValue(), rotor3.getValue()) || Objects.equals(rotor1.getValue(), rotor3.getValue())) {
                a.show();
                rotor1.setValue("I");
            }
        });
        rotor2.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
            if (Objects.equals(rotor1.getValue(), rotor2.getValue()) || Objects.equals(rotor2.getValue(), rotor3.getValue()) || Objects.equals(rotor1.getValue(), rotor3.getValue())) {
                a.show();
                rotor1.setValue("I");
            }
        });
        rotor3.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
            if (Objects.equals(rotor1.getValue(), rotor2.getValue()) || Objects.equals(rotor2.getValue(), rotor3.getValue()) || Objects.equals(rotor1.getValue(), rotor3.getValue())) {
                a.show();
                rotor1.setValue("I");
            }
        });


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


        // Plugs
        Rectangle[] plugEnds = new Rectangle[10];
        double rectangleSize = 40;
        double plugWirePadding = 50;
        double y;
        double oldY = 0;

        for (int i = 0; i < 5; i++) {
            y = plugWirePadding + oldY;
            plugEnds[i] = new Rectangle(30, y, rectangleSize, rectangleSize);
            plugEnds[i].setFill(Color.LIGHTSLATEGRAY);
            oldY = y;
            plugboardGroup.getChildren().add(plugEnds[i]);
            addDraggable(plugEnds[i], plugThings);
        }

        oldY = 0;
        for (int i = 5; i < plugEnds.length; i++) {
            y = plugWirePadding + oldY;
            plugEnds[i] = new Rectangle(300, y, rectangleSize, rectangleSize);
            plugEnds[i].setFill(Color.LIGHTSLATEGRAY);
            oldY = y;
            plugboardGroup.getChildren().add(plugEnds[i]);
            addDraggable(plugEnds[i], plugThings);
        }

        Line wire1 = new Line();
        Line wire2 = new Line();
        Line wire3 = new Line();
        Line wire4 = new Line();
        Line wire5 = new Line();

        wire1.startXProperty().bind(plugEnds[0].xProperty().add(plugEnds[0].widthProperty().divide(2)));
        wire1.startYProperty().bind(plugEnds[0].yProperty().add(plugEnds[0].heightProperty().divide(2)));
        wire1.endXProperty().bind(plugEnds[5].xProperty().add(plugEnds[5].widthProperty().divide(2)));
        wire1.endYProperty().bind(plugEnds[5].yProperty().add(plugEnds[5].heightProperty().divide(2)));

        wire2.startXProperty().bind(plugEnds[1].xProperty().add(plugEnds[1].widthProperty().divide(2)));
        wire2.startYProperty().bind(plugEnds[1].yProperty().add(plugEnds[1].heightProperty().divide(2)));
        wire2.endXProperty().bind(plugEnds[6].xProperty().add(plugEnds[6].widthProperty().divide(2)));
        wire2.endYProperty().bind(plugEnds[6].yProperty().add(plugEnds[6].heightProperty().divide(2)));

        wire3.startXProperty().bind(plugEnds[2].xProperty().add(plugEnds[2].widthProperty().divide(2)));
        wire3.startYProperty().bind(plugEnds[2].yProperty().add(plugEnds[2].heightProperty().divide(2)));
        wire3.endXProperty().bind(plugEnds[7].xProperty().add(plugEnds[7].widthProperty().divide(2)));
        wire3.endYProperty().bind(plugEnds[7].yProperty().add(plugEnds[7].heightProperty().divide(2)));

        wire4.startXProperty().bind(plugEnds[3].xProperty().add(plugEnds[3].widthProperty().divide(2)));
        wire4.startYProperty().bind(plugEnds[3].yProperty().add(plugEnds[3].heightProperty().divide(2)));
        wire4.endXProperty().bind(plugEnds[8].xProperty().add(plugEnds[8].widthProperty().divide(2)));
        wire4.endYProperty().bind(plugEnds[8].yProperty().add(plugEnds[8].heightProperty().divide(2)));

        wire5.startXProperty().bind(plugEnds[4].xProperty().add(plugEnds[4].widthProperty().divide(2)));
        wire5.startYProperty().bind(plugEnds[4].yProperty().add(plugEnds[4].heightProperty().divide(2)));
        wire5.endXProperty().bind(plugEnds[9].xProperty().add(plugEnds[9].widthProperty().divide(2)));
        wire5.endYProperty().bind(plugEnds[9].yProperty().add(plugEnds[9].heightProperty().divide(2)));

        plugboardGroup.getChildren().add(wire1);
        plugboardGroup.getChildren().add(wire2);
        plugboardGroup.getChildren().add(wire3);
        plugboardGroup.getChildren().add(wire4);
        plugboardGroup.getChildren().add(wire5);

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