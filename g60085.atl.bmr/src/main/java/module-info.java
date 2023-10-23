module com.example.g60085_atl_bmr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.g60085_atl_bmr to javafx.fxml;
    exports com.example.g60085_atl_bmr;
}