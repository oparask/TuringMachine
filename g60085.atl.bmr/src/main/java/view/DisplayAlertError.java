package view;

import javafx.scene.control.Alert;

/**
 * This class provides a utility method for displaying an error dialog box with custom title, header, and content.
 * It also modifies the style of result fields in the user interface to indicate an error.
 */
public class DisplayAlertError {
    /**
     * Displays an error dialog box with custom title, header, and content,
     * while also modifying the style of result fields in the user interface.
     *
     * @param headerText  The header of the error dialog box.
     * @param contentText The content of the error dialog box.
     */
    public static void showError(GridPane2 gridPane2, String headerText, String contentText) {
        gridPane2.getResultBMRField().setStyle("-fx-text-fill: red;");
        gridPane2.setResultBMRField("Failed!");
        gridPane2.getCaloriesField().setStyle("-fx-text-fill: red;");
        gridPane2.setCaloriesField("Failed!");

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
