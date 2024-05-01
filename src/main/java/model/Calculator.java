package model;

public class Calculator {

    private IArithmeticStrategy arithmeticStrategy;

    public void setStrategy(IArithmeticStrategy arithmeticStrategy) {
        this.arithmeticStrategy = arithmeticStrategy;
    }

    public void calculate(double a, double b) {
        arithmeticStrategy.calculate(a, b);
    }
}
