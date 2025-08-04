package state;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.apache.commons.compress.utils.ChecksumCalculatingInputStream;
import strategy.IArithmeticStrategy;

public class CalculatorState {
    public Text display;
    public Text minidisplay;
    public StringBuilder a;
    public StringBuilder b;
    public StringBuilder selectedString;
    public String tempString;
    public IArithmeticStrategy arithmeticStrategy;
    public boolean resetOnNextInput;

    public CalculatorState() {
        this.display = new Text();
        this.minidisplay = new Text();
        this.a = new StringBuilder();
        this.b = new StringBuilder();
        this.selectedString = new StringBuilder();
        this.tempString = "";
        this.arithmeticStrategy = null;
        this.resetOnNextInput = false;
    }

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

    public CalculatorState(
            Text display,
            Text minidisplay,
            StringBuilder a,
            StringBuilder b,
            StringBuilder selectedString,
            String tempString,
            IArithmeticStrategy arithmeticStrategy,
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

    public CalculatorState(CalculatorState state) {
        this.display = state.display;
        this.minidisplay = state.minidisplay;
        this.a = state.a;
        this.b = state.b;
        this.selectedString = state.selectedString;
        this.tempString = state.tempString;
        this.arithmeticStrategy = state.arithmeticStrategy;
        this.resetOnNextInput = state.resetOnNextInput;
    }

//    public CalculatorState(
//            CalculatorState state,
//            String string
//        ) {
//
//        /* Copied from state parameter: */
//        this.minidisplay = state.minidisplay;
//        this.b = state.b;
//        this.tempString = state.tempString;
//        this.arithmeticStrategy = state.arithmeticStrategy;
//
//        /* New state values: */
//        Text newDisplay = new Text();
//        newDisplay.setText(state.selectedString.toString());
//        this.display = newDisplay;
//        this.a = new StringBuilder().append(string);
//        this.selectedString = state.a;
//        this.resetOnNextInput = false;
//    }

}
