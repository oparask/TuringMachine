package view;

import model.Shape;

import java.util.List;

/**
 * The View class provides methods for displaying messages, drawing, and shapes in the ASCII Paint application.
 */
public class View {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    /**
     * Displays the welcome message.
     */
    public static void displayTitle() {
        System.out.println(ANSI_PURPLE + "( A )( S )( C )( I )( I )( _ )( P )( A )( I )( N )( T )" + ANSI_RESET);
        System.out.println();

    }

    /**
     * Displays the help message with available commands and usage.
     */
    public static void displayHelp() {
        System.out.println(ANSI_PURPLE + """
                ASCIIPaint commands:
                                                                              
                 \u001B[35m- Add a new shape:\u001B[0m add\u001B[0m \u001B[34m<shape>\u001B[0m \u001B[36m<characteristics>\u001B[0m \u001B[32m<color>\u001B[0m
                 \u001B[35m- Display the drawing:\u001B[0m show
                 \u001B[35m- Display the existing shapes:\u001B[0m list
                 \u001B[35m- Move a shape:\u001B[0m move \u001B[93m<i>\u001B[0m <horizontally> <vertically>
                 \u001B[35m- Change color:\u001B[0m color \u001B[93m<i>\u001B[0m \u001B[32m<color>\u001B[0m
                 \u001B[35m- Quit:\u001B[0m q
                 
                        \u001B[34m shape:\u001B[0m circle, rectangle or square
                        \u001B[36m characteristics:\u001B[0m circle     --> center.x center.y radius
                                          rectangle  --> upperLeftPoint.x upperLeftPoint.y width height
                                          square     --> upperLeftPoint.x upperLeftPoint.y side
                        \u001B[93m i:\u001B[0m index in the list of shapes
                        \u001B[32m color:\u001B[0m a letter
                """ + ANSI_RESET);
        System.out.println();
    }

    /**
     * Displays a message to inform that the drawing size is valid.
     */
    public static void validDrawing() {
        System.out.println(ANSI_GREEN + "The drawing size is valid." + ANSI_RESET);
        System.out.println();
    }

    /**
     * Prints a prompt symbol (">") and expects user input.
     */
    public static void displayEntrancePrompt() {
        System.out.print(ANSI_PURPLE + "Enter a command:\n> " + ANSI_RESET);
    }

    /**
     * Displays a message to inform that the shape has been added successfully.
     */
    public static void validCommandAdd() {
        System.out.println(ANSI_GREEN + "The shape has been added successfully!" + ANSI_RESET);
        System.out.println();
    }

    /**
     * Displays a message to inform that the shape has been moved successfully.
     */
    public static void validCommandMove() {
        System.out.println(ANSI_GREEN + "The shape has been moved successfully!" + ANSI_RESET);
        System.out.println();
    }

    /**
     * Displays a message to inform that the color of the shape has been changed successfully.
     */
    public static void validCommandColor() {
        System.out.println(ANSI_GREEN + "The color of the shape has been changed successfully!" + ANSI_RESET);
        System.out.println();
    }


    /**
     * Displays the drawing based on color information, height, and width.
     *
     * @param colorString The string containing color information.
     * @param height      The height of the drawing area.
     * @param width       The width of the drawing area.
     */
    public static void displayDrawing(String colorString, int height, int width) {


        int indexString = 0;
        for (int y = 0; y < height; y++) {
            String tmp = "" + (height - y);
            if (tmp.length() == 1) {
                System.out.print(ANSI_BLUE + (height - y) + "  |  " + ANSI_RESET);
            } else {
                System.out.print(ANSI_BLUE + (height - y) + " |  " + ANSI_RESET);
            }

            for (int x = 0; x < width; x++) {
                if (colorString.charAt(indexString) == ' ') {
                    System.out.print(".  ");
                } else {
                    System.out.print(colorString.charAt(indexString) + "  ");
                }
                indexString++;
            }
            System.out.println();
        }

        System.out.print("      ");
        for (int j = 0; j <= width; j++) {
            String tmp = "" + j;
            if (tmp.length() == 1) {
                System.out.print(ANSI_BLUE + j + "  ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println(ANSI_RESET);
        System.out.println();
    }

    /**
     * Displays the list of shapes with their indices.
     *
     * @param shapeList The list of Shape objects to be displayed.
     */
    public static void displayShapeList(List<Shape> shapeList) {
        for (int i = 0; i < shapeList.size(); i++) {
            System.out.println(shapeList.get(i) + " - " + i + "  ");
        }
        System.out.println();
    }

    /**
     * Displays a message to inform that the input is invalid.
     *
     * @param message The message to display.
     */
    public static void displayInvalidInput(String message) {
        System.out.println(ANSI_ORANGE + message + ANSI_RESET);
        System.out.println();
    }

    /**
     * Displays a general message.
     *
     * @param message The message to display.
     */
    public static void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    /**
     * Displays the end message when exiting the application.
     */
    public static void displayEnd() {
        System.out.print(ANSI_PURPLE + "Bye, see you next time for another drawing :)");
    }
}

