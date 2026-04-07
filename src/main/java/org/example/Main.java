package org.example;

import java.math.BigInteger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    public static void main(String[] args){
        new Main();

    }
    public Main() {
        //window
        JFrame frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //display
        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.LEFT);
        display.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel mainPanel = new JPanel();

        //numbers
        JPanel numbersPanel = new JPanel(new GridLayout(4,4,10,10));
        frame.add(numbersPanel,BorderLayout.WEST);
        JButton[] numbersButtons = new JButton[10];
        for(int i = 0; i < numbersButtons.length;i++) {
            String number = String.valueOf(i);
            numbersButtons[i] = new JButton(number);
            numbersPanel.add(numbersButtons[i]);
            numbersButtons[i].addActionListener(e -> {
                if(display.getText().charAt(0) == '0' && !isContainingOperation(display.getText())) {
                    display.setText(number);
                }else {
                    display.setText(display.getText() + number);
                }
            });
        }
        numbersPanel.setPreferredSize(new Dimension(200,200));

        //operations
        JPanel operationsPanel = new JPanel(new GridLayout(4, 1, 10,10));
        frame.add(operationsPanel, BorderLayout.EAST);

        JButton addition = new JButton("+");
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isContainingOperation(display.getText())) display.setText(display.getText() + addition.getText());
            }
        });
        JButton subtraction = new JButton("-");
        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isContainingOperation(display.getText())) display.setText(display.getText() + subtraction.getText());
            }
        });

        JButton multiplication = new JButton("*");
        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isContainingOperation(display.getText())) display.setText(display.getText() + multiplication.getText());
            }
        });
        JButton division = new JButton("/");
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isContainingOperation(display.getText())) display.setText(display.getText() + division.getText());
            }
        });

        JButton count = new JButton("=");
        count.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText(countExpression(display.getText()));
            }
        });

        JButton delete = new JButton("C");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
            }
        });

        operationsPanel.add(addition); operationsPanel.add(subtraction); operationsPanel.add(multiplication); operationsPanel.add(division); operationsPanel.add(count); operationsPanel.add(delete);
        operationsPanel.setPreferredSize(new Dimension(200,200));
        mainPanel.add(numbersPanel, BorderLayout.EAST);
        mainPanel.add(operationsPanel, BorderLayout.WEST);

        frame.add(display, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static boolean isContainingOperation(String s) {
        if(s.contains("+") || s.contains("-") || s.contains("*") || s.contains("/")) return true;
        return false;

    }

    private static String countExpression(String s) {
        int operationIndex = 0;
        char operation = '+';
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '/' || s.charAt(i) == '*') {
                operationIndex = i;
                operation = s.charAt(i);
            }
        }
        BigInteger value1 = new BigInteger(s.substring(0, operationIndex));
        BigInteger value2 =new BigInteger(s.substring(operationIndex + 1));

        switch (operation) {
            case '+':
                value1 = value1.add(value2);
                break;
            case '-':
                value1 = value1.subtract(value2);
                break;
            case '*':
                value1 = value1.multiply(value2);
                break;
            case '/':
                value1 = value1.divide(value2);
                break;
        }
        return value1.toString();
    }


}