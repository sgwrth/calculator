package model;

public class Multiplication implements IArithmeticStrategy {

    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}
