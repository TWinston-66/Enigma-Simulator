package com.enigma.enigma.Simulator.UI;

import com.enigma.enigma.Simulator.Enigma.Enigma;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Objects;

public class Rotors {

    private ComboBox<String> rotor1, rotor2, rotor3;
    private Label rotor1Heading, rotor2Heading, rotor3Heading;
    private Group group;

    private Enigma enigma;

    // Parameters
    double rotorMenuPadding = 300;
    double rotorMenuY = 150;
    double rotorMenuOffset = 75;
    double rotorMenuFontSize = 17;

    // Alerts
    private final Alert a = new Alert(Alert.AlertType.ERROR);

    public Rotors(Group group, Enigma enigma) {
        this.enigma = enigma;
        this.group = group;

        a.setContentText("Cannot Use Two Of the Same Rotors!");
    }

    public void drawRotors() {
        createLabels();
        createRotors();
    }

    private void createRotors() {
        rotor1 = new ComboBox<>(FXCollections.observableArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        ));

        rotor2 = new ComboBox<>(FXCollections.observableArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        ));

        rotor3 = new ComboBox<>(FXCollections.observableArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        ));

        rotor1.setValue("I");
        rotor2.setValue("II");
        rotor3.setValue("III");

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

        group.getChildren().add(rotor1);
        group.getChildren().add(rotor2);
        group.getChildren().add(rotor3);
    }

    private void createLabels() {
        rotor1Heading = new Label("Rotor 1");
        rotor1Heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        rotor1Heading.setLayoutX(rotorMenuOffset);
        rotor1Heading.setLayoutY(rotorMenuY - 40);
        group.getChildren().add(rotor1Heading);

        rotor2Heading = new Label("Rotor 2");
        rotor2Heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        rotor2Heading.setLayoutX(rotorMenuOffset + rotorMenuPadding);
        rotor2Heading.setLayoutY(rotorMenuY - 40);
        group.getChildren().add(rotor2Heading);

        rotor3Heading = new Label("Rotor 3");
        rotor3Heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        rotor3Heading.setLayoutX(rotorMenuOffset + (2 * rotorMenuPadding));
        rotor3Heading.setLayoutY(rotorMenuY - 40);
        group.getChildren().add(rotor3Heading);
    }
}
