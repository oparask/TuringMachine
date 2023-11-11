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

        String validCommandsRegex = "(?i)(add|show|list|move|color|delete|q)";

        while (true) {
            displayEntrancePrompt();

            String input = keyboard.nextLine().trim();
            String[] detailInput = input.split("\\s+");
            String commandType = detailInput[0];

            //try {
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
                    case "delete":
                        deleteShape(input);

                    default:
                        displayInvalidInput(invalidInputMessage);
                        checkForHelp();
                        break;
                }
            /*} catch (Exception e) {
                displayInvalidInput(e.getMessage());
            }*/
        }

    }


    /**
     * Parses and adds a shape to the drawing based on the provided input.
     *
     * @param input The user input representing the shape to be added.
     * @return true if the shape is successfully added to the drawing, false otherwise.
     */
    private boolean addShape(String input) {
        String circleRegex = "add\\s+circle\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String rectangleRegex = "add\\s+rectangle\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String squareRegex = "add\\s+square\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String lineRegex = "add\\s+line\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+[a-zA-Z]";

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
            case "line":
                if (input.matches(lineRegex)) {
                    double aX = Double.parseDouble(detailInput[2]);
                    double aY= Double.parseDouble(detailInput[3]);
                    double bX = Double.parseDouble(detailInput[4]);
                    double bY= Double.parseDouble(detailInput[5]);
                    char color = detailInput[6].charAt(0);
                    paint.newLine(aX, aY, bX, bY, color);
                    return true;
                }
                break;

            default:
                System.out.println("Invalid shape type. Please use 'circle', 'rectangle', 'square' or 'line'.");
                break;
        }
        return false;
    }

    public boolean deleteShape(String input){
        String[] detailInput = input.split("\\s+");
        if (detailInput.length < 2) {
            return false;
        }
        paint.deleteShape(Integer.parseInt(detailInput[1]));
        return true;
    }


    /**
     * Moves a shape in the drawing based on the provided input.
     *
     * @param input The user input representing the shape index and the amount to move horizontally and vertically.
     * @return true if the shape is successfully moved, false otherwise.
     */
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

    /**
     * Changes the color of a shape in the drawing based on the provided input.
     *
     * @param input The user input representing the shape index and the new color character.
     * @return true if the shape's color is successfully changed, false otherwise.
     */
    private boolean changeColor(String input) {
        String regex = "color\\s+\\d+\\s+[a-zA-Z]";
        String[] detailInput = input.split("\\s+");

        if (input.matches(regex)) {
            int indexShape = Integer.parseInt(detailInput[1]);
            char color = detailInput[2].charAt(0);
            this.paint.setColor(indexShape, color);
            return true;
        } else {
            return false;
        }
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
            displayInvalidInput("Invalid input! Try again. " + message);
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
