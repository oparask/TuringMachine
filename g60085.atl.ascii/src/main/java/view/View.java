package view;

import model.Shape;

import java.util.ArrayList;
import java.util.List;

public class View {


    public static void displayTitle() {
        System.out.println("Welcome to ASCII PAINT");
        System.out.println();
    }

    public static void displayHelp() {
        System.out.println("ASCIIPaint commands:\n"
                + " - Add a new shape : add [<shape>] [<characteristics>] [<color>]\n"
                + " - Display the drawing : show \n"
                + " - Display the numbered list of present shapes : list\n"
                + " - Move a shape : move <i> <horizontally> <vertically>\n"
                + " - Change color : color <i> [<color>]\n"
                + " - quit : q\n"
                + " - shape : circle, rectangle, square\n"
                + " - characteristics: circle     --> center.x center.y radius\n"
                + "                    rectangle  --> upperLeftPoint.x upperLeftPoint.y width height\n"
                + "                    square     --> upperLeftPoint.x upperLeftPoint.y side\n"
                + " - i : index in list of shapes\n"
                + " - color :  a letter\n");
        System.out.println();
    }

    public static void displayDrawing(String colorString, int height, int width) {
        int indexString = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (colorString.charAt(indexString) == ' ') {
                    System.out.print(' ');
                } else {
                    System.out.print(colorString.charAt(indexString));
                }
                indexString++;
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayShapeList(List<Shape> shapeList) {
        for (int i = 0; i < shapeList.size(); i++) {
            System.out.print(shapeList.get(i) + "-" + i + "  ");
        }
        System.out.println();

    }


    /**
     * Displays a message to inform that the input is invalid.
     *
     * @param message The message to display.
     */
    public static void displayInvalidInput(String message) {
        System.out.println(message);
        System.out.println();
    }

    /**
     * Displays a message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    /**
     * Prints a prompt symbol (> ) and expects something after.
     */
    public static void displayEntrancePrompt() {
        System.out.print("Enter a command: \n " +
                "> ");
    }

    public static void displayEnd() {
        System.out.print("Bye, see you next time for an another drawing :) ");
    }


}
