package state;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.apache.commons.compress.utils.ChecksumCalculatingInputStream;
import strategy.*;

public class CalculatorState {
    public Text display;
    public Text minidisplay;
    public StringBuilder a;
    public StringBuilder b;
    public StringBuilder selectedString;
    public String tempString;
    public IArithmeticStrategy arithmeticStrategy;
    public boolean resetOnNextInput;

    public CalculatorState(
            Text display,
            Text minidisplay,
            StringBuilder a,
            StringBuilder b,
            String tempString,
            boolean resetOnNextInput
    ) {
        this.display = display;
        this.minidisplay = minidisplay;
        this.a = a;
        this.b = b;
        this.selectedString = this.a;
        this.tempString = tempString;
        this.arithmeticStrategy = null;
        this.resetOnNextInput = resetOnNextInput;
    }

    /* Copy constructor. */
    public CalculatorState(CalculatorState state) {
        this.display = new Text();
        this.display.setText(state.display.getText());
        this.minidisplay = new Text();
        this.minidisplay.setText(state.minidisplay.getText());
        this.a = new StringBuilder();
        this.a.append(state.a.toString());
        this.b = new StringBuilder();
        this.b.append(state.b.toString());
        this.selectedString = (state.selectedString == state.a) ? this.a : this.b;
        this.tempString = state.tempString;
        if (state.arithmeticStrategy == null) {
            this.arithmeticStrategy = null;
        } else {
            final var clazz = state.arithmeticStrategy.getClass();
            switch (clazz.getSimpleName()) {
                case "Addition" -> this.arithmeticStrategy = new Addition();
                case "Subtraction" -> this.arithmeticStrategy = new Subtraction();
                case "Multiplication" -> this.arithmeticStrategy = new Multiplication();
                case "Division" -> this.arithmeticStrategy = new Division();
                default -> throw new IllegalArgumentException("Unknown strat" + clazz.getName());
            }
        }
        this.resetOnNextInput = state.resetOnNextInput;
    }

}
