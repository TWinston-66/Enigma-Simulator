package com.enigma.enigma.Simulator.UI.Controllers;

import com.enigma.enigma.Simulator.Enigma.Plug;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlugboardController {

    private String letterOrder = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private char[] letters = letterOrder.toCharArray();

    Alert moreThanOneSnappedPlug;

    public PlugboardController() {
        moreThanOneSnappedPlug = new Alert(Alert.AlertType.ERROR);
        moreThanOneSnappedPlug.setContentText("Only One Connection Per Plug!");
    }

    public void addDraggable(Rectangle rect, List<Circle> circles, int rectIndex, Group root, Plug[] plugSnaps) {

        // Set Rectangle to Docked Letter
        Text text = new Text();
        text.setFont(Font.font("Arial", 16)); // Set font size and type
        text.setFill(Color.BLACK); // Set font color
        text.setX(((rect.getX() + rect.getWidth() / 2) + 5 ) - ((text.getLayoutBounds().getWidth() / 2)) + 5);
        text.setY(((rect.getY() + rect.getHeight() / 2) + 5 ) - ((text.getLayoutBounds().getHeight() / 2)) + 5);
        root.getChildren().add(text); // Add text to the same group as the rectangle

        rect.setOnMousePressed(event -> {
            rect.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
            unsnapFromCircles(rect, circles);
            text.setX(((rect.getX() + rect.getWidth() / 2) + 5 ) - ((text.getLayoutBounds().getWidth() / 2)) + 5);
            text.setY(((rect.getY() + rect.getHeight() / 2) + 5 ) - ((text.getLayoutBounds().getHeight() / 2)) + 5);
            text.setText("");
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
            StringBuilder connectedLetters = new StringBuilder();

            for (Plug plugSnap : plugSnaps) {
                connectedLetters.append(plugSnap.getDockedLetter());
            }

            if (hasRepeatedChars(connectedLetters.toString())) {
                moreThanOneSnappedPlug.show();
            }

            text.setX(((rect.getX() + rect.getWidth() / 2) + 5 ) - ((text.getLayoutBounds().getWidth() / 2)) + 5);
            text.setY(((rect.getY() + rect.getHeight() / 2) + 5 ) - ((text.getLayoutBounds().getHeight() / 2)) + 5);
            text.setText(String.valueOf(letter));

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

    public void drawArrowLine(double startX, double startY, double endX, double endY, Group group) {
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

    public boolean hasRepeatedChars(String s) {
        // Create a HashSet to store the characters in the string
        Set<Character> set = new HashSet<>();

        // Iterate over the characters in the string
        for (char c : s.toCharArray()) {
            // If the character is already in the HashSet, return true
            if (set.contains(c)) {
                return true;
            }
            // Otherwise, add the character to the HashSet
            else {
                set.add(c);
            }
        }

        // If we reach this point, there are no repeated characters in the string
        return false;
    }
}
