package controller;

import model.AsciiPaint;

import java.util.ArrayList;
import java.util.List;
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


    /**
     * Initializes the drawing by prompting the user to specify the width and height of the drawing area
     * and creating an AsciiPaint object based on the user's input. If the user chooses not to specify the size,
     * a default AsciiPaint object is created.
     * <p>
     * The method repeatedly prompts the user until valid input is provided for the drawing size or until
     * the user chooses not to specify the size.
     */
    private void drawingInitialization() {
        Scanner keyboard = new Scanner(System.in);

        int width, height;

        while (true) {
            if (choseSize()) {
                try {
                    displayMessage("Enter the width and the height of the drawing: ");
                    String input = keyboard.nextLine().trim();
                    String[] detailInput = input.split("\\s+");
                    width = Integer.parseInt(detailInput[0]);
                    height = Integer.parseInt(detailInput[1]);

                    validDrawing();
                    paint = new AsciiPaint(width, height);
                    break;

                } catch (Exception e) {
                    displayInvalidInput(e.getMessage());
                }
            } else {
                paint = new AsciiPaint();
                break;
            }
        }
    }

    /**
     * Processes user commands for interacting with the drawing application. The user is prompted to enter commands
     * for adding shapes, displaying the drawing, listing shapes, moving shapes, changing colors, and quitting the application.
     * The method continuously prompts the user for input and processes the commands until the user chooses to quit (by entering "q").
     *
     * @return true if the user wants to continue interacting with the application, false if the user chooses to quit.
     */
    private boolean processCommand() {
        Scanner keyboard = new Scanner(System.in);
        String invalidInputMessage = "Invalid input! Try again!";

        while (true) {
            //try {
                displayEntrancePrompt();

                String input = keyboard.nextLine().trim();
                String[] detailInput = input.split("\\s+");
                String commandType = detailInput[0];

                switch (commandType.toLowerCase()) {
                    case "add" -> addShape(input);
                    case "show" ->
                            displayDrawing(paint.asASCII(), paint.getDrawing().getHeight(), paint.getDrawing().getWidth());
                    case "list" -> displayShapeList(paint.getDrawing().getShapes());
                    case "move" -> moveShape(input);
                    case "color" -> changeColorShape(input);
                    case "delete" -> deleteShape(input);
                    case "group" -> groupShapes(input);
                    case "ungroup" -> ungroupShapes(input);
                    case "undo" -> paint.undo();
                    case "redo" -> paint.redo();
                    case "exit" -> {
                        displayEnd();
                        return false;
                    }
                    default -> {
                        displayInvalidInput(invalidInputMessage);
                        checkForHelp();
                    }
                }

           /* } catch (Exception e) {
                displayInvalidInput(e.getMessage());
                checkForHelp();
            }*/
        }
    }


    /**
     * Parses and adds a shape to the drawing based on the provided input.
     *
     * @param input The user input representing the shape to be added.
     */
    private void addShape(String input) throws IllegalArgumentException {
        String circleRegex = "add\\s+circle\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+[a-zA-Z]";
        String rectangleRegex = "add\\s+rectangle\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String squareRegex = "add\\s+square\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String lineRegex = "add\\s+line\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+(\\d+\\.?\\d*)\\s+[a-zA-Z]";

        String[] detailInput = input.split("\\s+");

        if (detailInput.length == 1) {
            throw new IllegalArgumentException("You must enter the shape you want to add and its characteristics.");
        }
        String shape = detailInput[1];

        switch (shape.toLowerCase()) {
            case "circle":
                if (!input.toLowerCase().matches(circleRegex)) {
                    throw new IllegalArgumentException("Use the correct command to add a circle.");
                } else {
                    double x = Double.parseDouble(detailInput[2]);
                    double y = Double.parseDouble(detailInput[3]);
                    double radius = Double.parseDouble(detailInput[4]);
                    char color = detailInput[5].charAt(0);
                    paint.newCircle(x, y, radius, color);
                }
                break;
            case "rectangle":
                if (!input.toLowerCase().matches(rectangleRegex)) {
                    throw new IllegalArgumentException("Use the correct command to add a rectangle.");
                } else {
                    double x = Double.parseDouble(detailInput[2]);
                    double y = Double.parseDouble(detailInput[3]);
                    double width = Double.parseDouble(detailInput[4]);
                    double height = Double.parseDouble(detailInput[5]);
                    char color = detailInput[6].charAt(0);
                    paint.newRectangle(x, y, width, height, color);
                }
                break;
            case "square":
                if (!input.toLowerCase().matches(squareRegex)) {
                    throw new IllegalArgumentException("Use the correct command to add a square.");
                } else {
                    int x = Integer.parseInt(detailInput[2]);
                    int y = Integer.parseInt(detailInput[3]);
                    double side = Double.parseDouble(detailInput[4]);
                    char color = detailInput[5].charAt(0);
                    paint.newSquare(x, y, side, color);
                }
                break;
            case "line":
                if (!input.toLowerCase().matches(lineRegex)) {
                    throw new IllegalArgumentException("Use the correct command to add a line.");
                } else {
                    double aX = Double.parseDouble(detailInput[2]);
                    double aY = Double.parseDouble(detailInput[3]);
                    double bX = Double.parseDouble(detailInput[4]);
                    double bY = Double.parseDouble(detailInput[5]);
                    char color = detailInput[6].charAt(0);
                    paint.newLine(aX, aY, bX, bY, color);
                }
                break;
            default:
                displayInvalidInput("Invalid shape type. " +
                        "Please use 'circle', 'rectangle', 'square' or 'line' in your add command.");
                break;
        }
        validCommandAdd();
    }


    public void deleteShape(String input) throws IllegalArgumentException {
        String regex = "delete\\s+\\d+";

        if (!input.toLowerCase().matches(regex)) {
            throw new IllegalArgumentException("You must specify an index from the shape list.");
        }

        String[] detailInput = input.split("\\s+");
        paint.deleteShape(Integer.parseInt(detailInput[1]));
        validCommandDelete();
    }


    /**
     * Moves a shape in the drawing based on the provided input.
     *
     * @param input The user input representing the shape index and the amount to move horizontally and vertically.
     */
    private void moveShape(String input) throws IllegalArgumentException {
        String regex = "move\\s+(\\d+)\\s+(-?\\d+\\.?\\d*)\\s+(-?\\d+\\.?\\d*)";

        if (!input.toLowerCase().matches(regex)) {
            throw new IllegalArgumentException("You must specify an index from the shape list, delta x and delta y.");
        }

        String[] detailInput = input.split("\\s+");

        int shapeIndex = Integer.parseInt(detailInput[1]);
        double dx = Double.parseDouble(detailInput[2]);
        double dy = Double.parseDouble(detailInput[3]);

        paint.moveShape(shapeIndex, dx, dy);
        validCommandMove();
    }

    /**
     * Changes the color of a shape in the drawing based on the provided input.
     *
     * @param input The user input representing the shape index and the new color character.
     */
    private void changeColorShape(String input) throws IllegalArgumentException {
        String regex = "color\\s+\\d+\\s+[a-zA-Z]";

        if (!input.toLowerCase().matches(regex)) {
            throw new IllegalArgumentException("You must specify an index from the shape list and the color.");
        }

        String[] detailInput = input.split("\\s+");

        int shapeIndex = Integer.parseInt(detailInput[1]);
        char color = detailInput[2].charAt(0);

        this.paint.changeShapeColor(shapeIndex, color);
        validCommandColor();
    }

    public void groupShapes(String input) {
        String regex = "group\\s+\\d+(\\s+\\d+)*";

        if (!input.toLowerCase().matches(regex)) {
            throw new IllegalArgumentException("You must specify indexes from the shape list.");
        }

        String[] detailInput = input.split("\\s+");
        List<Integer> shapeIndexes = new ArrayList<>();

        for(int i = 1; i<detailInput.length; i++){
            shapeIndexes.add(Integer.parseInt(detailInput[i]));
        }

        paint.groupShapes(shapeIndexes);

        validCommandGroup();
    }
    public void ungroupShapes(String input){
        String regex = "ungroup\\s+\\d+";

        if (!input.toLowerCase().matches(regex)) {
            throw new IllegalArgumentException("You must specify an index of a group from the shape list.");
        }

        String[] detailInput = input.split("\\s+");

        paint.ungroupShapes(Integer.parseInt(detailInput[1]));
        validCommandGroup();
    }

    /**
     * Reads and validates a string input from the user based on a regular expression pattern.
     *
     * @param message The message to display to the user as a prompt.
     * @param regex   The regular expression pattern for validating the input.
     * @return The validated user input as a string.
     */
    private static String stringRobustReading(String message, String regex) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);

        String input = keyboard.nextLine();
        while (input.trim().isEmpty() || !input.trim().matches(regex)) { //Ensures that the user cannot enter only whitespace as a valid input.
            displayInvalidInput("Invalid input. " + message);
            input = keyboard.nextLine();
        }

        return input;
    }

    /**
     * Asks the user if they want to choose the size of the drawing and validates their response.
     *
     * @return true if the user wants to choose the size, false otherwise.
     */
    private boolean choseSize() {
        return stringRobustReading("Do you want to chose the size of the drawing?" +
                " (y or n)", "(?i)[yn]").equalsIgnoreCase("y");
    }

    /**
     * Asks the user if they need help and displays help information if the user requests it.
     */
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
