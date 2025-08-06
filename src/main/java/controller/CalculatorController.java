package controller;

import state.CalculatorState;
import strategy.IArithmeticStrategy;

public class CalculatorController {

    private final int MAX_STRING_LENGTH = 14;

    public CalculatorState btnClickOneToNine(CalculatorState state, String number) {
        var newState = new CalculatorState(state);
        if (newState.resetOnNextInput) {
            return resetStringWithInput(number);
        } else {
            if (!newState.selectedString.toString().equals("0")) {
                newState.selectedString.append(number);
            } else {
                newState.selectedString.replace(0, 1, number);
            }
        }
        newState.display.setText(newState.tempString + newState.selectedString);
        return newState;
    }

    public CalculatorState resetStringWithInput(String string) {
        var newState = new CalculatorState();
        newState.a.append(string);
        newState.display.setText(string);
        return newState;
    }

    public CalculatorState btnClickZero(CalculatorState state, String zero) {
        var newState = new CalculatorState(state);
        if (newState.resetOnNextInput) {
            newState = resetStringWithInput(zero);
        } else {
            if (!newState.selectedString.toString().equals("0")) {
                newState.selectedString.append("0");
            }
            newState.display.setText(newState.tempString + newState.selectedString);
        }
        return newState;
    }

    public CalculatorState btnClickStrategy(
            CalculatorState state,
            IArithmeticStrategy strat,
            String operator
    ) {
        var newState = new CalculatorState(state);
        if (newState.selectedString == newState.a) {
            newState.arithmeticStrategy = strat;
            newState.tempString = operator;
            newState.display.setText(newState.tempString);
            newState.minidisplay.setText(removePeriod(newState.a.toString()));
            newState.selectedString = newState.b;
            newState.resetOnNextInput = false;
        }
        return newState;
    }

    public CalculatorState btnClickEquals(CalculatorState state) {
        if (state.selectedString == state.a) {
            return state;
        }
        if (state.b.toString().isEmpty()) {
            return state;
        }
        var newState = new CalculatorState(state);
        if (newState.selectedString == newState.b && !newState.b.isEmpty()) {
            final var resultString = String.valueOf(doCalculation(newState));
            if (endsWithPeriodZero(resultString)) {
                newState = cropPeriodZero(newState, resultString);
            } else {
                if (isOverlong(resultString)) {
                    newState.display.setText(cropOverlong(resultString));
                } else {
                    newState.display.setText(resultString);
                }
                newState.a = new StringBuilder().append(resultString);
            }
            newState.selectedString = newState.a;
            newState.b = new StringBuilder();
            newState.tempString = "";
            newState.minidisplay.setText("");
            newState.resetOnNextInput = true;
        }
        return newState;
    }

    public double doCalculation(CalculatorState state) {
        return state.arithmeticStrategy.calculate(
                Double.parseDouble(state.a.toString()),
                Double.parseDouble(state.b.toString())
        );
    }

    public CalculatorState cropPeriodZero(CalculatorState state, String string) {
        var newState = new CalculatorState(state);
        StringBuilder sb = popChars(new StringBuilder().append(string), 2);
        newState.display.setText(sb.toString());
        newState.a = new StringBuilder().append(sb);
        return newState;
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

    public CalculatorState btnClickDelete(CalculatorState state) {
        final var newState = new CalculatorState(state);
        if (!newState.resetOnNextInput) {
            if (isSingleDigitNegative(newState.selectedString.toString())) {
                newState.selectedString
                        .replace(0, 1, "0")
                        .delete(1, newState.selectedString.length());
            }
            if (isSingleDigitPositive(newState.selectedString.toString())) {
                newState.selectedString.replace(0, 1, "0");
            }
            if (newState.selectedString.length() > 1) {
                newState.selectedString.deleteCharAt(newState.selectedString.length() - 1);
            }
            newState.display.setText(newState.tempString + newState.selectedString);
        }
        return newState;
    }

    public boolean isSingleDigitNegative(String string) {
        return string.length() == 2 && string.charAt(0) == '-';
    }

    public boolean isSingleDigitPositive(String string) {
        return string.length() == 1 && !string.equals("0");
    }

    public CalculatorState btnClickClear(CalculatorState state) {
        final var newState = new CalculatorState(state);
        newState.tempString = "";
        newState.a = new StringBuilder();
        newState.b = new StringBuilder();
        newState.selectedString = newState.a.append("0");
        newState.display.setText(newState.selectedString.toString());
        newState.minidisplay.setText("");
        return newState;
    }

    public String removePeriod(String string) {
        try {
            var sb = new StringBuilder().append(string);
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

    public CalculatorState btnClickPeriod(CalculatorState state, String period) {
        var newState = new CalculatorState(state);
        if (state.resetOnNextInput) {
            newState = resetStringWithInput("0" + period);
        } else {
            if (newState.selectedString.isEmpty()) {
                newState.selectedString.append("0.");
            }
            if (!hasPeriod(newState.selectedString.toString())) {
                newState.selectedString.append(".");
            }
            newState.display.setText(newState.tempString + newState.selectedString);
        }
        return newState;
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
