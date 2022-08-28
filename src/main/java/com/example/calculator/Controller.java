package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    double memory = 0;
    String operator = "";
    @FXML
    TextField textField;
    boolean isFloat = false;

    public void action_btn_numbers(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        addNumber(button.getText());
    }

    public void addNumber(String num) {
        String s = textField.getText();
        if (s.equals("0")) {
            textField.setText(num);
            return;
        }
        if (isFloat) {
            if (s.charAt(s.length() - 1) == '0' && s.charAt(s.length() - 2) == '.')
                s = s.substring(0, s.length() - 1);
        }
        textField.setText(s + num);
    }

    public void point() {
        if (!isFloat) {
            isFloat = true;
            String s = textField.getText();
            textField.setText(s + ".0");
        }
    }

    public void clear() {
        textField.setText("0");
        isFloat = false;
    }
    public void clearAll() {
        textField.setText("0");
        memory=0;
        operator="";
        isFloat = false;
    }
    public void operator(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        operator = button.getText();
        memory = Double.parseDouble(textField.getText());
        textField.setText("0");
    }

    public void equal() {
        if (operator.equals(""))
            return;
        double number2 = Double.parseDouble(textField.getText());
        double result = 0;
        switch (operator) {
            case "+" -> result = memory + number2;
            case "-" -> result = memory - number2;
            case "/" -> {
                if (number2 == 0)
                    result = 0;
                else
                    result = memory / number2;
            }
            case "*" -> result = memory * number2;
        }
        if (result % 1 == 0)
            textField.setText((int) result + "");
        else
            textField.setText(result + "");
    }
    public void percentage(){
        String number = textField.getText();
        if (number.equals("0") || number.equals("0.0"))
            return;
        double num = Double.parseDouble(number)/100;
        textField.setText(num+"");
    }
    public void sign(){
        String number = textField.getText();
        if (number.equals("0") || number.equals("0.0"))
            return;
        if (number.charAt(0)=='-')
            textField.setText(number.substring(1));
        else
            textField.setText("-"+number);
    }
}
