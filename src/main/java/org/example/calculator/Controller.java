package org.example.calculator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField display = new TextField("0");

    public void buttonEnterClick(ActionEvent actionEvent) {
        try {
            Expression e = new ExpressionBuilder(display.getText()).build();
            display.setText(String.valueOf(e.evaluate()));
        } catch (Exception ex) {
            display.setText("Invalid expression");
        }
    }


}
