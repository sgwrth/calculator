package controller;

import javafx.scene.text.Text;
import state.CalculatorState;
import strategy.IArithmeticStrategy;

public class CalculatorController {

    private final int MAX_STRING_LENGTH = 14;

    public CalculatorState btnClickOneToNine(CalculatorState state, String number) {
        CalculatorState newState = new CalculatorState(state);
        if (newState.resetOnNextInput) {
            newState = resetStringWithInput(newState, number);
        } else {
            if (!newState.selectedString.toString().equals("0")) {
                newState.selectedString.append(number);
            } else {
                newState.selectedString.replace(0, 1, number);
            }
        }
        newState.display.setText(newState.selectedString.toString());
        return newState;
    }

    public CalculatorState resetStringWithInput(CalculatorState state, String string) {
        Text newDisplay = new Text();
        newDisplay.setText(state.selectedString.toString());
        return new CalculatorState(
            newDisplay,
            state.minidisplay,
            new StringBuilder().append(string),
            state.b,
            state.a,
            state.tempString,
            state.arithmeticStrategy,
            false
        );
    }

    public void btnClickZero(CalculatorState state, String zero) {
        if (state.resetOnNextInput) {
            resetStringWithInput(state, zero);
        } else {
            if (!state.selectedString.toString().equals("0")) {
                state.selectedString.append("0");
            }
            state.display.setText(state.tempString + state.selectedString);
        }
    }

    public void btnClickStrategy(
            CalculatorState state,
            IArithmeticStrategy strat,
            String operator
    ) {
        if (state.selectedString == state.a) {
            state.arithmeticStrategy = strat;
            state.tempString = operator;
            state.display.setText(state.tempString);
            state.minidisplay.setText(removePeriod(state.a.toString()));
            state.selectedString = state.b;
            state.resetOnNextInput = false;
        }
    }

    public void btnClickEquals(CalculatorState state) {
        if (state.selectedString == state.b) {
            String resultString = String.valueOf(doCalculation(state));
            if (endsWithPeriodZero(resultString)) {
                cropPeriodZero(state, resultString);
            } else {
                if (isOverlong(resultString)) {
                    state.display.setText(cropOverlong(resultString));
                } else {
                    state.display.setText(resultString);
                }
                state.a = new StringBuilder().append(resultString);
            }
            state.selectedString = state.a;
            state.b = new StringBuilder();
            state.tempString = "";
            state.minidisplay.setText("");
            state.resetOnNextInput = true;
        }
    }

    public double doCalculation(CalculatorState state) {
        return state.arithmeticStrategy.calculate(
                Double.parseDouble(state.a.toString()),
                Double.parseDouble(state.b.toString()));
    }

    public void cropPeriodZero(CalculatorState state, String string) {
        StringBuilder sb = popChars(new StringBuilder().append(string), 2);
        state.display.setText(sb.toString());
        state.a = new StringBuilder().append(sb);
    }

    public boolean endsWithPeriodZero(String string) {
        return (string.charAt(string.length() - 1) == '0'
                && string.charAt(string.length() - 2) == '.');
    }

    public boolean isOverlong(String string) {
        return string.length() >= MAX_STRING_LENGTH;
    }

    public String cropOverlong(String string) {
        return string.substring(0, MAX_STRING_LENGTH - 3) + "...";
    }

    public void btnClickDelete(CalculatorState state) {
        if (!state.resetOnNextInput) {
            if (isSingleDigitNegative(state.selectedString.toString())) {
                state.selectedString.replace(0, 1, "0")
                        .delete(1, state.selectedString.length());
            }
            if (isSingleDigitPositive(state.selectedString.toString())) {
                state.selectedString.replace(0, 1, "0");
            }
            if (state.selectedString.length() > 1) {
                state.selectedString.deleteCharAt(state.selectedString.length() - 1);
            }
            state.display.setText(state.tempString + state.selectedString);
        }
    }

    public boolean isSingleDigitNegative(String string) {
        return string.length() == 2 && string.charAt(0) == '-';
    }

    public boolean isSingleDigitPositive(String string) {
        return string.length() == 1 && !string.equals("0");
    }

    public void btnClickClear(CalculatorState state) {
        state.tempString = "";
        state.a = new StringBuilder();
        state.b = new StringBuilder();
        state.selectedString = state.a.append("0");
        state.display.setText(state.selectedString.toString());
        state.minidisplay.setText("");
    }

    public String removePeriod(String string) {
        try {
            StringBuilder sb = new StringBuilder().append(string);
            if (endsWithPeriod(string)) {
                sb = popChars(sb, 1);
            }
            return sb.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public StringBuilder popChars(StringBuilder sb, int number) {
        return sb.delete(sb.length() - number, sb.length());
    }

    public boolean endsWithPeriod(String string) {
        return string.charAt(string.length() - 1) == '.';
    }

    public void btnClickPeriod(CalculatorState state, String period) {
        if (state.resetOnNextInput) {
            resetStringWithInput(state, "0" + period);
        } else {
            if (state.selectedString.isEmpty()) {
                state.selectedString.append("0.");
            }
            if (!hasPeriod(state.selectedString.toString())) {
                state.selectedString.append(".");
            }
            state.display.setText(state.tempString + state.selectedString);
        }
    }

    public boolean hasPeriod(String string) {
        return string.contains(".");
    }

    public void updateState(CalculatorState state, CalculatorState newState) {
        state.display.setText(newState.display.getText());
        state.minidisplay.setText(newState.minidisplay.getText());
        state.a = newState.a;
        state.b = newState.b;
        state.selectedString = newState.selectedString;
        state.tempString = newState.tempString;
        state.arithmeticStrategy = newState.arithmeticStrategy;
        state.resetOnNextInput = newState.resetOnNextInput;
    }

}
