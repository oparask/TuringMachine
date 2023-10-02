package view;

import model.AsciiPaint;

public class View {


    public static void displayTitle() {
        System.out.println("Welcome to ASCII PAINT");
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
    }

    public static void displayDrawing(AsciiPaint paint) {
        String colorString = paint.asASCII();
        int i = 0;
        for (int height = 0; height < paint.getDrawing().getHeight(); height++) {
            for (int width = 0; width < paint.getDrawing().getWidth(); width++) {
                if (colorString.charAt(i) == ' ') {
                    System.out.print(' ');
                } else {
                    System.out.print(colorString.charAt(i));
                }
                i++;
            }
        }
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
        System.out.print("> ");
    }


}
