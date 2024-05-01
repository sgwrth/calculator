package main;

import model.Calculator;
import view.CalculatorView;
import controller.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new CalculatorController(
                        new CalculatorView(),
                        new Calculator()
                )
        );
    }

}
