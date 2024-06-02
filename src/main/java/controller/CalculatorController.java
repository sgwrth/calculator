package controller;

import strategy.Addition;
import strategy.Division;
import strategy.IArithmeticStrategy;
import strategy.Multiplication;
import strategy.Subtraction;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class CalculatorController implements Initializable {

    private final int MAX_STRING_LENGTH = 14;

    public Text display;
    public Text minidisplay;

    private StringBuilder a;
    private StringBuilder b;
    private StringBuilder selectedString;
    private String tempString;
    private IArithmeticStrategy arithmeticStrategy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.a = new StringBuilder();
        this.b = new StringBuilder();
        this.tempString = "";
        this.selectedString = a;
    }

    public void enter0() {
        btnClickZero();
    }

    public void enter1() {
        btnClickOneToNine("1");
    }

    public void enter2() {
        btnClickOneToNine("2");
    }

    public void enter3() {
        btnClickOneToNine("3");
    }

    public void enter4() {
        btnClickOneToNine("4");
    }

    public void enter5() {
        btnClickOneToNine("5");
    }

    public void enter6() {
        btnClickOneToNine("6");
    }

    public void enter7() {
        btnClickOneToNine("7");
    }

    public void enter8() {
        btnClickOneToNine("8");
    }

    public void enter9() {
        btnClickOneToNine("9");
    }

    public void enterPeriod() {
        btnClickPeriod();
    }

    public void selectAddition() {
        btnClickStrategy(new Addition(), "+ ");
    }

    public void selectSubtraction() {
        btnClickStrategy(new Subtraction(), "- ");
    }

    public void selectMultiplication() {
        btnClickStrategy(new Multiplication(), "* ");
    }

    public void selectDivision() {
        btnClickStrategy(new Division(), "/ ");
    }

    public void equals() {
        btnClickEquals();
    }

    public void delete() {
        btnClickDelete();
    }

    public void clear() {
        btnClickClear();
    }

    public void btnClickOneToNine(String number) {
        if (!selectedString.toString().equals("0")) {
            selectedString.append(number);
        } else {
            selectedString.replace(0, 1, number);
        }
        display.setText(tempString + selectedString);
    }

    public void btnClickZero() {
        if (!selectedString.toString().equals("0")) {
            selectedString.append("0");
        }
        display.setText(tempString + selectedString);
    }

    public void btnClickStrategy(
            IArithmeticStrategy strat,
            String operatorSymbol) {
        if (selectedString == a) {
            arithmeticStrategy = strat;
            selectedString = b;
            tempString = operatorSymbol;
            display.setText(tempString);
            minidisplay.setText(removePeriod(a.toString()));
        } else {
            arithmeticStrategy = strat;
            tempString = removePeriod(a.toString()) + operatorSymbol;
            display.setText(tempString + selectedString);
        }
    }

    public void btnClickEquals() {
        double result = doCalculation();
        String resultString = String.valueOf(result);
        if (endsWithPeriodZero(resultString)) {
            cropPeriodZero(resultString);
        } else {
            if (isOverlong(resultString)) {
                display.setText(cropOverlong(resultString));
            } else {
                display.setText(resultString);
            }
            a = new StringBuilder().append(resultString);
        }
        selectedString = a;
        b = new StringBuilder();
        tempString = "";
        minidisplay.setText("");
    }

    public double doCalculation() {
        return arithmeticStrategy.calculate(
                Double.parseDouble(a.toString()),
                Double.parseDouble(b.toString()));
    }

    public void cropPeriodZero(String string) {
        StringBuilder cropped = new StringBuilder();
        cropped.append(string);
        cropped.delete(cropped.length() - 2, cropped.length());
        display.setText(cropped.toString());
        a = new StringBuilder().append(cropped);
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

    public void btnClickDelete() {
        char[] tempArray = selectedString.toString().toCharArray();
        if (selectedString.length() == 2 && tempArray[0] == '-') {
            selectedString.replace(0, 1, "0");
            selectedString.delete(1, selectedString.length());
        }
        if (selectedString.length() == 1 && !selectedString.toString().equals("0")) {
            selectedString.replace(0, 1, "0");
        }
        if (selectedString.length() > 1) {
            selectedString.deleteCharAt(selectedString.length() - 1);
        }
        display.setText(tempString + selectedString.toString());
    }

    public void btnClickClear() {
        tempString = "";
        a = new StringBuilder();
        b = new StringBuilder();
        selectedString = a;
        selectedString.append("0");
        display.setText(selectedString.toString());
        minidisplay.setText("");
    }

    public String removePeriod(String string) {
        try {
            if (string.charAt(string.length() - 1) == '.') {
                StringBuilder tempStringB = new StringBuilder();
                tempStringB.append(string);
                tempStringB.delete(tempStringB.length() - 1, tempStringB.length());
                string = tempStringB.toString();
            }
            return string;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public void btnClickPeriod() {
        if (selectedString.isEmpty()) {
            selectedString.append("0");
            selectedString.append(".");
        }
        if (hasPeriod(selectedString.toString())) {
            selectedString.append(".");
        }
        display.setText(tempString + selectedString);
    }

    public boolean hasPeriod(String string) {
        char[] array = string.toCharArray();
        for (char c : array) {
            if (c == '.') {
                return false;
            }
        }
        return true;
    }

}
