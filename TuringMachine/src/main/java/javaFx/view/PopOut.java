package javaFx.view;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Simple pop-up window for displaying messages.
 * The pop-up can be used to show informational or error messages to the user.
 */
public class PopOut {

    private Stage stage;

    /**
     * Constructs a new PopOut instance with the specified title, message, and message type.
     *
     * @param title          The title of the pop-up window.
     * @param message        The message to be displayed in the pop-up.
     * @param isErrorMessage A flag indicating whether the message is an error message.
     */
    public PopOut(String title, String message, boolean isErrorMessage) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);

        Text text = new Text(message);
        text.setFill(isErrorMessage ? Color.ORANGE : Color.GREEN);
        text.setFont(Font.font("Arial", 16));

        TextFlow textFlow = new TextFlow(text);
        textFlow.setPrefWidth(200); // Adjust the preferred width based on your needs

        VBox layout = new VBox(10);
        layout.getChildren().addAll(textFlow);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");

        Scene scene = new Scene(layout, 250, 100);
        stage.setScene(scene);
    }

    /**
     * Displays the pop-up window and waits for the user to close it.
     */
    public void show() {
        stage.showAndWait();
    }
}
