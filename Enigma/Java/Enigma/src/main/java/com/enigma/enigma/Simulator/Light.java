package com.enigma.enigma.Simulator;

import javafx.scene.shape.Circle;

public class Light {

    private double x, y, radius;
    private char letter;

    private Circle circle;

    public Light(double radius, char letter, int number, double width, double height) {

        int level;
        int rowPos;
        double x;
        double y;

        if (number < 10) {
            level = 1;
            rowPos = number;
            x = (rowPos+1.0)*width/11;
        } else if (number < 19) {
            level = 2;
            rowPos = number - 10;
            x = (rowPos+1.5)*width/11;
        } else {

            level = 3;
            rowPos = number - 19;
            x = (rowPos+2.0)*width/11;
        }
        y  = height/3 + level*(height*2/3)/4;

        this.x = x;
        this.y = y;
        this.radius = radius;
        this.letter = letter;
    }

    public double[] getPosition() {
        return new double[]{x, y};
    }
}
