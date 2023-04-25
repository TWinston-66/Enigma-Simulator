package com.enigma.enigma.Simulator.UI.Controllers;

import com.enigma.enigma.Simulator.Enigma.Enigma;
import com.enigma.enigma.Simulator.UI.Keyboard;
import com.enigma.enigma.Simulator.Util.Letters;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyboardController {

    public KeyboardController() {
    }

    public void initKeyboard(Enigma enigma, Keyboard keyboard, Scene scene) {
        final boolean[] isExecuted = {false};

        AtomicInteger pressedIndex = new AtomicInteger();

        scene.setOnKeyPressed(event -> {
            // check if the flag is false
            if (!isExecuted[0]) {
                // set the flag to true
                isExecuted[0] = true;

                String key = event.getText();
                int letterIndex = Letters.letterOrder.indexOf(key.toUpperCase());
                if (letterIndex != -1) {
                    char newLetter = enigma.encrypt(Letters.letters[letterIndex]);
                    letterIndex = Letters.letterOrder.indexOf(String.valueOf(newLetter).toUpperCase());
                    pressedIndex.set(letterIndex);
                    keyboard.getCircles()[letterIndex].setFill(Color.YELLOW);
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            if (pressedIndex.get() != -1) {
                keyboard.getCircles()[pressedIndex.get()].setFill(Color.LIGHTSLATEGRAY);
                isExecuted[0] = false;
            }
        });
    }
}
