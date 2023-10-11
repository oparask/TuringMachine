package controller;

import model.AsciiPaint;
import model.Shape;

import java.util.Scanner;

import static view.View.*;

/**
 * The Application class represents the main controller for the ASCII Paint application.
 * It handles user input, manages the drawing, and controls the interaction with the user.
 */
public class Application {
    private AsciiPaint paint;

    /**
     * Start the ASCII Paint application.
     */
    public void start() {
        displayTitle();
        displayHelp();
        drawingInitialization();

        boolean continueTheDrawing = true;

        while (continueTheDrawing) {
            continueTheDrawing = processCommand();
        }
    }

    private void drawingInitialization() {
        Scanner keyboard = new Scanner(System.in);
        String errorMessage = "Dimensions must be positive, greater than 10, and lower than 100. Try again: ";
        String regex = "\\d+\\s+\\d+"; // Use double backslashes to escape the '\'

        int width, height;

        if(choseSize()){
            while (true) {
                System.out.print("Enter the width and the height of the drawing: ");
                String input = keyboard.nextLine().trim();

                if (input.matches(regex)) {
                    String[] detailInput = input.split("\\s+");
                    width = Integer.parseInt(detailInput[0]);
                    height = Integer.parseInt(detailInput[1]);

                    if (width > 10 && height > 10 && width <= 100 && height <= 100) {
                        validDrawing();
                        paint = new AsciiPaint(width, height);
                        break;
                    } else {
                        System.out.println(errorMessage);
                    }
                } else {
                    System.out.println(errorMessage);
                }
            }
        } else {
            paint = new AsciiPaint();
        }

    }

    private boolean processCommand() {
        Scanner keyboard = new Scanner(System.in);

        String validCommandsRegex = "(?i)(add|show|list|move|color|q)";

        while (true) {
            displayEntrancePrompt();

            String input = keyboard.nextLine().trim();
            String[] detailInput = input.split("\\s+");
            String commandType = detailInput[0];

            if (!commandType.matches(validCommandsRegex)) {
                displayInvalidInput("Invalid command! Try again.");
                checkForHelp();
                continue; // Go back to the beginning of the loop for invalid commands
            }

            if (input.equalsIgnoreCase("q")) {
                displayEnd();
                return false; // User wants to quit
            }

            String invalidInputMessage = "Invalid input! Try again!";

            switch (commandType.toLowerCase()) {
                case "add":
                    if (!addShape(input)) {
                        displayInvalidInput(invalidInputMessage);
                        checkForHelp();
                    } else {
                        validCommandAdd();
                    }
                    break;
                case "show":
                    displayDrawing(paint.asASCII(), paint.getDrawing().getHeight(), paint.getDrawing().getWidth());
                    break;
                case "list":
                    displayShapeList(paint.getDrawing().getShapes());
                    break;
                case "move":
                    if (!moveShape(input)) {
                        displayInvalidInput(invalidInputMessage);
                        checkForHelp();
                    } else {
                        validCommandMove();
                    }
                    break;
                case "color":
                    if (!changeColor(input)) {
                        displayInvalidInput(invalidInputMessage);
                        checkForHelp();
                    } else {
                        validCommandColor();
                    }
                    break;
                default:
                    displayInvalidInput(invalidInputMessage);
                    checkForHelp();
                    break;
            }

            return true;
        }
    }

    private boolean addShape(String input) {
        String circleRegex = "add\\s+circle\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String rectangleRegex = "add\\s+rectangle\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String squareRegex = "add\\s+square\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";

        String[] detailInput = input.split("\\s+");
        if (detailInput.length < 2) {
            return false;
        }

        String shape = detailInput[1].toLowerCase();

        switch (shape) {
            case "circle":
                if (input.matches(circleRegex)) {
                    int x = Integer.parseInt(detailInput[2]);
                    int y = Integer.parseInt(detailInput[3]);
                    double radius = Double.parseDouble(detailInput[4]);
                    char color = detailInput[5].charAt(0);
                    paint.newCircle(x, y, radius, color);
                    return true;
                }
                break;
            case "rectangle":
                if (input.matches(rectangleRegex)) {
                    int x = Integer.parseInt(detailInput[2]);
                    int y = Integer.parseInt(detailInput[3]);
                    double width = Double.parseDouble(detailInput[4]);
                    double height = Double.parseDouble(detailInput[5]);
                    char color = detailInput[6].charAt(0);
                    paint.newRectangle(x, y, width, height, color);
                    return true;
                }
                break;
            case "square":
                if (input.matches(squareRegex)) {
                    int x = Integer.parseInt(detailInput[2]);
                    int y = Integer.parseInt(detailInput[3]);
                    double side = Double.parseDouble(detailInput[4]);
                    char color = detailInput[5].charAt(0);
                    paint.newSquare(x, y, side, color);
                    return true;
                }
                break;
            default:
                System.out.println("Invalid shape type. Please use 'circle', 'rectangle', or 'square'.");
                break;
        }
        return false;
    }


    private boolean moveShape(String input) {
        // move <i> <horizontally> <vertically>
        String regex = "move\\s+(\\d+)\\s+(-?\\d+\\.?\\d*)\\s+(-?\\d+\\.?\\d*)";


        String[] detailInput = input.split("\\s+");

        var list = paint.getDrawing().getShapes();

        if (input.matches(regex)) {
            int index = Integer.parseInt(detailInput[1]);

            if (index >= 0 && index < list.size()) { // Vérifiez si l'index est valide
                Shape shape = list.get(index);
                shape.move(Double.parseDouble(detailInput[2]), Double.parseDouble(detailInput[3]));
                return true;
            }
        }
        return false;
    }

    private boolean changeColor(String input) {
        String regex = "color\\s+\\d+\\s+[a-zA-Z]";
        String[] detailInput = input.split("\\s+");

        var list = paint.getDrawing().getShapes();

        if (input.matches(regex)) {
            Shape shape = list.get(Integer.parseInt(detailInput[1]));
            shape.setColor(detailInput[2].charAt(0));
            return true;
        } else {
            return false;
        }
    }

    private static String stringRobustReading(String message, String regex) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);

        String input = keyboard.nextLine();
        while (input.trim().isEmpty() || !input.trim().matches(regex)) { //Ensures that the user cannot enter only whitespace as a valid input.
            displayInvalidInput("Invalid input! Try again. " + message);
            input = keyboard.nextLine();
        }

        return input;

    }

    private boolean choseSize(){
        return stringRobustReading("Do you want to chose the size of the drawing? (y or n)", "(?i)[yn]").equalsIgnoreCase("y");
    }

    private void checkForHelp() {
        if (stringRobustReading("Do you need help (y or n)? : ", "(?i)[yn]").equalsIgnoreCase("y")) {
            displayHelp();
        }
    }

    /**
     * The main entry point for the ASCII Paint application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Application applicationASCIIPaint = new Application();
        applicationASCIIPaint.start();
    }
}
