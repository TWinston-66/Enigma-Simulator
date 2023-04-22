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
import javafx.scene.text.Font;
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


    Plug[] plugSnaps = new Plug[10];
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
                rotor2.setValue("I");
            }
        });
        rotor3.setOnAction(event -> {
            enigma.setRotors(new String[] { rotor1.getValue(),  rotor2.getValue(),  rotor3.getValue()});
            if (Objects.equals(rotor1.getValue(), rotor2.getValue()) || Objects.equals(rotor2.getValue(), rotor3.getValue()) || Objects.equals(rotor1.getValue(), rotor3.getValue())) {
                a.show();
                rotor3.setValue("I");
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
        button.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        button.setOnAction(event -> {
            stage.setScene(plugboard);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });

        Button back = new Button("Back");
        back.setLayoutX(700);
        back.setLayoutY(25);
        plugboardGroup.getChildren().add(back);
        back.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        back.setOnAction(event -> {
            stage.setScene(scene);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });

        Alert notEnoughPlugsUsed = new Alert(Alert.AlertType.ERROR);
        notEnoughPlugsUsed.setContentText("Please Use All 5 Plugs!");



        Label connectedLettersLabel1 = new Label("AH");
        Label connectedLettersLabel2 = new Label("TB");
        Label connectedLettersLabel3 = new Label("KJ");
        Label connectedLettersLabel4 = new Label("PI");
        Label connectedLettersLabel5 = new Label("QE");
        Label connectedLettersLabel6 = new Label("DV");
        Label connectedLettersLabel7 = new Label("YO");
        Label connectedLettersLabel8 = new Label("NF");
        Label connectedLettersLabel9 = new Label("ML");
        Label connectedLettersLabel10 = new Label("XZ");

        connectedLettersLabel1.setLayoutX(400);
        connectedLettersLabel1.setLayoutY(55);
        connectedLettersLabel1.setFont(new Font(20));

        connectedLettersLabel2.setLayoutX(400);
        connectedLettersLabel2.setLayoutY(80);
        connectedLettersLabel2.setFont(new Font(20));

        connectedLettersLabel3.setLayoutX(400);
        connectedLettersLabel3.setLayoutY(105);
        connectedLettersLabel3.setFont(new Font(20));

        connectedLettersLabel4.setLayoutX(400);
        connectedLettersLabel4.setLayoutY(130);
        connectedLettersLabel4.setFont(new Font(20));

        connectedLettersLabel5.setLayoutX(400);
        connectedLettersLabel5.setLayoutY(155);
        connectedLettersLabel5.setFont(new Font(20));

        connectedLettersLabel6.setLayoutX(400);
        connectedLettersLabel6.setLayoutY(170);
        connectedLettersLabel6.setFont(new Font(20));

        connectedLettersLabel7.setLayoutX(400);
        connectedLettersLabel7.setLayoutY(205);
        connectedLettersLabel7.setFont(new Font(20));

        connectedLettersLabel8.setLayoutX(400);
        connectedLettersLabel8.setLayoutY(240);
        connectedLettersLabel8.setFont(new Font(20));

        connectedLettersLabel9.setLayoutX(400);
        connectedLettersLabel9.setLayoutY(275);
        connectedLettersLabel9.setFont(new Font(20));

        connectedLettersLabel10.setLayoutX(400);
        connectedLettersLabel10.setLayoutY(310);
        connectedLettersLabel10.setFont(new Font(20));

        /*
        drawArrowLine(450, 70, 580, 70, plugboardGroup);
        drawArrowLine(450, 120, 580, 120, plugboardGroup);
        drawArrowLine(450, 170, 580, 170, plugboardGroup);
        drawArrowLine(450, 220, 580, 220, plugboardGroup);
        drawArrowLine(450, 270, 580, 270, plugboardGroup);
         */

        plugboardGroup.getChildren().add(connectedLettersLabel1);
        plugboardGroup.getChildren().add(connectedLettersLabel2);
        plugboardGroup.getChildren().add(connectedLettersLabel3);
        plugboardGroup.getChildren().add(connectedLettersLabel4);
        plugboardGroup.getChildren().add(connectedLettersLabel5);
        plugboardGroup.getChildren().add(connectedLettersLabel6);
        plugboardGroup.getChildren().add(connectedLettersLabel7);
        plugboardGroup.getChildren().add(connectedLettersLabel8);
        plugboardGroup.getChildren().add(connectedLettersLabel9);
        plugboardGroup.getChildren().add(connectedLettersLabel10);



        Button save = new Button("Save");
        save.setLayoutX(700);
        save.setLayoutY(200);
        plugboardGroup.getChildren().add(save);
        save.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");

        save.setOnMousePressed(event -> {
            save.setStyle("-fx-background-color: yellow; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                    "-fx-border-color: black; -fx-border-width: 2;");

            // Figure out + save connected letters
            StringBuilder connections = new StringBuilder();

            for (int i = 0; i < 5; i++) {
                connections.append(plugSnaps[i].getDockedLetter().toUpperCase()).append(plugSnaps[i + 5].getDockedLetter().toUpperCase()).append(" ");
            }

            if (connections.length() < 13) {
                notEnoughPlugsUsed.show();
            }

            System.out.println("Connected Letters: " + enigma.getPlugboardConnections());

            // Display Connected Letters

            // Write Letters to Enigma Machine
            enigma.setPlugboardConnections(connections.toString());

        });
        save.setOnMouseReleased(event -> {
            save.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                    "-fx-border-color: black; -fx-border-width: 2;");
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
        //Plug[] plugSnaps = new Plug[10];
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
            addDraggable(plugEnds[i], plugThings, i);
            plugSnaps[i] = new Plug(false);
        }

        oldY = 0;
        for (int i = 5; i < plugEnds.length; i++) {
            y = plugWirePadding + oldY;
            plugEnds[i] = new Rectangle(300, y, rectangleSize, rectangleSize);
            plugEnds[i].setFill(Color.LIGHTSLATEGRAY);
            oldY = y;
            plugboardGroup.getChildren().add(plugEnds[i]);
            addDraggable(plugEnds[i], plugThings, i);
            plugSnaps[i] = new Plug(false);
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

        drawArrowLine(450, 70, 580, 70, plugboardGroup);
        drawArrowLine(450, 120, 580, 120, plugboardGroup);
        drawArrowLine(450, 170, 580, 170, plugboardGroup);
        drawArrowLine(450, 220, 580, 220, plugboardGroup);
        drawArrowLine(450, 270, 580, 270, plugboardGroup);


        // Create the scene and set it on the primary stage
        stage.setScene(scene);
        stage.setTitle("Enigma Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // create a method to make a rectangle draggable and snap it to the closest circle
    private void addDraggable(Rectangle rect, List<Circle> circles, int rectIndex) {
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

            // Letter Snapped Index + Save Docked Letter to Plug
            int circleSnapped = snapToCircles(rect, circles);
            char letter = letters[circleSnapped];
            plugSnaps[rectIndex].setDockedLetter(String.valueOf(letter));

        });
    }

    private int snapToCircles(Rectangle rect, List<Circle> circles) {
        double rectCenterX = rect.getX() + rect.getWidth() / 2;
        double rectCenterY = rect.getY() + rect.getHeight() / 2;

        Circle closestCircle = null;
        double minDistance = Double.MAX_VALUE;
        int closestCircleIndex = -1;

        for (int i = 0; i < circles.size(); i++) {
            Circle circle = circles.get(i);
            double circleCenterX = circle.getCenterX();
            double circleCenterY = circle.getCenterY();
            double distance = Math.sqrt(Math.pow(rectCenterX - circleCenterX, 2) + Math.pow(rectCenterY - circleCenterY, 2));

            if (distance < circle.getRadius() && distance < minDistance) {
                closestCircle = circle;
                minDistance = distance;
                closestCircleIndex = i;
            }
        }

        if (closestCircle != null) {
            rect.setX(closestCircle.getCenterX() - rect.getWidth() / 2);
            rect.setY(closestCircle.getCenterY() - rect.getHeight() / 2);
        }

        return closestCircleIndex;
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

    public static void drawArrowLine(double startX, double startY, double endX, double endY, Group group) {
        // get the slope of the line and find its angle
        double slope = (startY - endY) / (startX - endX);
        double lineAngle = Math.atan(slope);

        double arrowAngle = startX > endX ? Math.toRadians(45) : -Math.toRadians(225);

        Line line = new Line(startX, startY, endX, endY);

        double lineLength = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        double arrowLength = lineLength / 10;

        // create the arrow legs
        Line arrow1 = new Line();
        arrow1.setStartX(line.getEndX());
        arrow1.setStartY(line.getEndY());
        arrow1.setEndX(line.getEndX() + arrowLength * Math.cos(lineAngle - arrowAngle));
        arrow1.setEndY(line.getEndY() + arrowLength * Math.sin(lineAngle - arrowAngle));

        Line arrow2 = new Line();
        arrow2.setStartX(line.getEndX());
        arrow2.setStartY(line.getEndY());
        arrow2.setEndX(line.getEndX() + arrowLength * Math.cos(lineAngle + arrowAngle));
        arrow2.setEndY(line.getEndY() + arrowLength * Math.sin(lineAngle + arrowAngle));

        group.getChildren().addAll(line, arrow1, arrow2);
    }

}