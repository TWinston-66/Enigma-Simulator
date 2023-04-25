package com.enigma.enigma.Simulator.UI;

import com.enigma.enigma.Simulator.Enigma.Enigma;
import com.enigma.enigma.Simulator.Enigma.Plug;
import com.enigma.enigma.Simulator.UI.Controllers.PlugboardController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class PlugboardScreen {

    private Button button;
    private Button back;
    private Button save;
    private Button reset;

    private Alert notEnoughPlugsUsed;
    private double plugButtonFontSize = 17;
    private Label connectedLettersLabel1 = new Label("A");
    private Label connectedLettersLabel2 = new Label("D");
    private Label connectedLettersLabel3 = new Label("F");
    private Label connectedLettersLabel4 = new Label("T");
    private Label connectedLettersLabel5 = new Label("W");
    private Label connectedLettersLabel6 = new Label("H");
    private Label connectedLettersLabel7 = new Label("J");
    private Label connectedLettersLabel8 = new Label("O");
    private Label connectedLettersLabel9 = new Label("P");
    private Label connectedLettersLabel10 = new Label("N");

    private Enigma enigma;

    private Plug[] plugSnaps;

    public PlugboardScreen(Enigma enigma, Plug[] plugSnaps, Group group, Stage stage, Scene plugboardScene, Scene scene, Group plugboardGroup, List<Circle> circle) {
        button = new Button("Plugboard");
        back = new Button("Back");
        save = new Button("Save");
        reset = new Button("Reset");

        notEnoughPlugsUsed = new Alert(Alert.AlertType.ERROR);
        notEnoughPlugsUsed.setContentText("Please Use All 5 Plugs!");

        this.enigma = enigma;
        this.plugSnaps = plugSnaps;

        drawPlugboardScreen(group, stage, plugboardScene, scene, plugboardGroup, circle, plugSnaps);
    }

    private void drawPlugboardScreen(Group group, Stage stage, Scene plugboardScene, Scene scene, Group plugboardGroup, List<Circle> circles, Plug[] plugSnaps) {
        drawButtons(group, stage, plugboardScene, scene, plugboardGroup);
        drawLabels(plugboardGroup);
        drawPlugs(plugboardGroup, circles, plugSnaps);
    }

    private void drawButtons(Group group, Stage stage, Scene plugboardScene, Scene scene, Group plugboardGroup) {

        // Main Screen to Plug board Button
        button.setLayoutX(650);
        button.setLayoutY(25);
        group.getChildren().add(button);
        button.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
        button.setOnAction(event -> {
            stage.setScene(plugboardScene);
            stage.setTitle("Enigma Simulator");
            stage.show();
        });

        // Plug board screen to main screen button
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

        // Save plugboard layout button
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
                save.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                        "-fx-border-color: black; -fx-border-width: 2;");
            }

            // Display Connected Letters
            connectedLettersLabel1.setText(plugSnaps[0].getDockedLetter());
            connectedLettersLabel2.setText(plugSnaps[5].getDockedLetter());
            connectedLettersLabel3.setText(plugSnaps[1].getDockedLetter());
            connectedLettersLabel4.setText(plugSnaps[6].getDockedLetter());
            connectedLettersLabel5.setText(plugSnaps[2].getDockedLetter());
            connectedLettersLabel6.setText(plugSnaps[7].getDockedLetter());
            connectedLettersLabel7.setText(plugSnaps[3].getDockedLetter());
            connectedLettersLabel8.setText(plugSnaps[8].getDockedLetter());
            connectedLettersLabel9.setText(plugSnaps[4].getDockedLetter());
            connectedLettersLabel10.setText(plugSnaps[9].getDockedLetter());

            // Write Letters to Enigma Machine
            enigma.setPlugboardConnections(connections.toString());

        });
        save.setOnMouseReleased(event -> {
            save.setStyle("-fx-background-color: gray; -fx-font-size: " + plugButtonFontSize + "px; -fx-text-fill: black; " +
                    "-fx-border-color: black; -fx-border-width: 2;");
        });


        // Plug board reset button (NOT ACTIVATED RN)
        reset.setLayoutX(700);
        reset.setLayoutY(250);
        //plugboardGroup.getChildren().add(reset);
        reset.setStyle("-fx-background-color: gray; -fx-font-size: " + 17 + "px; -fx-text-fill: black; " +
                "-fx-border-color: black; -fx-border-width: 2;");
    }

    private void drawLabels(Group plugboardGroup) {

        connectedLettersLabel1.setLayoutX(400);
        connectedLettersLabel1.setLayoutY(55);
        connectedLettersLabel1.setFont(new Font(20));

        connectedLettersLabel2.setLayoutX(600);
        connectedLettersLabel2.setLayoutY(55);
        connectedLettersLabel2.setFont(new Font(20));

        connectedLettersLabel3.setLayoutX(400);
        connectedLettersLabel3.setLayoutY(105);
        connectedLettersLabel3.setFont(new Font(20));

        connectedLettersLabel4.setLayoutX(600);
        connectedLettersLabel4.setLayoutY(105);
        connectedLettersLabel4.setFont(new Font(20));

        connectedLettersLabel5.setLayoutX(400);
        connectedLettersLabel5.setLayoutY(155);
        connectedLettersLabel5.setFont(new Font(20));

        connectedLettersLabel6.setLayoutX(600);
        connectedLettersLabel6.setLayoutY(155);
        connectedLettersLabel6.setFont(new Font(20));

        connectedLettersLabel7.setLayoutX(400);
        connectedLettersLabel7.setLayoutY(205);
        connectedLettersLabel7.setFont(new Font(20));

        connectedLettersLabel8.setLayoutX(600);
        connectedLettersLabel8.setLayoutY(205);
        connectedLettersLabel8.setFont(new Font(20));

        connectedLettersLabel9.setLayoutX(400);
        connectedLettersLabel9.setLayoutY(255);
        connectedLettersLabel9.setFont(new Font(20));

        connectedLettersLabel10.setLayoutX(600);
        connectedLettersLabel10.setLayoutY(255);
        connectedLettersLabel10.setFont(new Font(20));


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
    }

    private void drawPlugs(Group plugboardGroup, List<Circle> circles, Plug[] plugSnaps) {


        PlugboardController plugboardController = new PlugboardController();

        // Plugs
        Rectangle[] plugEnds = new Rectangle[10];
        double rectangleSize = 45;
        double plugWirePadding = 50;
        double y;
        double oldY = 0;

        for (int i = 0; i < 5; i++) {
            y = plugWirePadding + oldY;
            plugEnds[i] = new Rectangle(30, y, rectangleSize, rectangleSize);
            plugEnds[i].setFill(Color.LIGHTSLATEGRAY);
            oldY = y;
            plugboardGroup.getChildren().add(plugEnds[i]);
            plugboardController.addDraggable(plugEnds[i], circles, i, plugboardGroup, plugSnaps);
            plugSnaps[i] = new Plug(false);
        }

        oldY = 0;
        for (int i = 5; i < plugEnds.length; i++) {
            y = plugWirePadding + oldY;
            plugEnds[i] = new Rectangle(300, y, rectangleSize, rectangleSize);
            plugEnds[i].setFill(Color.LIGHTSLATEGRAY);
            oldY = y;
            plugboardGroup.getChildren().add(plugEnds[i]);
            plugboardController.addDraggable(plugEnds[i], circles, i, plugboardGroup, plugSnaps);
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

        plugboardController.drawArrowLine(450, 70, 580, 70, plugboardGroup);
        plugboardController.drawArrowLine(450, 120, 580, 120, plugboardGroup);
        plugboardController.drawArrowLine(450, 170, 580, 170, plugboardGroup);
        plugboardController.drawArrowLine(450, 220, 580, 220, plugboardGroup);
        plugboardController.drawArrowLine(450, 270, 580, 270, plugboardGroup);
    }
}
