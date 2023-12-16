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

public class PopOut {

    private Stage stage;

    public PopOut(String title, String message, boolean isErrorMessage) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle(title);

        Text text = new Text(message);
        text.setFill(isErrorMessage ? Color.ORANGE : Color.GREEN);
        text.setFont(Font.font("Arial", 16));

        TextFlow textFlow = new TextFlow(text);
        textFlow.setPrefWidth(200); // Ajustez la largeur préférée en fonction de vos besoins

        VBox layout = new VBox(10);
        layout.getChildren().addAll(textFlow);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");

        Scene scene = new Scene(layout, 250, 100);
        stage.setScene(scene);
    }

    public void show() {
        stage.showAndWait();
    }
}
