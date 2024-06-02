package strategy;

public class Subtraction implements IArithmeticStrategy {

    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
