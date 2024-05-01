package model;

public class Addition implements IArithmeticStrategy {

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
