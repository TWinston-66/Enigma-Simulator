package com.enigma.enigma.Simulator;

import com.enigma.enigma.Simulator.Enigma.Enigma;
import com.enigma.enigma.Simulator.Enigma.Plug;
import com.enigma.enigma.Simulator.UI.HelpScreen;
import com.enigma.enigma.Simulator.UI.Keyboard;
import com.enigma.enigma.Simulator.UI.PlugboardScreen;
import com.enigma.enigma.Simulator.UI.Rotors;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    // Window Parameters
    double width = 825;
    double height = 600;

    Plug[] plugSnaps = new Plug[10];
    Enigma enigma = new Enigma(new String[] {"VII", "V", "IV"}, "B", new int[] {10,5,12}, new int[] {1,2,3}, "AD FT WH JO PN");

    Keyboard keyboard;
    Keyboard plugboardKeyboard;
    Rotors rotors;

    HelpScreen helpScreen;

    PlugboardScreen plugboardScreen;

    @Override
    public void start(Stage stage) {

        // Group and Scene Objects
        Group group = new Group();

        Scene scene = new Scene(group, width, height, Color.DARKSLATEGRAY);
        scene.setFill(Color.GRAY);

        // Main Keyboard
        keyboard = new Keyboard(50, 25, group);
        keyboard.drawKeyboard(); // Draw Keyboard
        keyboard.initKeyboard(enigma, keyboard, scene); // Backend key-mapping and color "animation"

        // Rotors + Rotor Selector + Rotor Position UI
        rotors = new Rotors(group, enigma);
        rotors.drawRotors();

        // Help Screen
        Group helpGroup = new Group();
        Scene helpScene = new Scene(helpGroup, width, height, Color.DARKSLATEGRAY);
        helpScene.setFill(Color.GRAY);

        helpScreen = new HelpScreen();
        helpScreen.drawHelpScreen(helpGroup, stage, helpScene);

        // Plug Board Screen
        Group plugboardGroup = new Group();
        Scene plugboard = new Scene(plugboardGroup, width, height, Color.DARKSLATEGRAY);
        plugboard.setFill(Color.GRAY);

        plugboardScreen = new PlugboardScreen(enigma, plugSnaps);
        plugboardScreen.drawPlugboardScreen(group, stage, plugboard, scene, plugboardGroup, keyboard.getPlugThings(), plugSnaps);

        // Plugboard plugs
        plugboardKeyboard = new Keyboard(60, 15, plugboardGroup);
        plugboardKeyboard.drawKeyboard();


        // Create the scene and set it on the primary stage
        stage.setScene(scene);
        stage.setTitle("Enigma Simulator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}