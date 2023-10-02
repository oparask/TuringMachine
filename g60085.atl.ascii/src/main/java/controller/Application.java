package controller;

import model.AsciiPaint;
import model.Shape;

import java.util.Scanner;

import static view.View.*;

public class Application {

    private AsciiPaint paint;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int height, width;

        while (true) {
            System.out.print("Enter the height of the drawing: ");
            height = scanner.nextInt();

            System.out.print("Enter the width of the drawing: ");
            width = scanner.nextInt();

            if (height > 10 && width > 10) {
                System.out.println("The drawing size is valid.");
                // Add your code here to create the drawing with the specified dimensions.
                paint = new AsciiPaint(width, height);
                break; // Exit the loop if dimensions are valid
            } else {
                System.out.println("Dimensions must be positive and greater than 10. Please try again.");
                // Continue the loop to ask for dimensions again
            }
        }
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        boolean finished = false;
        while (!finished) {
            System.out.println("Enter a command: ");

        }
    }

    private static boolean InputValidator() {
        Scanner keyboard = new Scanner(System.in);

        String validCommandsRegex = "(?i)(add|show|list|move|color|q)";
        String input;
        String[] detailInput;
        String commandType;

        do {
            if (yesOrNoRobustReading("Do you need help (y or n)? : ").equalsIgnoreCase("y")) {
                displayHelp();
            }
            input = keyboard.nextLine().trim();
            detailInput = input.split("\\s+");
            commandType = detailInput[0];
        } while (!commandType.matches(validCommandsRegex));

        if (input.equalsIgnoreCase("q")) {
            return false; // User wants to quit
        } else {
            validCommand(input);
            return true; // User wants to continue drawing
        }
    }

    /**
     * Handles the addition of tiles based on the input provided.
     *
     * @param input the input specifying the type of addition.
     */
    private void validCommand(String input) {
        String invalidInputMessage = "Invalid input! Try again!";
        String[] detailInput = input.split("\\s+");
        String commandType = detailInput[0].toLowerCase();

        switch (commandType) {
            case "add":
                if (!addShape(input)) {
                    displayInvalidInput(invalidInputMessage);
                    InputValidator();
                }
                break;
            case "show":
                displayDrawing(paint);
                break;
            case "list":
              for(Shape shape : paint.getDrawing().getShapes()){
                  System.out.print(shape + " ");
              }
                for(int i = 0; i< paint.getDrawing().getShapes().size(); i++){
                    System.out.print();
                }


                break;
            case "move":
                if (!playTileAtPosition(input, game)) {
                    displayInvalidInput(invalidInputMessage);
                    tryToPlay(replayMessage, game);
                }
                break;
            case "color":
                game.pass();
                break;
            default:
                displayInvalidInput(invalidInputMessage);
                tryToPlay(replayMessage, game);
                break;
        }
    }

    private boolean addShape(String input) {
        String circleRegex = "add\\s+circle\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String rectangleRegex = "add\\s+rectangle\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";
        String squareRegex = "add\\s+square\\s+\\d+\\s+\\d+\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]";

        String[] detailInput =  input.split("\\s+");
        String shape = detailInput[1].toLowerCase();

        switch (shape) {
            case "circle":
                if (input.matches(circleRegex)) {
                    int x = Integer.parseInt(detailInput[2]);
                    int y = Integer.parseInt(detailInput[3]);
                    double radius = Double.parseDouble(detailInput[4]);
                    char color = detailInput[5].charAt(0);
                    paint.newCircle(x, y, radius, color );
                } else {
                    return false;
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
                } else {
                    return false;
                }
                break;

            case "square":
                if (input.matches(squareRegex)) {
                    int x = Integer.parseInt(detailInput[2]);
                    int y = Integer.parseInt(detailInput[3]);
                    double side = Double.parseDouble(detailInput[4]);
                    char color = detailInput[5].charAt(0);
                    paint.newSquare(x, y, side, color);
                } else {
                    return false;
                }
                break;
        }
        return false;
    }

    public boolean show(String input){

    }



    public boolean list(String input){

    }

    public boolean move(String input){

    }

    public boolean color(String input){

    }




    private static String robustReadingString(String message) {
        Scanner keyboard = new Scanner(System.in);
        displayMessage(message);

        String input = keyboard.nextLine();
        while (input.trim().isEmpty()) { //Ensures that the user cannot enter only whitespace as a valid input.
            displayInvalidInput("Invalid input! Please try: ");
            input = keyboard.nextLine();
        }

        return input;
    }


    private static String yesOrNoRobustReading(String message) {
        String regex = "(?i)[yn]"; // Regular expression pattern for matching the characters 'y' or 'n' (case-insensitive)
        String yesOrNo = robustReadingString(message);

        while (!yesOrNo.matches(regex)) {
            yesOrNo = robustReadingString(message);
        }

        return yesOrNo;
    }


}
