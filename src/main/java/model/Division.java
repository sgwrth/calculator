package model;

import javax.swing.*;

public class Division implements IArithmeticStrategy {

    @Override
    public double calculate(double a, double b) {
        // todo: handle elsewhere
        if (b == 0) {
            JOptionPane.showMessageDialog(null, "NOT DEFINED!", "FEHLER",
                    JOptionPane.ERROR_MESSAGE);
        }
        return a / b;
    }
}
