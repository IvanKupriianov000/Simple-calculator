module org.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires exp4j;


    opens org.example.calculator to javafx.fxml;
    exports org.example.calculator;
}